package agrStore.controller.staff;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import agrStore.bean.UploadFile;
import agrStore.entity.CategoryEntity;
import agrStore.entity.ProductEntity;
import agrStore.entity.ProviderEntity;
import agrStore.service.CategoryService;
import agrStore.service.ProductService;
import agrStore.service.ProviderService;
import agrStore.utility.Ultility;

@Controller
@RequestMapping("/staff")
@SessionAttributes("product")
public class StaffProductController {
	@Autowired
	ProductService productService;

	@Autowired
	CategoryService categoryService;

	@Autowired
	ProviderService providerService;

	@Autowired
	@Qualifier("product")
	UploadFile baseUploadFile;

	@Autowired
	Ultility ultility;

	@ModelAttribute("products")
	public List<ProductEntity> loadListProduct() {
		return productService.getListProduct();
	}

	@ModelAttribute("categories")
	public List<CategoryEntity> loadCategories() {
		return categoryService.getListCategory();
	}

	@ModelAttribute("providers")
	public List<ProviderEntity> loadProviders() {
		return providerService.getListProvider();
	}

	@ModelAttribute("sysDate")
	public Date getSystemDate() {
		return new Date();
	}

	@RequestMapping("/productManagement")
	public String productManagement() {
		return "staff/product/productManagement";
	}

	@RequestMapping(value = "/productManagement/product", method = RequestMethod.GET)
	public String handleProduct(@RequestParam(value = "action", required = false) String action,
			@RequestParam(value = "id", required = false) Integer id, Model model) {

		if (action != null) {
			switch (action) {
			case "add":
				System.out.println("==> Add product mode");
				model.addAttribute("product", new ProductEntity());

				model.addAttribute("mode", "ADD");
				break;

			case "view":
				if (id != null) {
					System.out.println("==> View product mode");
					ProductEntity product = productService.getProductById(id);
					model.addAttribute("mode", "VIEW");
					model.addAttribute("product", product);
					model.addAttribute("pdImg", product.getImage());
				}
				break;

			case "edit":
				if (id != null) {
					System.out.println("==> Edit product mode");
					ProductEntity product = productService.getProductById(id);
					model.addAttribute("mode", "EDIT");
					model.addAttribute("product", product);
				}
				break;
			}
		}

		return "staff/product/productForm"; // Trả về cùng một trang JSP
	}

	@RequestMapping(value = "/productManagement/product", params = "ADD", method = RequestMethod.POST)
	public String addProduct(HttpServletRequest request, Model model, @ModelAttribute("product") ProductEntity product,
			BindingResult errors) {

		Boolean isValid = Boolean.TRUE;

		if (product.getProductName() == null || ultility.standardizeName(product.getProductName()).isEmpty()) {
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
		} else if (product.getUnit() == null || ultility.standardize(product.getUnit()).isEmpty()) {
			errors.rejectValue("unit", "product", "Đơn vị của sản phẩm không được để trống!");
			isValid = Boolean.FALSE;
			System.out.println("Error: Unit field empty!");
		}

		if (!isValid) {
			System.out.println("Error: New product add failed!");
			model.addAttribute("mode", "ADD");
			return "staff/product/productForm";
		} else {
			try {
				ProductEntity newProduct = new ProductEntity();
				newProduct.setProductName(ultility.standardizeName(product.getProductName()));
				CategoryEntity category = categoryService.getCategoryById(product.getCategory().getCategoryId());
				ProviderEntity provider = providerService.getProviderById(product.getProvider().getId());
				newProduct.setCategory(category);
				newProduct.setProvider(provider);
				newProduct.setPrice(product.getPrice());
				newProduct.setQuantity(product.getQuantity());
				newProduct.setUnit(ultility.standardize(product.getUnit()));
				newProduct.setDescript(ultility.standardize(product.getDescript()));
				newProduct.setCreateAt(new Date());
				newProduct.setUpdateAt(new Date());
				newProduct.setStatus(isValid);
				newProduct.setImage(product.getImage());

				productService.addProduct(newProduct);
				System.out.println("==> New product add successfully!");
				return "redirect:/staff/productManagement.htm";
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Error: New product add failed!");
				model.addAttribute("mode", "ADD");
				return "staff/product/productForm";
			}
		}
	}

