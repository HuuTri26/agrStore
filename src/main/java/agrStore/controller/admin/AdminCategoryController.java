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

import agrStore.entity.CategoryEntity;
import agrStore.service.CategoryService;

@Controller
@RequestMapping("categoryManagement")
public class AdminCategoryController {
//	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	public AdminCategoryController(CategoryService categoryService) {
		super();
		this.categoryService = categoryService;
	}

	@RequestMapping()
	public String categoryManagement(HttpServletRequest request, HttpSession session,
			@CookieValue(value = "accountEmail", defaultValue = "", required = false) String userEmail) {
		// code

		return "admin/category/categoryManagement";
	}
	
	@RequestMapping(value="", method = RequestMethod.GET)
	public String getAllCategory(ModelMap model) {
		List<CategoryEntity> categoryEntities = this.categoryService.getAllCategory();
		model.addAttribute("categories", categoryEntities);
		return "admin/category/categoryManagement";
	}

}
