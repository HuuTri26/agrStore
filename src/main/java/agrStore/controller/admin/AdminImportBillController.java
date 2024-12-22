package agrStore.controller.admin;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import agrStore.entity.AccountEntity;
import agrStore.entity.ImportBillDetailEntity;
import agrStore.entity.ImportBillEntity;
import agrStore.entity.ProductEntity;
import agrStore.entity.ProviderEntity;
import agrStore.service.ImportBillDetailService;
import agrStore.service.ImportBillService;
import agrStore.service.ProductService;
import agrStore.service.ProviderService;

@Controller
@RequestMapping("/admin")
public class AdminImportBillController {

	@Autowired
	private ImportBillService importBillService;
	@Autowired
	private ImportBillDetailService importBillDetailService;
	@Autowired
	private ProviderService providerService;
	@Autowired
	private ProductService productService;

	@RequestMapping("/importBillManagement")
	public String importBillManagement(HttpServletRequest request, HttpSession session, ModelMap model) {
		// code
		model.addAttribute("currentPage", "importBill");
		List<ImportBillEntity> importBillEntities = this.importBillService.getAllImportBill();
		model.addAttribute("importBills", importBillEntities);

		return "admin/importBill/importBillManagement";
	}

	@RequestMapping(value = "/importBillManagement/importBill", method = RequestMethod.GET)
	public String handleImportBill(@RequestParam(value = "action", required = false) String action, HttpSession session,
			@RequestParam(value = "id", required = false) Integer id, Model model) {

		if (action != null) {
			switch (action) {
			case "create":
				model.addAttribute("mode", "CREATE");
				AccountEntity loggedInUser = (AccountEntity) session.getAttribute("loggedInUser");
				if (loggedInUser != null) {
					System.out.println("Gmail: " + loggedInUser.getGmail());
					model.addAttribute("loggedInUser", loggedInUser);
				} else {
					System.out.println("User not logged in!");
				}
				List<ProviderEntity> providers = this.providerService.getListProvider();
				model.addAttribute("providers", providers);

				return "admin/importBill/createImportBill";

			case "view":
				if (id != null) {
					ImportBillEntity importBillEntity = this.importBillService.getImportBillEntityById(id);
					// System.out.println(importBillEntity.getTotalPrice());

					List<ImportBillDetailEntity> importBillDetailEntities = this.importBillDetailService
							.getImportBillDetailByImportBillId(id);
					model.addAttribute("mode", "VIEW");
					model.addAttribute("importBill", importBillEntity);
					model.addAttribute("importBillDetails", importBillDetailEntities);
				}
				break;

			case "edit":
				if (id != null) {
					model.addAttribute("mode", "EDIT");
				}
				break;
			}
		}

		return "admin/importBill/importBillForm"; // Trả về cùng một trang JSP
	}

	@RequestMapping(value = "/importBillManagement/getProductsByProvider", method = RequestMethod.POST)
	public String getProductsByProviderId(ModelMap model, HttpSession session,
			@RequestParam("providerId") Integer providerId) {
		List<ProviderEntity> providers = this.providerService.getListProvider();
		model.addAttribute("providers", providers);
		session.setAttribute("adminSelectedProviderId", providerId);

		model.addAttribute("selectedProviderId", providerId);

		List<ProductEntity> products = this.productService.getProductsByProviderId(providerId);
		model.addAttribute("products", products);

		return "admin/importBill/createImportBill";
	}

	@RequestMapping(value = "/importBillManagement/getSelectedProducts", method = RequestMethod.POST)
	// hàm lấy danh sách sản phẩm đã được chọn khi click vào nút GET
	public String getSelectedProducts(@RequestParam("selectedProductIds") List<Integer> selectedProductIds, Model model,
			HttpSession session) {

		List<ProductEntity> selectedProductList = new ArrayList<ProductEntity>();
		List<ImportBillDetailEntity> importBillDetailList = new ArrayList<ImportBillDetailEntity>();
		int providerId = (int) session.getAttribute("adminSelectedProviderId");
		// láy ds provider để hiển thị ra select
		List<ProviderEntity> providers = this.providerService.getListProvider();
		model.addAttribute("providers", providers);

		// lấy danh sách sản phẩm ứng với providerId
		List<ProductEntity> products = this.productService.getProductsByProviderId(providerId);
		model.addAttribute("products", products);

		ProductEntity temp = null;
		for (Integer i : selectedProductIds) {
			temp = this.productService.getProductById(i);
			selectedProductList.add(temp);
		}

		for (ProductEntity i : selectedProductList) {
			ImportBillDetailEntity importBillDetailTemp = new ImportBillDetailEntity();
			importBillDetailTemp.setProduct(i);
			importBillDetailTemp.setPrice(0);
			importBillDetailTemp.setQuantity(0);
			importBillDetailList.add(importBillDetailTemp);
		}
		session.setAttribute("selectedProductIds", selectedProductIds);
		model.addAttribute("selectedProductList", selectedProductList);
		session.setAttribute("importBillDetailList", importBillDetailList);
		model.addAttribute("importBillDetailList", importBillDetailList);

		return "admin/importBill/createImportBill";
	}

