<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%@include file="/WEB-INF/views/include/customer/header.jsp"%>
<head>
<link rel="stylesheet"
	href="<c:url value='/assets/cart/main/src/lib/css/swiper-bundle.min.css'/>" />
<link rel="stylesheet"
	href="<c:url value='/assets/cart/main/src/lib/css/bvselect.css'/>" />
<link rel="stylesheet"
	href="<c:url value='/assets/cart/main/src/lib/css/bootstrap.min.css'/>" />
<link rel="stylesheet"
	href="<c:url value='/assets/cart/main/src/css/style.css'/>" />
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
				<li class="nav-item"><span class="current-page">Danh
						sách đơn hàng</span></li>
			</ul>
		</nav>
	</div>
	<!-- dashboard Secton Start  -->
	<div class="dashboard section">
		<div class="container">
			<div class="row dashboard__content">
				<div class="col-lg-12 section--xl pt-0">
					<div class="container">
						<!-- Order History  -->
						<div class="dashboard__order-history">
							<div class="dashboard__order-history-title">
								<h2 class="font-body--xxl-500">Chi tiết đơn hàng</h2>
								<a href="order-history.html">Trở về</a>
							</div>

							<div class="dashboard__details-content">
								<div class="row">
									<div class="col-xl-8">
										<div class="dashboard__details-card">
											<div class="dashboard__details-card-item">
												<h5 class="dashboard__details-card-title">Địa chỉ khách
													hàng</h5>
												<!-- billing Address -->
												<div class="dashboard__details-card-item__inner">
													<h2 class="font-body--lg-400 name">Dainne Russeell</h2>
													<p class="font-body--md-400">4140 Parker Rd. Allentown,
														New Mexico 31134</p>
												</div>
												<div class="dashboard__details-card-item__inner">
													<div
														class="
                                dashboard__details-card-item__inner-contact
                              ">
														<h5 class="title">Email</h5>
														<p class="font-body--md-400">dainne.ressell@gmail.com
														</p>
													</div>
													<div
														class="
                                dashboard__details-card-item__inner-contact
                              ">
														<h5 class="title">Phone</h5>
														<p class="font-body--md-400">(671) 555-0110</p>
													</div>
												</div>
											</div>
											<div class="dashboard__details-card-item">
												<h5 class="dashboard__details-card-title">Shipping
													Address</h5>
												<!-- Shipping Address -->
												<div class="dashboard__details-card-item__inner">
													<h2 class="font-body--lg-400 name">Dainne Russeell</h2>
													<p class="font-body--md-400">4140 Parker Rd. Allentown,
														New Mexico 31134</p>
												</div>
												<div class="dashboard__details-card-item__inner">
													<div
														class="
                                dashboard__details-card-item__inner-contact
                              ">
														<h5 class="title">Email</h5>
														<p class="font-body--md-400">dainne.ressell@gmail.com
														</p>
													</div>
													<div
														class="
                                dashboard__details-card-item__inner-contact
                              ">
														<h5 class="title">Phone</h5>
														<p class="font-body--md-400">(671) 555-0110</p>
													</div>
												</div>
											</div>
										</div>
									</div>
									<div class="col-xl-4">
										<div class="dashboard__totalpayment-card">
											<div class="dashboard__totalpayment-card-header">
												<div class="dashboard__totalpayment-card-header-item">
													<h5 class="title">Order id:</h5>
													<p class="details order-id">#4152</p>
												</div>
												<div class="dashboard__totalpayment-card-header-item">
													<h5 class="title">Payment Method:</h5>
													<p class="details order-id">Paypal</p>
												</div>
											</div>

											<div class="dashboard__totalpayment-card-body">
												<div class="dashboard__totalpayment-card-body-item">
													<h5 class="font-body--md-400">Subtotal:</h5>
													<p class="font-body--md-500">$365.00</p>
												</div>
												<div class="dashboard__totalpayment-card-body-item">
													<h5 class="font-body--md-400">Discount:</h5>
													<p class="font-body--md-500">20%</p>
												</div>
												<div class="dashboard__totalpayment-card-body-item">
													<h5 class="font-body--md-400">Shipping:</h5>
													<p class="font-body--md-500">Free</p>
												</div>
												<div class="dashboard__totalpayment-card-body-item total">
													<h5 class="font-body--xl-400">Total:</h5>
													<p class="font-body--xl-500">$84.00</p>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>

							<div class="progress__bar progress__bar-1x">
								<div class="progress__bar-border">
									<span class="progress__bar-border-active"></span>
								</div>
								<div class="progress__bar-item active">
									<div class="progress__bar-item-ball">
										<p
											class="
                          font-body--md-400
                          count-number count-number-active
                        ">
											01</p>
										<span class="check-mark"> <svg width="20" height="20"
												viewBox="0 0 20 20" fill="none"
												xmlns="http://www.w3.org/2000/svg">
                          <path
													d="M16.6663 5.83301L7.49967 14.9997L3.33301 10.833"
													stroke="currentColor" stroke-width="2"
													stroke-linecap="round" stroke-linejoin="round" />
                        </svg>
										</span>
									</div>
									<h2 class="font-body--md-400">Order received</h2>
								</div>
								<div class="progress__bar-item">
									<div class="progress__bar-item-ball">
										<p class="font-body--md-400 count-number">02</p>
									</div>
									<h2 class="font-body--md-400">Processing</h2>
								</div>
								<div class="progress__bar-item">
									<div class="progress__bar-item-ball">
										<p class="font-body--md-400 count-number">03</p>
									</div>
									<h2 class="font-body--md-400">one the way</h2>
								</div>
								<div class="progress__bar-item">
									<div class="progress__bar-item-ball">
										<p class="font-body--md-400 count-number">04</p>
									</div>
									<h2 class="font-body--md-400">Delivered</h2>
								</div>
							</div>

							<div
								class="
                    dashboard__order-history-table
                    dashboard__order-history-table__product-content
                  ">
								<div class="table-responsive">
									<table class="table">
										<thead>
											<tr>
												<th scope="col" class="dashboard__order-history-table-title">
													Product</th>
												<th scope="col" class="dashboard__order-history-table-title">
													Price</th>
												<th scope="col" class="dashboard__order-history-table-title">
													quantity</th>
												<th scope="col" class="dashboard__order-history-table-title">
													Subtotal</th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<!-- Product item  -->
												<td
													class="
                              dashboard__order-history-table-item
                              align-middle
                            ">
													<a href="product-details.html"
													class="dashboard__product-item">
														<div class="dashboard__product-item-img">
															<img
																src="<c:url value='/assets/cart/main/src/images/products/img-01.png'/>"
																alt="product" />
														</div>
														<h5 class="font-body--md-400">Apple</h5>
												</a>
												</td>
												<!-- Price  -->
												<td
													class="
                              dashboard__order-history-table-item
                              order-date
                              align-middle
                            ">
													$14.00</td>
												<!-- quantity -->
												<td
													class="
                              dashboard__order-history-table-item
                              order-total
                              align-middle
                            ">
													<p class="order-total-price">x5</p>
												</td>
												<!-- Subtotal  -->
												<td
													class="
                              dashboard__order-history-table-item
                              order-status
                              align-middle
                            "
													style="text-align: left">
													<p class="font-body--md-500">$70.00</p>
												</td>
											</tr>
											<tr>
												<!-- Product item  -->
												<td
													class="
                              dashboard__order-history-table-item
                              align-middle
                            ">
													<a href="product-details.html"
													class="dashboard__product-item">
														<div class="dashboard__product-item-img">
															<img
																src="<c:url value='/assets/cart/main/src/images/products/img-02.png'/>"
																alt="product" />
														</div>
														<h5 class="font-body--md-400">Orrange</h5>
												</a>
												</td>
												<!-- Price  -->
												<td
													class="
                              dashboard__order-history-table-item
                              order-date
                              align-middle
                            ">
													$14.00</td>
												<!-- quantity -->
												<td
													class="
                              dashboard__order-history-table-item
                              order-total
                              align-middle
                            ">
													<p class="order-total-price">x2</p>
												</td>
												<!-- Subtotal  -->
												<td
													class="
                              dashboard__order-history-table-item
                              order-status
                              align-middle
                            "
													style="text-align: left">
													<p class="font-body--md-500">$28.00</p>
												</td>
											</tr>
											<tr>
												<!-- Product item  -->
												<td
													class="
                              dashboard__order-history-table-item
                              align-middle
                            ">
													<a href="product-details.html"
													class="dashboard__product-item">
														<div class="dashboard__product-item-img">
															<img
																src="<c:url value='/assets/cart/main/src/images/products/img-01.png'/>"
																alt="product" />
														</div>
														<h5 class="font-body--md-400">Apple</h5>
												</a>
												</td>
												<!-- Price  -->
												<td
													class="
                              dashboard__order-history-table-item
                              order-date
                              align-middle
                            ">
													$26.00</td>
												<!-- quantity -->
												<td
													class="
                              dashboard__order-history-table-item
                              order-total
                              align-middle
                            ">
													<p class="order-total-price">x10</p>
												</td>
												<!-- Subtotal  -->
												<td
													class="
                              dashboard__order-history-table-item
                              order-status
                              align-middle
                            "
													style="text-align: left">
													<p class="font-body--md-500">$267.00</p>
												</td>
											</tr>
										</tbody>
									</table>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<script
		src="<c:url value='/assets/cart/main/src/lib/js/jquery.min.js'/>"></script>
	<script
		src="<c:url value='/assets/cart/main/src/lib/js/swiper-bundle.min.js'/>"></script>
	<script src="<c:url value='/assets/cart/main/src/lib/js/bvselect.js'/>"></script>
	<script
		src="<c:url value='/assets/cart/main/src/lib/js/bootstrap.bundle.min.js'/>"></script>
	<script src="<c:url value='/assets/cart/main/src/js/main.js'/>"></script>
	f
	<%@include file="/WEB-INF/views/include/customer/footer.jsp"%>

	<!--Footer For Mobile-->
	<!--Mobile Global Menu-->
	<!--Quickview Popup-->
	<%@include file="/WEB-INF/views/include/customer/footerMobile.jsp"%>

	<!-- Scroll Top Button and JS -->
	<%@include file="/WEB-INF/views/include/customer/js.jsp"%>
</body>
</html>