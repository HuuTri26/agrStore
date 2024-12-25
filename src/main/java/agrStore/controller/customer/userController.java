package agrStore.controller.customer;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;

import agrStore.bean.Mailer;
import agrStore.entity.AccountEntity;
import agrStore.entity.AddressEntity;
import agrStore.entity.CartEntity;
import agrStore.entity.DistrictEntity;
import agrStore.entity.ProvinceEntity;
import agrStore.entity.RoleEntity;
import agrStore.entity.WardEntity;
import agrStore.recaptcha.RecaptchaVerification;

import agrStore.utility.ServerLogger;

import agrStore.utility.LogginAttempManager;

import agrStore.utility.Ultility;
import agrStore.utility.UltilityImpl;
import agrStore.service.AccountService;
import agrStore.service.AddressService;
import agrStore.service.CartService;
import agrStore.service.DatabaseRoutingService;
import agrStore.service.DistrictService;
import agrStore.service.ProvinceService;
import agrStore.service.RoleService;
import agrStore.service.WardService;

@Controller
@RequestMapping("/user")
public class userController {

	@Autowired
	Ultility accountUltility;

	@Autowired
	Mailer mailer;

	@Autowired
	AccountService accountService;

	@Autowired
	ProvinceService provinceService;

	@Autowired
	DistrictService districtService;

	@Autowired
	WardService wardService;

	@Autowired
	RoleService roleService;

	@Autowired
	AddressService addressService;

	@Autowired
	CartService cartService;

	@Autowired
	DatabaseRoutingService databaseRoutingService;
	
	@Autowired
	LogginAttempManager logginAttempManager;

	@RequestMapping("/userLogin")
	public String showUserLoginForm(Model model) {
		model.addAttribute("account", new AccountEntity());
		System.out.println("==> Begin login session");
		return "customer/login/userLogin";
	}

	@RequestMapping(value = "/userLogin", method = RequestMethod.POST)
	public String userLogin(ModelMap model, HttpServletRequest request,
			@ModelAttribute("account") AccountEntity account, BindingResult errors, HttpSession session)
			throws IOException {
		// Bỏ comment tất cả đoạn này để chạy đc reCaptcha

//		// Lấy phản hồi của Google reCaptcha
//		String gRecaptchaResponse = request.getParameter("g-recaptcha-response");
//
//		// Lấy các mã captcha khi tạo ra cái ảnh
//		String captcha = session.getAttribute("captchaSecurity").toString();
//
//		// Lấy captcha do người dùng nhập
//		String captchaInput = request.getParameter("captcha-input");
//
//		System.out.println("==> Captcha code use for this sesion: "+ captcha);
//
//		// Xác minh ReCaptcha của Google
//		Boolean isVerify = RecaptchaVerification.verify(gRecaptchaResponse);
//
//		// Xác minh Captcha bằng hình ảnh
//		Boolean isMatch = captcha != null && captcha.equals(captchaInput);
//
//		if (!isVerify || !isMatch) {
//	        // Nếu reCAPTCHA hoặc ảnh CAPTCHA không đúng
//	        if (!isVerify) {
//	            System.out.println("Error: Google ReCaptcha verification failed!");
//	            model.addAttribute("reCaptcha", "Vui lòng nhập đúng ReCaptcha!");
//	        }
//	        if (!isMatch) {
//	            System.out.println("Error: Wrong image captcha code!");
//	            model.addAttribute("reCaptcha", "Vui lòng nhập đúng ReCaptcha!");
//	        }
//	        return "customer/login/userLogin";
//	    }
		
		 // Check if account is currently locked
	    if (logginAttempManager.isAccountLocked(account.getGmail())) {
	        errors.rejectValue("gmail", "account", 
	            "Tài khoản của bạn đã bị khóa. Vui lòng thử lại sau 5 phút!");
	        return "customer/login/userLogin";
	    }

		// Kiểm tra xem field dữ liệu nhập từ view có trống ko?
		if (account.getGmail().isEmpty()) {
			errors.rejectValue("gmail", "account", "Xin vui lòng nhập username(gmail) của bạn!");
			System.out.println("Error: Username field empty!");
			return "customer/login/userLogin";
		} else if (account.getPassword().isEmpty()) {
			errors.rejectValue("password", "account", "Xin vui lòng nhập password!");
			System.out.println("Error: Password field empty!");
			return "customer/login/userLogin";
		}

		// Kiểm tra username, password, và trạng thái tài khoản
		Boolean isValid = Boolean.TRUE;
		AccountEntity account_t = accountService.getAccountByGmail(account.getGmail());
		if (account_t == null) {
			logginAttempManager.incrementLoginAttempts(account.getGmail());
			errors.rejectValue("password", "account", "Mật khẩu của bạn sai hoặc username không đúng!");
			isValid = Boolean.FALSE;
			System.out.println("Error: This user's account doesn't exist!");
		} else if (!account_t.getPassword().equals(accountUltility.getHashPassword(account.getPassword()))) {
			logginAttempManager.incrementLoginAttempts(account.getGmail());
	        logginAttempManager.checkAndLockAccount(account.getGmail(), account_t);
			errors.rejectValue("password", "account", "Mật khẩu của bạn sai hoặc username không đúng!");
			isValid = Boolean.FALSE;
			System.out.println("Error: Wrong password!");
		} else if (!account_t.getStatus()) {
			errors.rejectValue("gmail", "account", "Tài khoản của bạn đã bị khóa!");
			isValid = Boolean.FALSE;
			System.out.println("Error: This user's account is out of order!");
		}

		if (isValid) {

			ServerLogger.logger.info("Account: " + account_t.getGmail() + " has been loggedIn!");

			 // Reset login attempts on successful login
	        logginAttempManager.resetLoginAttempts(account.getGmail());
			System.out.println("==> Login successfully! End login session");

			session.setAttribute("loggedInUser", account_t);

			if (account_t.getRole().getId() == 1) {
				return "admin/adminDashboard";
			} else if (account_t.getRole().getId() == 2) {
				return "staff/staffDashboard";
			} else {
				return "redirect:/index.htm";
			}

		} else {
			ServerLogger.logger.warn("Account: " + account.getGmail() + " failed to login!");
			return "customer/login/userLogin";
		}
	}

