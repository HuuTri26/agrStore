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
				<li class="nav-item"><a href="#" class="permal-link">Trang
						chủ</a></li>
				<li class="nav-item"><span class="current-page">Login</span></li>
			</ul>
		</nav>
	</div>

	<div class="page-contain login-page">

		<!-- Main content -->
		<div id="main-content" class="main-content">
			<div class="container">

				<div class="row">

					<!--Form Sign In-->
					<div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
						<div class="signin-container">
							<form:form action="userLogin.htm" name="frm-login" method="post"
								modelAttribute="account">
								<p class="form-row">
									<label for="fid-name">Email:<span class="requite">*</span></label>
									<form:input path="gmail" id="fid-name"
										placeholder="Nhập username(gmail)" class="txt-input" />
									<form:errors path="gmail" />
								</p>
								<p class="form-row">
									<label for="fid-pass">Password:<span class="requite">*</span></label>
									<form:input type="password" path="password" id="fid-pass"
										placeholder="Nhập password" class="txt-input" />
									<form:errors path="password" />
								</p>
								<p class="form-row wrap-btn">
									<button class="btn btn-submit btn-bold" type="submit">Login</button>
									<a href="forgotPass.htm" class="link-to-help">Quên mật khẩu</a>
								</p>
							</form:form>
						</div>
					</div>
					<!--Go to Register form-->
					<div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
						<div class="register-in-container">
							<div class="intro">
								<h4 class="box-title">Chưa có Acount:))</h4>
								<p class="sub-title">Các bước</p>
								<ul class="lis">
									<li>Xác thực email dk</li>
									<li>Nhập OTP gửi đến gmail dk</li>
									<li>Nhập thông tin cho tiết</li>
									<li>Tạo mk và xác nhận</li>
								</ul>
								<a href="userSignUpGmail.htm" class="btn btn-bold">Đăng ký
									ngay</a>
							</div>
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