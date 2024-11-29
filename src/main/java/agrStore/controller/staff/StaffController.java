package agrStore.controller.staff;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("/staff")
public class StaffController {
	@RequestMapping("/staffDashboard")
	public String staffDashboard(HttpServletRequest request, HttpSession session, ModelMap model,
			@CookieValue(value = "accountEmail", defaultValue = "", required = false) String userEmail) {
		// code
		model.addAttribute("currentPage", "dashboard");
		return "staff/staffDashboard";
	}
}
