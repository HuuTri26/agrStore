package agrStore.controller.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Category;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdminOrderController {
	@RequestMapping("/orderManagement")
	public String orderManagement(HttpServletRequest request, HttpSession session,
			@CookieValue(value = "accountEmail", defaultValue = "", required = false) String userEmail) {
		// code

		return "admin/order/orderManagement";
	}

	@RequestMapping(value = "/orderManagement/order", method = RequestMethod.GET)
	public String handleOrder(@RequestParam(value = "action", required = false) String action,
			@RequestParam(value = "id", required = false) Integer id, Model model) {

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
					// model.addAttribute("category", category);
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