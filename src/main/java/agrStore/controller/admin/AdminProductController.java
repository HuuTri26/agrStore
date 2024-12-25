package agrStore.controller.admin;

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
import agrStore.entity.AccountEntity;
import agrStore.entity.CategoryEntity;
import agrStore.entity.ProductEntity;
import agrStore.entity.ProviderEntity;
import agrStore.service.CategoryService;
import agrStore.service.ProductService;
import agrStore.service.ProviderService;
import agrStore.utility.ServerLogger;
import agrStore.utility.Ultility;
import agrStore.utility.UltilityImpl;


@Controller
@RequestMapping("/admin")
@SessionAttributes("product")
public class AdminProductController {

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
	public List<ProductEntity> loadListProduct(){
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
		return "admin/product/productManagement";
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
					product.setProductName(UltilityImpl.XSSEscape4HTML(product.getProductName()));
					product.setUnit(UltilityImpl.XSSEscape4HTML(product.getUnit()));
					product.setDescript(UltilityImpl.XSSEscape4HTML(product.getDescript()));
					model.addAttribute("product", product);
					model.addAttribute("pdImg", product.getImage());
				}
				break;

			case "edit":
				if (id != null) {
					System.out.println("==> Edit product mode");
					ProductEntity product = productService.getProductById(id);
					model.addAttribute("mode", "EDIT");
					product.setProductName(UltilityImpl.XSSEscape4HTML(product.getProductName()));
					product.setUnit(UltilityImpl.XSSEscape4HTML(product.getUnit()));
					product.setDescript(UltilityImpl.XSSEscape4HTML(product.getDescript()));
					model.addAttribute("product", product);
				}
				break;
			}
		}

		return "admin/product/productForm"; // Trả về cùng một trang JSP
	}

	@RequestMapping(value = "/productManagement/product", params = "ADD", method = RequestMethod.POST)
	public String addProduct(HttpServletRequest request, Model model, @ModelAttribute("product") ProductEntity product,
			BindingResult errors) {
		HttpSession session = request.getSession();
		AccountEntity loggedInUser = (AccountEntity) session.getAttribute("loggedInUser");
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
			return "admin/product/productForm";
		}else {
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
				ServerLogger.writeActionLog(loggedInUser.getGmail(), loggedInUser.getRole().getName(), "ADD", newProduct);
				return "redirect:/admin/productManagement.htm";
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Error: New product add failed!");
				ServerLogger.writeErrorLog(loggedInUser.getGmail(), loggedInUser.getRole().getName(), "ADD", e);
				model.addAttribute("mode", "ADD");
				return "admin/product/productForm";
			}
		}
	}

	@RequestMapping(value = "/productManagement/product", params = "EDIT", method = RequestMethod.POST)
	public String editProduct(Model model, @ModelAttribute("product") ProductEntity product, BindingResult errors,HttpServletRequest request) {
		HttpSession session = request.getSession();
		AccountEntity loggedInUser = (AccountEntity) session.getAttribute("loggedInUser");
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
			return "admin/product/productForm";
		}else {
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
				ServerLogger.writeActionLog(loggedInUser.getGmail(), loggedInUser.getRole().getName(), "UPDATE", product);
				return "redirect:/admin/productManagement.htm";
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Error: Product update failed!");
				ServerLogger.writeErrorLog(loggedInUser.getGmail(), loggedInUser.getRole().getName(), "UPDATE", e);
				model.addAttribute("mode", "EDIT");
				return "admin/product/productForm";
			}
		}

	}
	
	@RequestMapping(value = "/productManagement/deleteProduct", method = RequestMethod.GET)
	public String deleteProduct(@RequestParam("id") Integer id,HttpServletRequest request) {
		HttpSession session = request.getSession();
		AccountEntity loggedInUser = (AccountEntity) session.getAttribute("loggedInUser");
		ProductEntity product = productService.getProductById(id);
		
			try {
				product.setStatus(Boolean.FALSE);
				productService.updateProduct(product);
				System.out.println("==> Set product status to 'False' successfully!");
				ServerLogger.writeActionLog(loggedInUser.getGmail(), loggedInUser.getRole().getName(), "UPDATE", product);
			}catch (Exception e) {
				System.out.println("Error: Set product status to 'False' failed!");
				ServerLogger.writeErrorLog(loggedInUser.getGmail(), loggedInUser.getRole().getName(), "UPDATE", e);
				e.printStackTrace();
			}
		
			return "redirect:/admin/productManagement.htm";
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
			if(product.getProductId() != null) {
				model.addAttribute("mode", "EDIT");
			}else {
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
				return "admin/product/productForm";
			} catch (Exception e) {
				e.printStackTrace();

				model.addAttribute("imgError", "Upload file không thành công!");
				System.out.println("Error: Upload file failed!");
			}
		}
		
		return "admin/product/productForm";
	}

}
