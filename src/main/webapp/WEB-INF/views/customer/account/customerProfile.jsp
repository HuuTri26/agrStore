<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%@include file="/WEB-INF/views/include/customer/header.jsp"%>
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="X-UA-Compatible" content="ie=edge" />
<title>Đặt hàng</title>
<link rel="icon" type="image/png"
	href="<c:url value='/assets/cart/main/src/images/favicon/favicon-16x16.png'/>" />
<link rel="icon" type="image/png"
	href="<c:url value='/assets/cart/main/src/images/favicon/favicon-16x16.png'/>" />
<link rel="stylesheet"
	href="<c:url value='/assets/cart/main/src/lib/css/swiper-bundle.min.css'/>" />
<link rel="stylesheet"
	href="<c:url value='/assets/cart/main/src/lib/css/bvselect.css'/>" />
<link rel="stylesheet"
	href="<c:url value='/assets/cart/main/src/lib/css/bootstrap.min.css'/>" />
<link rel="stylesheet"
	href="<c:url value='/assets/cart/main/src/css/style.css'/>" />
</head>
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
	<header id="header" class="header-area style-01 layout-03">
		<div class="header-top bg-main hidden-xs">
			<%@include file="/WEB-INF/views/include/customer/bodyHeader.jsp"%>
		</div>
	</header>
	<div class="hero-section hero-background">
		<h1 class="page-title">argStore</h1>
	</div>

	<!--Navigation section-->
	<div class="container">
		<nav class="biolife-nav">
			<ul>
				<li class="nav-item"><a href="index.htm" class="permal-link">Trang
						chủ</a></li>
				<li class="nav-item"><span class="current-page">Profile</span></li>
			</ul>
		</nav>
	</div>
	<div class="container">
		<!-- Account Settings  -->
		<div class="dashboard__content-card">
			<div class="dashboard__content-card-header">
				<h5 class="font-body--xxl-500">Account Settings</h5>
			</div>
			<div class="dashboard__content-card-body">
				<div class="row">
					<div class="col-lg-5 order-lg-0 order-1">
						<div class="dashboard__content-card-img">
							<form action="#" style="text-align: center">
								<div class="dashboard__content-img-wrapper">
									<div id="imagePreview"
										style="background-image: url('./assets/cart/main/src/images/user/img-07.png');"></div>
								</div>
								<!-- <button class="button button--outline">
                            Choose Image
                          </button> -->
								<div class="upload-image button button--outline">
									<input type='file' id="imageUpload" accept=".png, .jpg, .jpeg"
										id="imageUpload" /> <label for="imageUpload">Choose
										Image</label>
								</div>
							</form>
						</div>
					</div>
					<div class="col-lg-7 order-lg-0 order-2">
						<form action="#">
							<div class="contact-form__content">
								<div class="contact-form-input">
									<label for="fname1">First Name </label> <input type="text"
										id="fname1" placeholder="Dianne" />
								</div>
								<div class="contact-form-input">
									<label for="lname2">Last Name </label> <input type="text"
										id="lname2" placeholder="Russell" />
								</div>
								<div class="contact-form-input">
									<label for="email1">Email </label> <input type="text"
										id="email1" placeholder="dianne.russell@gmail.com" />
								</div>
								<div class="contact-form-input">
									<label for="number1">Phone Number</label> <input type="number"
										id="number1" placeholder="(603) 555-0123" />
								</div>
								<div class="contact-form-btn">
									<button class="button button--md" type="submit">Save
										Changes</button>
								</div>
							</div>
						</form>
					</div>

				</div>
			</div>
		</div>
	</div>
	<%@include file="/WEB-INF/views/include/customer/footer.jsp"%>

	<!--Footer For Mobile-->
	<!--Mobile Global Menu-->
	<!--Quickview Popup-->
	<%@include file="/WEB-INF/views/include/customer/footerMobile.jsp"%>



	<script
		src="<c:url value='/assets/cart/main/src/lib/js/jquery.min.js'/>"></script>
	<script
		src="<c:url value='/assets/cart/main/src/lib/js/swiper-bundle.min.js'/>"></script>
	<script src="<c:url value='/assets/cart/main/src/lib/js/bvselect.js'/>"></script>
	<script
		src="<c:url value='/assets/cart/main/src/lib/js/bootstrap.bundle.min.js'/>"></script>
	<script src="<c:url value='/assets/cart/main/src/js/main.js'/>"></script>
	<!-- Scroll Top Button and JS -->
	<%@include file="/WEB-INF/views/include/customer/js.jsp"%>
</body>

</html>
