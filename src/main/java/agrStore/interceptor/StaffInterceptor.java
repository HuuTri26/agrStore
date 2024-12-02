package agrStore.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

		System.out.println("==> Interceptor check: " + uri);
		// Không kiểm tra khi truy cập đến các trang không yêu cầu đăng nhập

		// Kiểm tra người dùng đã đăng nhập hay chưa
		AccountEntity loggedInUser = (AccountEntity) request.getSession().getAttribute("loggedInUser");

		if (loggedInUser == null) {
			System.out.println("==> No permission, user intercepted! Using default database.");
			// Định tuyến mặc định đến DEFAULT_AGENT nếu chưa đăng nhập
			databaseRoutingService.routingUserWithRole(new RoleEntity("Default"));

			// Chuyển hướng đến trang index
			response.sendRedirect(request.getContextPath() + "/user/userLogin.htm");
			return false; // Dừng request
		}

		// Khi người dùng đã đăng nhập, định tuyến database dựa trên role của user
		if (loggedInUser.getRole() != null) {
			System.out.println("==> Routing database for role: " + loggedInUser.getRole().getName());
			databaseRoutingService.routingUserWithRole(loggedInUser.getRole());
		} else {
			System.out.println("==> Role not found, using default database!");
			databaseRoutingService.routingUserWithRole(new RoleEntity("Default"));
		}
		System.out.println(loggedInUser.getRole());
		// Kiểm tra nếu người dùng không thuộc role "Customer" (id = 3)
		if (loggedInUser.getRole().getId() != 2) {
			System.out.println("==> No permission, user intercepted! Role not allowed.");
			response.sendRedirect(request.getContextPath() + "/admin/adminDashboard.htm");
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