	@RequestMapping(value = "/importBillManagement/getSelectedProductsAfterDelete", method = RequestMethod.GET)
	// hàm lấy ra danh sách sản phẩm sau khi ta click nút xóa
	public String getSelectedProductsAfterDelete(Model model, HttpSession session,
			@RequestParam(value = "productId", required = false) Integer productIdCanXoa) {
		List<Integer> selectedProductIds = (List<Integer>) session.getAttribute("selectedProductIds");
		List<ProductEntity> selectedProductList = new ArrayList<ProductEntity>();
		int providerId = (int) session.getAttribute("adminSelectedProviderId");
		// láy ds provider để hiển thị ra select
		List<ProviderEntity> providers = this.providerService.getListProvider();
		model.addAttribute("providers", providers);
		// lấy danh sách sản phẩm ứng với providerId
		List<ProductEntity> products = this.productService.getProductsByProviderId(providerId);
		model.addAttribute("products", products);

		// lấy tổng tiên và số lượng hàng hóa của hóa đơn nhập hàng
		if (session.getAttribute("totalImportPrice") != null && session.getAttribute("totalImportQuantity") != null) {
			int totalImportPrice = (int) session.getAttribute("totalImportPrice");
			int totalImportQuantity = (int) session.getAttribute("totalImportQuantity");
		}

		List<ImportBillDetailEntity> importBillDetailList = (List<ImportBillDetailEntity>) session
				.getAttribute("importBillDetailList");

		importBillDetailList.removeIf(i -> i.getProduct().getProductId() == productIdCanXoa);

		session.setAttribute("importBillDetailList", importBillDetailList);
		model.addAttribute("importBillDetailList", importBillDetailList);

		return "admin/importBill/createImportBill";
	}

	@RequestMapping(value = "/importBillManagement/updateQuantityProduct", method = RequestMethod.POST)
	// hàm cập nhát số lượng sản phẩm và giá nhập
	public String updateQuantity(Model model, HttpSession session, @RequestParam("productId") List<Integer> productId,
			@RequestParam("quantity") List<Integer> quantities,
			@RequestParam("importPrice") List<Integer> importPrices) {

		// lấy ra providerID được chọn
		int providerId = (int) session.getAttribute("adminSelectedProviderId");
		// láy ds provider để hiển thị ra select
		List<ProviderEntity> providers = this.providerService.getListProvider();
		model.addAttribute("providers", providers);

		// lấy danh sách sản phẩm ứng với provider
		List<ProductEntity> products = this.productService.getProductsByProviderId(providerId);
		model.addAttribute("products", products);

		// Danh sách lỗi
		List<String> errors = new ArrayList<>();

		// Kiểm tra từng phần tử trong `quantities` và `importPrices`
		for (int i = 0; i < quantities.size(); i++) {
			if (quantities.get(i) <= 0 || importPrices.get(i) <= 0) {
				errors.add("Số lượng và giá nhập tại dòng " + (i + 1) + " phải lớn hơn 0.");
			}
		}

		// Nếu có lỗi, quay lại trang nhập và hiển thị lỗi
		if (!errors.isEmpty()) {
			model.addAttribute("errors", errors);

			// Lấy lại danh sách chi tiết hóa đơn để hiển thị
			List<ImportBillDetailEntity> importBillDetailList = (List<ImportBillDetailEntity>) session
					.getAttribute("importBillDetailList");
			model.addAttribute("importBillDetailList", importBillDetailList);

			return "admin/importBill/createImportBill";
		}

		// tính tổng tiền và số lượng hàng hóa của hóa đơn nhập hàng
		int totalImportPrice = 0;
		int totalImportQuantity = 0;

		// lấy dữ liệu trong table thêm vào importBillDetail
		List<ImportBillDetailEntity> importBillDetailList = (List<ImportBillDetailEntity>) session
				.getAttribute("importBillDetailList");
		for (int i = 0; i < importBillDetailList.size(); i++) {
			importBillDetailList.get(i).setQuantity(quantities.get(i));
			importBillDetailList.get(i).setPrice(importPrices.get(i));
			totalImportPrice += quantities.get(i) * importPrices.get(i);
			totalImportQuantity += quantities.get(i);
		}

		session.setAttribute("importBillDetailList", importBillDetailList);
		model.addAttribute("importBillDetailList", importBillDetailList);
		session.setAttribute("totalImportPrice", totalImportPrice);
		model.addAttribute("totalImportPrice", totalImportPrice);
		session.setAttribute("totalImportQuantity", totalImportQuantity);
		model.addAttribute("totalImportQuantity", totalImportQuantity);

		session.setAttribute("quantities", quantities);

		return "admin/importBill/createImportBill";
	}

