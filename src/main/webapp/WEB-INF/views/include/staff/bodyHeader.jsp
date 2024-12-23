<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<div class="page-header">

	<!-- Search container start -->
	<div class="toggle-sidebar" id="toggle-sidebar">
		<i class="bi bi-list"></i>
	</div>

	<!-- Breadcrumb start -->
	<ol class="breadcrumb">
		<li class="breadcrumb-item"><i class="bi bi-house"></i></li>
		<li class="breadcrumb-item breadcrumb-active" aria-current="page">Management
			Page</li>
	</ol>
	<!-- Header actions ccontainer start -->
	<div class="header-actions-container">
		<!-- Search container end -->
		<ul class="header-actions">
			<!-- Header actions start -->
			<li class="dropdown"><a href="#" id="userSettings"
				class="user-settings" data-toggle="dropdown" aria-haspopup="true">
					<span class="user-name d-none d-md-block">${loggedInUser.fullName }</span> <span
					class="avatar"> <img
						src="<c:url value='/assets/user-images/${loggedInUser.avatar }'/>"
						alt=""> <span class="status busy"></span>
				</span>
			</a>
				<div class="dropdown-menu dropdown-menu-end"
					aria-labelledby="userSettings">
					<div class="header-profile-actions">
						<a href="staff/staffProfile.htm">Profile</a> <a
							href="staff/staffChangePassword.htm">ChangePassword</a> <a
							href="user/logout.htm">Logout</a>
					</div>
				</div></li>
		</ul>
		<!-- Header actions end -->

	</div>
	<!-- Header actions ccontainer end -->

</div>