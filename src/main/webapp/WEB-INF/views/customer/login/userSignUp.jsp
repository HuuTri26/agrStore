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
												<option value="${ward.id}"
													>${ward.name}</option>
											</c:forEach>
										</select> <input type="text" id="wardText" readonly
											value="${selectedWard.name}" placeholder="Xã/Phường đã chọn" />

									</div>

									</p>

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