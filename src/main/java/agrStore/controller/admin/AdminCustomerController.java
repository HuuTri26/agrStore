package agrStore.controller.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import agrStore.entity.AccountEntity;
import agrStore.service.AccountService;

@Controller
@RequestMapping("customerManagement")
public class AdminCustomerController {
	@Autowired
	private AccountService accountService;
	
	// @RequestMapping("/customerManagement")
	@RequestMapping()
	public String customerManagement(HttpServletRequest request, HttpSession session,
			@CookieValue(value = "accountEmail", defaultValue = "", required = false) String userEmail) {
		// code

		return "admin/customer/customerManagement";
	}
	
	@RequestMapping(value="", method = RequestMethod.GET)
	public String getAllCustomer(ModelMap model) {
		List<AccountEntity> customers = this.accountService.getAllCustomer();
		model.addAttribute("customers", customers);
		return "admin/customer/customerManagement";
	}
	
	@RequestMapping(value="customerDetail/{id}", method = RequestMethod.GET)
	public String getCustomerDetail(ModelMap model, @PathVariable("id") Integer id) {
		AccountEntity account = this.accountService.getAccountById(id);
		model.addAttribute("customer", account);
		return "admin/customer/customerDetail";
	}

}