	@RequestMapping(value = "/importBillManagement/successImportBill", method = RequestMethod.POST)
	// click vào nút successImportBill để hoàn thành thêm importBill
	public String successImportBill(Model model, HttpSession session) {

		// lấy provider
		if (session.getAttribute("adminSelectedProviderId") == null) {
			model.addAttribute("errors", "Vui lòng chọn nhà cung cấp");
			// láy ds provider để hiển thị ra select
			List<ProviderEntity> providers = this.providerService.getListProvider();
			model.addAttribute("providers", providers);
			return "admin/importBill/createImportBill";
		} else {
			int providerId = (int) session.getAttribute("adminSelectedProviderId");
			if (providerId == 0) {
				model.addAttribute("errors", "Vui lòng chọn nhà cung cấp");
				// láy ds provider để hiển thị ra select
				List<ProviderEntity> providers = this.providerService.getListProvider();
				model.addAttribute("providers", providers);
				return "admin/importBill/createImportBill";
			}
		}
		if (session.getAttribute("totalImportPrice") == null) {
			model.addAttribute("errors", "Vui lòng chọn sản phẩm");
			// láy ds provider để hiển thị ra select
			List<ProviderEntity> providers = this.providerService.getListProvider();
			model.addAttribute("providers", providers);
			return "admin/importBill/createImportBill";
		}
		if (session.getAttribute("totalImportQuantity") == null) {
			model.addAttribute("errors", "Vui lòng chọn sản phẩm");
			// láy ds provider để hiển thị ra select
			List<ProviderEntity> providers = this.providerService.getListProvider();
			model.addAttribute("providers", providers);
			return "admin/importBill/createImportBill";
		}
		if (session.getAttribute("quantities") == null) {
			model.addAttribute("errors", "Vui lòng chọn sản phẩm");
			// láy ds provider để hiển thị ra select
			List<ProviderEntity> providers = this.providerService.getListProvider();
			model.addAttribute("providers", providers);
			return "admin/importBill/createImportBill";
		}
		if (session.getAttribute("importBillDetailList") == null) {
			model.addAttribute("errors", "Vui lòng chọn sản phẩm");
			// láy ds provider để hiển thị ra select
			List<ProviderEntity> providers = this.providerService.getListProvider();
			model.addAttribute("providers", providers);
			return "admin/importBill/createImportBill";
		}
		int providerId = (int) session.getAttribute("adminSelectedProviderId");
		ProviderEntity provider = this.providerService.getProviderById(providerId);
		// lấy account
		AccountEntity loggedInUser = (AccountEntity) session.getAttribute("loggedInUser");
		if (loggedInUser != null) {
			System.out.println("AccountId: " + loggedInUser.getAccountId());
		}
		// lấy tổng tiên và số lượng hàng hóa của hóa đơn nhập hàng
		int totalImportPrice = (int) session.getAttribute("totalImportPrice");
		int totalImportQuantity = (int) session.getAttribute("totalImportQuantity");

		// lấy số lượng từng sản phẩm trong importBillDetail
		List<Integer> quantities = (List<Integer>) session.getAttribute("quantities");

		// lấy ngày tạo importBill
		Date currentDate = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String formattedDate = dateFormat.format(currentDate);
		Date formattedDateObject = new Date();
		try {
			formattedDateObject = dateFormat.parse(formattedDate);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// lấy importBillDetail
		List<ImportBillDetailEntity> importBillDetailList = (List<ImportBillDetailEntity>) session
				.getAttribute("importBillDetailList");

		ImportBillEntity importBill = new ImportBillEntity(totalImportPrice, totalImportQuantity, formattedDateObject,
				loggedInUser);
		int resultAddImportBill = this.importBillService.addImportBill(importBill);
		if (resultAddImportBill == 0) {
			System.out.println("Thêm importBill thất bại");
		} else {
			System.out.println("Thêm importBill thành công");
		}

		for (ImportBillDetailEntity importBillDetailEntity : importBillDetailList) {
			importBillDetailEntity.setImportBill(importBill);
			this.importBillDetailService.addImportBillDetail(importBillDetailEntity);
		}

		for (int i = 0; i < importBillDetailList.size(); i++) {
			this.productService.updateQuantityProduct(importBillDetailList.get(i).getProduct().getProductId(),
					quantities.get(i));
		}

		importBillDetailList = null;
		totalImportPrice = 0;
		totalImportQuantity = 0;
		// adminSelectedProviderId = 0;
		int adminSelectedProviderId = 0;
		session.setAttribute("importBillDetailList", importBillDetailList);
		model.addAttribute("importBillDetailList", importBillDetailList);
		session.setAttribute("totalImportPrice", totalImportPrice);
		model.addAttribute("totalImportPrice", totalImportPrice);
		session.setAttribute("totalImportQuantity", totalImportQuantity);
		model.addAttribute("totalImportQuantity", totalImportQuantity);
		session.setAttribute("adminSelectedProviderId", adminSelectedProviderId);

		return "redirect:/admin/importBillManagement.htm"; // Redirect về trang quản lý importBill
	}

}