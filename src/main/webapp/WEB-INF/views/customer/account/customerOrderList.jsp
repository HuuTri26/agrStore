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
								<h2 class="font-body--xxl-500">Danh sách đơn đặt hàng</h2>
							</div>
							<div class="dashboard__order-history-table">
								<div class="table-responsive">
									<table class="table">
										<thead>
											<tr>
												<th scope="col" class="dashboard__order-history-table-title">
													Order Id</th>
												<th scope="col" class="dashboard__order-history-table-title">
													Ngày đặt</th>
												<th scope="col" class="dashboard__order-history-table-title">
													Tổng tiền</th>
												<th scope="col" class="dashboard__order-history-table-title">
													Trạng thái</th>
												<th scope="col" class="dashboard__order-history-table-title"></th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<!-- Order Id  -->
												<td class="dashboard__order-history-table-item order-id">
													#738</td>
												<!-- Date  -->
												<td
													class="
                              dashboard__order-history-table-item
                              order-date
                            ">
													8 Sep, 20220</td>
												<!-- Total  -->
												<td
													class="
                              dashboard__order-history-table-item
                              order-total
                            ">
													<p class="order-total-price">
														$135.00 <span class="quantity"> (5 Products)</span>
													</p>
												</td>
												<!-- Status -->
												<td
													class="
                              dashboard__order-history-table-item
                              order-status
                            ">
													Processing</td>
												<!-- Details page  -->
												<td
													class="
                              dashboard__order-history-table-item
                              order-details
                            ">
													<a href="customerOrderDetail.htm"> View Details</a>
												</td>
											</tr>

											<tr>
												<!-- Order Id  -->
												<td class="dashboard__order-history-table-item order-id">
													#130</td>
												<!-- Date  -->
												<td
													class="
                              dashboard__order-history-table-item
                              order-date
                            ">
													22 Oct, 2020</td>
												<!-- Total  -->
												<td
													class="
                              dashboard__order-history-table-item
                              order-total
                            ">
													<p class="order-total-price">
														$250.00 <span class="quantity"> (4 Products)</span>
													</p>
												</td>
												<!-- Status -->
												<td
													class="
                              dashboard__order-history-table-item
                              order-status
                            ">
													Completed</td>
												<!-- Details page  -->
												<td
													class="
                              dashboard__order-history-table-item
                              order-details
                            ">
													<a href="customerOrderDetail.htm"> View Details</a>
												</td>
											</tr>
										</tbody>
									</table>
								</div>
								<div class="dashboard__order-pagination">
									<nav aria-label="Page navigation pagination--one"
										class="pagination-wrapper">
										<ul class="pagination justify-content-center">
											<li class="page-item pagination-item disabled"><a
												class="page-link pagination-link" href="#" tabindex="-1">
													<svg width="8" height="14" viewBox="0 0 8 14" fill="none"
														xmlns="http://www.w3.org/2000/svg">
                              <path
															d="M6.91663 1.16634L1.08329 6.99967L6.91663 12.833"
															stroke="currentColor" stroke-width="1.5"
															stroke-linecap="round" stroke-linejoin="round"></path>
                            </svg>
											</a></li>
											<li class="page-item pagination-item"><a
												class="page-link pagination-link active" href="#">1</a></li>
											<li class="page-item pagination-item"><a
												class="page-link pagination-link" href="#">2</a></li>
											<li class="page-item pagination-item"><a
												class="page-link pagination-link" href="#">3</a></li>
											<li class="page-item pagination-item"><a
												class="page-link pagination-link" href="#">4</a></li>
											<li class="page-item pagination-item"><a
												class="page-link pagination-link" href="#">5</a></li>
											<li class="page-item pagination-item">
												<p class="page-link pagination-link">...</p>
											</li>
											<li class="page-item pagination-item"><a
												class="page-link pagination-link" href="#">21</a></li>
											<li class="page-item pagination-item"><a
												class="page-link pagination-link" href="#"> <svg
														width="8" height="14" viewBox="0 0 8 14" fill="none"
														xmlns="http://www.w3.org/2000/svg">
                              <path
															d="M1.08337 1.16634L6.91671 6.99967L1.08337 12.833"
															stroke="currentColor" stroke-width="1.5"
															stroke-linecap="round" stroke-linejoin="round"></path>
                            </svg>
											</a></li>
										</ul>
									</nav>
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