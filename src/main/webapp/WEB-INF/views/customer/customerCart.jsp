<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="agrStore.utility.UltilityImpl"%>
<!DOCTYPE html>
<html>
<%@include file="/WEB-INF/views/include/customer/header.jsp"%>


<head>
<style>
.orange-line {
	border-bottom: 2px solid orange;
	margin: 20px 0;
}

.cart-checkbox { .
	margin-right: 10px;
	font-size: 15px;
	cursor: pointer;
}

.cart-checkbox:hover { .
	border: 1px solid orrange;
}

.table-container {
	max-height: 400px; /* Điều chỉnh chiều cao tùy theo nhu cầu */
	overflow-y: auto;
}

.shop_table {
	width: 100%;
	border-collapse: collapse;
}

.shop_table th, .shop_table td {
	border: 1px solid #ddd;
	padding: 8px;
}

.shop_table thead {
	position: sticky;
	top: 0;
	background-color: #f2f2f2;
}

.wrap-buttons {
	margin-top: 20px;
	text-align: right;
}

.btn {
	display: inline-block;
	padding: 10px 20px;
	margin: 0 5px;
	background-color: #ff7f00; /* Màu cam */
	color: white;
	border: none;
	border-radius: 25px; /* Bo tròn */
	text-decoration: none;
	font-weight: bold;
	cursor: pointer;
	transition: background-color 0.3s ease;
	font-size: 20px;
}

.btn:hover {
	background-color: #e67300; /* Màu cam đậm khi hover */
}

.btn:disabled {
	background-color: #cccccc;
	cursor: not-allowed;
}

/* Style riêng cho nút "Trở về trang chủ" */
.back-to-shop {
	background-color: #4CAF50; /* Màu xanh lá */
	float: left;
}

