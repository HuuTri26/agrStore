package agrStore.controller.customer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.paypal.api.payments.PayerInfo;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.ShippingAddress;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.PayPalRESTException;

import agrStore.entity.AccountEntity;
import agrStore.entity.CartItemEntity;
import agrStore.entity.OrderBillDetailEntity;
import agrStore.entity.OrderBillEntity;
import agrStore.paypal.PayPalPaymentService;
import agrStore.service.CartItemService;
import agrStore.service.OrderBillDetailService;
import agrStore.service.OrderBillService;
import agrStore.service.ProductService;

@Controller
@RequestMapping("/customer")
public class customerPaymentController {

	@Autowired
	CartItemService cartItemService;

	@Autowired
	OrderBillService orderBillService;

	@Autowired
	OrderBillDetailService orderBillDetailService;

	@Autowired
	PayPalPaymentService palPaymentService;
	
	@Autowired
	ProductService productService;

	@RequestMapping("/customerCheckout")
	public String showCustomerCheckout() {

		return "customer/payment/customerCheckout";
	}

	private void cleanUpDuplicateOrderBills(AccountEntity loggedInUser, Double totalPrice,
			List<CartItemEntity> selectedCartItems) {
		// Tìm các OrderBill pending của người dùng
		List<OrderBillEntity> pendingOrderBills = orderBillService
				.getPendingOrderBillByAccountId(loggedInUser.getAccountId(), 1);

		if (pendingOrderBills != null && !pendingOrderBills.isEmpty()) {
			// Lọc và xóa các OrderBill trùng lặp
			List<OrderBillEntity> duplicateOrderBills = pendingOrderBills.stream().filter(bill ->
			// Kiểm tra trùng lặp dựa trên các tiêu chí:
			bill.getTotalPrice().equals(totalPrice)
					&& bill.getTotalQuantity() == cartItemService.getTotalQuantityOfCartItems(selectedCartItems))
					.collect(Collectors.toList());

			if (!duplicateOrderBills.isEmpty()) {
				System.out.println("==> Found " + duplicateOrderBills.size() + " duplicate pending orderBills");
				try {
					// Xóa các OrderBill trùng lặp
					orderBillService.deleteListOrderBill(duplicateOrderBills);
					System.out.println("==> Successfully deleted duplicate pending orderBills");
				} catch (Exception e) {
					System.err.println("==> Error deleting duplicate pending orderBills: " + e.getMessage());
					// Có thể log lỗi vào hệ thống log chuyên nghiệp
				}
			}
		}
	}

	@RequestMapping("/paymentError")
	public String showPaymentError(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		AccountEntity loggedInUser = (AccountEntity) session.getAttribute("loggedInUser");
		List<CartItemEntity> selectedCartItems = (List<CartItemEntity>) session.getAttribute("selectedCartItems");
		Double totalPrice = (Double) session.getAttribute("totalPrice");
		
		cleanUpDuplicateOrderBills(loggedInUser, totalPrice, selectedCartItems);
		
		System.out.println("==> Error: Could not process payment!");
		model.addAttribute("error-message",
				"Không thể thực hiện giao dịch, vui lòng thử lại hoặc liên hệ với chúng tôi để được nhận hỗ trợ!");
		return "customer/payment/error";
	}

	@RequestMapping("/cancelPayment")
	public String showCancelPayment(HttpServletRequest request) {
		HttpSession session = request.getSession();
		AccountEntity loggedInUser = (AccountEntity) session.getAttribute("loggedInUser");
		List<CartItemEntity> selectedCartItems = (List<CartItemEntity>) session.getAttribute("selectedCartItems");
		Double totalPrice = (Double) session.getAttribute("totalPrice");
		
		cleanUpDuplicateOrderBills(loggedInUser, totalPrice, selectedCartItems);

		System.out.println("==> Cancel payment!");
		OrderBillEntity orderBill = (OrderBillEntity) session.getAttribute("orderBill");
		orderBill.setStatusOrder(6);
		orderBillService.updateOrderBill(orderBill);
		System.out.println("==> Set orderBill status to 'Canceled'!");

		return "customer/payment/cancel";
	}

