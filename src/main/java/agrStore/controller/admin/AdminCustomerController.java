package agrStore.controller.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import agrStore.DAO.DistrictDAO;
import agrStore.DAO.ProvinceDAO;
import agrStore.DAO.WardDAO;
import agrStore.entity.AccountEntity;
import agrStore.entity.DistrictEntity;
import agrStore.entity.ProvinceEntity;
import agrStore.entity.WardEntity;
import agrStore.service.AccountService;

@Controller
@RequestMapping("/admin")
public class AdminCustomerController {

	@Autowired
	private AccountService accountService;
	@Autowired
	private WardDAO wardDAO;
	@Autowired
	private DistrictDAO districtDAO;
	@Autowired
	private ProvinceDAO provinceDAO;

	@RequestMapping("/customerManagement")
	public String customerManagement(HttpServletRequest request, HttpSession session, ModelMap model,
			@CookieValue(value = "accountEmail", defaultValue = "", required = false) String userEmail) {
		// code
		model.addAttribute("currentPage", "customer");
		List<AccountEntity> customerList = this.accountService.getAllCustomer();
		model.addAttribute("customers", customerList);
		return "admin/customer/customerManagement";
	}

	@RequestMapping(value = "/customerManagement/customer", method = RequestMethod.GET)
	public String handleCustomer(@RequestParam(value = "action", required = false) String action,
			@RequestParam(value = "id", required = false) Integer id, Model model) {
		AccountEntity customer = this.accountService.getAccountById(id);
//		 System.out.println("Xã id: " + customer.getAddress().getWard().getId());
//		int wardID = customer.getAddress().getWard().getId();
//		WardEntity xa = this.wardDAO.getWardById(wardID);
//		int districtID = xa.getDistrict().getId();
//		 System.out.println("Huyện ID: " + districtID);
//		DistrictEntity huyen = this.districtDAO.getDitrictById(districtID);
//		int provinceID = huyen.getProvince().getId();
//		System.out.println("Tỉnh ID: " + provinceID);
//		ProvinceEntity tinh = this.provinceDAO.getProvinceById(provinceID);
		
		if (action != null) {
			switch (action) {
			case "add":
				model.addAttribute("mode", "ADD");
				// model.addAttribute("category", new Category());
				break;

			case "view":
				if (id != null) {
					// Category category = categoryService.getCategoryById(id);
					model.addAttribute("mode", "VIEW");
					// model.addAttribute("category", category);
					model.addAttribute("customer", customer);
//					model.addAttribute("wardName", xa.getName());
//					model.addAttribute("districtName", huyen.getName());
//					model.addAttribute("provinceName", tinh.getName());
					
				}
				break;

			case "edit":
				if (id != null) {
					// Category category = categoryService.getCategoryById(id);
					model.addAttribute("mode", "EDIT");
					// model.addAttribute("category", category);
				}
				break;
			}
		}

		return "admin/customer/customerForm"; // Trả về cùng một trang JSP
	}

	@RequestMapping(value = "/customerManagement/customer", method = RequestMethod.POST)
	public String processCustoner(ModelMap model,
			// @ModelAttribute("staff") StaffEntity category,
			@RequestParam("mode") String mode) {

		if ("ADD".equals(mode)) {
			// categoryService.addCategory(category);
		} else if ("EDIT".equals(mode)) {
			// categoryService.updateCategory(category);
		}

		return "redirect:/admin/customer/customerManagement.htm"; // Redirect sau khi xử lý
	}

	@RequestMapping(value = "/customerManagement/customer", params = "btnCancel")
	public String backToCustomerManagement() {
		
		return "redirect:/admin/customer/customerManagement.htm";
	}
	

}
