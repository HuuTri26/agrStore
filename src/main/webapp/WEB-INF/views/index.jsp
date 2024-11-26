<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html class="no-js" lang="en">

<head>
<style>
.tab-head .tabs .tab-element {
	margin: 0 50px; /* Khoảng cách đều cả hai bên của mỗi thẻ */
}
</style>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>agrStore</title>
<link
	href="https://fonts.googleapis.com/css?family=Cairo:400,600,700&amp;display=swap"
	rel="stylesheet">
<link
	href="https://fonts.googleapis.com/css?family=Poppins:600&amp;display=swap"
	rel="stylesheet">
<link
	href="https://fonts.googleapis.com/css?family=Playfair+Display:400i,700i"
	rel="stylesheet">
<link
	href="https://fonts.googleapis.com/css?family=Ubuntu&amp;display=swap"
	rel="stylesheet">
<link rel="shortcut icon" type="image/x-icon"
	href="<c:url value='/assets/assets/images/favicon.png'/>" />
<link rel="stylesheet" type="text/css"
	href="<c:url value='/assets/assets/css/bootstrap.min.css'/>">
<link rel="stylesheet" type="text/css"
	href="<c:url value='/assets/assets/css/animate.min.css'/>">
<link rel="stylesheet" type="text/css"
	href="<c:url value='/assets/assets/css/font-awesome.min.css'/>">
<link rel="stylesheet" type="text/css"
	href="<c:url value='/assets/assets/css/nice-select.css'/>">
<link rel="stylesheet" type="text/css"
	href="<c:url value='/assets/assets/css/slick.min.css'/>">
<link rel="stylesheet" type="text/css"
	href="<c:url value='/assets/assets/css/style.css'/>">
