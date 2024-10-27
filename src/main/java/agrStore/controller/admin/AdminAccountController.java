package agrStore.controller.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminAccountController {
	@RequestMapping("/adminProfile")
	public String adminProfile(HttpServletRequest request, HttpSession session,
			@CookieValue(value = "accountEmail", defaultValue = "", required = false) String userEmail) {
		// code

		return "admin/account/adminProfile";
	}
	@RequestMapping("/adminChangePassword")
	public String adminChangePassword(HttpServletRequest request, HttpSession session,
			@CookieValue(value = "accountEmail", defaultValue = "", required = false) String userEmail) {
		// code

		return "admin/account/adminChangePassword";
	}

}
