package agrStore.controller.customer;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class indexController {
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String indexShow(Model model) {
		return "index";
	}
}
