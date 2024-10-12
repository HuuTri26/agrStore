<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- Sidebar wrapper start -->
<nav class="sidebar-wrapper">

	<!-- Sidebar brand starts -->
	<div class="sidebar-brand">
		<a href="adminDashboard.htm" class="logo"> <img
			src="<c:url value='/assets/admin/assets/images/logo.svg'/>"
			alt="Admin Dashboard" />
		</a>
	</div>
	<!-- Sidebar brand starts -->

	<!-- Sidebar menu starts -->
	<div class="sidebar-menu">
		<div class="sidebarMenuScroll">
			<ul>
				<li class="sidebar-dropdown active"><a
					href="adminDashboard.htm"> <i class="bi bi-house"></i> <span
						class="menu-text">Dashboard</span>
				</a></li>
				<li class="sidebar-dropdown"><a href="categoryManagement.htm">
						<i class="bi bi-handbag"></i> <span class="menu-text">Category</span>
				</a></li>
				<li class="sidebar-dropdown"><a href="productManagement.htm">
						<i class="bi bi-handbag"></i> <span class="menu-text">Product</span>
				</a></li>
				<li class="sidebar-dropdown"><a href="importBillManagement.htm">
						<i class="bi bi-handbag"></i> <span class="menu-text">Import
							Bill</span>
				</a></li>
				<li class="sidebar-dropdown"><a href="orderManagement.htm">
						<i class="bi bi-handbag"></i> <span class="menu-text">Orders</span>
				</a></li>
				<li class="sidebar-dropdown"><a href="staffManagement.htm">
						<i class="bi bi-handbag"></i> <span class="menu-text">Staffs</span>
				</a></li>
				<li class="sidebar-dropdown"><a href="customerManagement.htm">
						<i class="bi bi-handbag"></i> <span class="menu-text">Customer</span>
				</a></li>
				<li class="sidebar-dropdown"><a href="feedbackManagement.htm">
						<i class="bi bi-handbag"></i> <span class="menu-text">Feed
							back</span>
				</a></li>
			</ul>
		</div>
	</div>
	<!-- Sidebar menu ends -->

</nav>