package agrStore.controller.admin;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import agrStore.entity.CategoryEntity;
import agrStore.entity.ProductEntity;
import agrStore.service.CategoryService;
import agrStore.service.ProductService;

@Controller
public class AdminProductController {

	@Autowired
	ProductService productService;

	@Autowired
	CategoryService categoryService;

	@RequestMapping("/productManagement")
	public String productManagement(Model model) {
		System.out.println("");
		List<ProductEntity> products = productService.getListProduct();
		model.addAttribute("products", products);
		return "admin/product/productManagement";
	}
	
	@ModelAttribute("categories")
	public List<CategoryEntity> loadCategories(){
		return categoryService.getListCategory();
	}
	
	@RequestMapping(value = "/productManagement/product", method = RequestMethod.GET)
	public String handleProduct(@RequestParam(value = "action", required = false) String action,
			@RequestParam(value = "id", required = false) Integer id, Model model) {

		model.addAttribute("product", new ProductEntity());

		if (action != null) {
			switch (action) {
			case "add":
				System.out.println("==> Add product mode");
				model.addAttribute("mode", "ADD");
				model.addAttribute("product", new ProductEntity());
				break;

			case "view":
				if (id != null) {
					System.out.println("==> View product mode");
					ProductEntity product = productService.getProductById(id);
					model.addAttribute("mode", "VIEW");
					model.addAttribute("product", product);
				}
				break;

			case "edit":
				if (id != null) {
					System.out.println("==> Edit product mode");
					ProductEntity product = productService.getProductById(id);
					model.addAttribute("mode", "EDIT");
					model.addAttribute("product", product);
					System.out.println(product.getProductId());
				}
				break;
			}
		}

		return "admin/product/productForm"; // Trả về cùng một trang JSP
	}

	@RequestMapping(value = "/productManagement/product", params = "ADD", method = RequestMethod.POST)
	public String addProduct(Model model, @ModelAttribute("product") ProductEntity product, BindingResult errors) {

		Boolean isValid = Boolean.TRUE;

		if (product.getProductName().isEmpty()) {
			errors.rejectValue("productName", "product", "Tên sản phẩm không được để trống!");
			isValid = Boolean.FALSE;
			System.out.println("Error: Product Name field empty!");
		} else if (product.getCategory().getCategoryId() == null) {
			errors.rejectValue("category.categoryId", "product", "Vui lòng chọn danh mục của sản phẩm!");
			isValid = Boolean.FALSE;
			System.out.println("Error: Category field empty!");
		} else if (product.getPrice() < 0) {
			errors.rejectValue("price", "product", "Giá sản phẩm không hợp lệ!");
			isValid = Boolean.FALSE;
			System.out.println("Error: Invalid price field!");
		} else if (product.getUnit().isEmpty()) {
			errors.rejectValue("unit", "product", "Đơn vị của sản phẩm không được để trống!");
			isValid = Boolean.FALSE;
			System.out.println("Error: Unit field empty!");
		}

		if (!isValid) {
			model.addAttribute("mode", "ADD");
			return "admin/product/productForm";
		}
		
		try {
			ProductEntity newProduct = new ProductEntity();
			newProduct.setProductName(product.getProductName().trim());
			CategoryEntity category = categoryService.getCategoryById(product.getCategory().getCategoryId());
			newProduct.setCategory(category);
			newProduct.setPrice(product.getPrice());
			newProduct.setUnit(product.getUnit());
			newProduct.setDescript(product.getDescript());
			newProduct.setCreateAt(new Date());
			newProduct.setUpdateAt(new Date());
			newProduct.setStatus(Boolean.TRUE);

			productService.addProduct(newProduct);
			System.out.println("==> New product add successfully!");
			return "redirect:/productManagement.htm";
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error: New product add failed!");
			model.addAttribute("mode", "ADD");
	        return "admin/product/productForm";
		}
		
	}
	
	@RequestMapping(value = "/productManagement/product", params = "EDIT", method = RequestMethod.POST)
	public String editProduct(Model model, @ModelAttribute("product") ProductEntity product, BindingResult errors) {

	    Boolean isValid = Boolean.TRUE;

	    // Kiểm tra tính hợp lệ của các trường
	    if (product.getProductName() == null || product.getProductName().trim().isEmpty()) {
	        errors.rejectValue("productName", "product", "Tên sản phẩm không được để trống!");
	        isValid = Boolean.FALSE;
	        System.out.println("Error: Product Name field empty!");
	    }
	    if (product.getCategory() == null || product.getCategory().getCategoryId() == null) {
	        errors.rejectValue("category.categoryId", "product", "Vui lòng chọn danh mục của sản phẩm!");
	        isValid = Boolean.FALSE;
	        System.out.println("Error: Category field empty!");
	    }
	    if (product.getPrice() < 0) {
	        errors.rejectValue("price", "product", "Giá sản phẩm không hợp lệ!");
	        isValid = Boolean.FALSE;
	        System.out.println("Error: Invalid price field!");
	    }
	    if (product.getUnit() == null || product.getUnit().trim().isEmpty()) {
	        errors.rejectValue("unit", "product", "Đơn vị của sản phẩm không được để trống!");
	        isValid = Boolean.FALSE;
	        System.out.println("Error: Unit field empty!");
	    }

	    // Nếu không hợp lệ, trả về lại form chỉnh sửa
	    if (!isValid) {
	        model.addAttribute("mode", "EDIT");
	        return "admin/product/productForm";
	    }

	    // Cập nhật sản phẩm nếu hợp lệ
	    try {
	        product.setProductName(product.getProductName().trim());
	        CategoryEntity category = categoryService.getCategoryById(product.getCategory().getCategoryId());
	        product.setCategory(category);
	        product.setUpdateAt(new Date());

	        productService.updateProduct(product);
	        System.out.println("==> Product updated successfully!");
	        return "redirect:/productManagement.htm";
	    } catch (Exception e) {
	        e.printStackTrace();
	        System.out.println("Error: Product update failed!");
	        model.addAttribute("mode", "EDIT");
	        return "admin/product/productForm";
	    }
	}

}
