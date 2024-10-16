<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%@include file="/WEB-INF/views/include/customer/header.jsp"%>
<head>
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

/* Căn chỉnh và điều chỉnh layout cho phần form */
.col-lg-6 {
	padding: 15px; /* Thêm khoảng cách giữa các cột */
}

/* Cải thiện giao diện của form */
.form-row {
	margin-bottom: 15px; /* Khoảng cách giữa các dòng */
}

/* Cải thiện form */
.address-form {
	display: flex;
	flex-direction: column;
}

/* Các input và select có chiều dài 100%, padding và border */
.address-form select, .address-form input {
	width: 100%; /* Đảm bảo các ô nhập chiếm toàn bộ chiều rộng */
	padding: 8px 12px; /* Điều chỉnh padding để ô nhập không quá to */
	margin-bottom: 10px; /* Khoảng cách giữa các ô nhập */
	border: 1px solid #ccc; /* Đường viền ô nhập */
	border-radius: 4px; /* Bo góc cho ô nhập */
	font-size: 14px; /* Điều chỉnh kích thước font */
	line-height: 1.4; /* Chiều cao dòng */
	box-sizing: border-box;
	/* Đảm bảo padding và border được tính trong chiều rộng */
}

/* Định dạng cho các label */
.address-form label {
	margin-bottom: 5px; /* Khoảng cách giữa label và ô nhập */
	font-weight: bold; /* Làm đậm label */
	font-size: 14px; /* Kích thước chữ */
}

/* Tạo hiệu ứng khi hover vào các ô input */
.address-form input:focus, .address-form select:focus {
	border-color: #007bff; /* Màu viền khi focus */
	outline: none; /* Loại bỏ outline mặc định */
}

/* Định dạng cho input readonly */
.address-form input[readonly] {
	background-color: #f0f0f0; /* Màu nền cho ô nhập readonly */
	cursor: not-allowed; /* Đổi con trỏ khi hover vào ô nhập readonly */
}

/* Nút submit */
.wrap-btn {
	display: flex;
	justify-content: center;
	margin-top: 20px;
}

.btn-submit {
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

.btn-submit:hover {
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
				<li class="nav-item"><span class="current-page">Sign Up</span></li>
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

							<form action="userSignUp.htm" name="frm-login" method="post">
								<div class="col-lg-6 ">
									<p class="form-row">
										<label for="fid-name">Gmail:<span class="requite">*</span></label>
										<input type="text" id="fid-name" name="gmail"
											value="${account.gmail }" class="txt-input"
											readonly="readonly">
									</p>
									<p class="form-row">
										<label for="fid-pass">Mật khẩu:<span class="requite">*</span></label>
										<input type="password" id="fid-pass" name="password" value=""
											class="txt-input"> ${passErr }
									</p>
									<p class="form-row">
										<label for="fid-pass">Nhập lại mật khẩu:<span
											class="requite">*</span></label> <input type="password" id="fid-pass"
											name="re-enter-password" value="" class="txt-input">
										${rePassErr }
									</p>
									<p class="form-row">
										<label for="fid-name">Họ và tên:<span class="requite">*</span></label>
										<input required type="text" id="fid-name" name="full-name"
											value="" class="txt-input"> ${nameErr }
									</p>
									<p class="form-row">
										<label for="fid-pass">Số điện thoại:<span
											class="requite">*</span></label> <input required type="text"
											id="fid-pass" name="phone-number" value="" class="txt-input">
										${phoneErr }
									</p>
								</div>
								<div class="col-lg-6 ">

									<p class="form-row">
										<label for="fid-pass">Địa chỉ<span class="requite">*</span></label>
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
											placeholder="vd: Số 23, Đường Lê Văn Việt" class="txt-input w-5">
									</div>
									${streetErr }
									<p class="form-row wrap-btn">
										<button class="btn btn-submit btn-bold" name="sign-up">Sign
											up</button>
									</p>
								</div>
							</form>

						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- <script>
		function fillProvinceText() {
			var provinceSelect = document.getElementById("province");
			var provinceText = provinceSelect.options[provinceSelect.selectedIndex].text;
			document.getElementById("provinceText").value = provinceText;
		}

		function fillDistrictText() {
			var districtSelect = document.getElementById("district");
			var districtText = districtSelect.options[districtSelect.selectedIndex].text;
			document.getElementById("districtText").value = districtText;
		}

		function fillWardText() {
			var wardSelect = document.getElementById("ward");
			var wardText = wardSelect.options[wardSelect.selectedIndex].text;
			document.getElementById("wardText").value = wardText;
		}
	</script> -->

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