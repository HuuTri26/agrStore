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


	<script src="https://www.google.com/recaptcha/enterprise.js" async
		defer></script>

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
				<li class="nav-item"><span class="current-page">Gmail
						Sign up</span></li>
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

							<form:form action="userSignUpGmail.htm" name="frm-login"
								method="post" modelAttribute="account">
								<p class="form-row">
									<label for="fid-name">Nhập Gmail sử dụng để đăng ký cho
										tài khoản của bạn:<span class="requite">*</span>
									</label>
									<form:input path="gmail" id="fid-name" name="name"
										placeHolder="expamle@gmail.com" class="txt-input" />
									<form:errors path="gmail" />
								</p>

								<div class="g-recaptcha"
									data-sitekey="6LcQQlcqAAAAAMtzgq3PIXZrcABfkUJbqMCUhY_E"
									data-callback="onRecaptchaSuccess"></div>

								<div id="captcha-container" style="display: none;">
									<img src="${pageContext.request.contextPath}/captcha/index.htm"
										id="captcha-img"> <input name="captcha-input"
										type="text" placeholder="Nhập mã captcha">
								</div>

								<label>${reCaptcha }</label>


								
								<p class="form-row wrap-btn">
									<button class="btn btn-submit btn-bold" type="submit">Xác
									nhận</button>
									
								</p>	
							</form:form>
						</div>
					</div>


				</div>
			</div>
		</div>
	</div>

<head>

<!-- Replace the variables below. -->
<script>
	// Hàm được gọi khi reCAPTCHA thành công
	function onRecaptchaSuccess() {
		// Hiển thị ảnh CAPTCHA khi reCAPTCHA được xác nhận
		document.getElementById('captcha-container').style.display = 'block';
	}
</script>

<!-- Your code -->
</head>


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