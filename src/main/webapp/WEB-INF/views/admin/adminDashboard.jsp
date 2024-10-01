
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!doctype html>
<html lang="en">
<head>
<base href="${pageContext.servletContext.contextPath}/">
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Meta -->
<meta name="description" content="Responsive Bootstrap Admin Dashboards">
<meta name="author" content="Bootstrap Gallery" />
<link rel="canonical" href="https://www.bootstrap.gallery/">
<meta property="og:url" content="https://www.bootstrap.gallery">
<meta property="og:title"
	content="Admin Templates - Dashboard Templates | Bootstrap Gallery">
<meta property="og:description"
	content="Marketplace for Bootstrap Admin Dashboards">
<meta property="og:type" content="Website">
<meta property="og:site_name" content="Bootstrap Gallery">
<link rel="shortcut icon" href="assets/images/favicon.svg">

<!-- Title -->
<title>Admin dashboard</title>

<!-- *************
			************ Common Css Files *************
		************ -->

<!-- Animated css -->
<link rel="stylesheet"
	href="<c:url value='/assets/admin/assets/css/animate.css'/>">

<!-- Bootstrap font icons css -->
<link rel="stylesheet"
	href="<c:url value='/assets/admin/assets/fonts/bootstrap/bootstrap-icons.css'/>">

<!-- Main css -->
<link rel="stylesheet"
	href="<c:url value='/assets/admin/assets/css/main.min.css'/>">


<!-- *************
			************ Vendor Css Files *************
		************ -->

<!-- Scrollbar CSS -->
<link rel="stylesheet"
	href="<c:url value='/assets/admin/assets/vendor/overlay-scroll/OverlayScrollbars.min.css'/>">

</head>