	@RequestMapping(value = "/authPayment", params = "PayPal")
	public String authorizePayment(HttpServletRequest request) {
		HttpSession session = request.getSession();
		AccountEntity loggedInUser = (AccountEntity) session.getAttribute("loggedInUser");
		List<CartItemEntity> selectedCartItems = (List<CartItemEntity>) session.getAttribute("selectedCartItems");
		Double totalPrice = (Double) session.getAttribute("totalPrice");
		
		cleanUpDuplicateOrderBills(loggedInUser, totalPrice, selectedCartItems);
		
		OrderBillEntity orderBill = new OrderBillEntity();
		try {
			// Tạo OrderBill
			orderBill.setAccount(loggedInUser);
			orderBill.setOrderTime(new Date());
			orderBill.setTotalPrice(totalPrice);
			orderBill.setTotalQuantity(cartItemService.getTotalQuantityOfCartItems(selectedCartItems));
			orderBill.setStatusOrder(1);
			// orderBill.setEmployeeId(null);

			System.out.println("==> Create new orderBill!");
			orderBillService.addOrderBill(orderBill);
			System.out.println("==> OrderBill created successfully!");

			// Tạo OrderBillDetails
			System.out.println(
					"==> Create {0} new orderBillDetails for the orderBill!".formatted(selectedCartItems.size()));
			List<OrderBillDetailEntity> orderItems = new ArrayList<OrderBillDetailEntity>();
			for (CartItemEntity item : selectedCartItems) {

				OrderBillDetailEntity orderBilldt = new OrderBillDetailEntity();
				orderBilldt.setOrderBill(orderBill);
				orderBilldt.setQuantity(item.getQuantity());
				orderBilldt.setProduct(item.getProduct());
				orderBilldt.setPrice(item.getProduct().getPrice());

				orderItems.add(orderBilldt);

				System.out.println("==> Create new OrderBillDetail!");
				orderBillDetailService.addOrderBillDetail(orderBilldt);
				System.out.println("==> OrderBillDetail created successfully!");
			}
			// Bắt đầu thanh toán bằng PalPay
			session.setAttribute("orderBill", orderBill);
			session.setAttribute("orderItems", orderItems);
			String approvalLink = palPaymentService.authorizePayment(loggedInUser, orderItems);
			return "redirect:" + approvalLink;

		} catch (PayPalRESTException e) {
			e.printStackTrace();
			return "redirect:/customer/paymentError.htm";
		}

	}

	@RequestMapping("/reviewPayment")
	public String reviewPayment(ModelMap model, @RequestParam("paymentId") String payemtId,
			@RequestParam("PayerID") String payerId) {
		try {
			System.out.println("==> Show payment info");
			Payment payment = palPaymentService.getPaymentDetails(payemtId);
			PayerInfo payerInfo = payment.getPayer().getPayerInfo();
			Transaction transaction = payment.getTransactions().get(0);
			ShippingAddress shippingAddress = transaction.getItemList().getShippingAddress();

			model.addAttribute("payerInfo", payerInfo);
			model.addAttribute("transaction", transaction);
			model.addAttribute("shippingAddress", shippingAddress);
			return "customer/payment/review";
		} catch (PayPalRESTException e) {
			e.printStackTrace();
			return "redirect:/customer/paymentError.htm";
		}
	}

	@RequestMapping(value = "/execPayment", method = RequestMethod.POST)
	public String executePayment(HttpServletRequest request, ModelMap model, @RequestParam("paymentId") String payemtId,
			@RequestParam("PayerID") String payerId) {
		HttpSession session = request.getSession();
		AccountEntity loggedInUser = (AccountEntity) session.getAttribute("loggedInUser");
		List<CartItemEntity> selectedCartItems = (List<CartItemEntity>) session.getAttribute("selectedCartItems");
		Double totalPrice = (Double) session.getAttribute("totalPrice");

		try {
			System.out.println("==> Execute payment");
			Payment payment = palPaymentService.executePayment(payemtId, payerId);
			PayerInfo payerInfo = payment.getPayer().getPayerInfo();
			Transaction transaction = payment.getTransactions().get(0);

			OrderBillEntity orderBill = (OrderBillEntity) session.getAttribute("orderBill");
			orderBill.setStatusOrder(2);
			orderBillService.updateOrderBill(orderBill);
			System.out.println("==> Set order status to 'Confirmed'!");

			model.addAttribute("payerInfo", payerInfo);
			model.addAttribute("transaction", transaction);
			
			cleanUpDuplicateOrderBills(loggedInUser, totalPrice, selectedCartItems);
			
			productService.updateProductQuantityAfterPayment(selectedCartItems);
			
			System.out.println("==> Delete all selected cartItems in cart");
			cartItemService.deleteCartItem(selectedCartItems);
			
			session.removeAttribute("selectedCartItems");
			session.removeAttribute("totalPrice");

			return "customer/payment/receipt";

		} catch (PayPalRESTException e) {
			e.printStackTrace();
			return "redirect:/customer/paymentError.htm";
		}
	}

}
