package agrStore.controller.admin;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import agrStore.entity.AccountEntity;
import agrStore.entity.ProviderEntity;
import agrStore.service.ProviderService;
import agrStore.utility.ServerLogger;
import agrStore.utility.Ultility;
import agrStore.utility.UltilityImpl;

@Controller
@RequestMapping("/admin")
@SessionAttributes("provider")
public class AdminProviderController {
	@Autowired
	ProviderService providerService;
	
	@Autowired
	Ultility ultility;
	
	@ModelAttribute("providers")
	public List<ProviderEntity> loadListProvider(){
		return providerService.getListProvider();
	}

	@RequestMapping("/providerManagement")
	public String providerManagement(HttpServletRequest request, HttpSession session, ModelMap model,
			@CookieValue(value = "accountEmail", defaultValue = "", required = false) String userEmail) {
		// code
		model.addAttribute("currentPage", "Provider");
		model.addAttribute("providers", providerService.getListProvider());
		return "admin/provider/providerManagement";
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
					provider.setProviderName(UltilityImpl.XSSEscape4HTML(provider.getProviderName()));
					provider.setPhoneNumber(UltilityImpl.XSSEscape4HTML(provider.getPhoneNumber()));
				
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
					provider.setProviderName(UltilityImpl.XSSEscape4HTML(provider.getProviderName()));
					provider.setPhoneNumber(UltilityImpl.XSSEscape4HTML(provider.getPhoneNumber()));
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

		return "admin/provider/providerForm"; // Trả về cùng một trang JSP
	}
	
	@RequestMapping(value = "/providerManagement/provider", params = "ADD", method = RequestMethod.POST)
	public String addProvider(HttpServletRequest request, Model model, @ModelAttribute("provider") ProviderEntity provider,
			BindingResult errors) {
		
		HttpSession session = request.getSession();
		AccountEntity loggedInUser = (AccountEntity) session.getAttribute("loggedInUser");

		Boolean isValid = Boolean.TRUE;

		if (provider.getProviderName() == null || ultility.standardizeName(provider.getProviderName()).isEmpty()) {
			errors.rejectValue("providerName", "provider", "Tên doanh nghiệp không được để trống!");
			isValid = Boolean.FALSE;
			System.out.println("Error: Provider Name field empty!");
		}  else if (provider.getPhoneNumber() == null || ultility.isValidPhoneNumber(provider.getPhoneNumber()) == false) {
			errors.rejectValue("phoneNumber", "provider", "Số điện thoại doanh nghiệp không được để trống hoặc không đúng định dạng!");
			isValid = Boolean.FALSE;
			System.out.println("Error: Provider phone field empty!");
		}

		if (!isValid) {
			System.out.println("Error: New Provider add failed!");
			model.addAttribute("mode", "ADD");
			return "admin/provider/providerForm";
		}else {
			try {
				ProviderEntity newProvider = new ProviderEntity();
				newProvider.setProviderName(ultility.standardizeName(provider.getProviderName()));
				newProvider.setPhoneNumber(provider.getPhoneNumber());
				newProvider.setStatus(true);

				providerService.addProvider(newProvider);
				System.out.println("==> New provider add successfully!");
				ServerLogger.writeActionLog(loggedInUser.getGmail(), loggedInUser.getRole().getName(), "ADD", newProvider);
				
				return "redirect:/admin/providerManagement.htm";
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Error: New provider add failed!");
				ServerLogger.writeErrorLog(loggedInUser.getGmail(), loggedInUser.getRole().getName(), "ADD", e);
				model.addAttribute("mode", "ADD");
				return "admin/provider/providerForm";
			}
		}
	}
	
	@RequestMapping(value = "/providerManagement/provider", params = "EDIT", method = RequestMethod.POST)
	public String editProvider(HttpServletRequest request, Model model, @ModelAttribute("provider") ProviderEntity provider,
			BindingResult errors) {
		HttpSession session = request.getSession();
		AccountEntity loggedInUser = (AccountEntity) session.getAttribute("loggedInUser");
		Boolean isValid = Boolean.TRUE;

		if (provider.getProviderName() == null || ultility.standardizeName(provider.getProviderName()).isEmpty()) {
			errors.rejectValue("providerName", "provider", "Tên doanh nghiệp không được để trống!");
			isValid = Boolean.FALSE;
			System.out.println("Error: Provider Name field empty!");
		}  else if (provider.getPhoneNumber() == null || ultility.isValidPhoneNumber(provider.getPhoneNumber()) == false) {
			errors.rejectValue("phoneNumber", "provider", "Số điện thoại doanh nghiệp không được để trống!");
			isValid = Boolean.FALSE;
			System.out.println("Error: Provider phone field empty!");
		}

		if (!isValid) {
			System.out.println("Error:  Provider edit failed!");
			model.addAttribute("mode", "EDIT");
			return "admin/provider/providerForm";
		}else {
			try {
				provider.setProviderName(ultility.standardizeName(provider.getProviderName()));
				provider.setPhoneNumber(provider.getPhoneNumber());
				providerService.updateProvider(provider);
				System.out.println("==>  provider update successfully!");
				ServerLogger.writeActionLog(loggedInUser.getGmail(), loggedInUser.getRole().getName(), "UPDATE", provider);
				return "redirect:/admin/providerManagement.htm";
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Error: New provider add failed!");
				ServerLogger.writeErrorLog(loggedInUser.getGmail(), loggedInUser.getRole().getName(), "UPDATE", e);
				model.addAttribute("mode", "EDIT");
				return "admin/provider/providerForm";
			}
		}
	}
	
	@RequestMapping(value = "/providerManagement/deleteProvider", method = RequestMethod.GET)
	public String deleteProvider(@RequestParam("id") Integer id,HttpServletRequest request) {
		HttpSession session = request.getSession();
		AccountEntity loggedInUser = (AccountEntity) session.getAttribute("loggedInUser");
		ProviderEntity provider = providerService.getProviderById(id);
		
			try {
				provider.setStatus(Boolean.FALSE);
				providerService.updateProvider(provider);
				System.out.println("==> Set provider status to 'False' successfully!");
				ServerLogger.writeActionLog(loggedInUser.getGmail(), loggedInUser.getRole().getName(), "UPDATE", provider);
			}catch (Exception e) {
				System.out.println("Error: Set provider status to 'False' failed!");
				ServerLogger.writeErrorLog(loggedInUser.getGmail(), loggedInUser.getRole().getName(), "UPDATE", e);
				e.printStackTrace();
			}
		
			return "redirect:/admin/providerManagement.htm";
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
	 * return "redirect:/admin/provider/providerManagement.htm"; // Redirect sau khi
	 * xử lý }
	 */
}
