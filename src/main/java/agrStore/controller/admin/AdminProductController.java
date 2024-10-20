package agrStore.controller.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import agrStore.entity.ProductEntity;
import agrStore.service.ProductService;

@Controller
@RequestMapping("productManagement")
public class AdminProductController {
	private ProductService productService;

	@Autowired
	public AdminProductController(ProductService productService) {
		super();
		this.productService = productService;
	}

	@RequestMapping()
	public String productManagement(HttpServletRequest request, HttpSession session,
			@CookieValue(value = "accountEmail", defaultValue = "", required = false) String userEmail) {
		// code

		return "admin/product/productManagement";
	}

	@RequestMapping(method = RequestMethod.GET)
	public String getAllProduct(ModelMap model) {
		List<ProductEntity> products = this.productService.getAllProduct();
		model.addAttribute("products", products);
		return "admin/product/productManagement";
	}

	@RequestMapping(value = "/addProduct", method = RequestMethod.POST)
	public String addProduct() {
		return "admin/product/productManagement";
	}

}
