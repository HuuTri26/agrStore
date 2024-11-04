
package agrStore.controller.customer;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import agrStore.entity.CategoryEntity;
import agrStore.entity.ProductEntity;
import agrStore.service.CategoryService;
import agrStore.service.ProductService;
@Controller
public class homeController {
	
	@Autowired
	ProductService productService;
	
	@Autowired
	CategoryService categoryService;
	
	@ModelAttribute("categories")
	public List<CategoryEntity> loadListCategory(){
		return categoryService.getListCategory();
	}
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
    public String indexShow(HttpServletRequest request, Model model) {
        // Load dữ liệu mặc định với id = 1
        List<ProductEntity> products = productService.getListProductByCategoryId(1);
        List<ProductEntity> randProducts = productService.getRandomListProductByLimit(products, 12);
        model.addAttribute("randProducts", randProducts);
        
        // Thêm categoryId vào model để đánh dấu category đang được chọn
        model.addAttribute("selectedCategoryId", 1);
        
        System.out.println("check");
        return "customer/index";
    }
	
	@RequestMapping(value = "/showProducts", method = RequestMethod.GET)
	public String showProductByCategoryId(Model model, @RequestParam(value = "id") Integer id) {
		
		List<ProductEntity> products = productService.getListProductByCategoryId(id);
		List<ProductEntity> randProducts = productService.getRandomListProductByLimit(products, 12);
		
		model.addAttribute("randProducts", randProducts);
		model.addAttribute("selectedCategoryId", id);
		return "customer/index";
	}
}
