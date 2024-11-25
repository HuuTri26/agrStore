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

/* Cải thiện layout cho phần address-form * /
.col-lg-3 {
	padding: 15px; /* Khoảng cách giữa các cột */
margin-bottom






:



 



20px






; /* Khoảng cách phía dưới */
}

/* Form address */
.address-form {
	display: flex;
	flex-direction: column;
}

/* Các input, select có chiều dài 100%, padding và border */
.address-form select, .address-form input {
	width: 100%; /* Đảm bảo chiều rộng của ô nhập/select là 100% */
	padding: 8px 12px; /* Padding cho các ô nhập */
	margin-bottom: 10px; /* Khoảng cách giữa các ô nhập */
	border: 1px solid #ccc; /* Đường viền ô nhập */
	border-radius: 4px; /* Bo góc cho ô nhập */
	font-size: 14px; /* Kích thước chữ */
	line-height: 1.4; /* Chiều cao dòng */
	box-sizing: border-box;
	/* Đảm bảo padding và border được tính vào chiều rộng */
}

/* Định dạng cho các label */
.address-form label {
	margin-bottom: 5px; /* Khoảng cách giữa label và ô nhập */
	font-weight: bold; /* Làm đậm label */
	font-size: 14px; /* Kích thước chữ */
}

/* Hiệu ứng khi hover vào input */
.address-form input:focus, .address-form select:focus {
	border-color: #007bff; /* Màu viền khi focus */
	outline: none; /* Loại bỏ outline mặc định */
}

/* Input readonly với màu nền khác */
.address-form input[readonly] {
	background-color: #f0f0f0; /* Màu nền cho ô nhập readonly */
	cursor: not-allowed; /* Đổi con trỏ khi hover vào ô nhập readonly */
}

/* Nút submit */
.contact-form-btn {
	display: flex;
	justify-content: center; /* Căn giữa nút */
	margin-top: 20px;
}

.button--md {
	padding: 10px 20px; /* Điều chỉnh kích thước nút */
	background-color: #007bff; /* Màu nền của nút */
	color: white;
	border: none;
	border-radius: 4px; /* Bo góc cho nút */
	font-size: 16px; /* Kích thước chữ */
	cursor: pointer; /* Con trỏ khi hover vào nút */
	transition: background-color 0.3s ease;
	/* Hiệu ứng chuyển màu nền khi hover */
}

.button--md:hover {
	background-color: #0056b3; /* Màu nền khi hover */
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
		<div class="dashboard__content-card">s
			<div class="dashboard__content-card-header">
				<h5 class="font-body--xxl-500">Profile</h5>
			</div>
			<div class="dashboard__content-card-body">
				<div class="row">
					<div class="col-lg-3 order-lg-0 order-1">
						<div class="dashboard__content-card-img">
							<form action="customerProfile.htm" method="post"
								enctype="multipart/form-data" style="text-align: center">
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
							</form>
						</div>
					</div>
					<div class="col-lg-9 order-lg-0 order-2">

						<form action="customerProfile.htm" method="post">
							<div class="contact-form__content">
								<div class="col-lg-6 order-lg-0 order-2">
									<div class="contact-form-input">
										<label for="fname1">FullName </label> <input type="text"
											name="fullName" id="fname1" value="${loggedInUser.fullName }" />
										${nameErr }
									</div>
									<div class="contact-form-input">
										<label for="email1">Email </label> <input type="text"
											id="email1" value="${loggedInUser.gmail }"
											readonly="readonly" />
									</div>
									<div class="contact-form-input">
										<label for="number1">Phone Number</label> <input type="number"
											name="phoneNumber" id="number1"
											value="${loggedInUser.phoneNumber }" /> ${phoneErr }
									</div>
									<div class="contact-form-input">
										<label for="number1">Địa chỉ</label>
										<textarea rows="5" cols="100" readonly="readonly">${loggedInUser.address.streetName }, ${loggedInUser.address.ward.name }, ${loggedInUser.address.ward.district.name }, ${loggedInUser.address.ward.district.province.name }</textarea>
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
										<label for="fid-name">Tên đường:<span class="requite">*</span></label>
										<input type="text" id="streetName" name="streetName"
											placeholder="Nhập tên đường cụ thể" class="txt-input w-5">
										${streetErr }
									</div>
									<div class="contact-form-btn">
										<button class="button button--md" name="save">Lưu
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
