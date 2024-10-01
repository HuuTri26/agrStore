package agrStore.controller.customer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;

import agrStore.bean.Mailer;
import agrStore.entity.AccountEntity;
import agrStore.utility.AccountUltility;
import agrStore.service.AccountService;

@Controller
public class userController {

	@Autowired
	AccountUltility accountUltility;

	@Autowired
	Mailer mailer;

	@Autowired
	AccountService accountService;

	@RequestMapping("/userLogin")
	public String showUserLoginForm(Model model) {
		model.addAttribute("account", new AccountEntity());
		System.out.println("==> Begin login session");
		return "customer/login/userLogin";
	}

	@RequestMapping(value = "/userLogin", method = RequestMethod.POST)
	public String userLogin(ModelMap model, HttpServletRequest request,
			@ModelAttribute("account") AccountEntity account, BindingResult errors, HttpSession session) {

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
			errors.rejectValue("password", "account", "Mật khẩu của bạn sai hoặc username không đúng!");
			isValid = Boolean.FALSE;
			System.out.println("Error: This user's account doesn't exist!");
		} else if (!account_t.getPassword().equals(accountUltility.getHashPassword(account.getPassword()))) {
			errors.rejectValue("password", "account", "Mật khẩu của bạn sai hoặc username không đúng!");
			isValid = Boolean.FALSE;
			System.out.println("Error: Wrong password!");
		} else if (!account_t.getStatus()) {
			errors.rejectValue("gmail", "account", "Tài khoản của bạn đã bị khóa!");
			isValid = Boolean.FALSE;
			System.out.println("Error: This user's account is out of order!");
		}

		if (isValid) {
			System.out.println("==> Login successfully! End login session");
			session.setAttribute("loggedInUser", account_t);
			return "redirect:/index.htm";
		} else {
			System.out.println("==> Login failed!");
			return "customer/login/userLogin";
		}
	}

	@RequestMapping("/forgotPass")
	public String showforgotPassForm(Model model) {
		model.addAttribute("account", new AccountEntity());
		System.out.println("==> Begin forgot password session");

		return "customer/forgotPassword/userForgotPasswordGmail";
	}

	@RequestMapping(value = "/forgotPass", method = RequestMethod.POST)
	public String sendOTPToGmail(HttpServletRequest request, @ModelAttribute("account") AccountEntity account,
			BindingResult errors) {

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
			HttpSession session = request.getSession();
			String otp = accountUltility.generateOTP().toUpperCase();
			System.out.println("==> OTP for this forgot password session is: " + otp);
			// Lưu OTP vừa tạo vào session để xử lý cho action method tiếp theo
			session.setAttribute("otp", otp);
			// Lưu tài khoản user vào session để xử lý cho 2 action method kế tiếp
			session.setAttribute("account_t", account_t);
			System.out.println("==> Create session's attributes for 'otp' and 'account_t'");
			mailer.sendMailAsync("AgrStore", account.getGmail(), "OTP Forgot Password", "Mã OTP của bạn là: " + otp);

			return "customer/forgotPassword/userForgotPasswordGetOTP";
		} else {
			return "customer/forgotPassword/userForgotPasswordGmail";
		}
	}

	@RequestMapping(value = "/getOTPForgotPassword", params = "verify", method = RequestMethod.GET)
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
			System.out.println("==> Verify OTP successfully!");
			return "customer/forgotPassword/changeForgotPassword";
		} else {
			model.addAttribute("message", "Mã OTP bạn vừa nhập không đúng, vui lòng nhập lại!");
			System.out.println("==> Failed to verify OTP!");
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
			System.out.println("==> User's password updated successfully!");

			// Giải phóng dữ liệu của session
			request.getSession().invalidate();
			System.out.println("==> Invalidate session's data");

			// Giải phóng dữ liệu của model attributes
			sessionStatus.setComplete();
			System.out.println("==> Clear model attributes");

			System.out.println("==> End forgot password session");
			return "redirect:/";
		} else {
			System.out.println("Error: User's password updated failed!");
			return "customer/forgotPassword/changeForgotPassword";
		}
	}

	@RequestMapping(value = "/userSignUpGmail", method = RequestMethod.GET)
	public String userSignUpGmail(HttpServletRequest request) {

		return "customer/login/userSignUpGmail";
	}

	@RequestMapping(value = "/getOTPSignUp", method = RequestMethod.GET)
	public String usergetOTPSignUp(HttpServletRequest request) {

		return "customer/login/userSignUpGmailGetOTP";
	}

	@RequestMapping(value = "/userSignUp", method = RequestMethod.GET)
	public String userSignUp(HttpServletRequest request) {

		return "customer/login/userSignUp";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		session.removeAttribute("loggedInUser");
		return "redirect:/index.htm";
	}
}
