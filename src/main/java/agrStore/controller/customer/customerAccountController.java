package agrStore.controller.customer;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;

import agrStore.entity.AccountEntity;
import agrStore.entity.AddressEntity;
import agrStore.entity.DistrictEntity;
import agrStore.entity.ProvinceEntity;
import agrStore.entity.WardEntity;
import agrStore.service.AccountService;
import agrStore.service.AddressService;
import agrStore.service.DistrictService;
import agrStore.service.ProvinceService;
import agrStore.service.WardService;
import agrStore.utility.AccountUltility;

@Controller
public class customerAccountController {

	@Autowired
	AccountService accountService;

	@Autowired
	ProvinceService provinceService;

	@Autowired
	DistrictService districtService;

	@Autowired
	WardService wardService;

	@Autowired
	AddressService addressService;

	@Autowired
	AccountUltility accountUltility;

	@RequestMapping("/customerProfile")
	public String loadAddressSelection(@RequestParam(value = "provinceId", required = false) Integer provinceId,
			@RequestParam(value = "districtId", required = false) Integer districtId,
			@RequestParam(value = "wardId", required = false) Integer wardId, Model model, HttpServletRequest request) {

		HttpSession session = request.getSession();

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

		return "customer/account/customerProfile";
	}

	@RequestMapping(value = "/customerProfile", params = "save", method = RequestMethod.POST)
	public String saveNewAddress(HttpServletRequest request, Model model, SessionStatus sessionStatus) {
		HttpSession session = request.getSession();

		// Lấy các dữ liệu từ session
		AccountEntity loggedInUser = (AccountEntity) session.getAttribute("loggedInUser");
		WardEntity selectedWard = (WardEntity) session.getAttribute("selectedWard");

		Boolean isValid = Boolean.TRUE;

		// Lấy các trường mà người dùng đc phép sửa
		String fullName = request.getParameter("fullName");
		String phoneNumber = request.getParameter("phoneNumber");
		String streetName = request.getParameter("streetName");

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
		}

		if (isValid) {
			// Lưu address cũ
			AddressEntity oldAddr = loggedInUser.getAddress();
			AddressEntity existingAddr = addressService.getAddressByStreetAndWard(accountUltility.standardizeStreetName(streetName), selectedWard.getId());

			try {
				if (selectedWard != null) {
					// Tạo 1 address mà người dùng vừa nhập
					AddressEntity inputAddr = new AddressEntity();
					inputAddr.setStreetName(accountUltility.standardizeStreetName(streetName));
					inputAddr.setWard(selectedWard);

					if (inputAddr == oldAddr) { // Nếu address nhập trùng với address cũ
						System.out.println("==> Found duplicated address!");
					} else if (existingAddr != null) {
						System.out.println("==> Found existing address, set existing address to user's account!");
						loggedInUser.setAddress(existingAddr);
						
					}else { // Nếu address nhập ko tồn tại trong CSDL
						System.out.println("==> Address not found, create new address for user's account!");
						addressService.addAddress(inputAddr);
						loggedInUser.setAddress(inputAddr);
					}

					System.out.println("==> Set new address to account");
				}
				loggedInUser.setFullName(accountUltility.standardizeName(fullName));
				loggedInUser.setPhoneNumber(phoneNumber);

				accountService.updateAccount(loggedInUser);
				System.out.println("==> User's account updated successfully!");
				
				//Nếu 0 có account nào tham chiếu tới address cũ xóa address này
				if (accountService.countAccontByAddressId(oldAddr.getId()) == 0L) {
					System.out.println("==> Trying delete old address!");
					addressService.deleteAddress(oldAddr);
				}

				return "redirect:/index.htm";
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

		return "customer/account/customerProfile";
	}

	@RequestMapping("/customerChangePassword")
	public String customerChangePassword(HttpServletRequest request, HttpSession session,
			@CookieValue(value = "accountEmail", defaultValue = "", required = false) String userEmail) {
		// code

		return "customer/account/customerChangePassword";
	}

	@RequestMapping("/customerOrderList")
	public String customerOderList(HttpServletRequest request, HttpSession session,
			@CookieValue(value = "accountEmail", defaultValue = "", required = false) String userEmail) {
		// code

		return "customer/account/customerOrderList";
	}

	@RequestMapping("/customerOrderDetail")
	public String customerOrderDetail(HttpServletRequest request, HttpSession session,
			@CookieValue(value = "accountEmail", defaultValue = "", required = false) String userEmail) {
		// code

		return "customer/account/customerOrderDetail";
	}
}
