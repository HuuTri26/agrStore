package agrStore.controller.customer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomerAccountController {
	@RequestMapping("/customerProfile")
	public String customerProfile(HttpServletRequest request, HttpSession session,
			@CookieValue(value = "accountEmail", defaultValue = "", required = false) String userEmail) {
		// code

		return "customer/account/customerProfile";
	}

	@RequestMapping("/customerChangePassword")
	public String customerChangePassword(HttpServletRequest request, HttpSession session,
			@CookieValue(value = "accountEmail", defaultValue = "", required = false) String userEmail) {
		// code

		return "customer/account/customerChangePassword";
	}

	@RequestMapping("/customerOrderList")
	public String customerOderList(HttpServletRequest request, HttpSession session,
			@CookieValue(value = "accountEmail", defaultValue = "", required = false) String userEmail) {
		// code

		return "customer/account/customerOrderList";
	}

	@RequestMapping("/customerOrderDetail")
	public String customerOrderDetail(HttpServletRequest request, HttpSession session,
			@CookieValue(value = "accountEmail", defaultValue = "", required = false) String userEmail) {
		// code

		return "customer/account/customerOrderDetail";
	}
}
