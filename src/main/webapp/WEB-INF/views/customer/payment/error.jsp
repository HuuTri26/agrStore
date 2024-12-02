<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%@include file="/WEB-INF/views/include/customer/header.jsp"%>
<head>
<meta charset="UTF-8">
<title>Payment Error</title>
<style>
body {
	font-family: Arial, sans-serif;
	margin: 0;
	padding: 0;
	background-color: #f9f9f9;
	display: flex;
	justify-content: center;
	align-items: center;
	height: 100vh;
}

.error-container {
	text-align: center;
	background-color: #fff;
	padding: 30px;
	border-radius: 10px;
	box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
}

.error-container h1 {
	color: #d9534f;
	font-size: 36px;
}

.error-container h3 {
	color: #5a5a5a;
}

.error-container a {
	display: inline-block;
	margin-top: 20px;
	padding: 10px 20px;
	color: #fff;
	background-color: #0275d8;
	text-decoration: none;
	border-radius: 5px;
	font-size: 16px;
}

.error-container a:hover {
	background-color: #025aa5;
}
</style>
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
				<li class="nav-item"><a href="customer/customerCheckout.htm"
					class="permal-link">Chọn phương thức thanh toán</a></li>
				<li class="nav-item"><span class="current-page">Lỗi giao dịch</span></li>
			</ul>
		</nav>
	</div>
	<div class="error-container">
		<h1>Thực hiện giao dịch thất bại</h1>
		<h3>${error-message}</h3>
		<a href="customer/customerCheckout.htm">Quay lại</a>
	</div>

	<%@include file="/WEB-INF/views/include/customer/footer.jsp"%>

	<!--Footer For Mobile-->
	<!--Mobile Global Menu-->
	<!--Quickview Popup-->
	<%@include file="/WEB-INF/views/include/customer/footerMobile.jsp"%>



	<%-- 	<script
		src="<c:url value='/assets/cart/main/src/lib/js/jquery.min.js'/>"></script>
	<script
		src="<c:url value='/assets/cart/main/src/lib/js/swiper-bundle.min.js'/>"></script>
	<script src="<c:url value='/assets/cart/main/src/lib/js/bvselect.js'/>"></script>
	<script
		src="<c:url value='/assets/cart/main/src/lib/js/bootstrap.bundle.min.js'/>"></script>
	<script src="<c:url value='/assets/cart/main/src/js/main.js'/>"></script> --%>
	<!-- Scroll Top Button and JS -->
	<%@include file="/WEB-INF/views/include/customer/js.jsp"%>

</body>
</html>
