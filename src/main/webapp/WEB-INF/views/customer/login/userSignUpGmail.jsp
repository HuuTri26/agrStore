<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
						<li><a href="userLogin.htm" class="login-link"><i
								class="biolife-icon icon-login"></i>Đăng nhập/Đăng Ký</a></li>
					</ul>
				</div>
			</div>
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
				<li class="nav-item"><span class="current-page">Gmail Sign up</span></li>
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
							<form  action="#" name="frm-login" method="post">
								<p class="form-row">
									<label for="fid-name">Email to sign up:<span
										class="requite">*</span></label> <input required="required" required type="email" id="fid-name"
										name="name" value="" class="txt-input">
								</p>
								<a href="getOTPSignUp.htm"> Confirm <!-- <button class="btn btn-submit btn-bold" type="submit">Confirm</button> -->
								</a>
							</form>
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