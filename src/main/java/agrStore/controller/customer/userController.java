package agrStore.controller.customer;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import agrStore.entity.AccountEntity;
import agrStore.utility.AccountUltility;
import argStore.service.AccountService;

@Controller
public class userController {

	@Autowired
	AccountUltility accountUltility;
	
	@Autowired
	AccountService accountService;

	@RequestMapping("/userLogin")
	public String showUserLoginForm(Model model) {
		model.addAttribute("account", new AccountEntity());
		return "customer/login/userLogin";
	}

	@RequestMapping(value = "/userLogin", method = RequestMethod.POST)
	public String userLogin(ModelMap model, HttpServletRequest request,
			@ModelAttribute("account") AccountEntity account, BindingResult errors) {
		Boolean isValid = Boolean.TRUE;
		
		//Kiểm tra xem field dữ liệu nhập từ view có trống ko?
		if (account.getGmail().isEmpty()) {
			errors.rejectValue("gmail", "account", "Xin vui lòng nhập username(gmail) của bạn!");
			return "customer/login/userLogin";
		}else if (account.getPassword().isEmpty()) {
			errors.rejectValue("password", "account", "Xin vui lòng nhập password!");
			return "customer/login/userLogin";
		}
		
		//Kiểm tra username, password, và trạng thái tài khoản
		AccountEntity account_t =  accountService.getAccountByGmail(account.getGmail());
		if (account_t == null) {
			errors.rejectValue("password", "account", "Mật khẩu của bạn sai hoặc username không đúng!");
			isValid = Boolean.FALSE;
		}else if (!account_t.getPassword().equals(accountUltility.getHashPassword(account.getPassword()))) {
			errors.rejectValue("password", "account", "Mật khẩu của bạn sai hoặc username không đúng!");
			isValid = Boolean.FALSE;
		}else if(!account_t.getStatus()) {
			errors.rejectValue("gmail", "account", "Tài khoản của bạn đã bị khóa!");
			isValid = Boolean.FALSE;
		}
		
		if(isValid) {
			System.out.println("Login successfully!");
			return "redirect:/index.htm";
		}else {
			System.out.println("Login unsuccessfully!");
			return "customer/login/userLogin";
		}
	}

	@RequestMapping(value = "/forgotPass", method = RequestMethod.GET)
	public String forgotPass(HttpServletRequest request) {
		return "customer/forgotPassword/userForgotPasswordGmail";
	}

	@RequestMapping(value = "/getOTPForgotPassword", method = RequestMethod.GET)
	public String getOTPForgotPassword(HttpServletRequest request) {
		return "customer/forgotPassword/userForgotPasswordGetOTP";
	}

	@RequestMapping(value = "/changeForgotPassword", method = RequestMethod.GET)
	public String changeForgotPassword(HttpServletRequest request) {
		return "customer/forgotPassword/changeForgotPassword";
	}

	@RequestMapping(value = "/userSignUpGmail", method = RequestMethod.GET)
	public String userSignUpGmail(HttpServletRequest request) {

		return "customer/login/userSignUpGmail";
	}

	@RequestMapping(value = "/getOTPSignUp", method = RequestMethod.GET)
	public String usergetOTPSignUp(HttpServletRequest request) {

		return "customer/login/userSignUpGmailGetOTP";
	}

	@RequestMapping(value = "/userSignUp", method = RequestMethod.GET)
	public String userSignUp(HttpServletRequest request) {

		return "customer/login/userSignUp";
	}
}
