<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
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
.contact-form-input {
	margin-bottom: 15px;
}

.contact-form-input__dropdown {
	color: black;
	display: block;
	width: 100%;
	padding: 8px;
	margin-top: 5px;
	border: 1px solid #ccc;
	border-radius: 4px;
	box-sizing: border-box;
	display: block;
}

.checkout-btn {
	background-color: orange;
}
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
				<li class="nav-item"><a href="index.htm" class="permal-link">Trang
						chủ</a></li>
				<li class="nav-item"><span class="current-page">Đặt hàng</span></li>
			</ul>
		</nav>
	</div>
	<!-- Billing Section Start  -->
	<section class="section billing section--xl pt-0">
		<div class="container">
			<div class="row billing__content">
				<div class="col-lg-8">
					<div class="billing__content-card">
						<div class="billing__content-card-header">
							<h2 class="font-body--xxxl-500">Thông tin khách hàng</h2>
						</div>
						<div class="billing__content-card-body">
							<form action="#">
								<div class="contact-form__content">
									<div class="contact-form__content-group">
										<div class="contact-form-input">
											<label for="lname2">FullName </label> <input type="text"
												id="lname2" placeholder="Your last name" />
										</div>
									</div>



									<div class="contact-form__content-group">
										<!-- Country -->

										<!-- states -->
										<!-- <div class="contact-form-input">
											<label for="province">Tỉnh </label> <select id="province"
												class="contact-form-input__dropdown">
												<option value="">Chọn Tỉnh/Thành phố</option>
												<option value="01" selected>HCM</option>
												<option value="02">HN</option>
												<option value="03">DN</option>
											</select>
										</div>

										<div class="contact-form-input">
											<label for="district">Quận/Huyện</label> <select
												id="district" class="contact-form-input__dropdown">
												<option value="">Chọn Quận/Huyện</option>
												Các option này sẽ được cập nhật động dựa trên lựa chọn Tỉnh/Thành phố
											</select>
										</div>

										<div class="contact-form-input">
											<label for="ward">Phường/Xã</label> <select id="ward"
												class="contact-form-input__dropdown">
												<option value="">Chọn Phường/Xã</option>
												Các option này sẽ được cập nhật động dựa trên lựa chọn Quận/Huyện
											</select>
										</div> -->

										<!-- zip -->
										<!-- <div class="contact-form-input">
											<label for="zip">Zip Code</label> <select id="zip"
												class="contact-form-input__dropdown">
												<option value="01">1216</option>
												<option value="02">975</option>
												<option value="03">880</option>
												<option value="04">95</option>
											</select>
										</div> -->
									</div>
									<div class="contact-form-input">
										<label for="address">Address </label> <input type="text"
											id="address" placeholder="Your Address" />
									</div>
									<div class="contact-form__content-group">
										<div class="contact-form-input">
											<label for="email"> email </label> <input type="text"
												id="email" placeholder="Email Address" />
										</div>
										<div class="contact-form-input">
											<label for="phone"> Phone </label> <input type="number"
												id="phone" placeholder="Phone number" />
										</div>
									</div>
									<!-- 
									<div class="form-check">
										<input class="form-check-input" type="checkbox" value=""
											id="remember" /> <label
											class="form-check-label font-body--md-400" for="remember">
											Địa chỉ khác địa chỉ mặc định </label>
									</div> -->
								</div>
							</form>
						</div>
					</div>
					<div class="billing__content-card">
						<div class="billing__content-card-header">
							<h2 class="font-body--xxxl-500">Ghi chú</h2>
						</div>
						<div class="billing__content-card-body">
							<div class="contact-form-input contact-form-textarea">
								<label for="note">Ghi chú đơn hàng <span>...</span>
								</label>
								<!-- <input type="text" id="fname1" placeholder="Your first name" /> -->
								<textarea name="notes" id="note" placeholder="Ghi chú..."></textarea>
							</div>
						</div>
					</div>
				</div>
				<div class="col-lg-4">
					<div class="bill-card">
						<div class="bill-card__content">
							<div class="bill-card__header">
								<h2 class="bill-card__header-title font-body--xxl-500">
									Thông tin đặt hàng</h2>
							</div>
							<div class="bill-card__body">
								<!-- Product Info -->
								<div class="bill-card__product">
									<div class="bill-card__product-item">
										<div class="bill-card__product-item-content">
											<div class="img-wrapper">
												<img
													src="<c:url value='assets/cart/main/src/images/products/img-01.png'/>"
													alt="product-img" />
											</div>
											<h5 class="font-body--md-400">
												Green Apple <span class="quantity"> x5</span>
											</h5>
										</div>

										<p class="bill-card__product-price font-body--md-500">
											<fmt:formatNumber value="85000"
												pattern="#,###.## VND;VND -#,###.##" type="currency"
												currencySymbol="VND" />
										</p>
									</div>
									<div class="bill-card__product-item">
										<div class="bill-card__product-item-content">
											<div class="img-wrapper">
												<img
													src="<c:url value='/assets/cart/main/src/images/products/img-02.png'/>"
													alt="product-img" />
											</div>
											<h5 class="font-body--md-400">
												Orange <span class="quantity">x1</span>
											</h5>
										</div>

										<p class="bill-card__product-price font-body--md-500">
											<fmt:formatNumber value="85000"
												pattern="#,###.## VND;VND -#,###.##" type="currency"
												currencySymbol="VND" />
										</p>
									</div>
								</div>
								<!-- memo  -->
								<div class="bill-card__memo">
									<!-- Subtotal  -->
									<div class="bill-card__memo-item subtotal">
										<p class="font-body--md-400">Tạm tính:</p>
										<span class="font-body--md-500"><fmt:formatNumber
												value="85000" pattern="#,###.## VND;VND -#,###.##"
												type="currency" currencySymbol="VND" /></span>
									</div>
									<!-- Shipping  -->
									<div class="bill-card__memo-item shipping">
										<p class="font-body--md-400">Shipping:</p>
										<span class="font-body--md-500">Free</span>
									</div>
									<!-- total  -->
									<div class="bill-card__memo-item total">
										<p class="font-body--lg-400">Tổng tiền:</p>
										<span class="font-body--xl-500"><fmt:formatNumber
												value="85000" pattern="#,###.## VND;VND -#,###.##"
												type="currency" currencySymbol="VND" /></span>
									</div>
									<div class="biolife-progress-bar">
										<table>
											<tr>
												<td class="first-position"><span class="index"><fmt:formatNumber
															value="000000" pattern="#,###.## VND;VND -#,###.##"
															type="currency" currencySymbol="VND" /></span></td>
												<td class="mid-position">
													<div class="progress">
														<div class="progress-bar" role="progressbar"
															style="width: ${85000 * 100 / 1000000}%"
															aria-valuenow="${85000 * 100 / 1000000}"
															aria-valuemin="0" aria-valuemax="100"></div>
													</div>
												</td>
												<td class="last-position"><span class="index"><fmt:formatNumber
															value="1000000" pattern="#,###.## VND;VND -#,###.##"
															type="currency" currencySymbol="VND" /></span></td>
											</tr>
										</table>
										<p class="pickup-info">
											<b>Giới hạn tiêu dùng</b> <= 1.000.000/ngày
										</p>
									</div>

								</div>
							</div>
						</div>
						<div class="bill-card__content">
							<div class="bill-card__header">
								<div class="bill-card__header">
									<h2 class="bill-card__header-title font-body--xxl-500">
										Phương thức thanh toán</h2>
								</div>
							</div>
							<div class="bill-card__body">
								<form action="#">
									<!-- Payment Methods  -->
									<div class="bill-card__payment-method">
										<div class="bill-card__payment-method-item">
											<div class="form-check">
												<input class="form-check-input" type="radio" name="payment"
													id="cash" checked /> <label
													class="form-check-label font-body--400" for="cash">
													Tiền mặt </label>
											</div>
										</div>

										<div class="bill-card__payment-method-item">
											<div class="form-check">
												<input class="form-check-input" type="radio" name="payment"
													id="paypal" /> <label
													class="form-check-label font-body--400" for="paypal">
													Paypal </label>
											</div>
										</div>
										<div class="bill-card__payment-method-item">
											<div class="form-check">
												<input class="form-check-input" type="radio" name="payment"
													id="amazon" /> <label
													class="form-check-label font-body--400" for="amazon">
													Momo </label>
											</div>
										</div>
									</div>

									<button class="button button--lg w-100 checkout-btn"
										type="submit">
										<a href="customer/customerOrderList.htm">Đặt hàng</a>
									</button>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- Billing Section  End  -->
	<!-- FOOTER -->
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