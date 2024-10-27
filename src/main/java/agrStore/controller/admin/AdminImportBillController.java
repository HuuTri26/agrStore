package agrStore.controller.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdminImportBillController {
	@RequestMapping("/importBillManagement")
	public String importBillManagement(HttpServletRequest request, HttpSession session,ModelMap model,
			@CookieValue(value = "accountEmail", defaultValue = "", required = false) String userEmail) {
		// code
		  model.addAttribute("currentPage", "importBill");
		return "admin/importBill/importBillManagement";
	}
	
	@RequestMapping(value = "/importBillManagement/importBill", method = RequestMethod.GET)
	public String handleImportBill(@RequestParam(value = "action", required = false) String action,
			@RequestParam(value = "id", required = false) Integer id, Model model) {

		if (action != null) {
			switch (action) {
			case "create":
				model.addAttribute("mode", "CREATE");
				return "admin/importBill/createImportBill";
				// model.addAttribute("category", new Category());

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

		return "admin/importBill/importBillForm"; // Trả về cùng một trang JSP
	}

	@RequestMapping(value = "/importBillManagement/importBill", method = RequestMethod.POST)
	public String processImportBill(@ModelAttribute("importBill") @RequestParam("mode") String mode) {

		if ("CREATE".equals(mode)) {
			// categoryService.addCategory(category);
		} else if ("EDIT".equals(mode)) {
			// categoryService.updateCategory(category);
		}

		return "redirect:/admin/importBill/importBillManagement.htm"; // Redirect sau khi xử lý
	}

}