.back-to-shop:hover {
	background-color: #45a049; /* Màu xanh lá đậm khi hover */
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
	<%-- <!-- HEADER -->
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
	</header> --%>
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
				<li class="nav-item"><span class="current-page">Giỏ hàng</span></li>
			</ul>
		</nav>
	</div>

	<div class="page-contain shopping-cart">

		<!-- Main content -->
		<div id="main-content" class="main-content">
			<div class="container">

				<!--Cart Table-->
				<div class="shopping-cart-container">
					<div class="row">
						<div class="col-lg-9 col-md-12 col-sm-12 col-xs-12">
							<h3 class="box-title">Các sản phẩm trong giỏ hàng</h3>
							<form class="shopping-cart-form" action="customerCart.htm"
								method="get">
								<div class="table-container">
									<table class="shop_table cart-form">
										<thead>
											<tr>
												<th class="product-name">Tên sản phẩm</th>
												<th class="product-price">Đơn giá</th>
												<th class="product-quantity">Số lượng</th>
												<th class="product-subtotal">Tổng tiền</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach var="cItem" items="${cartItems }">

												<tr class="cart_item">
													<td class="product-thumbnail" data-title="Product Name">
														<input type="checkbox" class="cart-checkbox"
														name="selectedItemIds" value="${cItem.cartItemId}"
														onclick="this.form.submit()"
														${cItem.isSelected ? 'checked' : ''}> <a
														class="prd-thumb" href="#">
															<figure>
																<img width="113" height="113"
																	src="<c:url value='/assets/product-images/${cItem.product.image }'/>"
																	alt="shipping cart">
															</figure>
													</a> <a class="prd-name" href="#">${cItem.product.productName }</a>
														<div class="action">
															<a href="#" class="edit"><i class="fa fa-pencil"
																aria-hidden="true"></i></a> <a
																href="removeItemFromCart.htm?id=${cItem.cartItemId }"
																class="remove"><i class="fa fa-trash-o"
																aria-hidden="true"></i></a>
														</div>
													</td>
													<td class="product-price" data-title="Price">
														<div class="price price-contain">
															<ins>

																<span class="price-amount"> <!-- <span class="currencySymbol">$</span> -->


																	<fmt:formatNumber value="${cItem.product.price }"
																		pattern="#,###.## VND;VND -#,###.##" type="currency"
																		currencySymbol="VND" /></span>
															</ins>
															<!-- <del>
														<span class="price-amount"><span
															class="currencySymbol">£</span>95.00</span>
													</del> -->
														</div>
													</td>
													<td class="product-quantity" data-title="Quantity">
														<div class="quantity-box type1">
															<div class="qty-input">
																<input type="text" name="quantity_${cItem.cartItemId }"
																	value="${cItem.quantity }"
																	data-max_value="${cItem.product.quantity }"
																	data-min_value="1" data-step="1"> <a href="#"
																	class="qty-btn btn-up"><i class="fa fa-caret-up"
																	aria-hidden="true"></i></a> <a href="#"
																	class="qty-btn btn-down"><i
																	class="fa fa-caret-down" aria-hidden="true"></i></a>
															</div>
														</div>
													</td>
													<td class="product-subtotal" data-title="Total">
														<div class="price price-contain">
															<ins>
																<span class="price-amount"><fmt:formatNumber
																		value="${cItem.product.price * cItem.quantity}"
																		pattern="#,###.## VND;VND -#,###.##" type="currency"
																		currencySymbol="VND" /></span>
															</ins>
															<!-- <del>
														<span class="price-amount"><span
															class="currencySymbol">£</span>95.00</span>
													</del> -->
														</div>
													</td>
												</tr>

											</c:forEach>

										</tbody>
									</table>
								</div>
								<div class="cart_item wrap-buttons">
									<div class="wrap-btn-control" colspan="4">
										<a href="index.htm" class="btn back-to-shop">Trở về trang
											chủ</a>
										<button class="btn btn-update" name="update" type="submit">Cập
											nhật số lượng</button>
										<c:choose>
											<c:when test="${UltilityImpl.hasUnselectedItems(cartItems)}">
												<button class="btn btn-clear" name="selectedAll"
													type="submit" value="selectedAll">Chọn tất cả</button>
											</c:when>
											<c:otherwise>
												<button class="btn btn-clear" name="selectedAll"
													type="submit" value="selectedAll">Bỏ chọn tất cả</button>
											</c:otherwise>
										</c:choose>
										<button class="btn btn-clear" name="delete" type="submit">Xóa</button>
									</div>
								</div>
							</form>
						</div>
						<div class="col-lg-3 col-md-12 col-sm-12 col-xs-12">
							<div class="shpcart-subtotal-block">
								<div class="subtotal-line">
									<b class="stt-name">Tổng tiền <span class="sub">(2
											sản phẩm)</span></b> <span class="stt-price"><fmt:formatNumber
											value="170000" pattern="#,###.## VND;VND -#,###.##"
											type="currency" currencySymbol="VND" /></span>
								</div>
								<div class="subtotal-line">
									<b class="stt-name">Phí ship</b> <span class="stt-price"><fmt:formatNumber
											value="000000" pattern="#,###.## VND;VND -#,###.##"
											type="currency" currencySymbol="VND" /></span>
								</div>
								<div class="orange-line"></div>
								<div class="subtotal-line">
									<b class="stt-name">Thành tiền <span class="sub"> </span></b> <span
										class="stt-price"><fmt:formatNumber value="170000"
											pattern="#,###.## VND;VND -#,###.##" type="currency"
											currencySymbol="VND" /></span>
								</div>
								<!-- <div class="tax-fee">
									<p class="title">Est. Taxes & Fees</p>
									<p class="desc">Based on 56789</p>
								</div> -->
								<div class="btn-checkout">
									<a href="customerCheckout.htm" class="btn checkout">Mua</a>
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
														style="width: ${170000 * 100 / 1000000}%"
														aria-valuenow="${170000 * 100 / 1000000}"
														aria-valuemin="0" aria-valuemax="100"></div>
												</div>
											</td>
											<td class="last-position"><span class="index"><fmt:formatNumber
														value="1000000" pattern="#,###.## VND;VND -#,###.##"
														type="currency" currencySymbol="VND" /></span></td>
										</tr>
									</table>
								</div>
								<p class="pickup-info">
									<b>Giới hạn tiêu dùng</b> <= 1.000.000/ngày
								</p>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- Shopping Cart Section End    -->
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