package agrStore.interceptor;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import agrStore.entity.AccountEntity;
import agrStore.entity.RoleEntity;
import agrStore.service.DatabaseRoutingService;

public class UserInterceptor extends HandlerInterceptorAdapter {
	@Autowired
	DatabaseRoutingService databaseRoutingService;

	private static final Set<String> PUBLIC_URIS = Set.of("/index.htm", "/user/userLogin.htm", "/user/userSignUp.htm",
			"/user/userSignUpGmail.htm", "/getOTPSignUp.htm", "/changeForgotPassword.htm",
			"/userForgotPasswordGetOTP.htm", "/forgotPass.htm", "/getOTPForgotPassword.htm");

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		String uri = request.getRequestURI();
		System.out.println("==> UserInterceptor: Check uri: " + uri);

		if (PUBLIC_URIS.stream().anyMatch(uri::contains)) {
			return true;
		}

		// Check user authentication
		AccountEntity loggedInUser = (AccountEntity) request.getSession().getAttribute("loggedInUser");
		if (loggedInUser == null) {
			System.out.println("==> UserInterceptor: Access denied! Unauthorized access attempt. Redirecting to login.");
			response.sendRedirect(request.getContextPath() + "/index.htm");
			databaseRoutingService.clearDataSourceKey();
			return false;
		}

		// Routing based on user's role
		RoleEntity userRole = loggedInUser.getRole();
		if (userRole == null) {
			System.out.println("==> UserInterceptor: Access denied! No role found for user.");
			response.sendRedirect(request.getContextPath() + "/index.htm");
			databaseRoutingService.clearDataSourceKey();
			return false;
		}

		// Route to appropriate database based on user's role
		try {
			databaseRoutingService.routingUserWithRole(userRole);
			System.out.println("==> UserInterceptor: Routing to DataSource for role: " + userRole.getName());
		} catch (Exception e) {
			System.out.println("==> UserInterceptor: DataSource routing failed for role:" + userRole.getName());
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/index.htm");
			databaseRoutingService.clearDataSourceKey();
			return false;
		}

		return true; // Tiếp tục xử lý request
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
