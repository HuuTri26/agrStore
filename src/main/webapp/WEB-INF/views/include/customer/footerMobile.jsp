<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<base href="${pageContext.servletContext.contextPath}/">
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
				data-object="global-panel-opened" href="javascript:void(0)"> <span
				class="fa fa-globe"></span> <span class="text">Global</span>
			</a>
		</div>
	</div>
</div>

<!--Mobile Global Menu-->
<div class="mobile-block-global">
	<div class="biolife-mobile-panels">
		<span class="biolife-current-panel-title">Global</span> <a
			class="biolife-close-btn" data-object="global-panel-opened" href="#">&times;</a>
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
						<input type="text" name="qty12554" value="1" data-max_value="20"
							data-min_value="1" data-step="1"> <a href="#"
							class="qty-btn btn-up"><i class="fa fa-caret-up"
							aria-hidden="true"></i></a> <a href="#" class="qty-btn btn-down"><i
							class="fa fa-caret-down" aria-hidden="true"></i></a>
					</div>
					<div class="buttons">
						<a href="#" class="btn add-to-cart-btn btn-bold">add to cart</a>
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