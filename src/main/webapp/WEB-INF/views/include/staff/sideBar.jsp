<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- Sidebar wrapper start -->
<head>
<style>

.sidebar-dropdown {
    list-style: none; /* Bỏ gạch đầu dòng của li */
}

.sidebar-dropdown a {
    display: flex;          /* Đặt icon và text trên cùng một hàng */
    align-items: center;     /* Căn giữa icon và text theo chiều dọc */
    color: black;            /* Màu text mặc định */
    text-decoration: none;
    padding: 10px;
}

.sidebar-dropdown.active a {
    color: purple;           /* Đổi màu text sang tím khi li có lớp active */
}

.sidebar-dropdown:hover a {
    color: purple;           /* Đổi màu text sang tím khi hover */
}

.sidebar-dropdown i {
    font-size: 1.2rem;
    margin-right: 8px;       /* Khoảng cách giữa icon và text */
}
</style>
</head>
<nav class="sidebar-wrapper">

	<!-- Sidebar brand starts -->
	<div class="sidebar-brand">
		<a href="staff/staffDashboard.htm" class="logo"> <img
			src="<c:url value='/assets/assets/images/logo-biolife-1.png'/>"
			alt="staff Dashboard" />
		</a>
	</div>
	<!-- Sidebar brand starts -->

	<!-- Sidebar menu starts -->
	<div class="sidebar-menu">
		<div class="sidebarMenuScroll">
			<ul>
				<li class="sidebar-dropdown ${currentPage == 'dashboard' ? 'active' : ''}"><a
					href="staff/staffDashboard.htm"> <i class="bi bi-house-door"
						style="font-size: 1.2rem; margin-right: 8px;"></i> <span
						class="menu-text">Dashboard</span>
				</a></li>
				<%-- <li class="sidebar-dropdown ${currentPage == 'category' ? 'active' : ''}"><a href="staff/categoryManagement.htm">
						<i class="bi bi-collection"
						style="font-size: 1.2rem; margin-right: 8px;"></i> <span
						class="menu-text">Category</span>
				</a></li> --%>
				<li class="sidebar-dropdown ${currentPage == 'product' ? 'active' : ''}"><a href="staff/productManagement.htm">
						<i class="bi bi-box" style="font-size: 1.2rem; margin-right: 8px;"></i>
						<span class="menu-text">Product</span>
				</a></li>
				<%-- <li class="sidebar-dropdown ${currentPage == 'importBill' ? 'active' : ''}"><a href="staff/importBillManagement.htm">
						<i class="bi bi-file-earmark-text"
						style="font-size: 1.2rem; margin-right: 8px;"></i> <span
						class="menu-text">Import Bill</span>
				</a></li> --%>
				<li class="sidebar-dropdown ${currentPage == 'order' ? 'active' : ''}"><a href="staff/orderManagement.htm">
						<i class="bi bi-bag" style="font-size: 1.2rem; margin-right: 8px;"></i>
						<span class="menu-text">Orders</span>
				</a></li>
				<%-- <li class="sidebar-dropdown ${currentPage == 'staff' ? 'active' : ''}" ><a href="staff/staffManagement.htm">
						<i class="bi bi-people"
						style="font-size: 1.2rem; margin-right: 8px;"></i> <span
						class="menu-text">Staffs</span>
				</a></li> --%>
				<li class="sidebar-dropdown ${currentPage == 'customer' ? 'active' : ''}"><a href="staff/customerManagement.htm">
						<i class="bi bi-person"
						style="font-size: 1.2rem; margin-right: 8px;"></i> <span
						class="menu-text">Customer</span>
				</a></li>
			<%-- 	<li class="sidebar-dropdown ${currentPage == 'provider' ? 'active' : ''}"><a href="staff/providerManagement.htm">
						<i class="bi bi-truck"
						style="font-size: 1.2rem; margin-right: 8px;"></i> <span
						class="menu-text">Provider</span>
				</a></li> --%>
				<li class="sidebar-dropdown ${currentPage == 'feedback' ? 'active' : ''}"><a href="staff/feedbackManagement.htm">
						<i class="bi bi-chat-dots"
						style="font-size: 1.2rem; margin-right: 8px;"></i> <span
						class="menu-text">Feedback</span>
				</a></li>
			</ul>
		</div>
	</div>
	<!-- Sidebar menu ends -->

</nav>