	@RequestMapping("/forgotPass")
	public String showforgotPassForm(Model model) {
		model.addAttribute("account", new AccountEntity());

		return "customer/forgotPassword/userForgotPasswordGmail";
	}

	@RequestMapping(value = "/forgotPass", method = RequestMethod.POST)
	public String sendForgoPasstOTPToGmail(ModelMap model, HttpServletRequest request,
			@ModelAttribute("account") AccountEntity account, BindingResult errors, HttpSession session)
			throws IOException {

		// Lấy phản hồi của Google reCaptcha
		String gRecaptchaResponse = request.getParameter("g-recaptcha-response");

		// Lấy các mã captcha khi tạo ra cái ảnh
		String captcha = session.getAttribute("captchaSecurity").toString();

		// Lấy captcha do người dùng nhập
		String captchaInput = request.getParameter("captcha-input");

		System.out.println("==> Captcha code use for this sesion: " + captcha);

		// Xác minh ReCaptcha của Google
		Boolean isVerify = RecaptchaVerification.verify(gRecaptchaResponse);

		// Xác minh Captcha bằng hình ảnh
		Boolean isMatch = captcha != null && captcha.equals(captchaInput);

		if (!isVerify || !isMatch) {
			// Nếu reCAPTCHA hoặc ảnh CAPTCHA không đúng
			if (!isVerify) {
				System.out.println("Error: Google ReCaptcha verification failed!");
				model.addAttribute("reCaptcha", "Vui lòng nhập đúng ReCaptcha!");
			}
			if (!isMatch) {
				System.out.println("Error: Wrong image captcha code!");
				model.addAttribute("reCaptcha", "Vui lòng nhập đúng ReCaptcha!");
			}
			return "customer/forgotPassword/userForgotPasswordGmail";
		}

		// Kiểm tra xem field dữ liệu nhập từ view có trống ko?
		if (account.getGmail().isEmpty()) {
			errors.rejectValue("gmail", "account", "Vui lòng nhập gmail mà bạn thiết lập làm tài khoản!");
			System.out.println("Error: Gmail field empty!");
			return "customer/forgotPassword/userForgotPasswordGmail";
		} else if (!accountUltility.isValidGmail(account.getGmail())) {
			errors.rejectValue("gmail", "account", "Gmail bạn nhập không hợp lệ vui lòng kiểm tra lại!");
			System.out.println("Error: Invalid gmail regex pattern!");
			return "customer/forgotPassword/userForgotPasswordGmail";
		}

		// Kiểm tra tài khoản có tồn tại hay không?
		Boolean isValid = Boolean.TRUE;
		AccountEntity account_t = accountService.getAccountByGmail(account.getGmail());

		if (account_t == null) {
			errors.rejectValue("gmail", "account", "Gmail mà bạn vừa nhập không tồn tại, vui lòng kiểm tra lại!");
			isValid = Boolean.FALSE;
			System.out.println("Error: This user's account doesn't exist!");
		} else if (!account_t.getStatus()) {
			errors.rejectValue("gmail", "account",
					"Tài khoản của bạn đã bị khóa, bạn không thể lấy lại mật khẩu cho tài khoản này!");
			isValid = Boolean.FALSE;
			System.out.println("Error: This user's account is out of order!");
		}

		if (isValid) {
//			HttpSession session = request.getSession();
			String otp = accountUltility.generateOTP().toUpperCase();
//			System.out.println("==> OTP for this forgot password session is: " + otp);
			// Lưu OTP vừa tạo vào session để xử lý cho action method tiếp theo
			session.setAttribute("otp", otp);
			// Lưu tài khoản user vào session để xử lý cho 2 action method kế tiếp
			session.setAttribute("account_t", account_t);
			mailer.sendMailAsync("AgrStore", account.getGmail(), "OTP Forgot Password", "Mã OTP của bạn là: " + otp);
			ServerLogger.logger.info("OTP forgot password have been sent to gmail: " + account_t.getGmail());

			return "customer/forgotPassword/userForgotPasswordGetOTP";
		} else {
			return "customer/forgotPassword/userForgotPasswordGmail";
		}
	}
	
