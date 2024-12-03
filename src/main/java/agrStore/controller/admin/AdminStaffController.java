package agrStore.controller.admin;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;

import agrStore.DAO.AddressDAO;
import agrStore.DAO.DistrictDAO;
import agrStore.DAO.ProvinceDAO;
import agrStore.DAO.WardDAO;
import agrStore.entity.AccountEntity;
import agrStore.entity.AddressEntity;
import agrStore.entity.CartEntity;
import agrStore.entity.CategoryEntity;
import agrStore.entity.DistrictEntity;
import agrStore.entity.ProductEntity;
import agrStore.entity.ProviderEntity;
import agrStore.entity.ProvinceEntity;
import agrStore.entity.RoleEntity;
import agrStore.entity.WardEntity;
import agrStore.service.AccountService;
import agrStore.service.AddressService;
import agrStore.service.WardService;
import agrStore.service.DistrictService;
import agrStore.service.ProvinceService;
import agrStore.service.RoleService;
import agrStore.serviceImpl.ProvinceServiceImpl;
import agrStore.utility.Ultility;

@Controller
@RequestMapping("/admin")
public class AdminStaffController {
	@Autowired
	private AccountService accountService;
	@Autowired
	private AddressDAO addressDAO;
	@Autowired
	private WardDAO wardDAO;
	@Autowired
	private DistrictDAO districtDAO;
	@Autowired
	private ProvinceDAO provinceDAO;
	@Autowired
	private AddressService addressService;
	@Autowired
	private WardService wardService;

	@Autowired
	private RoleService roleService;

	@Autowired
	private DistrictService districtService;

	@Autowired
	private ProvinceService provinceService;

	@Autowired
	Ultility accountUltility;

	@ModelAttribute("provinces")
	public List<ProvinceEntity> loadProvince() {
		List<ProvinceEntity> provinces = provinceService.getListProvinces();
		return provinces;
	}

	@RequestMapping("/staffManagement")
	public String staffManagement(HttpServletRequest request, HttpSession session, ModelMap model,
			@CookieValue(value = "accountEmail", defaultValue = "", required = false) String userEmail) {
		// code
		model.addAttribute("currentPage", "staff");
		List<AccountEntity> staffList = this.accountService.getAllStaff();
		model.addAttribute("staffs", staffList);
		return "admin/staff/staffManagement";
	}

	@RequestMapping("/staffManagement/staff")
	public String adminProfile(HttpServletRequest request,
			@RequestParam(value = "action", required = false) String action,
			@RequestParam(value = "provinceId", required = false) Integer provinceId,
			@RequestParam(value = "districtId", required = false) Integer districtId,
			@RequestParam(value = "wardId", required = false) Integer wardId,
			@RequestParam(value = "id", required = false) Integer id, Model model) {
		// code
		HttpSession session = request.getSession();
		AccountEntity staff = this.accountService.getAccountById(id); // Lấy dữ liệu provider theo id
		// Lấy ds các tỉnh
		List<ProvinceEntity> provinces = provinceService.getListProvinces();
		model.addAttribute("provinces", provinces);

		// Nếu người dùng đã chọn tỉnh, lấy danh sách quận load ra view
		if (provinceId != null) {
			System.out.println("==> ProvinceId: " + provinceId);
			List<DistrictEntity> districts = districtService.getListDistrictsByProvinceId(provinceId);
			model.addAttribute("districts", districts);
			model.addAttribute("selectedProvinceId", provinceId);

			// Cập nhật tên tỉnh đã chọn
			ProvinceEntity selectedProvince = provinceService.getProvinceById(provinceId);

			// Lưu tỉnh vừa chọn vào session
			session.setAttribute("selectedProvince", selectedProvince);
		}

		// Nếu ng dùng đã chọn quận, lấy danh sách phường load ra view
		if (districtId != null) {
			System.out.println("==> DistrictId: " + districtId);
			List<WardEntity> wards = wardService.getListWardByDistrictId(districtId);
			model.addAttribute("wards", wards);
			model.addAttribute("selectedDistrictId", districtId);

			// Cập nhật tên quận đã chọn
			DistrictEntity selectedDistrict = districtService.getDistrictById(districtId);

			// Lưu quận vừa chọn vào session
			session.setAttribute("selectedDistrict", selectedDistrict);
		}

		// Nếu người dùng đã chọn phường, cập nhật tên phường
		if (wardId != null) {
			System.out.println("==> WardId: " + wardId);
			WardEntity selectedWard = wardService.getWardById(wardId);

			// Lưu phường vừa chọn vào session
			session.setAttribute("selectedWard", selectedWard);
		}
		if (action != null) {
			switch (action) {
			case "view":
				if (id != null) {
					// Category category = categoryService.getCategoryById(id);
					System.out.println("==> View Staff mode");
					model.addAttribute("mode", "VIEW");
					model.addAttribute("staff", staff);
					//session.setAttribute("staff", staff);
					// model.addAttribute("category", category);
				}
				break;

			case "edit":
				if (id != null) {
					// Category category = categoryService.getCategoryById(id);
					System.out.println("==> Edit Staff mode");
					model.addAttribute("mode", "EDIT");
				
					session.setAttribute("staff", staff);
					// model.addAttribute("category", category);
				}
				break;
			}
		}

	
		//model.addAttribute("staff", staff);
		return "admin/staff/staffForm";
	}

