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
/* Container Styling */
.container {
	max-width: 1200px;
	margin: 0 auto;
	padding: 20px;
}

/* Card Styling */
.dashboard__content-card {
	background-color: #fff;
	border-radius: 8px;
	box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
	padding: 20px;
	margin-bottom: 20px;
}

.dashboard__content-card-header h5 {
	font-size: 24px;
	font-weight: 600;
	margin-bottom: 20px;
}

/* Image Upload Styling */
.dashboard__content-card-img {
	text-align: center;
	margin-bottom: 20px;
}

.dashboard__content-img-wrapper {
	width: 150px;
	height: 150px;
	border-radius: 50%;
	overflow: hidden;
	margin: 0 auto 10px;
	background-color: #f0f0f0;
}

.dashboard__content-img-wrapper #imagePreview {
	width: 100%;
	height: 100%;
	background-size: cover;
	background-position: center;
	border: 1px solid #ddd;
}

.upload-image {
	margin: 10px 0;
}

.upload-image input[type="file"] {
	display: none;
}

.upload-image label {
	display: inline-block;
	padding: 8px 16px;
	background-color: #007bff;
	color: #fff;
	border-radius: 5px;
	cursor: pointer;
	font-size: 14px;
}

.upload-image button {
	background-color: #28a745;
	color: #fff;
	border: none;
	padding: 8px 16px;
	border-radius: 5px;
	cursor: pointer;
}

/* Form Styling */
.contact-form__content {
	display: flex;
	flex-wrap: wrap;
	gap: 20px;
}

.contact-form-input {
	width: 100%;
	margin-bottom: 15px;
}

.contact-form-input label {
	font-size: 14px;
	font-weight: 500;
	margin-bottom: 5px;
	display: block;
}

.contact-form-input input, .contact-form-input textarea,
	.contact-form-input select {
	width: 100%;
	padding: 10px 12px;
	font-size: 14px;
	border: 1px solid #ddd;
	border-radius: 5px;
	outline: none;
	transition: all 0.3s ease;
}

.contact-form-input input:focus, .contact-form-input textarea:focus,
	.contact-form-input select:focus {
	border-color: #007bff;
	box-shadow: 0 0 5px rgba(0, 123, 255, 0.5);
}

.contact-form-input textarea {
	resize: none;
	font-family: inherit;
	line-height: 1.5;
}

.address-form {
	display: flex;
	flex-wrap: wrap;
	gap: 15px;
}

.address-form label {
	font-size: 14px;
	font-weight: 500;
	margin-bottom: 5px;
	width: 100%;
}

.address-form input, .address-form select {
	width: 100%;
	padding: 10px 12px;
	font-size: 14px;
	border: 1px solid #ddd;
	border-radius: 5px;
	outline: none;
}

.address-form input:focus, .address-form select:focus {
	border-color: #007bff;
	box-shadow: 0 0 5px rgba(0, 123, 255, 0.5);
}

/* Button Styling */
.button {
	display: inline-block;
	padding: 10px 16px;
	font-size: 14px;
	text-align: center;
	text-decoration: none;
	border-radius: 5px;
	transition: all 0.3s ease;
	cursor: pointer;
}

.button--md {
	background-color: #007bff;
	color: #fff;
	border: none;
}

.button--md:hover {
	background-color: #0056b3;
}

.button--outline {
	background-color: transparent;
	color: #007bff;
	border: 1px solid #007bff;
}

.button--outline:hover {
	background-color: #007bff;
	color: #fff;
}

.nice-select .current {
	color: black !important; /* Ghi đè màu */
	font-size: 18px;
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
				<form action="customer/customerProfile.htm" method="post"
					enctype="multipart/form-data">
					<div class="row gx-3">
						<div class="row gx-3">
							<div class="col-sm-6 col-12">
								<div class="dashboard__content-card-img">

									<div class="dashboard__content-img-wrapper">
										<div id="imagePreview"
											style="background-image: url('<c:url value='/assets/user-images/${loggedInUser.avatar}' />');"></div>
									</div>
									<div class="upload-image button button--outline">
										<input name="avatar" type='file' id="imageUpload"
											accept=".png, .jpg, .jpeg" id="imageUpload" /> <label
											for="imageUpload">Chọn ảnh</label>
									</div>
									<div class="upload-image button button--outline">
										<button name="upload-img">Upload</button>
									</div>
									${avatarErr }
								</div>

							</div>
						</div>

						<div class="row gx-3">

							<div class="col-sm-6 col-12 contact-form__content">



								<div class="contact-form-input">
									<label for="fname1">FullName </label> <input type="text"
										name="fullName" id="fname1" value="${loggedInUser.fullName }" />
									${nameErr }
								</div>
								<div class="contact-form-input">
									<label for="email1">Email </label> <input type="text"
										id="email1" value="${loggedInUser.gmail }" readonly="readonly" />
								</div>
								<div class="contact-form-input">
									<label for="number1">Phone Number</label> <input type="number"
										name="phoneNumber" id="number1"
										value="${loggedInUser.phoneNumber }" /> ${phoneErr }
								</div>
								<div class="contact-form-input">
									<label for="number1">Địa chỉ</label>
									<textarea rows="3" cols="100" readonly="readonly"
										style="resize: none; width: 100%; font-size: 14px; padding: 10px; line-height: 1.5;">
${loggedInUser.address.streetName }, ${loggedInUser.address.ward.name }, ${loggedInUser.address.ward.district.name }, ${loggedInUser.address.ward.district.province.name }
										</textarea>
								</div>


							</div>
							<div class="col-sm-6 col-12">
								<div class="address-form">
									<label for="province">Tỉnh/Thành phố:</label> <select
										id="province" name="provinceId" class="form-select"
										onchange="this.form.submit()">
										<option value="">${loggedInUser.address.ward.district.province.name }</option>
										<c:forEach var="province" items="${provinces}">
											<option value="${province.id}"
												${province.id == selectedProvinceId ? 'selected' : ''}>${province.name}</option>
										</c:forEach>
									</select> <input type="text" id="provinceText" readonly
										value="${selectedProvince.name}"
										placeholder="Tỉnh/Thành phố đã chọn" /> <label for="district">Quận/Huyện:</label>
									<select id="district" name="districtId" class="form-select"
										onchange="this.form.submit()">
										<option value="">${loggedInUser.address.ward.district.name }</option>
										<c:forEach var="district" items="${districts}">
											<option value="${district.id}"
												${district.id == selectedDistrictId ? 'selected' : ''}>${district.name}</option>
										</c:forEach>
									</select> <input type="text" id="districtText" readonly
										value="${selectedDistrict.name}"
										placeholder="Quận/Huyện đã chọn" /> <label for="ward">Xã/Phường:</label>
									<select id="ward" name="wardId" class="form-select"
										onchange="this.form.submit()">
										<option value="">${loggedInUser.address.ward.name }</option>
										<c:forEach var="ward" items="${wards}">
											<option value="${ward.id}">${ward.name}</option>
										</c:forEach>
									</select> <input type="text" id="wardText" readonly
										value="${selectedWard.name}" placeholder="Xã/Phường đã chọn" />
									<label for="fid-name">Tên đường:<span class="requite">*</span>
									</label> <input value="${loggedInUser.address.streetName }" type="text"
										id="streetName" name="streetName"
										placeholder="Nhập tên đường cụ thể" class="txt-input w-5">
									${streetErr }
								</div>

							</div>

						</div>
						<div class="contact-form-btn">
							<button class="button button--md" name="save">Lưu thay
								đổi</button>
						</div>
					</div>
				</form>
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
