
package agrStore.controller.customer;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
@Controller
public class homeController {
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String indexShow(HttpServletRequest request,Model model) {
		return "customer/index";
	}
}
