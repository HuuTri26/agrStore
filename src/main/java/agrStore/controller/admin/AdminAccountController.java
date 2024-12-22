package agrStore.controller.admin;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import agrStore.entity.CategoryEntity;
import agrStore.entity.DistrictEntity;
import agrStore.entity.ProductEntity;
import agrStore.entity.ProviderEntity;
import agrStore.entity.ProvinceEntity;
import agrStore.entity.RoleEntity;
import agrStore.entity.WardEntity;
import agrStore.service.AccountService;
import agrStore.service.AddressService;
import agrStore.service.DistrictService;
import agrStore.service.ProvinceService;
import agrStore.service.RoleService;
import agrStore.service.WardService;
import agrStore.utility.Ultility;
import agrStore.utility.UltilityImpl;

@Controller
@RequestMapping("/admin")
public class AdminAccountController {
	
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
	
	@RequestMapping("/adminProfile")
	public String adminProfile(HttpServletRequest request, @RequestParam(value = "provinceId", required = false) Integer provinceId,
			@RequestParam(value = "districtId", required = false) Integer districtId,
			@RequestParam(value = "wardId", required = false) Integer wardId,
			@RequestParam(value = "id", required = false) Integer id, Model model ) {
		// code
		HttpSession session = request.getSession();
		AccountEntity loggedInUser = (AccountEntity) session.getAttribute("loggedInUser");
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
		AccountEntity profile = this.accountService.getAccountById(loggedInUser.getAccountId());
		profile.setFullName(UltilityImpl.XSSEscape4HTML(profile.getFullName()));
		profile.setPhoneNumber(UltilityImpl.XSSEscape4HTML(profile.getPhoneNumber()));
		model.addAttribute("profile", profile);
		return "admin/account/adminProfile";
	}
	// 
	@RequestMapping(value = "/adminProfile", params = "save", method = RequestMethod.POST)
	public String editProfile(HttpServletRequest request, Model model, SessionStatus sessionStatus) {

		HttpSession session = request.getSession();

		Boolean isValid = Boolean.TRUE;

		// Lấy các trường mà người dùng đc phép sửa
		String fullName = request.getParameter("fullName");
		String phoneNumber = request.getParameter("phoneNumber");
		String streetName = accountUltility.standardizeStreetName(request.getParameter("streetName"));

		// Lấy các dữ liệu từ session
		AccountEntity loggedInUser = (AccountEntity) session.getAttribute("loggedInUser");
		WardEntity selectedWard = (WardEntity) session.getAttribute("selectedWard");

		// Lưu address cũ
		AddressEntity oldAddr = loggedInUser.getAddress();

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
					loggedInUser.setAddress(existingAddr);
				} else { // Nếu address ko tồn tại trong CSDL
					System.out.println("==> Address not found, create new address for user's address!");
					// Tạo 1 address mà người dùng vừa nhập
					AddressEntity inputAddr = new AddressEntity();
					inputAddr.setStreetName(streetName);
					inputAddr.setWard(selectedWard);
					addressService.addAddress(inputAddr);
					loggedInUser.setAddress(inputAddr);
				}
			} catch (Exception e) {
				System.out.println("Error: User's address update failed!");
			}
		} else {
			System.out.println("==> Ward or street name not found, skipping address update!");
		}

		if (isValid) {

			try {
				loggedInUser.setFullName(accountUltility.standardizeName(fullName));
				loggedInUser.setPhoneNumber(phoneNumber);
				loggedInUser.setUpdateAt(new Date());
				accountService.updateAccount(loggedInUser);
				System.out.println("==> User's account updated successfully!");

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

				System.out.println("==> Clear model attributes");

			}
		}
		
		return "admin/account/adminProfile";
	}
	@RequestMapping("/adminChangePassword")
	public String adminChangePassword(HttpServletRequest request, HttpSession session,
			@CookieValue(value = "accountEmail", defaultValue = "", required = false) String userEmail, ModelMap model) {
		// code
		  model.addAttribute("changePass", new AccountEntity()); // Khởi tạo changePass
		//model.addAttribute("account", new AccountEntity());
		System.out.println("==> Open an admin change password session");
		return "admin/account/adminChangePassword";
	}
	
	@RequestMapping(value = "/adminChangePassword", method = RequestMethod.POST)
	public String adminShowChangePassword(HttpServletRequest request, @ModelAttribute("changePass") AccountEntity changePass,
			BindingResult errors,ModelMap model) {

		Boolean isValidPass = Boolean.TRUE;

		HttpSession session = request.getSession();
		AccountEntity loggedInUser = (AccountEntity) session.getAttribute("loggedInUser");
		String newPass = request.getParameter("new-password");
		String reEnterNewPass = request.getParameter("re-enter-new-password");

		System.out.println(loggedInUser.getRole().getName());

		if (changePass.getPassword().isEmpty()) {
			errors.rejectValue("password", "changePass", "Vui lòng nhập mật khẩu hiện tại!");
			isValidPass = Boolean.FALSE;
		} else if (newPass.isEmpty()) {
			model.addAttribute("newPass","Vui lòng nhập mật khẩu mới");
			isValidPass = Boolean.FALSE;
		} else if (reEnterNewPass.isEmpty()) {
			model.addAttribute("reNewPass","Vui lòng nhập lại mật khẩu mới");
			isValidPass = Boolean.FALSE;
		}

		if (!accountService.isExistAccount(loggedInUser.getGmail(),
				accountUltility.getHashPassword(changePass.getPassword())) && !changePass.getPassword().isEmpty()) {
			model.addAttribute("wrongPass","Sai mật khẩu !Vui lòng nhập lại mật khẩu mới");
			isValidPass = Boolean.FALSE;
		} else if (!newPass.equals(reEnterNewPass)) {
			model.addAttribute("reNewPass","Vui lòng nhập lại mật khẩu mới");
			isValidPass = Boolean.FALSE;
		}

		if (isValidPass) {
			try {
				loggedInUser.setPassword(accountUltility.getHashPassword(newPass));
				accountService.updateAccount(loggedInUser);
				System.out.println("==> Admin account password updated successfully!");
			} catch (Exception e) {
				System.out.println("Error: Admin account password updated unsuccessfully!");
			}
		} else {
			System.out.println("Error: Admin account password updated unsuccessfully!");
			 return "admin/account/adminChangePassword";
		}
		return "/admin/adminDashboard";
	}


}
