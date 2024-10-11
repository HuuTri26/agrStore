
package agrStore.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import agrStore.entity.AccountEntity;

public class CustomerInterceptor extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String uri = request.getRequestURI();

		System.out.println("==> Interceptor check: "+uri);
		// Không kiểm tra khi truy cập đến các trang sau
		if (	   uri.contains("index.htm")
				|| uri.contains("userLogin.htm") 
				|| uri.contains("userSignUp.htm")
				|| uri.contains("userSignUpGmail.htm")
				|| uri.contains("getOTPSignUp.htm")
				|| uri.contains("changeForgotPassword.htm")
				|| uri.contains("userForgotPasswordGetOTP.htm")
				|| uri.contains("userForgotPasswordGmail.htm") || uri.contains("logout.htm")) {
			return true;
		}
		AccountEntity loggedInUser = (AccountEntity) request.getSession().getAttribute("loggedInUser");
		
		if (loggedInUser == null || loggedInUser.getRole().getId() != 3) {
			System.out.println("==> No permission, user intercepted!");
			response.sendRedirect(request.getContextPath() + "/userLogin.htm");
		}


		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

	}

}

