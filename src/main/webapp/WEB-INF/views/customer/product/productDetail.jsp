<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page import="agrStore.utility.UltilityImpl"%>
<!DOCTYPE html>
<html class="no-js" lang="en">

<%@include file="/WEB-INF/views/include/customer/header.jsp"%>
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
				<li class="nav-item"><span class="current-page">Thông
						tin sản phẩm</span></li>
			</ul>
		</nav>
	</div>

	<div class="page-contain single-product">
		<div class="container">

			<!-- Main content -->
			<div id="main-content" class="main-content">

				<!-- summary info -->
				<div class="sumary-product single-layout">
					<div class="media">
						<ul class="biolife-carousel slider-for"
							data-slick='{"arrows":false,"dots":false,"slidesMargin":30,"slidesToShow":1,"slidesToScroll":1,"fade":true,"asNavFor":".slider-nav"}'>
							<li><img
								src="<c:url value='/assets/product-images/${product.image}'/>"
								alt="" width="500" height="500"></li>
						</ul>
					</div>
					<div class="product-attribute">
						<h3 class="title">${product.productName}</h3>
						<div class="rating">
							<p class="star-rating">
								<span class="width-${avgPercent }percent"></span>
							</p>
							<span class="review-count">(${feedbackCount } Reviews)</span>
						</div>
						<div>
							<span class="quantity">Số lượng: <b>${product.quantity}</b></span>
						</div>
						<div>
							<span class="provider">Nhà cung cấp: <b>${product.provider.providerName}</b></span>
						</div>
						<p class="excerpt">
							Description: <b>${product.descript}</b>
						</p>
						<div class="price">
							<ins>
								<span class="price-amount"><span class="currencySymbol">Đơn
										giá</span> <fmt:formatNumber value="${product.price }"
										pattern="#,###.## VND;VND -#,###.##" type="currency"
										currencySymbol="VND" /></span>
							</ins>
							<!-- <del>
								<span class="price-amount"><span class="currencySymbol">£</span>95.00</span>
							</del> -->
						</div>
					</div>
					<form class="action-form" action="customer/addItemIntoCart.htm"
						method="get">
						<div class="quantity-box">
							<span class="title">Đặt với Số lượng:</span>
							<div class="qty-input">
								<input type="text" name="quantity" value="1"
									data-max_value="${product.quantity}" data-min_value="1"
									data-step="1"> <a href="#" class="qty-btn btn-up"><i
									class="fa fa-caret-up" aria-hidden="true"></i></a> <a href="#"
									class="qty-btn btn-down"><i class="fa fa-caret-down"
									aria-hidden="true"></i></a> <input type="hidden" name="pId"
									value="${product.productId }">
							</div>
						</div>
						<div class="total-price-contain">
							<span class="title">Total Price:</span>
							<p class="price">
								<fmt:formatNumber value="${product.price }"
									pattern="#,###.## VND;VND -#,###.##" type="currency"
									currencySymbol="VND" />
							</p>
						</div>
						<div class="buttons">
							<!-- <a class="btn add-to-cart-btn">Thêm vào giỏ hàng</a> -->
							<button type="submit" class="btn add-to-cart-btn">Thêm
								vào giỏ hàng</button>
						</div>

						<!-- 		<div class="buttons">
							<a href="customer/customerCheckout.htm"
								class="btn add-to-cart-btn">Đặt hàng</a>
						</div> -->
						<div class="acepted-payment-methods">
							<ul class="payment-methods">
								<li><img
									src="<c:url value='assets/assets/images/card1.jpg'/>" alt=""
									width="51'" height="36"></li>
								<li><img
									src="<c:url value='assets/assets/images/card2.jpg'/>" alt=""
									width="51" height="36"></li>
								<li><img
									src="<c:url value='assets/assets/images/card3.jpg'/>" alt=""
									width="51" height="36"></li>
								<li><img
									src="<c:url value='assets/assets/images/card4.jpg'/>" alt=""
									width="51" height="36"></li>
							</ul>
						</div>
					</form>
				</div>

				<!-- Tab info -->
				<div class="product-tabs single-layout biolife-tab-contain">
					<div class="tab-head">
						<ul class="tabs">
							<li class="tab-element active"><a href="#tab_4th"
								class="tab-link">Customer Reviews <sup>(${feedbackCount })</sup>
							</a></li>
						</ul>
					</div>
					<div class="tab-content">
						<div id="tab_4th" class="tab-contain review-tab active">
							<div class="container">
								<div class="row">
									<div class="col-lg-5 col-md-5 col-sm-6 col-xs-12">
										<div class="rating-info">
											<p class="index">
												<strong class="rating">${avgStar }</strong>out of 5
											</p>
											<div class="rating">
												<p class="star-rating">
													<span class="width-${avgPercent }percent"></span>
												</p>
											</div>
											<p class="see-all">See all ${feedbackCount } reviews</p>
											<ul class="options">
												<li>
													<div class="detail-for">
														<span class="option-name">5stars</span> <span
															class="progres"> <span class="line-100percent"><span
																class="percent width-${percent_5 }percent"></span></span>
														</span> <span class="number">${star_5 }</span>
													</div>
												</li>
												<li>
													<div class="detail-for">
														<span class="option-name">4stars</span> <span
															class="progres"> <span class="line-100percent"><span
																class="percent width-${percent_4 }percent"></span></span>
														</span> <span class="number">${star_4 }</span>
													</div>
												</li>
												<li>
													<div class="detail-for">
														<span class="option-name">3stars</span> <span
															class="progres"> <span class="line-100percent"><span
																class="percent width-${percent_3 }percent"></span></span>
														</span> <span class="number">${star_3 }</span>
													</div>
												</li>
												<li>
													<div class="detail-for">
														<span class="option-name">2stars</span> <span
															class="progres"> <span class="line-100percent"><span
																class="percent width-${percent_2 }percent"></span></span>
														</span> <span class="number">${star_2 }</span>
													</div>
												</li>
												<li>
													<div class="detail-for">
														<span class="option-name">1star</span> <span
															class="progres"> <span class="line-100percent"><span
																class="percent width-${percent_1 }percent"></span></span>
														</span> <span class="number">${star_1 }</span>
													</div>
												</li>
											</ul>
										</div>
									</div>
									<div class="col-lg-7 col-md-7 col-sm-6 col-xs-12">
										<div class="review-form-wrapper">
											<span class="title">Submit your review</span>
											<form:form action="customer/feedbackForm.htm"
												name="frm-review" method="post" modelAttribute="feedback">
												<div class="comment-form-rating">
													<label>1. Your rating of this products:</label>
													<p class="stars">
														<a class="btn-rating" data-value="1" href="#"
															onclick="setStar(1)"><i class="fa fa-star-o"
															aria-hidden="true"></i></a> <a class="btn-rating"
															data-value="2" href="#" onclick="setStar(2)"><i
															class="fa fa-star-o" aria-hidden="true"></i></a> <a
															class="btn-rating" data-value="3" href="#"
															onclick="setStar(3)"><i class="fa fa-star-o"
															aria-hidden="true"></i></a> <a class="btn-rating"
															data-value="4" href="#" onclick="setStar(4)"><i
															class="fa fa-star-o" aria-hidden="true"></i></a> <a
															class="btn-rating" data-value="5" href="#"
															onclick="setStar(5)"><i class="fa fa-star-o"
															aria-hidden="true"></i></a>
													</p>
												</div>
												<!-- Các thuộc tính ẩn này phục vụ chức năng update -->
												<form:hidden path="feedbackId" />
												<form:hidden path="createAt" />
												<input type="hidden" name="orderBillDtId"
													value="${orderBillDtId }" />

												<!-- Thuộc tính này dùng cho cả add và update -->
												<input type="hidden" name="star" value="${star}">

												<p class="form-row wide-half">
													<input type="text" name="fullName" readonly="readonly"
														value="${loggedInUser.fullName }"
														placeHolder="Tên của bạn" />
												</p>
												<p class="form-row wide-half">
													<input type="text" name="Gmail" readonly="readonly"
														value="${loggedInUser.gmail }" placeHolder="Gmail của bạn" />
												</p>
												<p class="form-row">
													<form:textarea path="comment" id="txt-comment" cols="30"
														rows="10"
														placeholder="Bạn chỉ có thể thêm hoặc sửa đánh giá sau khi đã mua sản phẩm. Hãy đăng đăng nhập hoặc đăng ký để có thể mua đc sản phẩm này"></form:textarea>
												</p>
												<p class="form-row">
													<c:if test="${loggedInUser != null}">
														<button type="submit" name="${btnMode }">
															${btnMode == 'add' ? 'Thêm' : 'Cập nhật'}</button>
													</c:if>
												</p>

											</form:form>
										</div>
									</div>
								</div>
								<div id="comments">
									<ol class="commentlist">
										<li class="review">
											<div class="comment-container">

												<c:forEach var="feedback" items="${feedbacks }">

													<div class="row">
														<div
															class="comment-content col-lg-12 col-md-9 col-sm-8 col-xs-12">
															<p class="comment-in">
																<!-- 			<span class="post-name">Quality is our way of life</span> -->
																<span class="post-date">${feedback.createAt }</span>
															</p>
															<div class="rating">
																<p class="star-rating">
																	<span class="width-${feedback.starPercentage }percent"></span>
																</p>
															</div>
															<p class="author">
																by: <b>${feedback.account.fullName }</b>
															</p>
															<p class="comment-text"><c:out value="${UltilityImpl.XSSEscape4HTML(feedback.comment)}" /></p>
														</div>

													</div>

												</c:forEach>

											</div>
										</li>
									</ol>
									<div class="biolife-panigations-block version-2">
										<ul class="panigation-contain">
											<li><span class="current-page">1</span></li>
											<li><a href="#" class="link-page">2</a></li>
											<li><a href="#" class="link-page">3</a></li>
											<li><span class="sep">....</span></li>
											<li><a href="#" class="link-page">20</a></li>
											<li><a href="#" class="link-page next"><i
													class="fa fa-angle-right" aria-hidden="true"></i></a></li>
										</ul>
										<div class="result-count">
											<p class="txt-count">
												<b>1-5</b> of <b>${feedbackCount }</b> reviews
											</p>
											<a href="#" class="link-to">See all<i
												class="fa fa-caret-right" aria-hidden="true"></i></a>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>

				<!-- related products -->
				<div class="product-related-box single-layout">
					<div class="biolife-title-box lg-margin-bottom-26px-im">
						<span class="biolife-icon icon-organic"></span> <span
							class="subtitle">Sẩn phẩm cùng loại</span>
						<h3 class="main-title">List sản phẩm</h3>
					</div>
					<ul
						class="products-list biolife-carousel nav-center-02 nav-none-on-mobile"
						data-slick='{"rows":1,"arrows":true,"dots":false,"infinite":false,"speed":400,"slidesMargin":0,"slidesToShow":5, "responsive":[{"breakpoint":1200, "settings":{ "slidesToShow": 4}},{"breakpoint":992, "settings":{ "slidesToShow": 3, "slidesMargin":20 }},{"breakpoint":768, "settings":{ "slidesToShow": 2, "slidesMargin":10}}]}'>


						<c:forEach var="pd" items="${relatedProucts }">

							<li class="product-item">
								<div class="contain-product layout-default">
									<div class="product-thumb">
										<a href="#" class="link-to-product"> <img
											src="<c:url value='/assets/product-images/${pd.image}'/>"
											alt="dd" width="270" height="270" class="product-thumnail">
										</a>
									</div>
									<div class="info">
										<b class="categories">Rau củ</b>
										<h4 class="product-title">
											<a href="#" class="pr-name">Khoai tây</a>
										</h4>
										<div class="price">
											<ins>
												<span class="price-amount"><span
													class="currencySymbol"><fmt:formatNumber
															value="${pd.price }" pattern="#,###.## VND;VND -#,###.##"
															type="currency" currencySymbol="VND" /></span></span>
											</ins>
											<!-- <del>
											<span class="price-amount"><span
												class="currencySymbol">£</span>95.00</span>
										</del> -->
										</div>
										<div class="slide-down-box">
											<p class="message">~~</p>
											<div class="buttons">
												<a href="#" class="btn wishlist-btn"><i
													class="fa fa-heart" aria-hidden="true"></i></a> <a
													href="customer/addItemIntoCart.htm?pId=${pd.productId }"
													class="btn add-to-cart-btn"><i
													class="fa fa-cart-arrow-down" aria-hidden="true"></i>add to
													cart</a> <a href="#" class="btn compare-btn"><i
													class="fa fa-random" aria-hidden="true"></i></a>
											</div>
										</div>
									</div>
								</div>
							</li>

						</c:forEach>

					</ul>
				</div>

			</div>
		</div>
	</div>
	<script type="text/javascript">
	function setStar(value) {
	    document.querySelector('input[name="star"]').value = value;
	    const stars = document.querySelectorAll('.btn-rating');
	    stars.forEach((star, index) => {
	        if (index < value) {
	            star.classList.add('active');
	        } else {
	            star.classList.remove('active');
	        }
	    });
	}
	</script>
	<!-- FOOTER -->
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