	@RequestMapping(value = "/getOTPForgotPassword", params = "send-again", method = RequestMethod.POST)
	public String sendAgainForgotPassOTP(ModelMap model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		AccountEntity account_t = (AccountEntity) session.getAttribute("account_t");
		
		
		String otp = accountUltility.generateOTP().toUpperCase();
//		System.out.println("==> New generated OTP using for this session is: " + otp);
		if(account_t != null) {
			// Ghi đè lại OTP
			session.setAttribute("otp", otp);
			mailer.sendMailAsync("AgrStore", account_t.getGmail(), "OTP Forgot Password", "Mã OTP của bạn là: " + otp);
			model.addAttribute("message", "Mã OTP đã được gửi lại hãy kiểm tra lại gmail của bạn!");
			ServerLogger.logger.info("OTP forgot password have been sent again to gmail: " + account_t.getGmail());
			
			return "customer/forgotPassword/userForgotPasswordGetOTP";
		}else {
			return "customer/forgotPassword/userForgotPasswordGmail";
		}
	}

	@RequestMapping(value = "/getOTPForgotPassword", params = "verify", method = RequestMethod.POST)
	public String getOTPForgotPassword(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();

		// Lấy các ký tự ở từng ô mà người dùng nhập
		String a = request.getParameter("a");
		String b = request.getParameter("b");
		String c = request.getParameter("c");
		String d = request.getParameter("d");
		String e = request.getParameter("e");
		String f = request.getParameter("f");

		String otpInput = a + b + c + d + e + f;
		System.out.println("==> OTP input: " + otpInput);

		// Lấy OTP đã được lưu từ session
		String otp = (String) session.getAttribute("otp");

		if (otp.equals(otpInput)) {
//			System.out.println("==> Verify OTP successfully!");
			return "customer/forgotPassword/changeForgotPassword";
		} else {
			model.addAttribute("message", "Mã OTP bạn vừa nhập không đúng, vui lòng nhập lại!");
//			System.out.println("==> Failed to verify OTP!");
			return "customer/forgotPassword/userForgotPasswordGetOTP";
		}
	}


