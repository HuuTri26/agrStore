<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
<%@include file="/WEB-INF/views/include/admin/header.jsp"%>
<head>
<!-- Date Range CSS -->
<link rel="stylesheet"
	href="<c:url value='/assets/admin/assets/vendor/daterange/daterange.css'/>">

<!-- Uploader CSS -->
<link rel="stylesheet"
	href="<c:url value='/assets/admin/assets/vendor/dropzone/dropzone.min.css'/>" />
</head>
<body>
	<!-- Page wrapper start -->
	<div class="page-wrapper">
		<%@include file="/WEB-INF/views/include/admin/sideBar.jsp"%>
		<div class="main-container">
			<%@include file="/WEB-INF/views/include/admin/bodyHeader.jsp"%>
			<!-- Content wrapper scroll start -->
			<div class="content-wrapper-scroll">

				<!-- Content wrapper start -->
				<div class="content-wrapper">

					<!-- Row start -->
					<div class="row gx-3">
						<div class="col-xl-12">
							<!-- Card start -->
							<div class="card">
								<div class="card-body">

									<!-- Row start -->
									<div class="row gx-3">
										<div
											class="col-xxl-8 col-xl-12 col-lg-7 col-md-6 col-sm-12 col-12">
											<div class="row gx-3">
												<div class="col-sm-6 col-12">
													<div class="d-flex flex-row">
														<img
															src="<c:url value='/assets/admin/assets/images/user.png'/>"
															class="img-fluid change-img-avatar" alt="Free Dashboards">
														<div id="dropzone-sm" class="mb-4 dropzone-dark">
															<form action="/upload"
																class="dropzone needsclick dz-clickable"
																id="demo-upload">

																<div class="dz-message needsclick">
																	<button type="button" class="dz-button">Change
																		Image.</button>
																</div>

															</form>
														</div>
													</div>
												</div>
											</div>
											<div class="row gx-3">
												<div class="col-xxl-4 col-sm-6 col-12">
													<!-- Form Field Start -->
													<div class="mb-3">
														<label for="fullName" class="form-label">Full Name</label>
														<input type="text" class="form-control"
															placeholder="Full Name">
													</div>
												</div>
												<div class="col-xxl-4 col-sm-6 col-12">
													<!-- Form Field Start -->
													<div class="mb-3">
														<label for="emailID" class="form-label">Gmail</label> <input
															type="email" disabled="disabled" class="form-control"
															id="emailID" placeholder="reese@meail.com">
													</div>
												</div>
												<div class="col-xxl-4 col-sm-6 col-12">
													<!-- Form Field Start -->
													<div class="mb-3">
														<label for="phoneNo" class="form-label">Phone</label> <input
															type="text" class="form-control"
															placeholder="123-456-7890">
													</div>
												</div>

												<!-- <div class="col-xxl-4 col-sm-6 col-12">
													Form Field Start
													<div class="mb-3">
														<label for="city" class="form-label">City</label> <input
															type="text" class="form-control" id="city"
															placeholder="City">
													</div>
												</div> -->

												<div class="col-xxl-4 col-sm-6 col-12">
													<!-- Form Field Start -->
													<div class="mb-3">
														<label for="country" class="form-label">Province</label> <select
															class="form-control" id="country">
															<option selected="selected">TP HCM</option>
															<option>Cần Thơ</option>
															<option>Hải Phòng</option>
														</select>
													</div>
												</div>
												<div class="col-xxl-4 col-sm-6 col-12">
													<!-- Form Field Start -->
													<div class="mb-3">
														<label for="country" class="form-label">District</label> <select
															class="form-control" id="district">
															<option selected="selected">TP HCM</option>
															<option>Cần Thơ</option>
															<option>Hải Phòng</option>

														</select>
													</div>
												</div>
												<div class="col-xxl-4 col-sm-6 col-12">
													<!-- Form Field Start -->
													<div class="mb-3">
														<label for="country" class="form-label">Ward</label> <select
															class="form-control" id="country">
															<option selected="selected">TP HCM</option>
															<option>Cần Thơ</option>
															<option>Hải Phòng</option>

														</select>
													</div>
												</div>
												<div class="col-xxl-8 col-sm-12 col-12">
													<!-- Form Field Start -->
													<div class="mb-3">
														<label for="address" class="form-label">Address</label> <input
															type="text" disabled="disabled" class="form-control"
															id="address" placeholder="Address">
													</div>
												</div>
												<div class="col-xxl-2 col-sm-2 col-12">
													<!-- Form Field Start -->
													<div class="mb-3">
														<label for="status" class="form-label">Status</label> <input
															type="text" disabled="disabled" class="form-control"
															id="address" placeholder="Hoạt động">
													</div>
												</div>
												<!-- <div class="col-xxl-4 col-sm-6 col-12">
													Form Field Start
													<div class="mb-3">
														<label for="enterPassword" class="form-label">Password</label>
														<input type="password" class="form-control"
															id="enterPassword" placeholder="Enter Password">
													</div>
												</div> -->
											</div>
										</div>
										<div class="col-sm-12 col-12">
											<hr>
											<button class="btn btn-info">Save</button>
										</div>
									</div>
									<!-- Row end -->

								</div>
							</div>
							<!-- Card end -->
						</div>
					</div>
					<!-- Row end -->

				</div>
				<!-- Content wrapper end -->

				<!-- App Footer start -->
				<div class="app-footer">
					<span>..</span>
				</div>
				<!-- App footer end -->

			</div>
		</div>
	</div>
	<!-- Required jQuery first, then Bootstrap Bundle JS -->
	<%@include file="/WEB-INF/views/include/admin/footer.jsp"%>
</body>
</html>