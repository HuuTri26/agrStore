package agrStore.paypal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paypal.api.payments.Amount;
import com.paypal.api.payments.Item;
import com.paypal.api.payments.ItemList;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payer;
import com.paypal.api.payments.PayerInfo;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.PaymentExecution;
import com.paypal.api.payments.RedirectUrls;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;

import agrStore.entity.AccountEntity;
import agrStore.entity.OrderBillDetailEntity;
import agrStore.entity.OrderBillEntity;
import agrStore.service.OrderBillDetailService;

@Service
public class PayPalPaymentServiceImpl implements PayPalPaymentService {

	@Autowired
	OrderBillDetailService orderBillDetailService;

	private static final String CLIENT_ID = "ARsy6IKgSzGIIbrJlp8LOq6mYl_zHMKZCJBVCQRvlbiUdNIJv4mwLxAGTjxGwZyl7qnC9L_m5TkEzUY7";
	private static final String CLIENT_SECRET = "EKNHSCpjfp2qc8_Y-6brv1bQCUXvrn13mKqGCX7I3fyk6MXD4-Np3M22SL-b9XcHq5A4QlsSfqrruLvi";
	private static final String MODE = "sandbox";

	@Override
	public Payment executePayment(String paymentId, String payerId) throws PayPalRESTException {
		PaymentExecution paymentExecution = new PaymentExecution();
		paymentExecution.setPayerId(payerId);
		Payment payment = new Payment().setId(paymentId);
		APIContext apiContext = new APIContext(CLIENT_ID, CLIENT_SECRET, MODE);

		return payment.execute(apiContext, paymentExecution);
	}

	@Override
	public Payment getPaymentDetails(String paymentId) throws PayPalRESTException {
		APIContext apiContext = new APIContext(CLIENT_ID, CLIENT_SECRET, MODE);
		return Payment.get(apiContext, paymentId);
	}

	@Override
	public String authorizePayment(AccountEntity payer, List<OrderBillDetailEntity> orderItems)
			throws PayPalRESTException {
		Payer payerInfo = getPayerInfomation(payer);
		RedirectUrls redirectUrls = getRedirectUrls();
		List<Transaction> transactions = getTransactionInfomation(orderItems);

		Payment requestPayment = new Payment();
		requestPayment.setTransactions(transactions);
		requestPayment.setRedirectUrls(redirectUrls);
		requestPayment.setPayer(payerInfo);
		requestPayment.setIntent("authorize");

		APIContext apiContext = new APIContext(CLIENT_ID, CLIENT_SECRET, MODE);
		Payment approvedPayment = requestPayment.create(apiContext);
		return getApprovalLink(approvedPayment);
	}

	@Override
	public String getApprovalLink(Payment approvedPayment) {
		List<Links> links = approvedPayment.getLinks();
		String approvalLink = null;
		for (Links link : links) {
			if (link.getRel().equalsIgnoreCase("approval_url")) {
				approvalLink = link.getHref();
			}
		}
		System.out.println(approvalLink);
		return approvalLink;
	}

	@Override
	public String convertNumberFromGermanToUSFormat(String germanNum) {
		return germanNum.replace(',', '.');
	}

	@Override
	public List<Transaction> getTransactionInfomation(List<OrderBillDetailEntity> orderItems) {
		// Tính subtotal của các order detail
		double subtotal = 0.0;

		// Khởi tạo danh sách giao dịch
		List<Transaction> transactions = new ArrayList<>();

		ItemList itemList = new ItemList();
		List<Item> items = new ArrayList<>();

		// Thêm tất cả các mặt hàng vào danh sách items
		for (OrderBillDetailEntity orderDetail : orderItems) {
			Item item = new Item();
			item.setCurrency("USD");
			item.setName(orderDetail.getProduct().getProductName());

			// Lấy giá và số lượng
			String price = convertNumberFromGermanToUSFormat(String.valueOf(orderDetail.getPrice()));
			String quantity = String.valueOf(orderDetail.getQuantity());

			item.setPrice(price);
			item.setQuantity(quantity);

			// Tính toán subtotal
			subtotal += Double.parseDouble(price) * Integer.parseInt(quantity);

			items.add(item);
		}

		// Cập nhật itemList với danh sách items
		itemList.setItems(items);

		// Tạo đối tượng Amount
		Amount amount = new Amount();
		amount.setCurrency("USD");

		// Đảm bảo total khớp với subtotal
		amount.setTotal(convertNumberFromGermanToUSFormat(String.valueOf(subtotal)));

		// Tạo transaction
		Transaction transaction = new Transaction();
		transaction.setAmount(amount);
		transaction.setItemList(itemList);
		transaction.setDescription("Order containing multiple items");

		transactions.add(transaction);

		return transactions;
	}

	@Override
	public RedirectUrls getRedirectUrls() {
		RedirectUrls redirectUrls = new RedirectUrls();
		redirectUrls.setCancelUrl("http://argstoreptithcm.com/agrStoreManagement/customer/cancelPayment.htm");
		redirectUrls.setReturnUrl("http://argstoreptithcm.com/agrStoreManagement/customer/reviewPayment.htm");
		return redirectUrls;
	}

	@Override
	public Payer getPayerInfomation(AccountEntity payerAccount) {
		PayerInfo payerInfo = new PayerInfo();
		payerInfo.setFirstName(payerAccount.getFullName());
		payerInfo.setLastName(payerAccount.getGmail());

		Payer payer = new Payer();
		payer.setPaymentMethod("paypal");
		payer.setPayerInfo(payerInfo);

		return payer;
	}

}
