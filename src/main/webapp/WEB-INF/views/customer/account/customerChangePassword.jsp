<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<%@include file="/WEB-INF/views/include/customer/header.jsp"%>
<body class="biolife-body">
	<!-- Preloader -->
	<div id="biof-loading">
		<div class="biof-loading-center">
			<div class="biof-loading-center-absolute">
				<div class="dot dot-one"></div>
				<div class="dot dot-two"></div>
				<div class="dot dot-three"></div>
			</div>
		</div>
	</div>
	<!-- HEADER -->
	<header id="header" class="header-area style-01 layout-03">
		<div class="header-top bg-main hidden-xs">
			<%@include file="/WEB-INF/views/include/customer/bodyHeader.jsp"%>
		</div>
	</header>
	<!--Hero Section-->
	<div class="hero-section hero-background">
		<h1 class="page-title">argStore</h1>
	</div>

	<!--Navigation section-->
	<div class="container">
		<nav class="biolife-nav">
			<ul>
				<li class="nav-item"><a href="index.htm" class="permal-link">Trang
						chủ</a></li>
				<li class="nav-item"><span class="current-page">CHange
						Password</span></li>
			</ul>
		</nav>
	</div>

	<div class="page-contain login-page">

		<!-- Main content -->
		<div id="main-content" class="main-content">
			<div class="container">

				<div class="row">

					<!--Form Sign In-->
					<div class="col-lg-12 col-md-6 col-sm-6 col-xs-12">
						<div class="signin-container">
							<form:form action="customer/customerChangePassword.htm"
								method="post" modelAttribute="changePass">
								<p class="form-row">
									<label for="fid-name">Mật khẩu cũ:<span class="requite">*</span></label>
									<form:input path="password" type="password"
										class="form-control" id="currentPassword" />
									<form:errors path="password" />
									${wrongPass }
								</p>
								<p class="form-row">
									<label for="fid-name">Mật khẩu mới:<span
										class="requite">*</span></label> <input name="new-password"
										type="password" class="form-control" id="newPassword">
									${newPass }
								</p>
								<p class="form-row">
									<label for="fid-name">Nhập lại mật khẩu mới:<span
										class="requite">*</span></label> <input name="re-enter-new-password"
										type="password" class="form-control" id="renewPassword">
									${reNewPass }

								</p>
							    <a href="customer/userLogin.htm">
									<button class="btn btn-submit btn-bold" type="submit">Xác
										nhận</button>
								</a>
							</form:form>
						</div>
					</div>


				</div>
			</div>
		</div>
	</div>
	<!-- FOOTER -->
	<%@include file="/WEB-INF/views/include/customer/footer.jsp"%>

	<!--Footer For Mobile-->
	<!--Mobile Global Menu-->
	<!--Quickview Popup-->
	<%@include file="/WEB-INF/views/include/customer/footerMobile.jsp"%>

	<!-- Scroll Top Button and JS -->
	<%@include file="/WEB-INF/views/include/customer/js.jsp"%>
</body>
</html>