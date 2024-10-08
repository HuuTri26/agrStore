<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<base href="${pageContext.servletContext.contextPath}/">
<head>
<style>
.dropdown {
	position: relative;
	display: inline-block;
}

.dropdown-content {
	display: none;
	position: absolute;
	background-color: orange;
	min-width: 160px;
	box-shadow: 0px 8px 16px 0px rgba(0, 0, 0, 0.2);
	z-index: 5;
	border-radius: 10px;
	right: 0; /* Thay đổi này sẽ căn chỉnh dropdown về bên trái */
}

.dropdown:hover .dropdown-content {
	display: block;
	color: white;
	font-weight: 600;
	border-radius: 10px;
}

.dropdown-content a {
	color: black;
	padding: 12px 16px;
	text-decoration: none;
	display: block;
	padding: 12px 16px;
	text-align: left; /* Đảm bảo text được căn trái trong dropdown */
}

.dropdown-content a:hover {
	background-color: orange;
	border-radius: 10px;
	color: red;
}
</style>
</head>
<!-- HEADER -->
<!-- <header id="header" class="header-area style-01 layout-03">
	<div class="header-top bg-main hidden-xs"> -->
<div class="container">
	<div class="top-bar left">
		<ul class="horizontal-menu">
			<li><a href="#" class="biolife-logo"><img
					src="<c:url value='/assets/assets/images/organic-2.png'/>"
					alt="biolife logo" width="135" height="34"></a></li>
		</ul>
	</div>
	<div class="top-bar right">
		<ul class="horizontal-menu">
			<li><c:choose>
					<c:when test="${not empty sessionScope.loggedInUser}">
						<div class="dropdown">
							<a href="#" class="login-link"> <i
								class="biolife-icon icon-login"></i>${sessionScope.loggedInUser.gmail}
							</a>
							<div class="dropdown-content">
								<a href="customerProfile.htm">Profile</a> <a
									href="customerChangePassword.htm">Change Password</a> <a
									href="customerOrderList.htm">Order List</a> <a
									href="logout.htm">Log Out</a>
							</div>
						</div>
					</c:when>
					<c:otherwise>
						<a href="userLogin.htm" class="login-link"> <i
							class="biolife-icon icon-login"></i>Đăng nhập/Đăng Ký
						</a>
					</c:otherwise>
				</c:choose></li>
		</ul>
	</div>
</div>
<!-- 	</div>
</header> -->