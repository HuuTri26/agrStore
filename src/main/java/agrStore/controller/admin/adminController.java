package agrStore.controller.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

import agrStore.service.ImportBillService;
import agrStore.service.OrderBillService;

@Controller
public class adminController {
	
	@Autowired
	private ImportBillService importBillService;
	@Autowired
	private OrderBillService orderBillService;
	
	@RequestMapping("/adminDashboard")
	public String adminDashboard(HttpServletRequest request, HttpSession session, ModelMap model,
			@CookieValue(value = "accountEmail", defaultValue = "", required = false) String userEmail) {
		// code
		model.addAttribute("currentPage", "dashboard");
		
		long totalCostInWeek = this.importBillService.getTotalCostImportInWeek();
		model.addAttribute("totalCostInWeek", totalCostInWeek);
		
		long soDonHangTrongMotNgay = this.orderBillService.getNumberOrderBillForToday();
		model.addAttribute("soDonHangTrongMotNgay", soDonHangTrongMotNgay);
		
		return "admin/adminDashboard";
	}
}
