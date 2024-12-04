<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%@include file="/WEB-INF/views/include/customer/header.jsp"%>
<head>
<style>

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
	<!--Navigation section-->
	<div class="page-contain login-page">

		<!-- Main content -->
		<div id="main-content" class="main-content">
			<div class="container">

				<div class="row">
					<nav class="biolife-nav">
						<ul>
							<li class="nav-item"><a href="customer/customerCheckout.htm"
								class="permal-link">Chọn phương thức thanh toán</a></li>
							<li class="nav-item"><span class="current-page">Hủy
									giao dịch</span></li>
						</ul>
					</nav>
				</div>
				<h1>Hủy bỏ giao dịch</h1>
				<p>Giao dịch của bạn đã được hủy bỏ thành công PayPal sẽ tự động
					hoàn tiền cho bạn</p>
				<a href="index.htm">Quay lại</a>

			</div>
		</div>
	</div>
</body>
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
</html>
