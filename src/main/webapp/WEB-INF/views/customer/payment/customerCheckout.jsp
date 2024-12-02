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

.payment-icon {
	width: 35px;
	height: auto;
}

.payment-option img {
	vertical-align: middle;
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
				<li class="nav-item"><a href="customer/customerCart.htm" class="permal-link">Giỏ
						hàng</a></li>
				<li class="nav-item"><span class="current-page">Chọn phương thức thanh toán</span></li>
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
												id="lname2" value="${loggedInUser.fullName }"
												readonly="readonly" />
										</div>
									</div>

									<div class="contact-form__content-group"></div>
									<div class="contact-form-input">
										<label for="address">Address </label>
										<textarea id="address" rows="2" readonly="readonly"
											style="resize: none; width: 100%; font-size: 14px; padding: 10px; line-height: 1.5;">
${loggedInUser.address.streetName}, ${loggedInUser.address.ward.name}, ${loggedInUser.address.ward.district.name}, ${loggedInUser.address.ward.district.province.name}
                </textarea>
									</div>
									<div class="contact-form__content-group">
										<div class="contact-form-input">
											<label for="email"> Email </label> <input type="text"
												id="email" value="${loggedInUser.gmail }"
												readonly="readonly" />
										</div>
										<div class="contact-form-input">
											<label for="phone"> Phone </label> <input type="text"
												id="phone" value="${loggedInUser.phoneNumber }" />
										</div>
									</div>
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
								<label for="note">Ghi chú đơn hàng <span>(nếu có)</span>
								</label>
								<textarea name="notes" id="note"
									placeholder="Nhập địa chỉ nhận hàng nếu có. Nếu địa chỉ để trống hoặc không tồn tại, chúng tôi sẽ mặc định ship hàng tới địa chỉ mà bạn dùng làm đăng ký tài khoản."></textarea>
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

									<c:forEach var="cItem" items="${selectedCartItems }">

										<div class="bill-card__product-item">
											<div class="bill-card__product-item-content">
												<div class="img-wrapper">
													<img
														src="<c:url value='/assets/product-images/${cItem.product.image }'/>"
														alt="product-img" />
												</div>
												<h5 class="font-body--md-400">
													${cItem.product.productName } <span class="quantity">
														x${cItem.quantity }</span>
												</h5>
											</div>

											<p class="bill-card__product-price font-body--md-500">
												<fmt:formatNumber value="${cItem.product.price }"
													pattern="#,###.## VND;VND -#,###.##" type="currency"
													currencySymbol="VND" />
											</p>
										</div>

									</c:forEach>

								</div>
								<!-- memo  -->
								<div class="bill-card__memo">
									<!-- Subtotal  -->
									<div class="bill-card__memo-item subtotal">
										<p class="font-body--md-400">Tạm tính:</p>
										<span class="font-body--md-500"><fmt:formatNumber
												value="${totalPrice }" pattern="#,###.## VND;VND -#,###.##"
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
												value="${totalPrice }" pattern="#,###.## VND;VND -#,###.##"
												type="currency" currencySymbol="VND" /></span>
									</div>
									<div class="biolife-progress-bar">
										<table>
											<tr>
												<td class="first-position"><span class="index"><fmt:formatNumber
															value="${totalPrice }"
															pattern="#,###.## VND;VND -#,###.##" type="currency"
															currencySymbol="VND" /></span></td>
												<td class="mid-position">
													<div class="progress">
														<div class="progress-bar" role="progressbar"
															style="width: ${totalPrice * 100 / 1000000}%"
															aria-valuenow="${totalPrice * 100 / 1000000}"
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
								<form action="customer/authPayment.htm" method="post">
									<!-- Payment Methods -->
									<div class="bill-card__payment-method">
										<!-- Cash Payment -->
										<div class="bill-card__payment-method-item">
											<div class="form-check payment-option">
												<input class="form-check-input" type="radio" name="payment"
													id="cash" checked /> <label
													class="form-check-label font-body--400" for="cash">
													<img src="<c:url value='/assets/logos/cash.png'/>"
													alt="Cash Icon" class="payment-icon" /> Tiền mặt
												</label>
											</div>
										</div>

										<!-- Paypal Payment -->
										<div class="bill-card__payment-method-item">
											<div class="form-check payment-option">
												<input class="form-check-input" type="radio" name="payment"
													id="paypal" /> <label
													class="form-check-label font-body--400" for="paypal">
													<img src="<c:url value='/assets/logos/paypal.jpg'/>"
													alt="Paypal Icon" class="payment-icon" /> Paypal
												</label>
											</div>
										</div>

										<!-- Momo Payment -->
										<div class="bill-card__payment-method-item">
											<div class="form-check payment-option">
												<input class="form-check-input" type="radio" name="payment"
													id="momo" /> <label
													class="form-check-label font-body--400" for="momo">
													<img src="<c:url value='/assets/logos/momo.png'/>"
													alt="Momo Icon" class="payment-icon" /> Momo
												</label>
											</div>
										</div>
									</div>

									<!-- Checkout Button -->
									<button class="button button--lg w-100 checkout-btn"
										type="submit">Đặt hàng</button>
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