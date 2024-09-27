package agrStore.controller.customer;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class userController {
	@RequestMapping(value = "/userLogin", method = RequestMethod.GET)
	public String userLogin(HttpServletRequest request) {
		
		return "customer/login/userLogin";
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