	//
	@RequestMapping(value = "/staffManagement/staff", params = "EDIT", method = RequestMethod.POST)
	public String editProfile(HttpServletRequest request, Model model, SessionStatus sessionStatus) {

		HttpSession session = request.getSession();

		Boolean isValid = Boolean.TRUE;

		// Lấy các trường mà người dùng đc phép sửa
		String fullName = request.getParameter("fullName");
		String phoneNumber = request.getParameter("phoneNumber");
		String streetName = accountUltility.standardizeStreetName(request.getParameter("streetName"));

		// Lấy các dữ liệu từ session
		AccountEntity staff = (AccountEntity) session.getAttribute("staff");
		WardEntity selectedWard = (WardEntity) session.getAttribute("selectedWard");

		// Lưu address cũ
		AddressEntity oldAddr = staff.getAddress();

		if (fullName.isEmpty()) {
			model.addAttribute("nameErr", "Vui lòng nhập họ và tên!");
			isValid = Boolean.FALSE;
			System.out.println("Error: Name field empty!");
		} else if (phoneNumber.isEmpty()) {
			model.addAttribute("phoneErr", "Vui lòng nhập số điện thoại của bạn!");
			isValid = Boolean.FALSE;
			System.out.println("Error: Phone number field empty!");
		} else if (!accountUltility.isValidPhoneNumber(phoneNumber)) {
			model.addAttribute("phoneErr", "Số điện thoại bạn nhập không hợp lệ, vui lòng nhập lại!");
			isValid = Boolean.FALSE;
			System.out.println("Error: Phone number invalid!");
		} else if (!streetName.isEmpty() && !accountUltility.isValidStreetName(streetName)) {
			model.addAttribute("streetErr", "Tên đường không hợp lệ, vui lòng nhập lại!");
			isValid = Boolean.FALSE;
			System.out.println("Error: Street name invalid!");
		}

		if (selectedWard != null && !streetName.isEmpty()) {
			System.out.println("==> Found new ward, begin update new address!");
			// Kiểm tra địa chỉ có tồn tại trong CSDL?
			AddressEntity existingAddr = addressService.getAddressByStreetAndWard(streetName, selectedWard.getId());
			try {
				if (existingAddr != null) { // Nếu address có tồn tại trong CSDL
					System.out.println("==> Found existing address, set existing address to user's account!");
					staff.setAddress(existingAddr);
				} else { // Nếu address ko tồn tại trong CSDL
					System.out.println("==> Address not found, create new address for user's address!");
					// Tạo 1 address mà người dùng vừa nhập
					AddressEntity inputAddr = new AddressEntity();
					inputAddr.setStreetName(streetName);
					inputAddr.setWard(selectedWard);
					addressService.addAddress(inputAddr);
					staff.setAddress(inputAddr);
				}
			} catch (Exception e) {
				System.out.println("Error: User's address update failed!");
			}
		} else {
			System.out.println("==> Ward or street name not found, skipping address update!");
		}

		if (isValid) {

			try {
				staff.setFullName(accountUltility.standardizeName(fullName));
				staff.setPhoneNumber(phoneNumber);
				staff.setUpdateAt(new Date());
				accountService.updateAccount(staff);
				System.out.println("==> Staff's account updated successfully!");

				// Nếu 0 có account nào tham chiếu tới address cũ xóa address này
				if (accountService.countAccontByAddressId(oldAddr.getId()) == 0L) {
					addressService.deleteAddress(oldAddr);
					System.out.println("==> Old address deleted!");
				}

				return "redirect:/admin/adminDashboard.htm";
			} catch (Exception e) {
				System.out.println("Error: User's account updated failed!");

			} finally {
				// Giải phóng dữ liệu của model attributes
				sessionStatus.setComplete();
				session.removeAttribute("selectedProvince");
				session.removeAttribute("selectedDistrict");
				session.removeAttribute("selectedWard");
				session.removeAttribute("staff");

				System.out.println("==> Clear model attributes");

			}
		}

		return "admin/staff/staffForm";
	}

