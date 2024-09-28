package agrStore.controller.customer;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
public class customerCartController {
	@RequestMapping("/customerCart")
	public String customerCartShow(HttpServletRequest request, HttpSession session,
			@CookieValue(value = "userEmail", defaultValue = "", required = false) String userEmail) {
		// code

		return "customer/customerCart";
	}
}
