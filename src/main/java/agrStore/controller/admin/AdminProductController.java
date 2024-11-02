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

import agrStore.entity.ProductEntity;

@Controller
public class AdminProductController {
	@RequestMapping("/productManagement")
	public String productManagement(HttpServletRequest request, HttpSession session,ModelMap model,
			@CookieValue(value = "accountEmail", defaultValue = "", required = false) String userEmail) {
		// code
		  model.addAttribute("currentPage", "product");
		return "admin/product/productManagement";
	}

	@RequestMapping(value = "/productManagement/product", method = RequestMethod.GET)
	public String handleproduct(@RequestParam(value = "action", required = false) String action,
			@RequestParam(value = "id", required = false) Integer id, Model model) {

		if (action != null) {
			switch (action) {
			case "add":
				model.addAttribute("mode", "ADD");
				// model.addAttribute("product", new product());
				break;

			case "view":
				if (id != null) {
					// product product = productService.getproductById(id);
					model.addAttribute("mode", "VIEW");
					// model.addAttribute("product", product);
				}
				break;

			case "edit":
				if (id != null) {
					// product product = productService.getproductById(id);
					model.addAttribute("mode", "EDIT");
					// model.addAttribute("product", product);
				}
				break;
			}
		}

		return "admin/product/productForm"; // Trả về cùng một trang JSP
	}

	@RequestMapping(value = "/productManagement/product", method = RequestMethod.POST)
	public String processproduct(@ModelAttribute("product") ProductEntity product, @RequestParam("mode") String mode) {

		if ("ADD".equals(mode)) {
			// productService.addproduct(product);
		} else if ("EDIT".equals(mode)) {
			// productService.updateproduct(product);
		}

		return "redirect:/admin/product/productManagement.htm"; // Redirect sau khi xử lý
	}
}
