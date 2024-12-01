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
	public String importBillManagement(HttpServletRequest request, HttpSession session, ModelMap model,
			@CookieValue(value = "accountEmail", defaultValue = "", required = false) String userEmail) {
		// code
		model.addAttribute("currentPage", "importBill");
		List<ImportBillEntity> importBillEntities = this.importBillService.getAllImportBill();
		model.addAttribute("importBills", importBillEntities);
//		AccountEntity loggedInUser = (AccountEntity) session.getAttribute("loggedInUser");
//		if (loggedInUser != null) {
//			System.out.println("Gmail: " + loggedInUser.getGmail());
//		} else {
//			System.out.println("User not logged in!");
//		}
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
//				List<ProductEntity> products = this.productService.getProductsByProviderId(1);
//				model.addAttribute("products", products);

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

	/*
	 * @RequestMapping(value =
	 * "/importBillManagement/getProductsByProvider/{providerId}", method =
	 * RequestMethod.POST) public String getProductsByProviderId(ModelMap
	 * model, @PathVariable("providerId") Integer providerId) { List<ProviderEntity>
	 * providers = this.providerService.getListProvider();
	 * model.addAttribute("providers", providers);
	 * 
	 * model.addAttribute("selectedProviderId", providerId);
	 * 
	 * List<ProductEntity> products =
	 * this.productService.getProductsByProviderId(providerId);
	 * model.addAttribute("products", products);
	 * 
	 * return "admin/importBill/createImportBill"; }
	 */

	/*
	 * @RequestMapping(value = "/importBillManagement/getTempProducts", method =
	 * RequestMethod.POST) public String getTempProducts(ModelMap
	 * model, @RequestBody String selectedProducts) {
	 * 
	 * // bỏ List<ProviderEntity> providers =
	 * this.providerService.getListProvider(); model.addAttribute("providers",
	 * providers);
	 * 
	 * // Chuyển tù JSON sang ArrayList if (!selectedProducts.equals("")) {
	 * System.out.println("Khác rỗng"); String cleaned =
	 * selectedProducts.replace("[", "").replace("]", "").replace("\"", ""); // Tách
	 * chuỗi thành mảng String[] stringArray = cleaned.split(","); // Chuyển mảng
	 * String thành ArrayList<Integer> ArrayList<Integer> integerList = new
	 * ArrayList<>(); for (String s : stringArray) {
	 * integerList.add(Integer.parseInt(s)); } System.out.println(integerList); //
	 * Output: [1, 2, 3]
	 * 
	 * List<ProductEntity> selectedProductList = new ArrayList<ProductEntity>();
	 * ProductEntity temp = null; for (Integer i : integerList) { temp =
	 * this.productService.getProductById(i); selectedProductList.add(temp); } for
	 * (ProductEntity productEntity : selectedProductList) {
	 * System.out.println(productEntity.getProductName() + ", " +
	 * productEntity.getPrice()); } model.addAttribute("selectedProductList",
	 * selectedProductList); } else { System.out.println("Đầu vào rỗng"); }
	 * 
	 * return "admin/importBill/createImportBill"; }
	 */

//	@RequestMapping(value = "/importBillManagement/getSelectedProducts", method = RequestMethod.POST)
//	// hàm lấy danh sách sản phẩm đã được chọn khi click vào nút GET
//	public String getSelectedProducts(@RequestParam("selectedProductIds") List<Integer> selectedProductIds, Model model,
//			HttpSession session) {
//
//		List<ProductEntity> selectedProductList = new ArrayList<ProductEntity>();
//		int providerId = (int) session.getAttribute("adminSelectedProviderId");
//		// láy ds provider để hiển thị ra select
//		List<ProviderEntity> providers = this.providerService.getListProvider();
//		model.addAttribute("providers", providers);
//
//		// lấy danh sách sản phẩm ứng với providerId
//		List<ProductEntity> products = this.productService.getProductsByProviderId(providerId);
//		model.addAttribute("products", products);
//
//		ProductEntity temp = null;
//		for (Integer i : selectedProductIds) {
//			temp = this.productService.getProductById(i);
//			selectedProductList.add(temp);
//		}
//		session.setAttribute("selectedProductIds", selectedProductIds);
//		model.addAttribute("selectedProductList", selectedProductList);
//
//		return "admin/importBill/createImportBill";
//	}

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

//	@RequestMapping(value = "/importBillManagement/getSelectedProductsAfterDelete", method = RequestMethod.GET)
//	// hàm lấy ra danh sách sản phẩm sau khi ta click nút xóa
//	public String getSelectedProductsAfterDelete(Model model, HttpSession session,
//			@RequestParam(value = "productId", required = false) Integer productIdCanXoa) {
//		List<Integer> selectedProductIds = (List<Integer>) session.getAttribute("selectedProductIds");
//		List<ProductEntity> selectedProductList = new ArrayList<ProductEntity>();
//		int providerId = (int) session.getAttribute("adminSelectedProviderId");
//		// láy ds provider để hiển thị ra select
//		List<ProviderEntity> providers = this.providerService.getListProvider();
//		model.addAttribute("providers", providers);
//		// lấy danh sách sản phẩm ứng với providerId
//		List<ProductEntity> products = this.productService.getProductsByProviderId(providerId);
//		model.addAttribute("products", products);
//
//		ProductEntity temp = null;
//		for (Integer i : selectedProductIds) {
//			temp = this.productService.getProductById(i);
//			selectedProductList.add(temp);
//		}
//		selectedProductList.removeIf(product -> product.getProductId() == productIdCanXoa);
//		selectedProductIds.removeIf(i -> i == productIdCanXoa);
//
//		session.setAttribute("selectedProductIds", selectedProductIds);
//
//		model.addAttribute("selectedProductList", selectedProductList);
//
//		return "admin/importBill/createImportBill";
//	}

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
		int totalImportPrice = (int) session.getAttribute("totalImportPrice");
		int totalImportQuantity = (int) session.getAttribute("totalImportQuantity");

		List<ImportBillDetailEntity> importBillDetailList = (List<ImportBillDetailEntity>) session
				.getAttribute("importBillDetailList");

		/*
		 * ProductEntity temp = null; for (Integer i : selectedProductIds) { temp =
		 * this.productService.getProductById(i); selectedProductList.add(temp); }
		 */

		importBillDetailList.removeIf(i -> i.getProduct().getProductId() == productIdCanXoa);
		// selectedProductIds.removeIf(i -> i == productIdCanXoa);

		// session.setAttribute("selectedProductIds", selectedProductIds);
		// model.addAttribute("selectedProductList", selectedProductList);

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

		/*
		 * List<Integer> selectedProductIds = (List<Integer>)
		 * session.getAttribute("selectedProductIds"); List<ProductEntity>
		 * selectedProductList = new ArrayList<ProductEntity>(); ProductEntity temp =
		 * null; for (Integer i : selectedProductIds) { temp =
		 * this.productService.getProductById(i); selectedProductList.add(temp); }
		 * session.setAttribute("selectedProductIds", selectedProductIds);
		 * model.addAttribute("selectedProductList", selectedProductList);
		 */

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
		session.setAttribute("importBillDetailList", importBillDetailList);
		model.addAttribute("importBillDetailList", importBillDetailList);
		session.setAttribute("totalImportPrice", totalImportPrice);
		model.addAttribute("totalImportPrice", totalImportPrice);
		session.setAttribute("totalImportQuantity", totalImportQuantity);
		model.addAttribute("totalImportQuantity", totalImportQuantity);

		return "redirect:/importBillManagement.htm"; // Redirect về trang quản lý importBill
	}

	@RequestMapping(value = "/importBillManagement/importBill", method = RequestMethod.POST)
	public String processImportBill(@ModelAttribute("importBill") @RequestParam("mode") String mode) {

		if ("CREATE".equals(mode)) {
			// categoryService.addCategory(category);

		} else if ("EDIT".equals(mode)) {
			// categoryService.updateCategory(category);
		}

		return "redirect:/admin/importBill/importBillManagement.htm"; // Redirect sau khi xử lý
	}

}