	@RequestMapping(value = "/changeForgotPassword", method = RequestMethod.GET)
	public String changeForgotPassword(HttpServletRequest request, Model model, SessionStatus sessionStatus) {

		Boolean isValid = Boolean.TRUE;
		HttpSession session = request.getSession();

		// Lấy account được lưu từ session
		AccountEntity account_t = (AccountEntity) session.getAttribute("account_t");

		// Lấy new-pass và re-enter-new-pass vừa nhập của user
		String newPass = request.getParameter("new-pass");
		String reEnterPass = request.getParameter("re-enter-new-pass");

		if (newPass.isEmpty()) {
			model.addAttribute("message1", "Vui lòng nhập mật khẩu mới!");
			isValid = Boolean.FALSE;
			System.out.println("Error: New password field empty!");
		} else if (reEnterPass.isEmpty()) {
			model.addAttribute("message2", "Vui lòng nhập lại mật khẩu mới!");
			isValid = Boolean.FALSE;
			System.out.println("Error: Re enter new password field empty!");
		} else if (!newPass.equals(reEnterPass)) {
			model.addAttribute("message2", "Mật khẩu vừa nhập không trùng khớp, vui lòng nhập lại!");
			isValid = Boolean.FALSE;
			System.out.println("Error: Re enter new password doesn't match!");
		}

		if (isValid) {
			account_t.setPassword(accountUltility.getHashPassword(newPass));
			accountService.updateAccount(account_t);
			ServerLogger.writeActionLog(account_t.getGmail(), account_t.getRole().getName(), "UPDATE", account_t);

			// Giải phóng dữ liệu của session
			request.getSession().invalidate();
			System.out.println("==> Invalidate session's data");

			// Giải phóng dữ liệu của model attributes
			sessionStatus.setComplete();
			System.out.println("==> Clear model attributes");

			System.out.println("==> End forgot password session");
			return "redirect:/";
		} else {
			ServerLogger.logger.warn("Failed to change password for account: "+ account_t.getGmail());
			return "customer/forgotPassword/changeForgotPassword";
		}
	}

	@RequestMapping("/userSignUpGmail")
	public String userSignUpGmail(Model model) {
		model.addAttribute("account", new AccountEntity());

		return "customer/login/userSignUpGmail";
	}

	@RequestMapping(value = "/userSignUpGmail", method = RequestMethod.POST)
	public String sendSignUpOTPToGmail(ModelMap model, HttpServletRequest request,
			@ModelAttribute("account") AccountEntity account, BindingResult errors, HttpSession session)
			throws IOException {

		// Lấy phản hồi của Google reCaptcha
		String gRecaptchaResponse = request.getParameter("g-recaptcha-response");

		// Lấy các mã captcha khi tạo ra cái ảnh
		String captcha = session.getAttribute("captchaSecurity").toString();

		// Lấy captcha do người dùng nhập
		String captchaInput = request.getParameter("captcha-input");

		System.out.println("==> Captcha code use for this sesion: " + captcha);

		// Xác minh ReCaptcha của Google
		Boolean isVerify = RecaptchaVerification.verify(gRecaptchaResponse);

		// Xác minh Captcha bằng hình ảnh
		Boolean isMatch = captcha != null && captcha.equals(captchaInput);

		if (!isVerify || !isMatch) {
			// Nếu reCAPTCHA hoặc ảnh CAPTCHA không đúng
			if (!isVerify) {
				System.out.println("Error: Google ReCaptcha verification failed!");
				model.addAttribute("reCaptcha", "Vui lòng nhập đúng ReCaptcha!");
			}
			if (!isMatch) {
				System.out.println("Error: Wrong image captcha code!");
				model.addAttribute("reCaptcha", "Vui lòng nhập đúng ReCaptcha!");
			}
			return "customer/login/userSignUpGmail";
		}

		// Kiểm tra xem field dữ liệu nhập từ view có trống ko?
		if (account.getGmail().isEmpty() && account.getGmail() == null) {
			errors.rejectValue("gmail", "account", "Vui lòng nhập gmail mà bạn thiết lập làm tài khoản!");
			System.out.println("Error: Gmail field empty!");
			return "customer/login/userSignUpGmail";
		} else if (!accountUltility.isValidGmail(account.getGmail())) {
			errors.rejectValue("gmail", "account", "Gmail bạn nhập không hợp lệ vui lòng kiểm tra lại!");
			System.out.println("Error: Invalid gmail regex pattern!");
			return "customer/login/userSignUpGmail";
		}

		// Kiểm tra tài khoản có tồn tại hay không?
		Boolean isValid = Boolean.TRUE;
		AccountEntity account_t = accountService.getAccountByGmail(account.getGmail());

		if (account_t != null) {
			errors.rejectValue("gmail", "account", "Gmail mà bạn vừa nhập đã tồn tại, vui lòng chọn gmail khác!");
			isValid = Boolean.FALSE;
			System.out.println("Error: This user's account is already exist!");
		}

		if (isValid) {
//			HttpSession session = request.getSession();
			String otp = accountUltility.generateOTP().toUpperCase();
//			System.out.println("==> OTP for this sign up session is: " + otp);
			// Lưu OTP vừa tạo vào session để xử lý cho action method tiếp theo
			session.setAttribute("otp", otp);
			// Lưu tài khoản user vào session để xử lý cho 2 action method kế tiếp
			session.setAttribute("account", account);
			mailer.sendMailAsync("AgrStore", account.getGmail(), "OTP Đăng ký tài khoản", "Mã OTP của bạn là: " + otp);
			ServerLogger.logger.info("OTP sign up account have been sent to gmail: " + account.getGmail());
			
			return "customer/login/userSignUpGmailGetOTP";
		} else {
			return "customer/login/userSignUpGmail";
		}

	}
	