	@RequestMapping("/staffManagement/addStaff")
	public String handleStaff(@RequestParam(value = "provinceId", required = false) Integer provinceId,
			@RequestParam(value = "districtId", required = false) Integer districtId,
			@RequestParam(value = "wardId", required = false) Integer wardId, Model model, HttpServletRequest request) {

		HttpSession session = request.getSession();

		// Lấy ds các tỉnh
		List<ProvinceEntity> provinces = provinceService.getListProvinces();
		model.addAttribute("provinces", provinces);
		System.out.println("==> ProvinceId: " + provinceId);
		System.out.println("==> DistricyId: " + districtId);
		System.out.println("==> WardId: " + wardId);
		// Nếu người dùng đã chọn tỉnh, lấy danh sách quận load ra view
		if (provinceId != null) {
			System.out.println("==> ProvinceId: " + provinceId);
			List<DistrictEntity> districts = districtService.getListDistrictsByProvinceId(provinceId);
			model.addAttribute("districts", districts);
			model.addAttribute("selectedProvinceId", provinceId);

			// Cập nhật tên tỉnh đã chọn
			ProvinceEntity selectedProvince = provinceService.getProvinceById(provinceId);

			// Lưu tỉnh vừa chọn vào session
			session.setAttribute("selectedProvince", selectedProvince);
		}

		// Nếu ng dùng đã chọn quận, lấy danh sách phường load ra view
		if (districtId != null) {
			System.out.println("==> DistrictId: " + districtId);
			List<WardEntity> wards = wardService.getListWardByDistrictId(districtId);
			model.addAttribute("wards", wards);
			model.addAttribute("selectedDistrictId", districtId);

			// Cập nhật tên quận đã chọn
			DistrictEntity selectedDistrict = districtService.getDistrictById(districtId);

			// Lưu quận vừa chọn vào session
			session.setAttribute("selectedDistrict", selectedDistrict);
		}

		// Nếu người dùng đã chọn phường, cập nhật tên phường
		if (wardId != null) {
			System.out.println("==> WardId: " + wardId);
			WardEntity selectedWard = wardService.getWardById(wardId);

			// Lưu phường vừa chọn vào session
			session.setAttribute("selectedWard", selectedWard);
		}
		System.out.println("==> Add staff mode");
		model.addAttribute("mode", "ADD");
		// model.addAttribute("staff", new AccountEntity());
		// session.setAttribute("account", account);
		// model.addAttribute("category", new Category());

		return "admin/staff/addStaff"; // Trả về cùng một trang JSP
	}

	/*
	 * @RequestMapping("/getDistricts") public List<DistrictEntity>
	 * getDistricts(@RequestParam("address.ward.district.province.id") Integer
	 * provinceId) { return
	 * districtService.getListDistrictsByProvinceId(provinceId); }
	 * 
	 * @RequestMapping("/getWards") public List<WardEntity>
	 * getWards(@RequestParam("address.ward.district.id") Integer districtId) {
	 * return wardService.getListWardByDistrictId(districtId); }
	 */

