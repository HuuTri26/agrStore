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
import agrStore.utility.ServerLogger;

public class CustomerInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	DatabaseRoutingService databaseRoutingService;
	

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String uri = request.getRequestURI();
		ServerLogger.logger.info("CustomerInterceptor check uri: " + uri);

		// Check user authentication
		AccountEntity loggedInUser = (AccountEntity) request.getSession().getAttribute("loggedInUser");

		// If no user is logged in, redirect to login
		if (loggedInUser == null) {
			ServerLogger.logger.warn("CustomerInterceptor: Access denied! Unauthorized access attempt. Redirecting to login.");
			response.sendRedirect(request.getContextPath() + "/user/userLogin.htm");
			databaseRoutingService.clearDataSourceKey();
			return false;
		}

		// Validate user role
		RoleEntity userRole = loggedInUser.getRole();
		if (userRole == null) {
//			System.out.println("==> CustomerInterceptor: Access denied! No role found for user.");
			ServerLogger.logger.warn("CustomerInterceptor: Access denied! No role found for user.");
			response.sendRedirect(request.getContextPath() + "/user/userLogin.htm");
			databaseRoutingService.clearDataSourceKey();
			return false;
		}

		// Strict admin role check (assuming admin role has ID 1)
		if (userRole.getId() != 3) {
			ServerLogger.logger.warn("CustomerInterceptor: Access denied! This account don't have a permission..");
//			System.out.println("==> CustomerInterceptor: Access denied! This account don't have a permission.");
			response.sendRedirect(request.getContextPath() + "/user/userLogin.htm");
			databaseRoutingService.clearDataSourceKey();
			return false;
		}

		// Route to appropriate database
		try {
			databaseRoutingService.clearDataSourceKey();
			databaseRoutingService.routingUserWithRole(userRole);
//			System.out.println("==> CustomerInterceptor:  Routing to DataSource for role: " + userRole.getName());
			ServerLogger.logger.info("CustomerInterceptor:  Routing to DataSource for role: " + userRole.getName());
		} catch (Exception e) {
//			System.out.println("==> CustomerInterceptor: DataSource routing failed for role: " + userRole.getName());
			ServerLogger.logger.info("CustomerInterceptor:  DataSource routing failed for role: " + userRole.getName());
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
