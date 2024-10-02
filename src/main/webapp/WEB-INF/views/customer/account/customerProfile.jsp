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


<style>
.address-form {
	max-width: 400px;
	margin: 20px auto;
}

.address-form label {
	display: block;
	margin: 10px 0 5px;
}

.address-form .form-select, .address-form .form-control {
	width: 100%;
	padding: 10px;
	margin-bottom: 15px;
	border-radius: 5px;
	border: 1px solid #ccc;
}

.signin-container .form-row .btn-submit {
	float: right;
	width: 500px;
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
				<h5 class="font-body--xxl-500">Profile</h5>
			</div>
			<div class="dashboard__content-card-body">
				<div class="row">
					<div class="col-lg-3 order-lg-0 order-1">
						<div class="dashboard__content-card-img">
							<form action="#" style="text-align: center">
								<div class="dashboard__content-img-wrapper">
									<div id="imagePreview"
										style="background-image: url('.main/src/images/user/img-07.png');"></div>
								</div>
								<!-- <button class="button button--outline">
                            Choose Image
                          </button> -->
								<div class="upload-image button button--outline">
									<input type='file' id="imageUpload" accept=".png, .jpg, .jpeg"
										id="imageUpload" /> <label for="imageUpload">Chọn ảnh</label>
								</div>
							</form>
						</div>
					</div>
					<div class="col-lg-9 order-lg-0 order-2">

						<form action="#">
							<div class="contact-form__content">
								<div class="col-lg-6 order-lg-0 order-2">
									<div class="contact-form-input">
										<label for="fname1">FullName </label> <input type="text"
											id="fname1" placeholder="Quoc Toan" />
									</div>
									<div class="contact-form-input">
										<label for="email1">Email </label> <input type="text"
											id="email1" placeholder="dinhquoctoan@gmail.com" />
									</div>
									<div class="contact-form-input">
										<label for="number1">Phone Number</label> <input type="number"
											id="number1" placeholder="0123456789" />
									</div>
									<div class="contact-form-input">
										<label for="number1">Địa chỉ</label> <input type="text"
											id="address" placeholder="TPHCM" />
									</div>
								</div>
								<div class="col-lg-3 order-lg-0 order-2">
									<div class="address-form">
										<label for="province">Tỉnh/Thành phố:</label> <select
											id="province" name="provinceId" class="form-select"
											onchange="this.form.submit()">
											<option value="">Chọn Tỉnh/Thành phố</option>
											<c:forEach var="province" items="${provinces}">
												<option value="${province.id}"
													${province.id == selectedProvinceId ? 'selected' : ''}>${province.name}</option>
											</c:forEach>
										</select> <input type="text" id="provinceText" readonly
											value="${selectedProvince.name}"
											placeholder="Tỉnh/Thành phố đã chọn" /> <label
											for="district">Quận/Huyện:</label> <select id="district"
											name="districtId" class="form-select"
											onchange="this.form.submit()">
											<option value="">Chọn Quận/Huyện</option>
											<c:forEach var="district" items="${districts}">
												<option value="${district.id}"
													${district.id == selectedDistrictId ? 'selected' : ''}>${district.name}</option>
											</c:forEach>
										</select> <input type="text" id="districtText" readonly
											value="${selectedDistrict.name}"
											placeholder="Quận/Huyện đã chọn" /> <label for="ward">Xã/Phường:</label>
										<select id="ward" name="wardId" class="form-select"
											onchange="this.form.submit()">
											<option value="">Chọn Xã/Phường</option>
											<c:forEach var="ward" items="${wards}">
												<option value="${ward.id}">${ward.name}</option>
											</c:forEach>
										</select> <input type="text" id="wardText" readonly
											value="${selectedWard.name}" placeholder="Xã/Phường đã chọn" />


									</div>
									<div class="contact-form-btn">
										<button class="button button--md" type="submit">Lưu
											thay đổi</button>
									</div>
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
