package agrStore.interceptor;

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

public class StaffInterceptor extends HandlerInterceptorAdapter {
	@Autowired
	DatabaseRoutingService databaseRoutingService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String uri = request.getRequestURI();
		System.out.println("==> staffInterceptor Check uri: " + uri);

		// Check user authentication
		AccountEntity loggedInUser = (AccountEntity) request.getSession().getAttribute("loggedInUser");

		// If no user is logged in, redirect to login
		if (loggedInUser == null) {
			System.out.println("==> staffInterceptor: Access denied! Unauthorized access attempt. Redirecting to login.");
			response.sendRedirect(request.getContextPath() + "/user/userLogin.htm");
			databaseRoutingService.clearDataSourceKey();
			return false;
		}

		// Validate user role
		RoleEntity userRole = loggedInUser.getRole();
		if (userRole == null) {
			System.out.println("==> staffInterceptor: Access denied! No role found for user.");
			response.sendRedirect(request.getContextPath() + "/user/userLogin.htm");
			databaseRoutingService.clearDataSourceKey();
			return false;
		}

		// Strict staff role check (assuming staff role has ID 1)
		if (userRole.getId() != 2) {
			System.out.println("==> staffInterceptor: Access denied! This account don't have a permission.");
			response.sendRedirect(request.getContextPath() + "/user/userLogin.htm");
			databaseRoutingService.clearDataSourceKey();
			return false;
		}

		// Route to appropriate database
		try {
			databaseRoutingService.clearDataSourceKey();
			databaseRoutingService.routingUserWithRole(userRole);
			System.out.println("==> staffInterceptor: Routing to DataSource for role: " + userRole.getName());
		} catch (Exception e) {
			System.out.println("staffInterceptor: DataSource routing failed for role:" + userRole.getName());
			response.sendRedirect(request.getContextPath() + "/user/userLogin.htm");
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
