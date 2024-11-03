package agrStore.controller.admin;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Category;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

@Controller
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
		AccountEntity employee = this.accountService.getAccountById(orderBill.getEmployeeId());
		Map<Integer, String> statusOrderMap = new LinkedHashMap<>();
        statusOrderMap.put(1, "Chờ xác nhận");
        statusOrderMap.put(2, "Đã xác nhận");
        statusOrderMap.put(3, "Chờ giao hàng");
        statusOrderMap.put(4, "Hoàn thành");
        List<OrderBillDetailEntity> orderBillDetailEntities = this.orderBillDetailService.getAllOrderBillDetailByOrderBillID(id);

        

		if (action != null) {
			switch (action) {
			case "add":
				model.addAttribute("mode", "ADD");
				// model.addAttribute("category", new Category());
				break;

			case "view":
				if (id != null) {
					// Category category = categoryService.getCategoryById(id);
					model.addAttribute("mode", "VIEW");
					model.addAttribute("orderBill", orderBill);
					model.addAttribute("employee", employee);
					model.addAttribute("statusOrderMap", statusOrderMap);
					model.addAttribute("orderBillDetailEntities", orderBillDetailEntities);
				}
				break;

			case "edit":
				if (id != null) {
					// Category category = categoryService.getCategoryById(id);
					model.addAttribute("mode", "EDIT");
					// model.addAttribute("category", category);
				}
				break;
			}
		}

		return "admin/order/orderForm"; // Trả về cùng một trang JSP
	}

	@RequestMapping(value = "/orderManagement/order", method = RequestMethod.POST)
	public String processCategory(@ModelAttribute("order") @RequestParam("mode") String mode) {

		if ("ADD".equals(mode)) {
			// categoryService.addCategory(category);
		} else if ("EDIT".equals(mode)) {
			// categoryService.updateCategory(category);
		}

		return "redirect:/admin/order/orderManagement.htm"; // Redirect sau khi xử lý
	}
}