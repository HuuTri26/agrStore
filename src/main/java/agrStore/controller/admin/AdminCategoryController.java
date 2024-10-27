package agrStore.controller.admin;

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

@Controller
public class AdminCategoryController {
	@RequestMapping("/categoryManagement")
	public String categoryManagement(HttpServletRequest request, HttpSession session, ModelMap model,
			@CookieValue(value = "accountEmail", defaultValue = "", required = false) String userEmail) {
		// code
		  model.addAttribute("currentPage", "category");

		return "admin/category/categoryManagement";
	}

	/*
	 * @RequestMapping("/addCategory") public String addCategory() { // code
	 * 
	 * return "admin/category/addCategory"; }
	 */
	// @Autowired
	// private CategoryService categoryService;

	@RequestMapping(value = "/categoryManagement/category", method = RequestMethod.GET)
	public String handleCategory(@RequestParam(value = "action", required = false) String action,
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

		return "admin/category/categoryForm"; // Trả về cùng một trang JSP
	}

	@RequestMapping(value = "/categoryManagement/category", method = RequestMethod.POST)
	public String processCategory(@ModelAttribute("category") Category category, @RequestParam("mode") String mode) {

		if ("ADD".equals(mode)) {
			// categoryService.addCategory(category);
		} else if ("EDIT".equals(mode)) {
			// categoryService.updateCategory(category);
		}

		return "redirect:/admin/category/categoryManagement.htm"; // Redirect sau khi xử lý
	}

}