	@RequestMapping(value = "/getOTPSignUp", params = "send-again", method = RequestMethod.POST)
	public String sendAgainSignUpOTP(ModelMap model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		AccountEntity account = (AccountEntity) session.getAttribute("account");
		
		String otp = accountUltility.generateOTP().toUpperCase();
		System.out.println("==> New generated OTP using for this session is: " + otp);
		
		if(account != null) {
			// Ghi đè lại OTP
			session.setAttribute("otp", otp);
			mailer.sendMailAsync("AgrStore", account.getGmail(), "OTP Đăng ký tài khoản", "Mã OTP của bạn là: " + otp);
			model.addAttribute("message", "Mã OTP đã được gửi lại hãy kiểm tra lại gmail của bạn!");
			ServerLogger.logger.info("OTP sign up account have been sent again to gmail: " + account.getGmail());
			
			return "customer/login/userSignUpGmailGetOTP";
		}else {
			return "customer/login/userSignUpGmail";
		}
		
	}

	@RequestMapping(value = "/getOTPSignUp", params = "verify", method = RequestMethod.POST)
	public String usergetOTPSignUp(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();

		// Lấy các ký tự ở từng ô mà người dùng nhập
		String a = request.getParameter("a");
		String b = request.getParameter("b");
		String c = request.getParameter("c");
		String d = request.getParameter("d");
		String e = request.getParameter("e");
		String f = request.getParameter("f");

		String otpInput = a + b + c + d + e + f;
		System.out.println("==> OTP input: " + otpInput);

		// Lấy OTP đã được lưu từ session
		String otp = (String) session.getAttribute("otp");

		if (otp.equals(otpInput)) {
			System.out.println("==> Verify OTP successfully!");

			// Load dữ liệu mặc định
			List<ProvinceEntity> provinces = provinceService.getListProvinces();
			model.addAttribute("provinces", provinces);

			List<DistrictEntity> districts = districtService.getListDistrictsByProvinceId(1);
			model.addAttribute("districts", districts);

			List<WardEntity> wards = wardService.getListWardByDistrictId(1);
			model.addAttribute("wards", wards);

			return "customer/login/userSignUp";
		} else {
			model.addAttribute("message", "Mã OTP bạn vừa nhập không đúng, vui lòng nhập lại!");
			System.out.println("==> Failed to verify OTP!");
			return "customer/login/userSignUpGmailGetOTP";
		}
	}

	@RequestMapping("/userSignUp")
	public String loadArressSelection(@RequestParam(value = "provinceId", required = false) Integer provinceId,
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

		return "customer/login/userSignUp";
	}

