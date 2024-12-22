package agrStore.controller.staff;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

import agrStore.DAO.ProductDAO;
import agrStore.entity.OrderBillEntity;
import agrStore.entity.ProductEntity;
import agrStore.service.AccountService;
import agrStore.service.ImportBillService;
import agrStore.service.OrderBillService;

@Controller
@RequestMapping("/staff")
public class StaffController {
	
	@Autowired
	private ImportBillService importBillService;
	@Autowired
	private OrderBillService orderBillService;
	@Autowired
	private AccountService accountService;
	@Autowired
	private ProductDAO productDAO;
	
	@RequestMapping("/staffDashboard")
	public String staffDashboard(HttpServletRequest request, HttpSession session, ModelMap model,
			@CookieValue(value = "accountEmail", defaultValue = "", required = false) String userEmail) {
		// code
		model.addAttribute("currentPage", "dashboard");
		
		long totalCostInWeek = this.importBillService.getTotalCostImportInWeek();
		model.addAttribute("totalCostInWeek", totalCostInWeek);
		
		long soDonHangTrongMotNgay = this.orderBillService.getNumberOrderBillForToday();
		model.addAttribute("soDonHangTrongMotNgay", soDonHangTrongMotNgay);
		
		long doanhThuTrongNgay = this.orderBillService.getTodayRevenue();
		model.addAttribute("doanhThuTrongNgay", doanhThuTrongNgay);
		
		int soTaiKhoanKhachHang = this.accountService.countAccountByRole(3);
		model.addAttribute("soTaiKhoanKhachHang", soTaiKhoanKhachHang);
		
		List<OrderBillEntity> orderBillToday = this.orderBillService.getOrderBillToday();
		model.addAttribute("orderBillToday", orderBillToday);
		
		// láy danh sách các sản phẩm đc bán nhiều nhất
		List<Object[]> favoriteObject = this.productDAO.getTheMostPurchasedProduct();
		
		List<ProductEntity> favoriteProduct = new ArrayList<ProductEntity>();
		for (Object[] object : favoriteObject) {
			Integer id = (Integer) object[0];
			// System.out.println(object[2]);
			favoriteProduct.add(this.productDAO.getProductById(id));
		}
		model.addAttribute("favoriteProduct", favoriteProduct);
		// lấy số lượng đã bán ra ứng với từng sản phẩm nằm trong ds các sản phẩm đc mua nhiều nhất
		List<Long> soLuongDanBanCuaSanPhamYeuThich = new ArrayList<Long>();
		for (Object[] object : favoriteObject) {
			Long id = (Long) object[2];
			soLuongDanBanCuaSanPhamYeuThich.add(id);
		}
		model.addAttribute("soLuongDanBanCuaSanPhamYeuThich", soLuongDanBanCuaSanPhamYeuThich);
		
		
		
		return "staff/staffDashboard";
	}
}

