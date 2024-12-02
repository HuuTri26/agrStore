package agrStore.controller.staff;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import agrStore.entity.ProviderEntity;
import agrStore.service.ProviderService;
import agrStore.utility.Ultility;

public class StaffProviderController {
	@Autowired
	ProviderService providerService;

	@Autowired
	Ultility ultility;

	@ModelAttribute("providers")
	public List<ProviderEntity> loadListProvider() {
		return providerService.getListProvider();
	}

	@RequestMapping("/providerManagement")
	public String providerManagement(HttpServletRequest request, HttpSession session, ModelMap model,
			@CookieValue(value = "accountEmail", defaultValue = "", required = false) String userEmail) {
		// code
		model.addAttribute("currentPage", "Provider");
		model.addAttribute("providers", providerService.getListProvider());
		return "staff/provider/providerManagement";
	}

	@RequestMapping(value = "/providerManagement/provider", method = RequestMethod.GET)
	public String handleProvider(@RequestParam(value = "action", required = false) String action,
			@RequestParam(value = "id", required = false) Integer id, Model model) {

		if (action != null) {
			switch (action) {
			case "add":
				System.out.println("==> Add provider mode");
				model.addAttribute("provider", new ProviderEntity());
				model.addAttribute("mode", "ADD");
				// model.addAttribute("category", new Category());
				break;

			case "view":
				if (id != null) {
					// Category category = categoryService.getCategoryById(id);
					System.out.println("==> View provider mode");
					ProviderEntity provider = providerService.getProviderById(id);
					model.addAttribute("mode", "VIEW");
					model.addAttribute("provider", provider);
					// model.addAttribute("category", category);
				}
				break;

			case "edit":
				if (id != null) {
					// Category category = categoryService.getCategoryById(id);
					System.out.println("==> Edit provider mode");
					model.addAttribute("mode", "EDIT");
					ProviderEntity provider = providerService.getProviderById(id); // Lấy dữ liệu provider theo id
					model.addAttribute("provider", provider);
					// model.addAttribute("category", category);
				}
				break;

			case "delete":
				if (id != null) {
					// Category category = categoryService.getCategoryById(id);
					model.addAttribute("mode", "DELETE");
					// model.addAttribute("category", category);
				}
				break;
			}
		}

		return "staff/provider/providerForm"; // Trả về cùng một trang JSP
	}

	@RequestMapping(value = "/providerManagement/provider", params = "ADD", method = RequestMethod.POST)
	public String addProvider(HttpServletRequest request, Model model,
			@ModelAttribute("provider") ProviderEntity provider, BindingResult errors) {

		Boolean isValid = Boolean.TRUE;

		if (provider.getProviderName() == null || ultility.standardizeName(provider.getProviderName()).isEmpty()) {
			errors.rejectValue("providerName", "provider", "Tên doanh nghiệp không được để trống!");
			isValid = Boolean.FALSE;
			System.out.println("Error: Provider Name field empty!");
		} else if (provider.getPhoneNumber() == null
				|| ultility.isValidPhoneNumber(provider.getPhoneNumber()) == false) {
			errors.rejectValue("phoneNumber", "provider",
					"Số điện thoại doanh nghiệp không được để trống hoặc không đúng định dạng!");
			isValid = Boolean.FALSE;
			System.out.println("Error: Provider phone field empty!");
		}

		if (!isValid) {
			System.out.println("Error: New Provider add failed!");
			model.addAttribute("mode", "ADD");
			return "staff/provider/providerForm";
		} else {
			try {
				ProviderEntity newProvider = new ProviderEntity();
				newProvider.setProviderName(ultility.standardizeName(provider.getProviderName()));
				newProvider.setPhoneNumber(provider.getPhoneNumber());
				newProvider.setStatus(true);

				providerService.addProvider(newProvider);
				System.out.println("==> New provider add successfully!");
				return "redirect:/staff/providerManagement.htm";
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Error: New provider add failed!");
				model.addAttribute("mode", "ADD");
				return "staff/provider/providerForm";
			}
		}
	}

	@RequestMapping(value = "/providerManagement/provider", params = "EDIT", method = RequestMethod.POST)
	public String editProvider(HttpServletRequest request, Model model,
			@ModelAttribute("provider") ProviderEntity provider, BindingResult errors) {

		Boolean isValid = Boolean.TRUE;

		if (provider.getProviderName() == null || ultility.standardizeName(provider.getProviderName()).isEmpty()) {
			errors.rejectValue("providerName", "provider", "Tên doanh nghiệp không được để trống!");
			isValid = Boolean.FALSE;
			System.out.println("Error: Provider Name field empty!");
		} else if (provider.getPhoneNumber() == null
				|| ultility.isValidPhoneNumber(provider.getPhoneNumber()) == false) {
			errors.rejectValue("providerPhone", "provider", "Số điện thoại doanh nghiệp không được để trống!");
			isValid = Boolean.FALSE;
			System.out.println("Error: Provider phone field empty!");
		}

		if (!isValid) {
			System.out.println("Error:  Provider edit failed!");
			model.addAttribute("mode", "EDIT");
			return "staff/provider/providerForm";
		} else {
			try {
				provider.setProviderName(ultility.standardizeName(provider.getProviderName()));
				provider.setPhoneNumber(provider.getPhoneNumber());

				providerService.updateProvider(provider);
				System.out.println("==>  provider update successfully!");
				return "redirect:/staff/providerManagement.htm";
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Error: New provider add failed!");
				model.addAttribute("mode", "EDIT");
				return "staff/provider/providerForm";
			}
		}
	}

	@RequestMapping(value = "/providerManagement/deleteProvider", method = RequestMethod.GET)
	public String deleteProvider(@RequestParam("id") Integer id) {

		ProviderEntity provider = providerService.getProviderById(id);

		try {
			provider.setStatus(Boolean.FALSE);
			providerService.updateProvider(provider);
			System.out.println("==> Set provider status to 'False' successfully!");
		} catch (Exception e) {
			System.out.println("Error: Set provider status to 'False' failed!");
			e.printStackTrace();
		}

		return "redirect:/staff/providerManagement.htm";
	}

	/*
	 * @RequestMapping(value = "/providerManagement/provider", method =
	 * RequestMethod.POST) public String
	 * processCategory(@ModelAttribute("provider") @RequestParam("mode") String
	 * mode) {
	 * 
	 * if ("ADD".equals(mode)) { // categoryService.addCategory(category); } else if
	 * ("EDIT".equals(mode)) { // categoryService.updateCategory(category); }
	 * 
	 * return "redirect:/staff/provider/providerManagement.htm"; // Redirect sau khi
	 * xử lý }
	 */
}
