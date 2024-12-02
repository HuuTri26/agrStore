package agrStore.controller.staff;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("/staff")
public class StaffAccountController {
	@RequestMapping("/staffProfile")
	public String staffProfile(HttpServletRequest request, HttpSession session,
			@CookieValue(value = "accountEmail", defaultValue = "", required = false) String userEmail) {
		// code

		return "staff/account/staffProfile";
	}
	@RequestMapping("/staffChangePassword")
	public String staffChangePassword(HttpServletRequest request, HttpSession session,
			@CookieValue(value = "accountEmail", defaultValue = "", required = false) String userEmail) {
		// code

		return "staff/account/staffChangePassword";
	}
}
