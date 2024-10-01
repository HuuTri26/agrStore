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

							<form action="#" name="frm-login" method="post">
								<div class="col-lg-6 ">
									<p class="form-row">
										<label for="fid-name">Email:<span class="requite">*</span></label>
										<input required type="email" id="fid-name" name="name"
											value="" class="txt-input">
									</p>
									<p class="form-row">
										<label for="fid-pass">Password:<span class="requite">*</span></label>
										<input required type="email" id="fid-pass" name="password"
											value="" class="txt-input">
									</p>
									<p class="form-row">
										<label for="fid-pass">Re- Password:<span
											class="requite">*</span></label> <input required type="email"
											id="fid-pass" name="password" value="" class="txt-input">
									</p>
									<p class="form-row">
										<label for="fid-name">Name:<span class="requite">*</span></label>
										<input required type="text" id="fid-name" name="name" value=""
											class="txt-input">
									</p>
									<p class="form-row">
										<label for="fid-pass">Phone number:<span
											class="requite">*</span></label> <input required type="text"
											id="fid-pass" name="password" value="" class="txt-input">
									</p>
								</div>
								<div class="col-lg-6 ">

									<p class="form-row">
										<label for="fid-pass">Dia chi<span class="requite">*</span></label>
									<div class="address-form">
										<label for="province">Tỉnh/Thành phố:</label> <select
											id="province" class="form-select">
											<option value="">Chọn Tỉnh/Thành phố</option>
											<option value="HCM">TP. Hồ Chí Minh</option>
											<option value="HN">Hà Nội</option>
											<option value="DN">Đà Nẵng</option>
											<!-- Thêm các tỉnh/thành phố khác -->
										</select> <label for="district">Quận/Huyện:</label> <select
											id="district" class="form-select">
											<option value="">Chọn Quận/Huyện</option>
											<option value="Q1">Quận 1</option>
											<option value="Q2">Quận 2</option>
											<option value="Q3">Quận 3</option>
											<!-- Thêm các quận/huyện khác -->
										</select> <label for="ward">Xã/Phường:</label> <select id="ward"
											class="form-select">
											<option value="">Chọn Xã/Phường</option>
											<option value="P1">Phường 1</option>
											<option value="P2">Phường 2</option>
											<option value="P3">Phường 3</option>
											<!-- Thêm các xã/phường khác -->
										</select>
										<!-- <label for="street">Đường:</label> <input type="text"
											id="street" class="form-control" placeholder="Nhập tên đường" /> -->
									</div>
									</p>

									<p class="form-row wrap-btn">
										<button class="btn btn-submit btn-bold" type="submit">Sign
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