	@RequestMapping(value = "/productManagement/product", params = "EDIT", method = RequestMethod.POST)
	public String editProduct(Model model, @ModelAttribute("product") ProductEntity product, BindingResult errors) {

		Boolean isValid = Boolean.TRUE;

		// Kiểm tra tính hợp lệ của các trường
		if (product.getProductName() == null || ultility.standardizeName(product.getProductName()).isEmpty()) {
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
		if (product.getUnit() == null || ultility.standardize(product.getUnit()).isEmpty()) {
			errors.rejectValue("unit", "product", "Đơn vị của sản phẩm không được để trống!");
			isValid = Boolean.FALSE;
			System.out.println("Error: Unit field empty!");
		}

		// Nếu không hợp lệ, trả về lại form chỉnh sửa
		if (!isValid) {
			System.out.println("Error: Product update failed!");
			model.addAttribute("mode", "EDIT");
			return "staff/product/productForm";
		} else {
			// Cập nhật sản phẩm nếu hợp lệ
			try {
				product.setProductName(ultility.standardizeName(product.getProductName()));
				CategoryEntity category = categoryService.getCategoryById(product.getCategory().getCategoryId());
				ProviderEntity provider = providerService.getProviderById(product.getProvider().getId());
				product.setCategory(category);
				product.setProvider(provider);
				product.setUnit(ultility.standardize(product.getUnit()));
				product.setUpdateAt(new Date());

				productService.updateProduct(product);
				System.out.println("==> Product updated successfully!");
				return "redirect:/staff/productManagement.htm";
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Error: Product update failed!");
				model.addAttribute("mode", "EDIT");
				return "staff/product/productForm";
			}
		}

	}

	@RequestMapping(value = "/productManagement/deleteProduct", method = RequestMethod.GET)
	public String deleteProduct(@RequestParam("id") Integer id) {

		ProductEntity product = productService.getProductById(id);

		try {
			product.setStatus(Boolean.FALSE);
			productService.updateProduct(product);
			System.out.println("==> Set product status to 'False' successfully!");
		} catch (Exception e) {
			System.out.println("Error: Set product status to 'False' failed!");
			e.printStackTrace();
		}

		return "redirect:/staff/productManagement.htm";
	}

	@RequestMapping(value = "/product/uploadImg", method = RequestMethod.POST)
	public String uploadProductImage(HttpServletRequest request, Model model,
			@RequestParam("image") MultipartFile image) {
		if (image.isEmpty()) {
			model.addAttribute("imgError", "Vui lòng chọn file để upload!");
			System.out.println("Error: Image file not found!");
		} else {
			HttpSession session = request.getSession();
			ProductEntity product = (ProductEntity) session.getAttribute("product");
			if (product.getProductId() != null) {
				model.addAttribute("mode", "EDIT");
			} else {
				model.addAttribute("mode", "ADD");
			}
			try {

				String timestamp = new SimpleDateFormat("yyMMddHHmmss").format(new Date());
				String fileName = image.getOriginalFilename();
				String newFileName = timestamp + '-' + fileName;
				String photoPath = baseUploadFile.getBasePath() + File.separator + newFileName;
				image.transferTo(new File(photoPath));
				System.out.println("==> Upload file successfully!");

				Thread.sleep(2500);
				product.setImage(newFileName);
				model.addAttribute("imgError", "Upload file thành công!");
				return "staff/product/productForm";
			} catch (Exception e) {
				e.printStackTrace();

				model.addAttribute("imgError", "Upload file không thành công!");
				System.out.println("Error: Upload file failed!");
			}
		}

		return "staff/product/productForm";
	}
}