<link rel="stylesheet" type="text/css"
	href="<c:url value='/assets/assets/css/main-color.css'/>">

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
						<li><a href="#"><i class="fa fa-envelope"
								aria-hidden="true"></i>huutri91thcslvm@gmail.com</a></li>
					</ul>
				</div>
				<div class="top-bar right">
					<ul class="horizontal-menu">
						<li><a href="login.html" class="login-link"><i
								class="biolife-icon icon-login"></i>Đăng nhập/Đăng Ký</a></li>
					</ul>
				</div>
			</div>
		</div>
		<div class="header-middle biolife-sticky-object ">
			<div class="container">
				<div class="row">
					<div class="col-lg-3 col-md-2 col-md-6 col-xs-6">
						<a href="index-2.html" class="biolife-logo"><img
							src="<c:url value='/assets/assets/images/organic-3.png'/>"
							alt="biolife logo" width="135" height="34"></a>
					</div>
					<div class="col-lg-6 col-md-7 hidden-sm hidden-xs">
						<div class="primary-menu">
							<ul class="menu biolife-menu clone-main-menu clone-primary-menu"
								id="primary-menu" data-menuname="main menu">
								<li class="menu-item"><a href="index-2.html">Trang chủ</a></li>
								<li class="menu-item menu-item-has-children has-megamenu">
									<a href="#" class="menu-name" data-title="product">Sản phẩm</a>
								</li>
								<li class="menu-item menu-item-has-children has-child"><a
									href="#" class="menu-name" data-title="provider">Nhà cung
										cấp</a></li>
								<li class="menu-item menu-item-has-children has-child"><a
									href="#" class="menu-name" data-title="category">Loại nông
										sản</a></li>
							</ul>
						</div>
					</div>
					<div class="col-lg-3 col-md-3 col-md-6 col-xs-6">
						<div class="biolife-cart-info">
							<div class="mobile-search">
								<a href="javascript:void(0)" class="open-searchbox"><i
									class="biolife-icon icon-search"></i></a>
								<div class="mobile-search-content">
									<form action="#" class="form-search" name="mobile-seacrh"
										method="get">
										<a href="#" class="btn-close"><span
											class="biolife-icon icon-close-menu"></span></a> <input
											type="text" name="s" class="input-text" value=""
											placeholder="Search here..."> <select name="category">
											<option value="-1" selected>Tất cả loại</option>
											<option value="vegetables">Rau củ</option>
											<option value="fresh_berries">Tươi sống</option>
											<option value="ocean_foods">Hải sản</option>
											<option value="fastfood">Thức ăn nhanh</option>
											<option value="fresh_onion">Thực phẩm đống hộp</option>
											<option value="papaya_crisps">Lương khô</option>
											<option value="snack">Ăn vặt</option>
										</select>
										<button type="submit" class="btn-submit">go</button>
									</form>
								</div>
							</div>
							<div class="wishlist-block hidden-sm hidden-xs">
								<a href="#" class="link-to"> <span class="icon-qty-combine">
										<i class="icon-heart-bold biolife-icon"></i> <span class="qty">4</span>
								</span>
								</a>
							</div>
							<div class="minicart-block">
								<div class="minicart-contain">
									<a href="" class="link-to"> <span class="icon-qty-combine">
											<i class="icon-cart-mini biolife-icon"></i> <span class="qty">8</span>
									</span> <span class="title">Giỏ hàng -</span> <span class="sub-total">0.00</span>
									</a>
									<div class="cart-content">
										<div class="cart-inner">
											<ul class="products">
												<li>
													<div class="minicart-item">
														<div class="thumb">
															<a href="#"><img
																src="<c:url value='/assets/assets/images/minicart/pr-01.jpg'/>"
																width="90" height="90" alt="National Fresh"></a>
														</div>
														<div class="left-info">
															<div class="product-title">
																<a href="#" class="product-name">National Fresh
																	Fruit</a>
															</div>
															<div class="price">
																<ins>
																	<span class="price-amount"><span
																		class="currencySymbol">£</span>85.00</span>
																</ins>
																<del>
																	<span class="price-amount"><span
																		class="currencySymbol">£</span>95.00</span>
																</del>
															</div>
															<div class="qty">
																<label for="cart[id123][qty]">Qty:</label> <input
																	type="number" class="input-qty" name="cart[id123][qty]"
																	id="cart[id123][qty]" value="1" disabled>
															</div>
														</div>
														<div class="action">
															<a href="#" class="edit"><i class="fa fa-pencil"
																aria-hidden="true"></i></a> <a href="#" class="remove"><i
																class="fa fa-trash-o" aria-hidden="true"></i></a>
														</div>
													</div>
												</li>
											</ul>
											<p class="btn-control">
												<a href="#" class="btn view-cart">Xem giỏ hàng</a> <a
													href="#" class="btn">Thanh Toán</a>
											</p>
										</div>
									</div>
								</div>
							</div>
							<div class="mobile-menu-toggle">
								<a class="btn-toggle" data-object="open-mobile-menu"
									href="javascript:void(0)"> <span></span> <span></span> <span></span>
								</a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="header-bottom hidden-sm hidden-xs">
			<div class="container">
				<div class="row">
					<div class="col-lg-3 col-md-4">
						<div class="vertical-menu vertical-category-block">
							<div class="block-title">
								<span class="menu-icon"> <span class="line-1"></span> <span
									class="line-2"></span> <span class="line-3"></span>
								</span> <span class="menu-title">Tất cả loại</span> <span class="angle"
									data-tgleclass="fa fa-caret-down"><i
									class="fa fa-caret-up" aria-hidden="true"></i></span>
							</div>
							<div class="wrap-menu">
								<ul class="menu clone-main-menu">
									<li class="menu-item menu-item-has-children has-megamenu">
										<a href="#" class="menu-name" data-title="Fruit & Nut Gifts"><i
											class="biolife-icon icon-fruits"></i>Trái cây</a>
									</li>
									<li class="menu-item menu-item-has-children has-megamenu">
										<a href="#" class="menu-name" data-title="Vegetables"><i
											class="biolife-icon icon-broccoli-1"></i>Rau củ</a>
									</li>
									<li class="menu-item menu-item-has-children has-megamenu">
										<a href="#" class="menu-name" data-title="Fresh Berries"><i
											class="biolife-icon icon-grape"></i>Thực phẩm tươi sống</a>
									</li>
									<li class="menu-item"><a href="#" class="menu-name"
										data-title="Ocean Foods"><i class="biolife-icon icon-fish"></i>Hải
											sản</a></li>
									<li class="menu-item menu-item-has-children has-child"><a
										href="#" class="menu-name" data-title="Butter & Eggs"><i
											class="biolife-icon icon-honey"></i>Thực phẩm đống hộp</a></li>
									<li class="menu-item"><a href="#" class="menu-title"><i
											class="biolife-icon icon-fast-food"></i>Thức ăn nhanh</a></li>
									<li class="menu-item"><a href="#" class="menu-title"><i
											class="biolife-icon icon-beef"></i>Thịt tươi</a></li>

									<li class="menu-item"><a href="#" class="menu-title"><i
											class="biolife-icon icon-avocado"></i>Snack</a></li>

								</ul>
							</div>
						</div>
					</div>
					<div class="col-lg-9 col-md-8 padding-top-2px">
						<div class="header-search-bar layout-01">
							<form action="#" class="form-search" name="desktop-seacrh"
								method="get">
								<input type="text" name="s" class="input-text" value=""
									placeholder="Search here..."> <select name="category">
									<option value="-1" selected>Tất cả</option>
									<option value="vegetables">Rau củ</option>
									<option value="fresh_berries">Thực phẩm sống</option>
									<option value="ocean_foods">Hải sản</option>
									<option value="butter_eggs">Butter & Eggs</option>
									<option value="fastfood">Thức ăn nhanh</option>

									<option value="papaya_crisps">Snack</option>
									<option value="oatmeal">khác</option>
								</select>
								<button type="submit" class="btn-submit">
									<i class="biolife-icon icon-search"></i>
								</button>
							</form>
						</div>
						<div class="live-info">
							<p class="telephone">
								<i class="fa fa-phone" aria-hidden="true"></i><b
									class="phone-number"> 123 456 7891</b>
							</p>
							<p class="working-time">Mon-Fri: 8:30am-7:30pm; Sat-Sun:
								9:30am-4:30pm</p>
						</div>
					</div>
				</div>
			</div>
		</div>
	</header>

	<!-- Page Contain -->
	<div class="page-contain">

		<!-- Main content -->
		<div id="main-content" class="main-content">

			<!--Block 03: Product Tab-->
			<div
				class="product-tab z-index-20 sm-margin-top-193px xs-margin-top-30px">
				<div class="container">
					<div class="biolife-title-box">
						<h2>
							<span class="subtitle">PRODUCT</span>
						</h2>
						<!-- <h3 class="main-title">Related Products</h3> -->
					</div>
					<div class="biolife-tab biolife-tab-contain sm-margin-top-34px">
						<!-- <div class="tab-head tab-head__icon-top-layout icon-top-layout">
							<ul class="tabs md-margin-bottom-35-im xs-margin-bottom-40-im">
								<li class="tab-element active"><a href="#tab01_1st"
									class="tab-link"><span class="biolife-icon icon-lemon"></span>Rau
										củ</a></li>
								<li class="tab-element"><a href="#tab01_2nd"
									class="tab-link"><span class="biolife-icon icon-grape2"></span>Trái
										cây</a></li>
								<li class="tab-element"><a href="#tab01_3rd"
									class="tab-link"><span class="biolife-icon icon-blueberry"></span>Thực
										phẩm sống</a></li>
								<li class="tab-element"><a href="#tab01_4th"
									class="tab-link"><span class="biolife-icon icon-orange"></span>Thức
										ăn nhanh</a></li>
								<li class="tab-element"><a href="#tab01_5th"
									class="tab-link"><span class="biolife-icon icon-broccoli"></span>snack</a>
								</li>
							</ul>
						</div> -->
						<div class="tab-content">
							<div id="tab01_1st" class="tab-contain active">
								<ul
									class="products-list biolife-carousel nav-center-02 nav-none-on-mobile eq-height-contain"
									data-slick='{"rows":2 ,"arrows":true,"dots":false,"infinite":true,"speed":400,"slidesMargin":10,"slidesToShow":4, "responsive":[{"breakpoint":1200, "settings":{ "slidesToShow": 4}},{"breakpoint":992, "settings":{ "slidesToShow": 3, "slidesMargin":25 }},{"breakpoint":768, "settings":{ "slidesToShow": 2, "slidesMargin":15}}]}'>
									<li class="product-item">
										<div class="contain-product layout-default">
											<div class="product-thumb">
												<a href="#" class="link-to-product"> <img
													src="<c:url value='/assets/assets/images/products/p-05.jpg'/>"
													alt="Vegetables" width="270" height="270"
													class="product-thumnail">
												</a> <a class="lookup btn_call_quickview" href="#"><i
													class="biolife-icon icon-search"></i></a>
											</div>
											<div class="info">
												<b class="categories">Vegetables</b>
												<h4 class="product-title">
													<a href="#" class="pr-name">Organic Hass Avocado, Large</a>
												</h4>
												<div class="price ">
													<ins>
														<span class="price-amount"><span
															class="currencySymbol">£</span>85.00</span>
													</ins>
													<del>
														<span class="price-amount"><span
															class="currencySymbol">£</span>95.00</span>
													</del>
												</div>
												<div class="slide-down-box">
													<p class="message">All products are carefully selected
														to ensure food safety.</p>
													<div class="buttons">
														<a href="#" class="btn wishlist-btn"><i
															class="fa fa-heart" aria-hidden="true"></i></a> <a href="#"
															class="btn add-to-cart-btn"><i
															class="fa fa-cart-arrow-down" aria-hidden="true"></i>Thêm
															vào giỏ hàng</a> <a href="#" class="btn compare-btn"><i
															class="fa fa-random" aria-hidden="true"></i></a>
													</div>
												</div>
											</div>
										</div>
									</li>

									<c:forEach var="product" items="${randProducts }">

										<li class="product-item">
											<div class="contain-product layout-default">
												<div class="product-thumb">
													<a href="#" class="link-to-product"> <img
														src="<c:url value='/assets/product-images/${product.image }'/>"
														alt="Vegetables" width="270" height="270"
														class="product-thumnail">
													</a> <a class="lookup btn_call_quickview" href="#"><i
														class="biolife-icon icon-search"></i></a>
												</div>
												<div class="info">
													<b class="categories">${product.category.categoryName }</b>
													<h4 class="product-title">
														<a href="#" class="pr-name">${product.descript }</a>
													</h4>
													<div class="price ">
														<ins>
															<span class="price-amount"><span
																class="currencySymbol">VND</span>${product.price }</span>
														</ins>
														<del>
															<span class="price-amount"><span
																class="currencySymbol">VND</span>95.00</span>
														</del>
													</div>
													<div class="slide-down-box">
														<p class="message">All products are carefully selected
															to ensure food safety.</p>
														<div class="buttons">
															<a href="#" class="btn wishlist-btn"><i
																class="fa fa-heart" aria-hidden="true"></i></a> <a href="#"
																class="btn add-to-cart-btn"><i
																class="fa fa-cart-arrow-down" aria-hidden="true"></i>Thêm
																vào giỏ hàng</a> <a href="#" class="btn compare-btn"><i
																class="fa fa-random" aria-hidden="true"></i></a>
														</div>
													</div>
												</div>
											</div>
										</li>

									</c:forEach>
							</div>
						</div>
					</div>
				</div>
				<!--   Block 4: Category: -->
				<div
					class="product-tab z-index-20 sm-margin-top-193px xs-margin-top-30px">
					<div class="container">
						<div class="biolife-title-box">
							<span class="subtitle">CATEGORY</span>
							<!-- <h3 class="main-title">Related Products</h3> -->
						</div>
						<div class="biolife-tab biolife-tab-contain sm-margin-top-34px">
							<div class="tab-head tab-head__icon-top-layout icon-top-layout">
								<ul class="tabs md-margin-bottom-35-im xs-margin-bottom-40-im">
									<!-- 
									<li class="tab-element  ml-5"><a href="#tab01_2nd"
										class="tab-link"><span class="biolife-icon icon-grape2"></span>Trái
											cây</a></li>
									<li class="tab-element  ml-5"><a href="#tab01_3rd"
										class="tab-link"><span class="biolife-icon icon-blueberry"></span>Thực
											phẩm sống</a></li>
									<li class="tab-element  ml-5"><a href="#tab01_4th"
										class="tab-link"><span class="biolife-icon icon-orange"></span>Thức
											ăn nhanh</a></li>
									<li class="tab-element  ml-5"><a href="#tab01_5th"
										class="tab-link"><span class="biolife-icon icon-broccoli"></span>snack</a>
									</li> -->
								</ul>
							</div>
						</div>
					</div>
				</div>


				<!-- Block 5: List provider -->
				<div
					class="product-tab z-index-20 sm-margin-top-193px xs-margin-top-30px">
					<div class="container">
						<div class="biolife-title-box">
							<span class="subtitle">PROVIDER</span>
							<!-- <h3 class="main-title">Related Products</h3> -->
						</div>
						<div
							class="brand-slide sm-margin-top-100px background-fafafa xs-margin-top-80px xs-margin-bottom-80px">
							<div class="container">
								<ul class="biolife-carousel nav-center-bold nav-none-on-mobile"
									data-slick='{"rows":1,"arrows":true,"dots":false,"infinite":false,"speed":400,"slidesMargin":30,"slidesToShow":4, "responsive":[{"breakpoint":1200, "settings":{ "slidesToShow": 4}},{"breakpoint":992, "settings":{ "slidesToShow": 3}},{"breakpoint":768, "settings":{ "slidesToShow": 2, "slidesMargin":10}},{"breakpoint":450, "settings":{ "slidesToShow": 1, "slidesMargin":10}}]}'>

									
									
								</ul>
							</div>
						</div>
					</div>
				</div>

				<!--Block 06: Products-->
				<div class="Product-box sm-margin-top-96px xs-margin-top-0">
					<div class="container">
						<div class="row">
							<div class="col-lg-4 col-md-5 col-sm-6">
								<div class="advance-product-box">
									<div
										class="biolife-title-box bold-style biolife-title-box__bold-style">
										<h3 class="title">Sản phẩm tiêu biểu</h3>
									</div>
									<ul
										class="products biolife-carousel nav-top-right nav-none-on-mobile"
										data-slick='{"arrows":true, "dots":false, "infinite":false, "speed":400, "slidesMargin":30, "slidesToShow":1}'>
										<li class="product-item">
											<div
												class="contain-product deal-layout contain-product__deal-layout">
												<div class="product-thumb">
													<a href="#" class="link-to-product"> <img
														src="<c:url value='/assets/assets/images/home-03/product_deal_330x330.jpg'/>"
														alt="dd" width="330" height="330" class="product-thumnail">
													</a>
													<div class="labels">
														<span class="sale-label">-50%</span>
													</div>
												</div>
												<div class="info">
													<div class="biolife-countdown"
														data-datetime="2020/02/18 00:00:00"></div>
													<b class="categories">Trái cây</b>
													<h4 class="product-title">
														<a href="#" class="pr-name">Trái cây tươi</a>
													</h4>
													<div class="price ">
														<ins>
															<span class="price-amount"><span
																class="currencySymbol">£</span>85.00</span>
														</ins>
														<del>
															<span class="price-amount"><span
																class="currencySymbol">£</span>95.00</span>
														</del>
													</div>
													<div class="slide-down-box">
														<p class="message">All products are carefully selected
															to ensure food safety.</p>
														<div class="buttons">
															<a href="#" class="btn wishlist-btn"><i
																class="fa fa-heart" aria-hidden="true"></i></a> <a href="#"
																class="btn add-to-cart-btn">add to cart</a> <a href="#"
																class="btn compare-btn"><i class="fa fa-random"
																aria-hidden="true"></i></a>
														</div>
													</div>
												</div>
											</div>
										</li>
										<li class="product-item">
											<div
												class="contain-product deal-layout contain-product__deal-layout">
												<div class="product-thumb">
													<a href="#" class="link-to-product"> <img
														src="<c:url value='/assets/assets/images/home-03/product_deal-02_330x330.jpg'/>"
														alt="dd" width="330" height="330" class="product-thumnail">
													</a>
													<div class="labels">
														<span class="sale-label">-50%</span>
													</div>
												</div>
												<div class="info">
													<div class="biolife-countdown"
														data-datetime="2020/04/18 00:00:00"></div>
													<b class="categories">Fresh Fruit</b>
													<h4 class="product-title">
														<a href="#" class="pr-name">National Fresh Fruit</a>
													</h4>
													<div class="price ">
														<ins>
															<span class="price-amount"><span
																class="currencySymbol">£</span>85.00</span>
														</ins>
														<del>
															<span class="price-amount"><span
																class="currencySymbol">£</span>95.00</span>
														</del>
													</div>
													<div class="slide-down-box">
														<p class="message">All products are carefully selected
															to ensure food safety.</p>
														<div class="buttons">
															<a href="#" class="btn wishlist-btn"><i
																class="fa fa-heart" aria-hidden="true"></i></a> <a href="#"
																class="btn add-to-cart-btn">add to cart</a> <a href="#"
																class="btn compare-btn"><i class="fa fa-random"
																aria-hidden="true"></i></a>
														</div>
													</div>
												</div>
											</div>
										</li>
										<li class="product-item">
											<div
												class="contain-product deal-layout contain-product__deal-layout">
												<div class="product-thumb">
													<a href="#" class="link-to-product"> <img
														src="<c:url value='/assets/assets/images/home-03/product_deal-03_330x330.jpg'/>"
														alt="dd" width="330" height="330" class="product-thumnail">
													</a>
													<div class="labels">
														<span class="sale-label">-50%</span>
													</div>
												</div>
												<div class="info">
													<div class="biolife-countdown"
														data-datetime="2020/01/18 00:00:00"></div>
													<b class="categories">Fresh Fruit</b>
													<h4 class="product-title">
														<a href="#" class="pr-name">National Fresh Fruit</a>
													</h4>
													<div class="price ">
														<ins>
															<span class="price-amount"><span
																class="currencySymbol">£</span>85.00</span>
														</ins>
														<del>
															<span class="price-amount"><span
																class="currencySymbol">£</span>95.00</span>
														</del>
													</div>
													<div class="slide-down-box">
														<p class="message">All products are carefully selected
															to ensure food safety.</p>
														<div class="buttons">
															<a href="#" class="btn wishlist-btn"><i
																class="fa fa-heart" aria-hidden="true"></i></a> <a href="#"
																class="btn add-to-cart-btn">add to cart</a> <a href="#"
																class="btn compare-btn"><i class="fa fa-random"
																aria-hidden="true"></i></a>
														</div>
													</div>
												</div>
											</div>
										</li>
									</ul>
								</div>
							</div>
							<div class="col-lg-8 col-md-7 col-sm-6">
								<div class="advance-product-box">
									<div
										class="biolife-title-box bold-style biolife-title-box__bold-style">
										<h3 class="title">Sản phẩm đánh giá cao</h3>
									</div>
									<ul
										class="products biolife-carousel eq-height-contain nav-center-03 nav-none-on-mobile row-space-29px"
										data-slick='{"rows":2,"arrows":true,"dots":false,"infinite":false,"speed":400,"slidesMargin":30,"slidesToShow":2,"responsive":[{"breakpoint":1200,"settings":{ "rows":2, "slidesToShow": 2}},{"breakpoint":992, "settings":{ "rows":2, "slidesToShow": 1}},{"breakpoint":768, "settings":{ "rows":2, "slidesToShow": 2}},{"breakpoint":500, "settings":{ "rows":2, "slidesToShow": 1}}]}'>
										<li class="product-item">
											<div
												class="contain-product right-info-layout contain-product__right-info-layout">
												<div class="product-thumb">
													<a href="#" class="link-to-product"> <img
														src="<c:url value='/assets/assets/images/products/p-19.jpg'/>"
														alt="dd" width="270" height="270" class="product-thumnail">
													</a>
												</div>
												<div class="info">
													<b class="categories">Vegetables</b>
													<h4 class="product-title">
														<a href="#" class="pr-name">Pumpkins Fairytale</a>
													</h4>
													<div class="price ">
														<ins>
															<span class="price-amount"><span
																class="currencySymbol">£</span>85.00</span>
														</ins>
														<del>
															<span class="price-amount"><span
																class="currencySymbol">£</span>95.00</span>
														</del>
													</div>
													<div class="rating">
														<p class="star-rating">
															<span class="width-80percent"></span>
														</p>
														<span class="review-count">(05 Reviews)</span>
													</div>
												</div>
											</div>
										</li>
										<li class="product-item">
											<div
												class="contain-product right-info-layout contain-product__right-info-layout">
												<div class="product-thumb">
													<a href="#" class="link-to-product"> <img
														src="<c:url value='/assets/assets/images/products/p-03.jpg'/>"
														alt="dd" width="270" height="270" class="product-thumnail">
													</a>
												</div>
												<div class="info">
													<b class="categories">Vegetables</b>
													<h4 class="product-title">
														<a href="#" class="pr-name">Passover Cauliflower</a>
													</h4>
													<div class="price ">
														<ins>
															<span class="price-amount"><span
																class="currencySymbol">£</span>85.00</span>
														</ins>
														<del>
															<span class="price-amount"><span
																class="currencySymbol">£</span>95.00</span>
														</del>
													</div>
													<div class="rating">
														<p class="star-rating">
															<span class="width-80percent"></span>
														</p>
														<span class="review-count">(05 Reviews)</span>
													</div>
												</div>
											</div>
										</li>
										<li class="product-item">
											<div
												class="contain-product right-info-layout contain-product__right-info-layout">
												<div class="product-thumb">
													<a href="#" class="link-to-product"> <img
														src="<c:url value='/assets/assets/images/products/p-02.jpg'/>"
														alt="dd" width="270" height="270" class="product-thumnail">
													</a>
												</div>
												<div class="info">
													<b class="categories">Vegetables</b>
													<h4 class="product-title">
														<a href="#" class="pr-name">Hot Chili Peppers</a>
													</h4>
													<div class="price ">
														<ins>
															<span class="price-amount"><span
																class="currencySymbol">£</span>85.00</span>
														</ins>
														<del>
															<span class="price-amount"><span
																class="currencySymbol">£</span>95.00</span>
														</del>
													</div>
													<div class="rating">
														<p class="star-rating">
															<span class="width-80percent"></span>
														</p>
														<span class="review-count">(05 Reviews)</span>
													</div>
												</div>
											</div>
										</li>
										<li class="product-item">
											<div
												class="contain-product right-info-layout contain-product__right-info-layout">
												<div class="product-thumb">
													<a href="#" class="link-to-product"> <img
														src="<c:url value='/assets/assets/images/products/p-22.jpg'/>"
														alt="dd" width="270" height="270" class="product-thumnail">
													</a>
												</div>
												<div class="info">
													<b class="categories">Vegetables</b>
													<h4 class="product-title">
														<a href="#" class="pr-name">Cherry Tomato Seeds</a>
													</h4>
													<div class="price ">
														<ins>
															<span class="price-amount"><span
																class="currencySymbol">£</span>85.00</span>
														</ins>
														<del>
															<span class="price-amount"><span
																class="currencySymbol">£</span>95.00</span>
														</del>
													</div>
													<div class="rating">
														<p class="star-rating">
															<span class="width-80percent"></span>
														</p>
														<span class="review-count">(05 Reviews)</span>
													</div>
												</div>
											</div>
										</li>
										<li class="product-item">
											<div
												class="contain-product right-info-layout contain-product__right-info-layout">
												<div class="product-thumb">
													<a href="#" class="link-to-product"> <img
														src="<c:url value='/assets/assets/images/products/p-20.jpg'/>"
														alt="dd" width="270" height="270" class="product-thumnail">
													</a>
												</div>
												<div class="info">
													<b class="categories">Vegetables</b>
													<h4 class="product-title">
														<a href="#" class="pr-name">National Fresh Fruit</a>
													</h4>
													<div class="price ">
														<ins>
															<span class="price-amount"><span
																class="currencySymbol">£</span>85.00</span>
														</ins>
														<del>
															<span class="price-amount"><span
																class="currencySymbol">£</span>95.00</span>
														</del>
													</div>
													<div class="rating">
														<p class="star-rating">
															<span class="width-80percent"></span>
														</p>
														<span class="review-count">(05 Reviews)</span>
													</div>
												</div>
											</div>
										</li>
										<li class="product-item">
											<div
												class="contain-product right-info-layout contain-product__right-info-layout">
												<div class="product-thumb">
													<a href="#" class="link-to-product"> <img
														src="<c:url value='/assets/assets/images/products/p-05.jpg'/>"
														alt="dd" width="270" height="270" class="product-thumnail">
													</a>
												</div>
												<div class="info">
													<b class="categories">Vegetables</b>
													<h4 class="product-title">
														<a href="#" class="pr-name">Organic Hass Avocado</a>
													</h4>
													<div class="price ">
														<ins>
															<span class="price-amount"><span
																class="currencySymbol">£</span>85.00</span>
														</ins>
														<del>
															<span class="price-amount"><span
																class="currencySymbol">£</span>95.00</span>
														</del>
													</div>
													<div class="rating">
														<p class="star-rating">
															<span class="width-80percent"></span>
														</p>
														<span class="review-count">(05 Reviews)</span>
													</div>
												</div>
											</div>
										</li>
										<li class="product-item">
											<div
												class="contain-product right-info-layout contain-product__right-info-layout">
												<div class="product-thumb">
													<a href="#" class="link-to-product"> <img
														src="<c:url value='/assets/assets/images/products/p-06.jpg'/>"
														alt="dd" width="270" height="270" class="product-thumnail">
													</a>
												</div>
												<div class="info">
													<b class="categories">Vegetables</b>
													<h4 class="product-title">
														<a href="#" class="pr-name">Packham's Pears</a>
													</h4>
													<div class="price ">
														<ins>
															<span class="price-amount"><span
																class="currencySymbol">£</span>85.00</span>
														</ins>
														<del>
															<span class="price-amount"><span
																class="currencySymbol">£</span>95.00</span>
														</del>
													</div>
													<div class="rating">
														<p class="star-rating">
															<span class="width-80percent"></span>
														</p>
														<span class="review-count">(05 Reviews)</span>
													</div>
												</div>
											</div>
										</li>
										<li class="product-item">
											<div
												class="contain-product right-info-layout contain-product__right-info-layout">
												<div class="product-thumb">
													<a href="#" class="link-to-product"> <img
														src="<c:url value='/assets/assets/images/products/p-20.jpg'/>"
														alt="dd" width="270" height="270" class="product-thumnail">
													</a>
												</div>
												<div class="info">
													<b class="categories">Vegetables</b>
													<h4 class="product-title">
														<a href="#" class="pr-name">National Fresh Fruit</a>
													</h4>
													<div class="price ">
														<ins>
															<span class="price-amount"><span
																class="currencySymbol">£</span>85.00</span>
														</ins>
														<del>
															<span class="price-amount"><span
																class="currencySymbol">£</span>95.00</span>
														</del>
													</div>
													<div class="rating">
														<p class="star-rating">
															<span class="width-80percent"></span>
														</p>
														<span class="review-count">(05 Reviews)</span>
													</div>
												</div>
											</div>
										</li>
									</ul>
									<div
										class="biolife-banner style-01 biolife-banner__style-01 xs-margin-top-80px-im sm-margin-top-30px-im">
										<div class="banner-contain">
											<a href="#" class="bn-link"></a>
											<div class="text-content">
												<span class="first-line">Daily Fresh</span> <b
													class="second-line">Natural</b> <i class="third-line">Fresh
													Food</i> <span class="fourth-line">Premium Quality</span>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>

				<!--Block 07: Brands-->
				<%-- <div
				class="brand-slide sm-margin-top-100px background-fafafa xs-margin-top-80px xs-margin-bottom-80px">
				<div class="container">
					<ul class="biolife-carousel nav-center-bold nav-none-on-mobile"
						data-slick='{"rows":1,"arrows":true,"dots":false,"infinite":false,"speed":400,"slidesMargin":30,"slidesToShow":4, "responsive":[{"breakpoint":1200, "settings":{ "slidesToShow": 4}},{"breakpoint":992, "settings":{ "slidesToShow": 3}},{"breakpoint":768, "settings":{ "slidesToShow": 2, "slidesMargin":10}},{"breakpoint":450, "settings":{ "slidesToShow": 1, "slidesMargin":10}}]}'>
						<li>
							<div class="biolife-brd-container">
								<a href="#" class="link">
									<figure>
										<img src="<c:url value='/assets/assets/images/home-03/brd-01.jpg'/>" width="214"
											height="163" alt="">
									</figure>
								</a>
							</div>
						</li>
						<li>
							<div class="biolife-brd-container">
								<a href="#" class="link">
									<figure>
										<img src="assets/images/home-03/brd-02.jpg" width="214"
											height="163" alt="">
									</figure>
								</a>
							</div>
						</li>
						<li>
							<div class="biolife-brd-container">
								<a href="#" class="link">
									<figure>
										<img src="assets/images/home-03/brd-03.jpg" width="153"
											height="163" alt="">
									</figure>
								</a>
							</div>
						</li>
						<li>
							<div class="biolife-brd-container">
								<a href="#" class="link">
									<figure>
										<img src="assets/images/home-03/brd-04.jpg" width="224"
											height="163" alt="">
									</figure>
								</a>
							</div>
						</li>
						<li>
							<div class="biolife-brd-container">
								<a href="#" class="link">
									<figure>
										<img src="assets/images/home-03/brd-01.jpg" width="214"
											height="163" alt="">
									</figure>
								</a>
							</div>
						</li>
						<li>
							<div class="biolife-brd-container">
								<a href="#" class="link">
									<figure>
										<img src="assets/images/home-03/brd-02.jpg" width="214"
											height="163" alt="">
									</figure>
								</a>
							</div>
						</li>
						<li>
							<div class="biolife-brd-container">
								<a href="#" class="link">
									<figure>
										<img src="assets/images/home-03/brd-03.jpg" width="153"
											height="163" alt="">
									</figure>
								</a>
							</div>
						</li>
						<li>
							<div class="biolife-brd-container">
								<a href="#" class="link">
									<figure>
										<img src="assets/images/home-03/brd-04.jpg" width="224"
											height="163" alt="">
									</figure>
								</a>
							</div>
						</li>
					</ul>
				</div>
			</div> --%>

				<!--Block 08: Blog Posts-->
				<!-- <div
				class="blog-posts sm-margin-top-93px sm-padding-top-72px xs-padding-bottom-50px">
				<div class="container">
					<div class="biolife-title-box">
						<span class="subtitle">The freshest and most exciting news</span>
						<h3 class="main-title">From the Blog</h3>
					</div>
					<ul
						class="biolife-carousel nav-center nav-none-on-mobile xs-margin-top-36px"
						data-slick='{"rows":1,"arrows":true,"dots":false,"infinite":false,"speed":400,"slidesMargin":30,"slidesToShow":3, "responsive":[{"breakpoint":1200, "settings":{ "slidesToShow": 3}},{"breakpoint":992, "settings":{ "slidesToShow": 2}},{"breakpoint":768, "settings":{ "slidesToShow": 2}},{"breakpoint":600, "settings":{ "slidesToShow": 1}}]}'>
						<li>
							<div class="post-item effect-01 style-bottom-info layout-02 ">
								<div class="thumbnail">
									<a href="#" class="link-to-post"><img
										src="assets/images/our-blog/post-thumb-01.jpg" width="370"
										height="270" alt=""></a>
									<div class="post-date">
										<span class="date">26</span> <span class="month">dec</span>
									</div>
								</div>
								<div class="post-content">
									<h4 class="post-name">
										<a href="#" class="linktopost">Ashwagandha: The #1 Herb in
											the World for Anxiety?</a>
									</h4>
									<div class="post-meta">
										<a href="#" class="post-meta__item author"><img
											src="assets/images/home-03/post-author.png" width="28"
											height="28" alt=""><span>Admin</span></a> <a href="#"
											class="post-meta__item btn liked-count">2<span
											class="biolife-icon icon-comment"></span></a> <a href="#"
											class="post-meta__item btn comment-count">6<span
											class="biolife-icon icon-like"></span></a>
										<div class="post-meta__item post-meta__item-social-box">
											<span class="tbn"><i class="fa fa-share-alt"
												aria-hidden="true"></i></span>
											<div class="inner-content">
												<ul class="socials">
													<li><a href="#" title="twitter" class="socail-btn"><i
															class="fa fa-twitter" aria-hidden="true"></i></a></li>
													<li><a href="#" title="facebook" class="socail-btn"><i
															class="fa fa-facebook" aria-hidden="true"></i></a></li>
													<li><a href="#" title="pinterest" class="socail-btn"><i
															class="fa fa-pinterest" aria-hidden="true"></i></a></li>
													<li><a href="#" title="youtube" class="socail-btn"><i
															class="fa fa-youtube" aria-hidden="true"></i></a></li>
													<li><a href="#" title="instagram" class="socail-btn"><i
															class="fa fa-instagram" aria-hidden="true"></i></a></li>
												</ul>
											</div>
										</div>
									</div>
									<p class="excerpt">Did you know that red-staining foods are
										excellent lymph-movers? In fact, many plants that were
										historically used as dyes...</p>
									<div class="group-buttons">
										<a href="#" class="btn readmore">continue reading</a>
									</div>
								</div>
							</div>
						</li>
						<li>
							<div class="post-item effect-01 style-bottom-info layout-02">
								<div class="thumbnail">
									<a href="#" class="link-to-post"><img
										src="assets/images/our-blog/post-thumb-02.jpg" width="370"
										height="270" alt=""></a>
									<div class="post-date">
										<span class="date">26</span> <span class="month">dec</span>
									</div>
								</div>
								<div class="post-content">
									<h4 class="post-name">
										<a href="#" class="linktopost">Ashwagandha: The #1 Herb in
											the World for Anxiety?</a>
									</h4>
									<div class="post-meta">
										<a href="#" class="post-meta__item author"><img
											src="assets/images/home-03/post-author.png" width="28"
											height="28" alt=""><span>Admin</span></a> <a href="#"
											class="post-meta__item btn liked-count">2<span
											class="biolife-icon icon-comment"></span></a> <a href="#"
											class="post-meta__item btn comment-count">6<span
											class="biolife-icon icon-like"></span></a>
										<div class="post-meta__item post-meta__item-social-box">
											<span class="tbn"><i class="fa fa-share-alt"
												aria-hidden="true"></i></span>
											<div class="inner-content">
												<ul class="socials">
													<li><a href="#" title="twitter" class="socail-btn"><i
															class="fa fa-twitter" aria-hidden="true"></i></a></li>
													<li><a href="#" title="facebook" class="socail-btn"><i
															class="fa fa-facebook" aria-hidden="true"></i></a></li>
													<li><a href="#" title="pinterest" class="socail-btn"><i
															class="fa fa-pinterest" aria-hidden="true"></i></a></li>
													<li><a href="#" title="youtube" class="socail-btn"><i
															class="fa fa-youtube" aria-hidden="true"></i></a></li>
													<li><a href="#" title="instagram" class="socail-btn"><i
															class="fa fa-instagram" aria-hidden="true"></i></a></li>
												</ul>
											</div>
										</div>
									</div>
									<p class="excerpt">Did you know that red-staining foods are
										excellent lymph-movers? In fact, many plants that were
										historically used as dyes...</p>
									<div class="group-buttons">
										<a href="#" class="btn readmore">continue reading</a>
									</div>
								</div>
							</div>
						</li>
						<li>
							<div class="post-item effect-01 style-bottom-info layout-02">
								<div class="thumbnail">
									<a href="#" class="link-to-post"><img
										src="assets/images/our-blog/post-thumb-04.jpg" width="370"
										height="270" alt=""></a>
									<div class="post-date">
										<span class="date">26</span> <span class="month">dec</span>
									</div>
								</div>
								<div class="post-content">
									<h4 class="post-name">
										<a href="#" class="linktopost">Ashwagandha: The #1 Herb in
											the World for Anxiety?</a>
									</h4>
									<div class="post-meta">
										<a href="#" class="post-meta__item author"><img
											src="assets/images/home-03/post-author.png" width="28"
											height="28" alt=""><span>Admin</span></a> <a href="#"
											class="post-meta__item btn liked-count">2<span
											class="biolife-icon icon-comment"></span></a> <a href="#"
											class="post-meta__item btn comment-count">6<span
											class="biolife-icon icon-like"></span></a>
										<div class="post-meta__item post-meta__item-social-box">
											<span class="tbn"><i class="fa fa-share-alt"
												aria-hidden="true"></i></span>
											<div class="inner-content">
												<ul class="socials">
													<li><a href="#" title="twitter" class="socail-btn"><i
															class="fa fa-twitter" aria-hidden="true"></i></a></li>
													<li><a href="#" title="facebook" class="socail-btn"><i
															class="fa fa-facebook" aria-hidden="true"></i></a></li>
													<li><a href="#" title="pinterest" class="socail-btn"><i
															class="fa fa-pinterest" aria-hidden="true"></i></a></li>
													<li><a href="#" title="youtube" class="socail-btn"><i
															class="fa fa-youtube" aria-hidden="true"></i></a></li>
													<li><a href="#" title="instagram" class="socail-btn"><i
															class="fa fa-instagram" aria-hidden="true"></i></a></li>
												</ul>
											</div>
										</div>
									</div>
									<p class="excerpt">Did you know that red-staining foods are
										excellent lymph-movers? In fact, many plants that were
										historically used as dyes...</p>
									<div class="group-buttons">
										<a href="#" class="btn readmore">continue reading</a>
									</div>
								</div>
							</div>
						</li>
						<li>
							<div class="post-item effect-01 style-bottom-info layout-02">
								<div class="thumbnail">
									<a href="#" class="link-to-post"><img
										src="assets/images/our-blog/post-thumb-03.jpg" width="370"
										height="270" alt=""></a>
									<div class="post-date">
										<span class="date">26</span> <span class="month">dec</span>
									</div>
								</div>
								<div class="post-content">
									<h4 class="post-name">
										<a href="#" class="linktopost">Ashwagandha: The #1 Herb in
											the World for Anxiety?</a>
									</h4>
									<div class="post-meta">
										<a href="#" class="post-meta__item author"><img
											src="assets/images/home-03/post-author.png" width="28"
											height="28" alt=""><span>Admin</span></a> <a href="#"
											class="post-meta__item btn liked-count">2<span
											class="biolife-icon icon-comment"></span></a> <a href="#"
											class="post-meta__item btn comment-count">6<span
											class="biolife-icon icon-like"></span></a>
										<div class="post-meta__item post-meta__item-social-box">
											<span class="tbn"><i class="fa fa-share-alt"
												aria-hidden="true"></i></span>
											<div class="inner-content">
												<ul class="socials">
													<li><a href="#" title="twitter" class="socail-btn"><i
															class="fa fa-twitter" aria-hidden="true"></i></a></li>
													<li><a href="#" title="facebook" class="socail-btn"><i
															class="fa fa-facebook" aria-hidden="true"></i></a></li>
													<li><a href="#" title="pinterest" class="socail-btn"><i
															class="fa fa-pinterest" aria-hidden="true"></i></a></li>
													<li><a href="#" title="youtube" class="socail-btn"><i
															class="fa fa-youtube" aria-hidden="true"></i></a></li>
													<li><a href="#" title="instagram" class="socail-btn"><i
															class="fa fa-instagram" aria-hidden="true"></i></a></li>
												</ul>
											</div>
										</div>
									</div>
									<p class="excerpt">Did you know that red-staining foods are
										excellent lymph-movers? In fact, many plants that were
										historically used as dyes...</p>
									<div class="group-buttons">
										<a href="#" class="btn readmore">continue reading</a>
									</div>
								</div>
							</div>
						</li>
						<li>
							<div class="post-item effect-01 style-bottom-info layout-02">
								<div class="thumbnail">
									<a href="#" class="link-to-post"><img
										src="assets/images/our-blog/post-thumb-05.jpg" width="370"
										height="270" alt=""></a>
									<div class="post-date">
										<span class="date">26</span> <span class="month">dec</span>
									</div>
								</div>
								<div class="post-content">
									<h4 class="post-name">
										<a href="#" class="linktopost">Ashwagandha: The #1 Herb in
											the World for Anxiety?</a>
									</h4>
									<div class="post-meta">
										<a href="#" class="post-meta__item author"><img
											src="assets/images/home-03/post-author.png" width="28"
											height="28" alt=""><span>Admin</span></a> <a href="#"
											class="post-meta__item btn liked-count">2<span
											class="biolife-icon icon-comment"></span></a> <a href="#"
											class="post-meta__item btn comment-count">6<span
											class="biolife-icon icon-like"></span></a>
										<div class="post-meta__item post-meta__item-social-box">
											<span class="tbn"><i class="fa fa-share-alt"
												aria-hidden="true"></i></span>
											<div class="inner-content">
												<ul class="socials">
													<li><a href="#" title="twitter" class="socail-btn"><i
															class="fa fa-twitter" aria-hidden="true"></i></a></li>
													<li><a href="#" title="facebook" class="socail-btn"><i
															class="fa fa-facebook" aria-hidden="true"></i></a></li>
													<li><a href="#" title="pinterest" class="socail-btn"><i
															class="fa fa-pinterest" aria-hidden="true"></i></a></li>
													<li><a href="#" title="youtube" class="socail-btn"><i
															class="fa fa-youtube" aria-hidden="true"></i></a></li>
													<li><a href="#" title="instagram" class="socail-btn"><i
															class="fa fa-instagram" aria-hidden="true"></i></a></li>
												</ul>
											</div>
										</div>
									</div>
									<p class="excerpt">Did you know that red-staining foods are
										excellent lymph-movers? In fact, many plants that were
										historically used as dyes...</p>
									<div class="group-buttons">
										<a href="#" class="btn readmore">continue reading</a>
									</div>
								</div>
							</div>
						</li>
						<li>
							<div class="post-item effect-01 style-bottom-info layout-02">
								<div class="thumbnail">
									<a href="#" class="link-to-post"><img
										src="assets/images/our-blog/post-thumb-06.jpg" width="370"
										height="270" alt=""></a>
									<div class="post-date">
										<span class="date">26</span> <span class="month">dec</span>
									</div>
								</div>
								<div class="post-content">
									<h4 class="post-name">
										<a href="#" class="linktopost">Ashwagandha: The #1 Herb in
											the World for Anxiety?</a>
									</h4>
									<div class="post-meta">
										<a href="#" class="post-meta__item author"><img
											src="assets/images/home-03/post-author.png" width="28"
											height="28" alt=""><span>Admin</span></a> <a href="#"
											class="post-meta__item btn liked-count">2<span
											class="biolife-icon icon-comment"></span></a> <a href="#"
											class="post-meta__item btn comment-count">6<span
											class="biolife-icon icon-like"></span></a>
										<div class="post-meta__item post-meta__item-social-box">
											<span class="tbn"><i class="fa fa-share-alt"
												aria-hidden="true"></i></span>
											<div class="inner-content">
												<ul class="socials">
													<li><a href="#" title="twitter" class="socail-btn"><i
															class="fa fa-twitter" aria-hidden="true"></i></a></li>
													<li><a href="#" title="facebook" class="socail-btn"><i
															class="fa fa-facebook" aria-hidden="true"></i></a></li>
													<li><a href="#" title="pinterest" class="socail-btn"><i
															class="fa fa-pinterest" aria-hidden="true"></i></a></li>
													<li><a href="#" title="youtube" class="socail-btn"><i
															class="fa fa-youtube" aria-hidden="true"></i></a></li>
													<li><a href="#" title="instagram" class="socail-btn"><i
															class="fa fa-instagram" aria-hidden="true"></i></a></li>
												</ul>
											</div>
										</div>
									</div>
									<p class="excerpt">Did you know that red-staining foods are
										excellent lymph-movers? In fact, many plants that were
										historically used as dyes...</p>
									<div class="group-buttons">
										<a href="#" class="btn readmore">continue reading</a>
									</div>
								</div>
							</div>
						</li>
					</ul>
				</div>
			</div>

		</div> -->

			</div>

			<!-- FOOTER -->
			<footer id="footer" class="footer layout-03">
				<div class="footer-content background-footer-03">
					<div class="container">
						<div class="row">

							<div
								class="col-lg-6 col-md-4 col-sm-6 md-margin-top-5px sm-margin-top-50px xs-margin-top-40px">
								<section class="footer-item">
									<h3 class="section-title">GROUP MEME</h3>
									<div class="row">
										<div class="col-lg-6 col-sm-6 col-xs-6">
											<div class="wrap-custom-menu vertical-menu-2">
												<ul class="menu">
													<li><a href="#">Bùi Hữu Trí </a></li>
													<li><a href="#">Đinh Quốc Toàn</a></li>
													<li><a href="#">VÕ Hồng Nguyên</a></li>

													<li><a href="#">Huỳnh Trần Nhật Tân </a></li>


												</ul>
											</div>
										</div>
										<div class="col-lg-6 col-sm-6 col-xs-6">
											<div class="wrap-custom-menu vertical-menu-2">
												<ul class="menu">
													<li><a href="#">- N21DCAT058</a></li>
													<li><a href="#">- N21DCAT057</a></li>
													<li><a href="#">- N21DCAT036</a></li>
													<li><a href="#">- N21DCAT045</a></li>
												</ul>
											</div>
										</div>
									</div>
								</section>
							</div>
							<div
								class="col-lg-4 col-md-4 col-sm-6 md-margin-top-5px sm-margin-top-50px xs-margin-top-40px">
								<section class="footer-item">
									<h3 class="section-title">...</h3>
									<div
										class="contact-info-block footer-layout xs-padding-top-10px">
										<ul class="contact-lines">
											<li>
												<p class="info-item">
													<i class="biolife-icon icon-location"></i> <b class="desc">97
														Man Thiện </b>
												</p>
											</li>
											<li>
												<p class="info-item">
													<i class="biolife-icon icon-phone"></i> <b class="desc">Phone:
														222 888</b>
												</p>
											</li>
											<li>
												<p class="info-item">
													<i class="biolife-icon icon-letter"></i> <b class="desc">Email:
														huutri91thcslvm@gmail.com</b>
												</p>
											</li>
											<li>
												<p class="info-item">
													<i class="biolife-icon icon-clock"></i> <b class="desc">Hours:
													</b>
												</p>
											</li>
										</ul>
									</div>
									<!-- <div class="biolife-social inline">
								<ul class="socials">
									<li><a href="#" title="twitter" class="socail-btn"><i
											class="fa fa-twitter" aria-hidden="true"></i></a></li>
									<li><a href="#" title="facebook" class="socail-btn"><i
											class="fa fa-facebook" aria-hidden="true"></i></a></li>
									<li><a href="#" title="pinterest" class="socail-btn"><i
											class="fa fa-pinterest" aria-hidden="true"></i></a></li>
									<li><a href="#" title="youtube" class="socail-btn"><i
											class="fa fa-youtube" aria-hidden="true"></i></a></li>
									<li><a href="#" title="instagram" class="socail-btn"><i
											class="fa fa-instagram" aria-hidden="true"></i></a></li>
								</ul>
							</div> -->
								</section>
							</div>
						</div>
						<!-- <div class="row">
					<div class="col-xs-12">
						<div class="separator sm-margin-top-70px xs-margin-top-40px"></div>
					</div>
					<div class="col-lg-6 col-sm-6 col-xs-12">
						<div class="copy-right-text">
							<p>
								<a href="templateshub.net">Templates Hub</a>
							</p>
						</div>
					</div>
					<div class="col-lg-6 col-sm-6 col-xs-12">
						<div class="payment-methods">
							<ul>
								<li><a href="#" class="payment-link"><img
										src="assets/images/card1.jpg" width="51" height="36" alt=""></a></li>
								<li><a href="#" class="payment-link"><img
										src="assets/images/card2.jpg" width="51" height="36" alt=""></a></li>
								<li><a href="#" class="payment-link"><img
										src="assets/images/card3.jpg" width="51" height="36" alt=""></a></li>
								<li><a href="#" class="payment-link"><img
										src="assets/images/card4.jpg" width="51" height="36" alt=""></a></li>
								<li><a href="#" class="payment-link"><img
										src="assets/images/card5.jpg" width="51" height="36" alt=""></a></li>
							</ul>
						</div>
					</div>
				</div> -->
					</div>
				</div>
			</footer>

			<!--Footer For Mobile-->
			<div class="mobile-footer">
				<div class="mobile-footer-inner">
					<div class="mobile-block block-menu-main">
						<a class="menu-bar menu-toggle btn-toggle"
							data-object="open-mobile-menu" href="javascript:void(0)"> <span
							class="fa fa-bars"></span> <span class="text">Menu</span>
						</a>
					</div>
					<div class="mobile-block block-sidebar">
						<a class="menu-bar filter-toggle btn-toggle"
							data-object="open-mobile-filter" href="javascript:void(0)"> <i
							class="fa fa-sliders" aria-hidden="true"></i> <span class="text">Sidebar</span>
						</a>
					</div>
					<div class="mobile-block block-minicart">
						<a class="link-to-cart" href="#"> <span
							class="fa fa-shopping-bag" aria-hidden="true"></span> <span
							class="text">Cart</span>
						</a>
					</div>
					<div class="mobile-block block-global">
						<a class="menu-bar myaccount-toggle btn-toggle"
							data-object="global-panel-opened" href="javascript:void(0)">
							<span class="fa fa-globe"></span> <span class="text">Global</span>
						</a>
					</div>
				</div>
			</div>

			<!--Mobile Global Menu-->
			<div class="mobile-block-global">
				<div class="biolife-mobile-panels">
					<span class="biolife-current-panel-title">Global</span> <a
						class="biolife-close-btn" data-object="global-panel-opened"
						href="#">&times;</a>
				</div>
				<div class="block-global-contain">
					<div class="glb-item my-account">
						<b class="title">My Account</b>
						<ul class="list">
							<li class="list-item"><a href="#">Login/register</a></li>
							<li class="list-item"><a href="#">Wishlist <span
									class="index">(8)</span></a></li>
							<li class="list-item"><a href="#">Checkout</a></li>
						</ul>
					</div>
					<div class="glb-item currency">
						<b class="title">Currency</b>
						<ul class="list">
							<li class="list-item"><a href="#">€ EUR (Euro)</a></li>
							<li class="list-item"><a href="#">$ USD (Dollar)</a></li>
							<li class="list-item"><a href="#">£ GBP (Pound)</a></li>
							<li class="list-item"><a href="#">¥ JPY (Yen)</a></li>
						</ul>
					</div>
					<div class="glb-item languages">
						<b class="title">Language</b>
						<ul class="list inline">
							<li class="list-item"><a href="#"><img
									src="<c:url value='/assets/assets/images/languages/us.jpg'/>"
									alt="flag" width="24" height="18"></a></li>
							<li class="list-item"><a href="#"><img
									src="<c:url value='/assets/assets/images/languages/fr.jpg'/>"
									alt="flag" width="24" height="18"></a></li>
							<li class="list-item"><a href="#"><img
									src="<c:url value='/assets/assets/images/languages/ger.jpg'/>"
									alt="flag" width="24" height="18"></a></li>
							<li class="list-item"><a href="#"><img
									src="<c:url value='/assets/assets/images/languages/jap.jpg'/>"
									alt="flag" width="24" height="18"></a></li>
						</ul>
					</div>
				</div>
			</div>

			<!--Quickview Popup-->
			<div id="biolife-quickview-block" class="biolife-quickview-block">
				<div class="quickview-container">
					<a href="#" class="btn-close-quickview"
						data-object="open-quickview-block"><span
						class="biolife-icon icon-close-menu"></span></a>
					<div class="biolife-quickview-inner">
						<div class="media">
							<ul class="biolife-carousel quickview-for"
								data-slick='{"arrows":false,"dots":false,"slidesMargin":30,"slidesToShow":1,"slidesToScroll":1,"fade":true,"asNavFor":".quickview-nav"}'>
								<li><img
									src="<c:url value='/assets/assets/images/details-product/detail_01.jpg'/>"
									alt="" width="500" height="500"></li>
								<li><img
									src="<c:url value='/assets/assets/images/details-product/detail_02.jpg'/>"
									alt="" width="500" height="500"></li>
								<li><img
									src="<c:url value='/assets/assets/images/details-product/detail_03.jpg'/>"
									alt="" width="500" height="500"></li>
								<li><img
									src="<c:url value='/assets/assets/images/details-product/detail_04.jpg'/>"
									alt="" width="500" height="500"></li>
								<li><img
									src="<c:url value='/assets/assets/images/details-product/detail_05.jpg'/>"
									alt="" width="500" height="500"></li>
								<li><img
									src="<c:url value='/assets/assets/images/details-product/detail_06.jpg'/>"
									alt="" width="500" height="500"></li>
								<li><img
									src="<c:url value='/assets/assets/images/details-product/detail_07.jpg'/>"
									alt="" width="500" height="500"></li>
							</ul>
							<ul class="biolife-carousel quickview-nav"
								data-slick='{"arrows":true,"dots":false,"centerMode":false,"focusOnSelect":true,"slidesMargin":10,"slidesToShow":3,"slidesToScroll":1,"asNavFor":".quickview-for"}'>
								<li><img
									src="<c:url value='/assets/assets/images/details-product/thumb_01.jpg'/>"
									alt="" width="88" height="88"></li>
								<li><img
									src="<c:url value='/assets/assets/images/details-product/thumb_02.jpg'/>"
									alt="" width="88" height="88"></li>
								<li><img
									src="<c:url value='/assets/assets/images/details-product/thumb_03.jpg'/>"
									alt="" width="88" height="88"></li>
								<li><img
									src="<c:url value='/assets/assets/images/details-product/thumb_04.jpg'/>"
									alt="" width="88" height="88"></li>
								<li><img
									src="<c:url value='/assets/assets/images/details-product/thumb_05.jpg'/>"
									alt="" width="88" height="88"></li>
								<li><img
									src="<c:url value='/assets/assets/images/details-product/thumb_06.jpg'/>"
									alt="" width="88" height="88"></li>
								<li><img
									src="<c:url value='/assets/assets/images/details-product/thumb_07.jpg'/>"
									alt="" width="88" height="88"></li>
							</ul>
						</div>
						<div class="product-attribute">
							<h4 class="title">
								<a href="#" class="pr-name">National Fresh Fruit</a>
							</h4>
							<div class="rating">
								<p class="star-rating">
									<span class="width-80percent"></span>
								</p>
							</div>

							<div class="price price-contain">
								<ins>
									<span class="price-amount"><span class="currencySymbol">£</span>85.00</span>
								</ins>
								<del>
									<span class="price-amount"><span class="currencySymbol">£</span>95.00</span>
								</del>
							</div>
							<p class="excerpt">Lorem</p>
							<div class="from-cart">
								<div class="qty-input">
									<input type="text" name="qty12554" value="1"
										data-max_value="20" data-min_value="1" data-step="1">
									<a href="#" class="qty-btn btn-up"><i
										class="fa fa-caret-up" aria-hidden="true"></i></a> <a href="#"
										class="qty-btn btn-down"><i class="fa fa-caret-down"
										aria-hidden="true"></i></a>
								</div>
								<div class="buttons">
									<a href="#" class="btn add-to-cart-btn btn-bold">add to
										cart</a>
								</div>
							</div>

							<div class="product-meta">
								<div class="product-atts">
									<div class="product-atts-item">
										<b class="meta-title">Categories:</b>
										<ul class="meta-list">
											<li><a href="#" class="meta-link">Milk & Cream</a></li>
											<li><a href="#" class="meta-link">Fresh Meat</a></li>
											<li><a href="#" class="meta-link">Fresh Fruit</a></li>
										</ul>
									</div>
									<div class="product-atts-item">
										<b class="meta-title">Tags:</b>
										<ul class="meta-list">
											<li><a href="#" class="meta-link">food theme</a></li>
											<li><a href="#" class="meta-link">organic food</a></li>
											<li><a href="#" class="meta-link">organic theme</a></li>
										</ul>
									</div>
									<div class="product-atts-item">
										<b class="meta-title">Brand:</b>
										<ul class="meta-list">
											<li><a href="#" class="meta-link">Fresh Fruit</a></li>
										</ul>
									</div>
								</div>
								<span class="sku">SKU: N/A</span>
								<div class="biolife-social inline add-title">
									<span class="fr-title">Share:</span>
									<ul class="socials">
										<li><a href="#" title="twitter" class="socail-btn"><i
												class="fa fa-twitter" aria-hidden="true"></i></a></li>
										<li><a href="#" title="facebook" class="socail-btn"><i
												class="fa fa-facebook" aria-hidden="true"></i></a></li>
										<li><a href="#" title="pinterest" class="socail-btn"><i
												class="fa fa-pinterest" aria-hidden="true"></i></a></li>
										<li><a href="#" title="youtube" class="socail-btn"><i
												class="fa fa-youtube" aria-hidden="true"></i></a></li>
										<li><a href="#" title="instagram" class="socail-btn"><i
												class="fa fa-instagram" aria-hidden="true"></i></a></li>
									</ul>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>

			<!-- Scroll Top Button -->
			<a class="btn-scroll-top"><i class="biolife-icon icon-left-arrow"></i></a>

			<script src="<c:url value='/assets/assets/js/jquery-3.4.1.min.js'/>"></script>
			<script src="<c:url value='/assets/assets/js/bootstrap.min.js'/>"></script>
			<script
				src="<c:url value='/assets/assets/js/jquery.countdown.min.js'/>"></script>
			<script
				src="<c:url value='/assets/assets/js/jquery.nice-select.min.js'/>"></script>
			<script
				src="<c:url value='/assets/assets/js/jquery.nicescroll.min.js'/>"></script>
			<script src="<c:url value='/assets/assets/js/slick.min.js'/>"></script>
			<script src="<c:url value='/assets/assets/js/biolife.framework.js'/>"></script>
			<script src="<c:url value='/assets/assets/js/functions.js'/>"></script>
</body>

</html>