	@RequestMapping(value = "/staffManagement/addStaff", params = "add-staff", method = RequestMethod.POST)
	public String addStaff(HttpServletRequest request, Model model, SessionStatus sessionStatus) {
		AccountEntity account = new AccountEntity();
		HttpSession session = request.getSession();

		// Lấy các dữ liệu được trong session
		WardEntity selectedWard = (WardEntity) session.getAttribute("selectedWard");

		// Lấy các field thông tin đăng ký
		String gmail = request.getParameter("gmail");
		// String password = request.getParameter("password");
		// String reEnterPassword = request.getParameter("re-enter-password");
		String fullName = request.getParameter("full-name");
		String phoneNumber = request.getParameter("phone-number");
		String streetName = request.getParameter("streetName");

		Boolean isValid = Boolean.TRUE;
		if (!accountUltility.isValidGmail(gmail) || gmail.isEmpty()
				|| accountService.getAccountByGmail(gmail) != null) {
			model.addAttribute("gmailErr", "Gmail tồn tại , sai hoặc bị bỏ trống , vui lòng nhập gmail!");
			isValid = Boolean.FALSE;
		}
		/*
		 * else if (password.isEmpty()) { model.addAttribute("passErr",
		 * "Vui lòng nhập mật khẩu!"); isValid = Boolean.FALSE;
		 * System.out.println("Error: Password field empty!"); } else if
		 * (reEnterPassword.isEmpty()) { model.addAttribute("rePassErr",
		 * "Vui lòng nhập lại mật khẩu!"); isValid = Boolean.FALSE;
		 * System.out.println("Error: Re enter password empty!"); } else if
		 * (!password.equals(reEnterPassword)) { model.addAttribute("rePassErr",
		 * "Mật khẩu không khớp, vui lòng nhập lại!"); isValid = Boolean.FALSE; }
		 */
		else if (fullName.isEmpty()) {
			model.addAttribute("nameErr", "Vui lòng nhập họ và tên!");
			isValid = Boolean.FALSE;
			System.out.println("Error: Name field empty!");
		} else if (phoneNumber.isEmpty()) {
			model.addAttribute("phoneErr", "Vui lòng nhập số điện thoại của bạn!");
			isValid = Boolean.FALSE;
			System.out.println("Error: Phone number field empty!");
		} else if (!accountUltility.isValidPhoneNumber(phoneNumber)) {
			model.addAttribute("phoneErr", "Số điện thoại bạn nhập không hợp lệ, vui lòng nhập lại!");
			isValid = Boolean.FALSE;
			System.out.println("Error: Phone number invalid!");
		} else if (streetName.isEmpty()) {
			model.addAttribute("streetErr", "Tên đường không được phép để trống!");
			isValid = Boolean.FALSE;
			System.out.println("Error: Street name field empty!");
		}
		/*
		 * else if (!accountUltility.isValidStreetName(streetName)) {
		 * model.addAttribute("streetErr",
		 * "Tên đường không hợp lệ, vui lòng nhập lại!"); isValid = Boolean.FALSE;
		 * System.out.println("Error: Street name invalid!"); }
		 */

		if (isValid) {
			AddressEntity address = addressService
					.getAddressByStreetAndWard(accountUltility.standardizeStreetName(streetName), selectedWard.getId());

			// Kiểm tra address này đã tồn tại trong hệ thống hay ko?
			if (address != null) {
				// Nếu có tìm thấy gán address_t này cho account
				System.out.println("==> Existing address found, set this address to user's account!");
				account.setAddress(address);
			} else {
				// Nếu ko tìm thấy, tạo address mới cho user's account
				System.out.println("==> Address not found, create new address for user's account!");
				try {
					AddressEntity newAddr = new AddressEntity();

					newAddr.setWard(selectedWard);
					newAddr.setStreetName(accountUltility.standardizeStreetName(streetName));

					addressService.addAddress(newAddr);

					account.setAddress(newAddr);
					System.out.println("==> User's address created successfully!");

				} catch (Exception e) {
					System.out.println("Error: User's address created failed!");
				}
			}

			// Tạo account mới với role là customer
			System.out.println("==> Create new user's account");
			RoleEntity staffRole = roleService.getRoleById(2);
			if (staffRole != null) {
				try {
					account.setGmail(gmail);
					account.setPassword(accountUltility.getHashPassword("123"));
					account.setFullName(accountUltility.standardizeName(fullName));
					account.setPhoneNumber(phoneNumber);
					account.setStatus(Boolean.TRUE);
					account.setCreateAt(new Date());
					account.setUpdateAt(new Date());
					account.setRole(staffRole);

					accountService.addAccount(account);

					System.out.println("==> Staff's account created successfully!");

					return "redirect:/admin/staffManagement.htm";
				} catch (Exception e) {
					System.out.println("Error: Staff's account created failed!");
				} finally {

					// Giải phóng dữ liệu của session
					// request.getSession().invalidate();
					session.removeAttribute("selectedWard");
					session.removeAttribute("selectedProvince");
					session.removeAttribute("selectedSisstrict");
					System.out.println("==> Invalidate session's data");

					// Giải phóng dữ liệu của model attributes
					sessionStatus.setComplete();
					System.out.println("==> Clear model attributes");

					System.out.println("End sign up session");
				}

			} else {
				// Thêm thông báo chi tiết khi không tìm thấy RoleEntity
				System.out.println("Error: Role 2 not found in the database. User account cannot be created!");
			}
		}

		return "admin/staff/addStaff";

	}

	@RequestMapping(value = "/staffManagement/disableStaff", method = RequestMethod.GET)
	public String disableStaff(@RequestParam("id") Integer id) {

		AccountEntity staff = accountService.getAccountById(id);

		try {
			if (staff.getStatus() == Boolean.TRUE) {
				staff.setStatus(Boolean.FALSE);
			} else {
				staff.setStatus(Boolean.TRUE);

			}

			accountService.updateAccount(staff);
			System.out.println("==> Switch staff status to  successfully!");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("==> Switch staff status to  failed!");
		}

		return "redirect:/admin/staffManagement.htm";
	}

	/*
	 * @RequestMapping(value = "/staffManagement/staff", method =
	 * RequestMethod.POST) public String processStaff( // @ModelAttribute("staff")
	 * StaffEntity category,
	 * 
	 * @RequestParam("mode") String mode) {
	 * 
	 * if ("ADD".equals(mode)) { // categoryService.addCategory(category); } else if
	 * ("EDIT".equals(mode)) { // categoryService.updateCategory(category); }
	 * 
	 * return "redirect:/admin/staff/staffManagement.htm"; // Redirect sau khi xử lý
	 * }
	 */
}
