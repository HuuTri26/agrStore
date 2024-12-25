package agrStore.controller.admin;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import agrStore.entity.AccountEntity;
import agrStore.entity.OrderBillDetailEntity;
import agrStore.entity.OrderBillEntity;
import agrStore.service.AccountService;
import agrStore.service.OrderBillDetailService;
import agrStore.service.OrderBillService;
import agrStore.utility.ServerLogger;

@Controller
@RequestMapping("/admin")
public class AdminOrderController {

	@Autowired
	private OrderBillService orderBillService;
	@Autowired
	private AccountService accountService;
	@Autowired
	private OrderBillDetailService orderBillDetailService;

	@RequestMapping("/orderManagement")
	public String orderManagement(HttpServletRequest request, HttpSession session, ModelMap model,
			@CookieValue(value = "accountEmail", defaultValue = "", required = false) String userEmail) {
		// code
		model.addAttribute("currentPage", "order");
		List<OrderBillEntity> orderBills = this.orderBillService.getAllOrderBill();
		
		model.addAttribute("orderBills", orderBills);
		

		return "admin/order/orderManagement";
	}

	@RequestMapping(value = "/orderManagement/order", method = RequestMethod.GET)
	public String handleOrder(@RequestParam(value = "action", required = false) String action,
			@RequestParam(value = "id", required = false) Integer id, Model model) {
		OrderBillEntity orderBill = this.orderBillService.getOrderBillById(id);
		
		Map<Integer, String> statusOrderMap = new LinkedHashMap<>();
		statusOrderMap.put(1, "Chờ xác nhận");
		statusOrderMap.put(2, "Đã xác nhận");
		statusOrderMap.put(3, "Chờ giao hàng");
		statusOrderMap.put(4, "Hoàn thành");
		statusOrderMap.put(5, "Hủy");
		List<OrderBillDetailEntity> orderBillDetailEntities = this.orderBillDetailService
				.getAllOrderBillDetailByOrderBillID(id);

		if (action != null) {
			switch (action) {
//			case "add":
//				model.addAttribute("mode", "ADD");
//				
//				break;

			case "view":
				if (id != null) {
					
					model.addAttribute("mode", "VIEW");

					model.addAttribute("orderBill", orderBill);
					
					model.addAttribute("statusOrderMap", statusOrderMap);
					model.addAttribute("orderBillDetailEntities", orderBillDetailEntities);
				}
				break;

//			case "edit":
//				if (id != null) {
//					
//					model.addAttribute("mode", "EDIT");
//
//					model.addAttribute("orderBill", orderBill);
//					model.addAttribute("statusOrderMap", statusOrderMap);
//					model.addAttribute("orderBillDetailEntities", orderBillDetailEntities);
//				}
//				break;
			}
		}

		return "admin/order/orderForm"; // Trả về cùng một trang JSP
	}

//	@RequestMapping(value = "/orderManagement/order", method = RequestMethod.POST)
//	public String processCategory(@ModelAttribute("orderBill") @RequestParam("mode") String mode) {
//		System.out.println("mode: " + mode);
//		if ("ADD".equals(mode)) {
//			
//		} else if ("EDIT".equals(mode)) {
//			
//		}
//
//		return "redirect:/admin/order/orderManagement.htm"; // Redirect sau khi xử lý
//	}

//	@RequestMapping(value = "/orderManagement/order", method = RequestMethod.PUT)
//	public String updateStatusOrderBill(@ModelAttribute("orderBill") String statusOrder,
//			@RequestParam("mode") String mode) {
//		System.out.println("mode: " + mode);
//		System.out.println("statusOrder: " + statusOrder);
//
//		if ("ADD".equals(mode)) {
//			
//		} else if ("EDIT".equals(mode)) {
//			
//			System.out.println(mode);
//		}
//
//		return "redirect:/admin/order/orderManagement.htm"; // Redirect sau khi xử lý
//	}

	@RequestMapping(value = "/orderManagement/order/updateOrderStatus", method = RequestMethod.POST)
	public String updateOrderBillStatus(@RequestParam("orderBillId") Integer orderBillId,
			@RequestParam("statusOrder") int statusOrder, ModelMap model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		AccountEntity loggedInUser = (AccountEntity) session.getAttribute("loggedInUser");
		System.out.println(orderBillId);
		System.out.println(statusOrder);
		int kq = this.orderBillService.updateOrderBillStatus(orderBillId, statusOrder);
		if (kq == 1) {
			List<OrderBillEntity> orderBills = this.orderBillService.getAllOrderBill();
			
			model.addAttribute("orderBills", orderBills);
			ServerLogger.writeActionLog(loggedInUser.getGmail(), loggedInUser.getRole().getName(), "UPDATE", orderBills);
			
		} else {
			System.out.println("Update thất bại");
		}

		return "admin/order/orderManagement";
	}

	@RequestMapping(value = "/orderManagement/deleteOrderBillUnConfirm", method = RequestMethod.GET)
	public String deleteOrderBillUnConfirm(@RequestParam("orderBillId") Integer orderBillId, ModelMap model,HttpServletRequest request) {
		HttpSession session = request.getSession();
		AccountEntity loggedInUser = (AccountEntity) session.getAttribute("loggedInUser");
		try {
			
			int result = this.orderBillService.deleteOrderBillUnconfirmedById(orderBillId);
			if (result == 1) {
				List<OrderBillEntity> orderBills = this.orderBillService.getAllOrderBill();
				model.addAttribute("orderBills", orderBills);
				model.addAttribute("notification", "Đã xóa thành công hóa đơn mua hàng");
				model.addAttribute("status", 200);
				ServerLogger.writeActionLog(loggedInUser.getGmail(), loggedInUser.getRole().getName(), "DELETE", orderBills);
				// System.out.println("Đã xóa hóa đơn mua hàng thành công");
			}
			else {
				List<OrderBillEntity> orderBills = this.orderBillService.getAllOrderBill();
				model.addAttribute("orderBills", orderBills);
				model.addAttribute("notification", "Chỉ được xóa các orderBill Chưa xác nhận");
				model.addAttribute("status", 404);

			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			ServerLogger.writeErrorLog(loggedInUser.getGmail(), loggedInUser.getRole().getName(), "DELETE", e);
		}
		return "admin/order/orderManagement";
	}
}