<body>

	<!-- Loading wrapper start -->
	<div id="loading-wrapper">
		<div class="spinner">
			<div class="line1"></div>
			<div class="line2"></div>
			<div class="line3"></div>
			<div class="line4"></div>
			<div class="line5"></div>
			<div class="line6"></div>
		</div>
	</div>
	<!-- Loading wrapper end -->

	<!-- Page wrapper start -->
	<div class="page-wrapper">

		<!-- Sidebar wrapper start -->
		<nav class="sidebar-wrapper">

			<!-- Sidebar brand starts -->
			<div class="sidebar-brand">
				<a href="index.html" class="logo"> <img
					src="<c:url value='/assets/admin/assets/images/logo.svg'/>"
					alt="Max Free Admin Dashboard" />
				</a>
			</div>
			<!-- Sidebar brand starts -->

			<!-- Sidebar menu starts -->
			<div class="sidebar-menu">
				<div class="sidebarMenuScroll">
					<ul>
						<li class="sidebar-dropdown active"><a href="#"> <i
								class="bi bi-house"></i> <span class="menu-text">Dashboards</span>
						</a>
							<div class="sidebar-submenu">
								<ul>
									<li><a href="index.html" class="current-page">Sales</a></li>
									<li><a href="social.html">Social</a></li>
								</ul>
							</div></li>
						<li class="sidebar-dropdown"><a href="#"> <i
								class="bi bi-handbag"></i> <span class="menu-text">Product</span>
						</a>
							<div class="sidebar-submenu">
								<ul>
									<li><a href="orders.html">Order History</a></li>
									<li><a href="products.html">Products</a></li>
									<li><a href="view-cart.html">Shopping Cart</a></li>
									<li><a href="checkout.html">Billing Details</a></li>
									<li><a href="customers.html">Customers</a></li>
									<li><a href="add-product.html">Add Product</a></li>
									<li><a href="reviews.html">Reviews</a></li>
								</ul>
							</div></li>
						<li class="sidebar-dropdown"><a href="#"> <i
								class="bi bi-gem"></i> <span class="menu-text">Widgets</span>
						</a>
							<div class="sidebar-submenu">
								<ul>
									<li><a href="widgets.html">Widgets</a></li>
									<li><a href="graph-widgets.html">Graph Widgets</a></li>
								</ul>
							</div></li>
						<li class="sidebar-dropdown"><a href="#"> <i
								class="bi bi-collection"></i> <span class="menu-text">UI
									Elements</span>
						</a>
							<div class="sidebar-submenu">
								<ul>
									<li><a href="accordions.html">Accordions</a></li>
									<li><a href="alerts.html">Alerts</a></li>
									<li><a href="buttons.html">Buttons</a></li>
									<li><a href="badges.html">Badges</a></li>
									<li><a href="cards.html">Cards</a></li>
									<li><a href="carousel.html">Carousel</a></li>
									<li><a href="dropdowns.html">Dropdowns</a></li>
									<li><a href="icons.html">Icons</a></li>
									<li><a href="modals.html">Modals</a></li>
									<li><a href="offcanvas.html">Off Canvas</a></li>
									<li><a href="placeholders.html">Placeholders</a></li>
									<li><a href="progress.html">Progress Bars</a></li>
									<li><a href="spinners.html">Spinners</a></li>
									<li><a href="tabs.html">Tabs</a></li>
									<li><a href="tooltips.html">Tooltips</a></li>
									<li><a href="typography.html">Typography</a></li>
								</ul>
							</div></li>
						<li class="sidebar-dropdown"><a href="#"> <i
								class="bi bi-stickies"></i> <span class="menu-text">Pages</span>
						</a>
							<div class="sidebar-submenu">
								<ul>
									<li><a href="create-invoice.html">Create Invoice</a></li>
									<li><a href="view-invoice.html">View Invoice</a></li>
									<li><a href="invoice-list.html">Invoice List</a></li>
									<li><a href="subscribers.html">Subscribers</a></li>
									<li><a href="contacts.html">Contacts</a></li>
									<li><a href="pricing.html">Pricing</a></li>
									<li><a href="profile.html">Profile</a></li>
									<li><a href="account-settings.html">Account Settings</a></li>
								</ul>
							</div></li>
						<li class="sidebar-dropdown"><a href="#"> <i
								class="bi bi-calendar4"></i> <span class="menu-text">Calendars</span>
						</a>
							<div class="sidebar-submenu">
								<ul>
									<li><a href="calendar.html">Daygrid View</a></li>
									<li><a href="calendar-external-draggable.html">External
											Draggable</a></li>
									<li><a href="calendar-google.html">Google Calendar</a></li>
									<li><a href="calendar-list-view.html">List View</a></li>
									<li><a href="calendar-selectable.html">Selectable</a></li>
									<li><a href="calendar-week-numbers.html">Week Numbers</a>
									</li>
								</ul>
							</div></li>
						<li class="sidebar-dropdown"><a href="#"> <i
								class="bi bi-columns-gap"></i> <span class="menu-text">Forms</span>
						</a>
							<div class="sidebar-submenu">
								<ul>
									<li><a href="form-inputs.html">Form Inputs</a></li>
									<li><a href="form-checkbox-radio.html">Checkbox &amp;
											Radio</a></li>
									<li><a href="form-file-input.html">File Input</a></li>
									<li><a href="form-validations.html">Validations</a></li>
									<li><a href="bs-select.html">Bootstrap Select</a></li>
									<li><a href="date-time-pickers.html">Date Time Pickers</a>
									</li>
									<li><a href="input-mask.html">Input Masks</a></li>
									<li><a href="summernote.html">Summernote</a></li>
									<li><a href="form-layout1.html">Form Layout</a></li>
									<li><a href="form-layout2.html">Form Layout 2</a></li>
									<li><a href="form-layout3.html">Form Layout 3</a></li>
									<li><a href="form-layout4.html">Form Layout Horizontal</a>
									</li>
									<li><a href="form-layout5.html">Form Layout Tabs</a></li>
								</ul>
							</div></li>
						<li class="sidebar-dropdown"><a href="#"> <i
								class="bi bi-window-split"></i> <span class="menu-text">Tables</span>
						</a>
							<div class="sidebar-submenu">
								<ul>
									<li><a href="tables.html">Tables</a></li>
									<li><a href="data-tables.html">Data Tables</a></li>
								</ul>
							</div></li>
						<li class="sidebar-dropdown"><a href="#"> <i
								class="bi bi-pie-chart"></i> <span class="menu-text">Graphs
									&amp; Maps</span>
						</a>
							<div class="sidebar-submenu">
								<ul>
									<li><a href="apex.html">Apex</a></li>
									<li><a href="morris.html">Morris</a></li>
									<li><a href="maps.html">Maps</a></li>
								</ul>
							</div></li>
						<li class="sidebar-dropdown"><a href="#"> <i
								class="bi bi-layout-sidebar"></i> <span class="menu-text">Layouts</span>
						</a>
							<div class="sidebar-submenu">
								<ul>
									<li><a href="layout.html">Default Layout</a></li>
									<li><a href="layout-grid.html">Grid Layout</a></li>
									<li><a href="layout-mega-menu.html">Mega Menu</a></li>
									<li><a href="layout-full-screen.html">Full Screen</a></li>
									<li><a href="layout-toggle-screen.html">Toggle Screen</a>
									</li>
									<li><a href="layout-welcome.html">Welcome Layout</a></li>
								</ul>
							</div></li>
						<li class="sidebar-dropdown"><a href="#"> <i
								class="bi bi-x-diamond"></i> <span class="menu-text">Authentication</span>
						</a>
							<div class="sidebar-submenu">
								<ul>
									<li><a href="login.html">Login</a></li>
									<li><a href="signup.html">Signup</a></li>
									<li><a href="error.html">Error</a></li>
									<li><a href="maintenance.html">Maintenance</a></li>
								</ul>
							</div></li>
						<li><a href="starter-page.html"> <i
								class="bi bi-hand-index-thumb"></i> <span class="menu-text">Link</span>
						</a></li>
					</ul>
				</div>
			</div>
			<!-- Sidebar menu ends -->

		</nav>
		<!-- Sidebar wrapper end -->

		<!-- *************
				************ Main container start *************
			************* -->
		<div class="main-container">

			<!-- Page header starts -->
			<div class="page-header">

				<div class="toggle-sidebar" id="toggle-sidebar">
					<i class="bi bi-list"></i>
				</div>

				<!-- Breadcrumb start -->
				<ol class="breadcrumb">
					<li class="breadcrumb-item"><i class="bi bi-house"></i></li>
					<li class="breadcrumb-item breadcrumb-active" aria-current="page">Sales</li>
				</ol>
				<!-- Breadcrumb end -->

				<!-- Header actions ccontainer start -->
				<div class="header-actions-container">

					<!-- Search container start -->
					<div class="search-container">

						<!-- Search input group start -->
						<div class="input-group">
							<input type="text" class="form-control" placeholder="Search">
							<button class="btn" type="button">
								<i class="bi bi-search"></i>
							</button>
						</div>
						<!-- Search input group end -->

					</div>
					<!-- Search container end -->

					<!-- Header actions start -->
					<ul class="header-actions">

						<!-- Messages start -->
						<li class="dropdown"><a href="#" data-toggle="dropdown"
							aria-haspopup="true"> <i class="bi bi-bell fs-4 lh-1"></i>
						</a>
							<div class="dropdown-menu dropdown-menu-end shadow">
								<div class="dropdown-item">
									<div class="d-flex py-2 border-bottom">
										<img src="assets/images/user.png"
											class="img-4x me-3 rounded-3" alt="Free Admin Theme" />
										<div class="m-0">
											<h6 class="mb-1">Sophie Michiels</h6>
											<p class="mb-2">Membership has been ended.</p>
											<p class="small m-0 text-secondary">Today, 07:30pm</p>
										</div>
									</div>
								</div>
								<div class="dropdown-item">
									<div class="d-flex py-2 border-bottom">
										<img src="assets/images/user2.png"
											class="img-4x me-3 rounded-3" alt="Free Admin Theme" />
										<div class="m-0">
											<h6 class="mb-1">Sophie Michiels</h6>
											<p class="mb-2">Congratulate, James for new job.</p>
											<p class="small m-0 text-secondary">Today, 08:00pm</p>
										</div>
									</div>
								</div>
								<div class="dropdown-item">
									<div class="d-flex py-2">
										<img src="assets/images/user3.png"
											class="img-4x me-3 rounded-3" alt="Free Admin Theme" />
										<div class="m-0">
											<h6 class="mb-1">Sophie Michiels</h6>
											<p class="mb-2">Lewis added new schedule release.</p>
											<p class="small m-0 text-secondary">Today, 09:30pm</p>
										</div>
									</div>
								</div>
							</div></li>
						<!-- Messages end -->

						<li class="dropdown d-none d-md-block"><a href="#"
							id="countries" data-toggle="dropdown" aria-haspopup="true"> <img
								src="assets/images/flags/1x1/gb.svg" class="flag-img"
								alt="Free Admin Dashboards" />
						</a>
							<div class="dropdown-menu dropdown-menu-end mini"
								aria-labelledby="countries">
								<div class="country-container">
									<a href="index.html"> <img
										src="assets/images/flags/1x1/us.svg"
										alt="Free Admin Dashboards" />
									</a> <a href="index.html"> <img
										src="assets/images/flags/1x1/in.svg"
										alt="Free Google Dashboards" />
									</a> <a href="index.html"> <img
										src="assets/images/flags/1x1/br.svg" alt="Free Admin Panels" />
									</a> <a href="index.html"> <img
										src="assets/images/flags/1x1/tr.svg"
										alt="Free Modern Dashboards" />
									</a> <a href="index.html"> <img
										src="assets/images/flags/1x1/ca.svg"
										alt="Free Bootstrap Dashboards" />
									</a>
								</div>
							</div></li>
						<li class="dropdown"><a href="#" id="userSettings"
							class="user-settings" data-toggle="dropdown" aria-haspopup="true">
								<span class="user-name d-none d-md-block">Tony Robbins</span> <span
								class="avatar"> <img src="assets/images/user3.png"
									alt="Free Admin Templates"> <span class="status busy"></span>
							</span>
						</a>
							<div class="dropdown-menu dropdown-menu-end"
								aria-labelledby="userSettings">
								<div class="header-profile-actions">
									<a href="profile.html">Profile</a> <a
										href="account-settings.html">Settings</a> <a href="login.html">Logout</a>
								</div>
							</div></li>
					</ul>
					<!-- Header actions end -->

				</div>
				<!-- Header actions ccontainer end -->

			</div>
			<!-- Page header ends -->

			<!-- Content wrapper scroll start -->
			<div class="content-wrapper-scroll">

				<!-- Content wrapper start -->
				<div class="content-wrapper">

					<!-- Row start -->
					<div class="row gx-3">
						<div class="col-xxl-3 col-sm-6 col-12">
							<div class="stats-tile">
								<div class="sale-icon shade-blue">
									<i class="bi bi-pie-chart"></i>
								</div>
								<div class="sale-details">
									<h3 class="text-blue">$189</h3>
									<p>Sales</p>
								</div>
								<div class="sale-graph">
									<div id="sparklineLine1"></div>
								</div>
							</div>
						</div>
						<div class="col-xxl-3 col-sm-6 col-12">
							<div class="stats-tile">
								<div class="sale-icon shade-yellow">
									<i class="bi bi-emoji-smile"></i>
								</div>
								<div class="sale-details">
									<h3 class="text-yellow">240</h3>
									<p>Orders</p>
								</div>
								<div class="sale-graph">
									<div id="sparklineLine2"></div>
								</div>
							</div>
						</div>
						<div class="col-xxl-3 col-sm-6 col-12">
							<div class="stats-tile">
								<div class="sale-icon shade-red">
									<i class="bi bi-box-seam"></i>
								</div>
								<div class="sale-details">
									<h3 class="text-red">150</h3>
									<p>Signups</p>
								</div>
								<div class="sale-graph">
									<div id="sparklineLine3"></div>
								</div>
							</div>
						</div>
						<div class="col-xxl-3 col-sm-6 col-12">
							<div class="stats-tile">
								<div class="sale-icon shade-green">
									<i class="bi bi-handbag"></i>
								</div>
								<div class="sale-details">
									<h3 class="text-green">$150</h3>
									<p>Revenue</p>
								</div>
								<div class="sale-graph">
									<div id="sparklineLine4"></div>
								</div>
							</div>
						</div>
					</div>
					<!-- Row end -->

					<!-- Row start -->
					<div class="row gx-3">
						<div class="col-12">
							<div class="card">
								<div class="card-header">
									<div class="card-title">Earnings</div>
								</div>
								<div class="card-body">

									<!-- Row start -->
									<div class="row gx-3">
										<div class="col-sm-8 col-12">
											<div id="world-map-markers2" class="chart-height"></div>
										</div>
										<div class="col-sm-4 col-12">
											<div class="global-sales">
												<h3>
													<i class="bi bi-globe icon"></i>$2,75,000 <i
														class="bi bi-arrow-up-right text-primary"></i>
												</h3>
												<p>This dashboard unquestionably the largest visitors in
													the world with TWO million monthly active users and ONE
													million daily active.</p>
											</div>
										</div>
									</div>
									<!-- Row end -->

								</div>
							</div>
						</div>
					</div>
					<!-- Row end -->

					<!-- Row start -->
					<div class="row gx-3">
						<div class="col-xxl-9  col-sm-12 col-12">

							<div class="card">
								<div class="card-body">

									<!-- Row start -->
									<div class="row gx-3">
										<div class="col-xxl-3 col-sm-4 col-12">
											<div class="reports-summary">
												<div class="reports-summary-block">
													<h6>Overall Sales</h6>
													<h5>12 Millions</h5>
												</div>
												<div class="reports-summary-block">
													<h6>Overall Earnings</h6>
													<h5>78 Millions</h5>
												</div>
												<div class="reports-summary-block">
													<h6>Overall Revenue</h6>
													<h5>60 Millions</h5>
												</div>
												<div class="reports-summary-block">
													<h6>New Customers</h6>
													<h5>23k</h5>
												</div>
												<button class="btn btn-info download-reports">Reports</button>
											</div>
										</div>
										<div class="col-xxl-9 col-sm-8 col-12">
											<div class="row gx-3">
												<div class="col-12">
													<div class="graph-day-selection mt-2" role="group">
														<button type="button" class="btn active">Today</button>
														<button type="button" class="btn">Yesterday</button>
														<button type="button" class="btn">7 days</button>
														<button type="button" class="btn">15 days</button>
														<button type="button" class="btn">30 days</button>
													</div>
												</div>
												<div class="col-12">
													<div id="revenueGraph"></div>
												</div>
											</div>
										</div>
									</div>
									<!-- Row end -->

								</div>
							</div>

						</div>
						<div class="col-xxl-3  col-sm-12 col-12">

							<div class="card">
								<div class="card-header">
									<div class="card-title">Sales</div>
								</div>
								<div class="card-body">
									<div id="salesGraph" class="auto-align-graph"></div>
									<div class="num-stats">
										<h2>2100</h2>
										<h6 class="text-truncate">12% higher than last month.</h6>
									</div>
								</div>
							</div>

						</div>
					</div>
					<!-- Row end -->

					<!-- Row start -->
					<div class="row gx-3">
						<div class="col-xxl-12">
							<div class="card">
								<div class="card-header">
									<div class="card-title">Orders</div>
								</div>
								<div class="card-body">

									<div class="table-responsive">
										<table class="table v-middle">
											<thead>
												<tr>
													<th>Customer</th>
													<th>Product</th>
													<th>User ID</th>
													<th>Ordered Placed</th>
													<th>Amount</th>
													<th>Payment Status</th>
													<th>Order Status</th>
												</tr>
											</thead>
											<tbody>
												<tr>
													<td>
														<div class="media-box">
															<img src="assets/images/user3.png" class="media-avatar"
																alt="Bootstrap Gallery">
															<div class="media-box-body">
																<div class="text-truncate">Ellie Collins</div>
															</div>
														</div>
													</td>
													<td>
														<div class="media-box">
															<img src="assets/images/food/img3.jpg"
																class="media-avatar" alt="Free Admin Themes">
															<div class="media-box-body">
																<div class="text-truncate">Ginger Snacks</div>
															</div>
														</div>
													</td>
													<td>Max827</td>
													<td>12/12/2021</td>
													<td>$18.00</td>
													<td><span class="text-green td-status"><i
															class="bi bi-check-circle"></i> Paid</span></td>
													<td><span class="badge shade-green min-90">Delivered</span>
													</td>
												</tr>
												<tr>
													<td>
														<div class="media-box">
															<img src="assets/images/user.png" class="media-avatar"
																alt="Bootstrap Gallery">
															<div class="media-box-body">
																<div class="text-truncate">Sophie Nguyen</div>
															</div>
														</div>
													</td>
													<td>
														<div class="media-box">
															<img src="assets/images/food/img6.jpg"
																class="media-avatar" alt="Free Admin Themes">
															<div class="media-box-body">
																<div class="text-truncate">Guava Sorbet</div>
															</div>
														</div>
													</td>
													<td>Max253</td>
													<td>18/12/2021</td>
													<td>$32.00</td>
													<td><span class="text-red td-status"><i
															class="bi bi-x-circle"></i> Failed</span></td>
													<td><span class="badge shade-red min-90">Cancelled</span>
													</td>
												</tr>
												<tr>
													<td>
														<div class="media-box">
															<img src="assets/images/user4.png" class="media-avatar"
																alt="Bootstrap Gallery">
															<div class="media-box-body">
																<div class="text-truncate">Darcy Ryan</div>
															</div>
														</div>
													</td>
													<td>
														<div class="media-box">
															<img src="assets/images/food/img5.jpg"
																class="media-avatar" alt="Free Admin Themes">
															<div class="media-box-body">
																<div class="text-truncate">Gooseberry Surprise</div>
															</div>
														</div>
													</td>
													<td>Max878</td>
													<td>22/12/2021</td>
													<td>$19.00</td>
													<td><span class="text-blue td-status"><i
															class="bi bi-clock-history"></i> Awaiting</span></td>
													<td><span class="badge shade-blue min-90">Processing</span>
													</td>
												</tr>
											</tbody>
										</table>
									</div>

								</div>
							</div>
						</div>
					</div>
					<!-- Row end -->

					<!-- Row start -->
					<div class="row gx-3">
						<div class="col-xxl-3 col-sm-6 col-12">
							<div class="card">
								<div class="card-header">
									<div class="card-title">Notifications</div>
								</div>
								<div class="card-body">
									<div class="scroll370">
										<ul class="user-messages">
											<li>
												<div class="customer shade-blue">MK</div>
												<div class="delivery-details">
													<span class="badge shade-blue">Sales</span>
													<h5>Marie Kieffer</h5>
													<p>Thanks for choosing Apple product, further if you
														have any questions please contact sales team.</p>
												</div>
											</li>
											<li>
												<div class="customer shade-blue">ES</div>
												<div class="delivery-details">
													<span class="badge shade-blue">Marketing</span>
													<h5>Ewelina Sikora</h5>
													<p>Boost your sales by 50% with the easiest and proven
														marketing tool for customer enggement &amp; motivation.</p>
												</div>
											</li>
											<li>
												<div class="customer shade-blue">TN</div>
												<div class="delivery-details">
													<span class="badge shade-blue">Business</span>
													<h5>Teboho Ncube</h5>
													<p>Use an exclusive promo code HKYMM50 and get 50% off
														on your first order in the new year.</p>
												</div>
											</li>
											<li>
												<div class="customer shade-blue">CJ</div>
												<div class="delivery-details">
													<span class="badge shade-blue">Admin</span>
													<h5>Carla Jackson</h5>
													<p>Befor inviting the administrator, you must create a
														role that can be assigned to them.</p>
												</div>
											</li>
											<li>
												<div class="customer shade-red">JK</div>
												<div class="delivery-details">
													<span class="badge shade-red">Security</span>
													<h5>Julie Kemp</h5>
													<p>Your security subscription has expired. Please renew
														the subscription.</p>
												</div>
											</li>
										</ul>
									</div>
								</div>
							</div>
						</div>
						<div class="col-xxl-3 col-sm-6 col-12">
							<div class="card">
								<div class="card-header">
									<div class="card-title">Activity</div>
								</div>
								<div class="card-body">

									<div class="scroll370">
										<div class="activity-container">
											<div class="activity-block">
												<div class="activity-user">
													<img src="assets/images/user.png" alt="Free Admin Template">
												</div>
												<div class="activity-details">
													<h4>Lilly Desmet</h4>
													<h5>3 hours ago</h5>
													<p>Sent invoice ref. #23457</p>
													<span class="badge shade-green">Sent</span>
												</div>
											</div>
											<div class="activity-block">
												<div class="activity-user">
													<img src="assets/images/user3.png"
														alt="Free Admin Template">
												</div>
												<div class="activity-details">
													<h4>Jennifer Wilson</h4>
													<h5>7 hours ago</h5>
													<p>Paid invoice ref. #23459</p>
													<span class="badge shade-red">Payments</span>
												</div>
											</div>
											<div class="activity-block">
												<div class="activity-user">
													<img src="assets/images/user4.png"
														alt="Free Admin Template">
												</div>
												<div class="activity-details">
													<h4>Elliott Hermans</h4>
													<h5>1 day ago</h5>
													<p>Paid invoice ref. #23473</p>
													<span class="badge shade-green">Paid</span>
												</div>
											</div>
											<div class="activity-block">
												<div class="activity-user">
													<img src="assets/images/user5.png"
														alt="Free Admin Template">
												</div>
												<div class="activity-details">
													<h4>Sophie Michiels</h4>
													<h5>3 day ago</h5>
													<p>Paid invoice ref. #26788</p>
													<span class="badge shade-green">Sent</span>
												</div>
											</div>
											<div class="activity-block">
												<div class="activity-user">
													<img src="assets/images/user2.png"
														alt="Free Admin Template">
												</div>
												<div class="activity-details">
													<h4>Ilyana Maes</h4>
													<h5>One week ago</h5>
													<p>Paid invoice ref. #34546</p>
													<span class="badge shade-red">Invoice</span>
												</div>
											</div>
										</div>
									</div>

								</div>
							</div>
						</div>
						<div class="col-xxl-3 col-sm-6 col-12">
							<div class="card">
								<div class="card-header">
									<div class="card-title">Transactions</div>
								</div>
								<div class="card-body">
									<div class="scroll370">
										<div class="transactions-container">
											<div class="transaction-block">
												<div class="transaction-icon shade-blue">
													<i class="bi bi-credit-card"></i>
												</div>
												<div class="transaction-details">
													<h4>Visa Card</h4>
													<p class="text-truncate">Laptop Ordered</p>
												</div>
												<div class="transaction-amount text-red">$1590</div>
											</div>
											<div class="transaction-block">
												<div class="transaction-icon shade-green">
													<i class="bi bi-paypal"></i>
												</div>
												<div class="transaction-details">
													<h4>Paypal</h4>
													<p class="text-truncate">Payment Received</p>
												</div>
												<div class="transaction-amount text-green">$310</div>
											</div>
											<div class="transaction-block">
												<div class="transaction-icon shade-blue">
													<i class="bi bi-pin-map"></i>
												</div>
												<div class="transaction-details">
													<h4>Travel</h4>
													<p class="text-truncate">Yosemite Trip</p>
												</div>
												<div class="transaction-amount text-red">$4900</div>
											</div>
											<div class="transaction-block">
												<div class="transaction-icon shade-blue">
													<i class="bi bi-bag-check"></i>
												</div>
												<div class="transaction-details">
													<h4>Shopping</h4>
													<p class="text-truncate">Bill Paid</p>
												</div>
												<div class="transaction-amount text-red">$285</div>
											</div>
											<div class="transaction-block">
												<div class="transaction-icon shade-green">
													<i class="bi bi-boxes"></i>
												</div>
												<div class="transaction-details">
													<h4>Bank</h4>
													<p class="text-truncate">Investment</p>
												</div>
												<div class="transaction-amount text-green">$150</div>
											</div>
											<div class="transaction-block">
												<div class="transaction-icon shade-green">
													<i class="bi bi-paypal"></i>
												</div>
												<div class="transaction-details">
													<h4>Paypal</h4>
													<p class="text-truncate">Amount Received</p>
												</div>
												<div class="transaction-amount text-green">$790</div>
											</div>
											<div class="transaction-block">
												<div class="transaction-icon shade-blue">
													<i class="bi bi-credit-card-2-front"></i>
												</div>
												<div class="transaction-details">
													<h4>Credit Card</h4>
													<p class="text-truncate">Online Shopping</p>
												</div>
												<div class="transaction-amount text-red">$280</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="col-xxl-3 col-sm-6 col-12">
							<div class="card">
								<div class="card-header">
									<div class="card-title">Tasks</div>
								</div>
								<div class="card-body">
									<div id="taskGraph"></div>
									<ul class="task-list-container">
										<li class="task-list-item">
											<div class="task-icon shade-blue">
												<i class="bi bi-clipboard-plus"></i>
											</div>
											<div class="task-info">
												<h5 class="task-title">New</h5>
												<p class="amount-spend">12</p>
											</div>
										</li>
										<li class="task-list-item">
											<div class="task-icon shade-green">
												<i class="bi bi-clipboard-check"></i>
											</div>
											<div class="task-info">
												<h5 class="task-title">Done</h5>
												<p class="amount-spend">15</p>
											</div>
										</li>
									</ul>
								</div>
							</div>
						</div>
					</div>
					<!-- Row end -->

				</div>
				<!-- Content wrapper end -->

				<!-- App Footer start -->
				<div class="app-footer">
					<span>© Bootstrap Gallery 2023</span>
				</div>
				<!-- App footer end -->

			</div>
			<!-- Content wrapper scroll end -->

		</div>
		<!-- *************
				************ Main container end *************
			************* -->

	</div>
	<!-- Page wrapper end -->

	<!-- *************
			************ Required JavaScript Files *************
		************* -->
	<!-- Required jQuery first, then Bootstrap Bundle JS -->
	<script src="<c:url value='/assets/admin/assets/js/jquery.min.js'/>"></script>
	<script
		src="<c:url value='/assets/admin/assets/js/bootstrap.bundle.min.js'/>"></script>
	<script src="<c:url value='/assets/admin/assets/js/modernizr.js'/>"></script>
	<script src="<c:url value='/assets/admin/assets/js/moment.js'/>"></script>

	<!-- *************
			************ Vendor Js Files *************
		************* -->

	<!-- Overlay Scroll JS -->
	<script
		src="<c:url value='/assets/admin/assets/vendor/overlay-scroll/jquery.overlayScrollbars.min.js'/>"></script>
	<script
		src="<c:url value='/assets/admin/assets/vendor/overlay-scroll/custom-scrollbar.js'/>"></script>

	<!-- Apex Charts -->
	<script
		src="<c:url value='/assets/admin/assets/vendor/apex/apexcharts.min.js'/>"></script>
	<script
		src="<c:url value='/assets/admin/assets/vendor/apex/custom/sales/sparkline.js'/>"></script>
	<script
		src="<c:url value='/assets/admin/assets/vendor/apex/custom/sales/salesGraph.js'/>"></script>
	<script
		src="<c:url value='/assets/admin/assets/vendor/apex/custom/sales/revenueGraph.js'/>"></script>
	<script
		src="<c:url value='/assets/admin/assets/vendor/apex/custom/sales/taskGraph.js'/>"></script>

	<!-- Vector Maps -->
	<script
		src="<c:url value='/assets/admin/assets/vendor/jvectormap/jquery-jvectormap-2.0.5.min.js'/>"></script>
	<script
		src="<c:url value='/assets/admin/assets/vendor/jvectormap/world-mill-en.js'/>"></script>
	<script
		src="<c:url value='/assets/admin/assets/vendor/jvectormap/gdp-data.js'/>"></script>
	<script
		src="<c:url value='/assets/admin/assets/vendor/jvectormap/custom/world-map-markers2.js'/>"></script>

	<!-- Main Js Required -->
	<script src="<c:url value='/assets/admin/assets/js/main.js'/>"></script>

</body>

</html>