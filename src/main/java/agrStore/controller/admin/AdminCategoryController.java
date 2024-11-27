package agrStore.controller.admin;

import java.io.File;
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
import agrStore.service.CategoryService;
import agrStore.utility.Ultility;

@Controller
@SessionAttributes("category")
public class AdminCategoryController {

	@Autowired
	CategoryService categoryService;

	@Autowired
	@Qualifier("category")
	UploadFile baseUploadFile;

	@Autowired
	Ultility ultility;

	@ModelAttribute("categories")
	public List<CategoryEntity> loadListCategory() {
		return categoryService.getListCategory();
	}

	@RequestMapping("/categoryManagement")
	public String categoryManagement() {
		return "admin/category/categoryManagement";
	}

	@RequestMapping(value = "/categoryManagement/category", method = RequestMethod.GET)
	public String handleCategory(@RequestParam(value = "action", required = false) String action,
			@RequestParam(value = "id", required = false) Integer id, Model model) {

		if (action != null) {
			switch (action) {
			case "add":
				System.out.println("==> Add category mode");
				model.addAttribute("mode", "ADD");
				model.addAttribute("category", new CategoryEntity());
				break;

			case "view":
				if (id != null) {
					System.out.println("==> View category mode");
					CategoryEntity category = categoryService.getCategoryById(id);
					model.addAttribute("mode", "VIEW");
					model.addAttribute("category", category);
				}
				break;

			case "edit":
				if (id != null) {
					System.out.println("==> Edit category mode");
					CategoryEntity category = categoryService.getCategoryById(id);
					model.addAttribute("mode", "EDIT");
					model.addAttribute("category", category);
				}
				break;
			}
		}

		return "admin/category/categoryForm"; // Trả về cùng một trang JSP
	}

	@RequestMapping(value = "/categoryManagement/category", params = "ADD", method = RequestMethod.POST)
	public String addCategory(HttpServletRequest request, Model model,
			@ModelAttribute("category") CategoryEntity category, BindingResult errors) {

		Boolean isValid = Boolean.TRUE;

		if (category.getCategoryName() == null || ultility.standardizeName(category.getCategoryName()).isEmpty()) {
			errors.rejectValue("categoryName", "category", "Tên danh mục không được để trống!");
			isValid = Boolean.FALSE;
			System.out.println("Error: Category Name field empty!");
		}

		if (!isValid) {
			System.out.println("Error: New category add failed!");
			model.addAttribute("mode", "ADD");
			return "admin/category/categoryForm";
		} else {
			try {
				category.setCategoryName(ultility.standardizeName(category.getCategoryName()));
				category.setStatus(isValid);
				category.setDescript(ultility.standardize(category.getDescript()));

				categoryService.addCategory(category);
				System.out.println("==> New category add successfully!");
				return "redirect:/categoryManagement.htm";
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Error: New category add failed!");
				model.addAttribute("mode", "ADD");
				return "admin/category/categoryForm";
			}
		}
	}

	@RequestMapping(value = "/categoryManagement/category", params = "EDIT", method = RequestMethod.POST)
	public String editCategory(HttpServletRequest request, Model model,
			@ModelAttribute("category") CategoryEntity category, BindingResult errors) {

		Boolean isValid = Boolean.TRUE;

		if (category.getCategoryName() == null || ultility.standardizeName(category.getCategoryName()).isEmpty()) {
			errors.rejectValue("categoryName", "category", "Tên danh mục không được để trống!");
			isValid = Boolean.FALSE;
			System.out.println("Error: Category Name field empty!");
		}

		if (!isValid) {
			System.out.println("Error: New category update failed!");
			model.addAttribute("mode", "ADD");
			return "admin/category/categoryForm";
		} else {
			try {
				category.setCategoryName(ultility.standardizeName(category.getCategoryName()));
				category.setDescript(ultility.standardize(category.getDescript()));

				categoryService.updateCategory(category);
				return "redirect:/categoryManagement.htm";
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Error: New category update failed!");
				model.addAttribute("mode", "EDIT");
				return "admin/category/categoryForm";
			}
		}
	}

	@RequestMapping(value = "/categoryManagement/deleteCategory", method = RequestMethod.GET)
	public String deleteCategory(@RequestParam("id") Integer id) {

		CategoryEntity category = categoryService.getCategoryById(id);

		try {
			category.setStatus(Boolean.FALSE);

			categoryService.updateCategory(category);
			System.out.println("==> Set category status to 'False' successfully!");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("==> Set category status to 'False' failed!");
		}

		return "redirect:/categoryManagement.htm";
	}

	@RequestMapping(value = "category/uploadImg", method = RequestMethod.POST)
	public String uploadCategoryImage(HttpServletRequest request, Model model,
			@RequestParam("image") MultipartFile image) {
		if (image.isEmpty()) {
			model.addAttribute("imgError", "Vui lòng chọn file để upload!");
			System.out.println("Error: Image file not found!");
		}else {
			HttpSession session = request.getSession();
			CategoryEntity category = (CategoryEntity) session.getAttribute("category");
			
			if(category.getCategoryId() != null) {
				model.addAttribute("mode", "EDIT");
			}else {
				model.addAttribute("mode", "ADD");
			}
			try {
				String fileName = image.getOriginalFilename();
				String photoPath = baseUploadFile.getBasePath() + File.separator + fileName;
				image.transferTo(new File(photoPath));
				System.out.println("==> Upload file successfully!");

				Thread.sleep(2500);
				category.setImage(fileName);
				model.addAttribute("imgError", "Upload file thành công!");
				return "admin/category/categoryForm";
			}catch (Exception e) {
				e.printStackTrace();
				model.addAttribute("imgError", "Upload file không thành công!");
				System.out.println("Error: Upload file failed!");
			}
		}
		return "admin/category/categoryForm";
	}

}