	@RequestMapping(value = "/userSignUp", params = "sign-up", method = RequestMethod.POST)
	public String userSignUp(HttpServletRequest request, Model model, SessionStatus sessionStatus) {
		HttpSession session = request.getSession();

		// Lấy các dữ liệu được trong session
		AccountEntity account = (AccountEntity) session.getAttribute("account");
		WardEntity selectedWard = (WardEntity) session.getAttribute("selectedWard");

		// Lấy các field thông tin đăng ký
		String password = request.getParameter("password");
		String reEnterPassword = request.getParameter("re-enter-password");
		String fullName = request.getParameter("full-name");
		String phoneNumber = request.getParameter("phone-number");
		String streetName = request.getParameter("streetName");

		Boolean isValid = Boolean.TRUE;

		if (password.isEmpty() || !accountUltility.isPasswordValid(password)) {
			model.addAttribute("passErr", "Vui lòng nhập mật khẩu!");
			isValid = Boolean.FALSE;
			System.out.println("Error: Password field empty or not valid !");
		} else if (reEnterPassword.isEmpty()) {
			model.addAttribute("rePassErr", "Vui lòng nhập lại mật khẩu!");
			isValid = Boolean.FALSE;
			System.out.println("Error: Re enter password empty!");
		} else if (!password.equals(reEnterPassword)) {
			model.addAttribute("rePassErr", "Mật khẩu không khớp, vui lòng nhập lại!");
			isValid = Boolean.FALSE;
		} else if (fullName.isEmpty()) {
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
		} else if (!accountUltility.isValidStreetName(streetName)) {
			model.addAttribute("streetErr", "Tên đường không hợp lệ, vui lòng nhập lại!");
			isValid = Boolean.FALSE;
			System.out.println("Error: Street name invalid!");
		}

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
					ServerLogger.writeActionLog(account.getGmail(), "Customer", "ADD", newAddr);

				} catch (Exception e) {
					ServerLogger.writeErrorLog(account.getGmail(), "Customer", "ADD", e);
				}
			}

			// Tạo account mới với role là customer
			System.out.println("==> Create new user's account");
			RoleEntity customerRole = roleService.getRoleById(3);
			if (customerRole != null) {
				try {

					account.setPassword(accountUltility.getHashPassword(reEnterPassword));
					account.setFullName(accountUltility.standardizeName(fullName));
					account.setPhoneNumber(phoneNumber);
					account.setStatus(Boolean.TRUE);
					account.setCreateAt(new Date());
					account.setUpdateAt(new Date());
					account.setRole(customerRole);

					accountService.addAccount(account);
					ServerLogger.writeActionLog(account.getGmail(), "Customer", "ADD", account);

					CartEntity cart = new CartEntity(account);
					cartService.addCart(cart);
					ServerLogger.writeActionLog(account.getGmail(), "Customer", "ADD", cart);

					return "redirect:/";
				} catch (Exception e) {
					ServerLogger.writeErrorLog(account.getGmail(), "Customer", "ADD", e);
				} finally {

					// Giải phóng dữ liệu của session
					request.getSession().invalidate();
					System.out.println("==> Invalidate session's data");

					// Giải phóng dữ liệu của model attributes
					sessionStatus.setComplete();
					System.out.println("==> Clear model attributes");

					System.out.println("End sign up session");
				}

			} else {
				// Thêm thông báo chi tiết khi không tìm thấy RoleEntity
				ServerLogger.logger.warn("Cannot found role for account:" + account.getGmail());
			}
		}

		return "customer/login/userSignUp";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request, SessionStatus sessionStatus, HttpSession session) {
		// Giải phóng dữ liệu của session
		session = request.getSession();
		AccountEntity loggedInUser = (AccountEntity) request.getAttribute("loggedInUser");
		ServerLogger.logger.info("Account: " + loggedInUser + "log out");
		
		// Xóa khóa
		databaseRoutingService.clearDataSourceKey();

		// Giải phóng dữ liệu của model attributes
		sessionStatus.setComplete();
		System.out.println("==> Clear model attributes");
		session.removeAttribute("loggedInUser");
		session.removeAttribute("selectedProvince");
		session.removeAttribute("selectedDistrict");
		session.removeAttribute("selectedWard");
		session.removeAttribute("staff");
		return "redirect:/index.htm";
	}

}
