package agrStore.controller.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Category;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
@Controller
public class AdminProviderController {
		@RequestMapping("/providerManagement")
		public String providerManagement(HttpServletRequest request, HttpSession session,ModelMap model,
				@CookieValue(value = "accountEmail", defaultValue = "", required = false) String userEmail) {
			// code
			  model.addAttribute("currentPage", "provider");
			return "admin/provider/providerManagement";
		}
		
		
		@RequestMapping(value = "/providerManagement/provider", method = RequestMethod.GET)
		public String handleProvider(@RequestParam(value = "action", required = false) String action,
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

			return "admin/provider/providerForm"; // Trả về cùng một trang JSP
		}

		@RequestMapping(value = "/providerManagement/provider", method = RequestMethod.POST)
		public String processCategory(@ModelAttribute("provider")  @RequestParam("mode") String mode) {

			if ("ADD".equals(mode)) {
				// categoryService.addCategory(category);
			} else if ("EDIT".equals(mode)) {
				// categoryService.updateCategory(category);
			}

			return "redirect:/admin/provider/providerManagement.htm"; // Redirect sau khi xử lý
		}
}
