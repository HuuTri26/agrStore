<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
<%@include file="/WEB-INF/views/include/staff/header.jsp"%>
<head>
<!-- Date Range CSS -->
<link rel="stylesheet"
	href="<c:url value='/assets/admin/assets/vendor/daterange/daterange.css'/>">

<!-- Uploader CSS -->
<link rel="stylesheet"
	href="<c:url value='/assets/admin/assets/vendor/dropzone/dropzone.min.css'/>" />
<style>
.dropzone-dark {
	width: 100%;
	max-width: 600px; /* Reduced from 800px */
	margin: 0 auto;
	padding: 20px; /* Reduced from 30px */
	background: #f8f9fa;
	border-radius: 8px; /* Reduced from 12px */
	box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
}

.dz-message {
	width: 100%;
	margin-bottom: 15px; /* Reduced from 20px */
}

.dz-button {
	width: 100%;
	padding: 25px; /* Reduced from 40px */
	border: 2px dashed #4a90e2;
	border-radius: 6px; /* Reduced from 8px */
	cursor: pointer;
	background: #f0f7ff;
	transition: all 0.3s ease;
	height: 30vh;
}

.dz-button:hover {
	border-color: #357abd;
	background: #e6f2ff;
	transform: translateY(-2px);
}

.dz-button::file-selector-button {
	padding: 10px 20px; /* Reduced from 12px 24px */
	background: #4a90e2;
	color: white;
	border: none;
	border-radius: 4px; /* Reduced from 6px */
	font-size: 14px; /* Reduced from 16px */
	cursor: pointer;
	transition: background 0.3s ease;
}

.dz-button::file-selector-button:hover {
	background: #357abd;
}

.button-container {
	display: flex;
	justify-content: flex-end;
	margin-top: 15px; /* Reduced from 20px */
}

.upload-btn {
	padding: 12px 30px; /* Reduced from 15px 40px */
	background: linear-gradient(45deg, #4a90e2, #357abd);
	color: white;
	border: none;
	border-radius: 6px; /* Reduced from 8px */
	font-size: 16px; /* Reduced from 18px */
	font-weight: 600;
	cursor: pointer;
	transition: all 0.3s ease;
	box-shadow: 0 4px 15px rgba(74, 144, 226, 0.3);
	width: 100%;
}

.upload-btn:hover {
	background: linear-gradient(45deg, #357abd, #2868a9);
	transform: translateY(-2px);
	box-shadow: 0 6px 20px rgba(74, 144, 226, 0.4);
}

.upload-btn:active {
	transform: translateY(1px);
}
</style>
</head>
<body>
	<!-- Page wrapper start -->
	<div class="page-wrapper">
		<%@include file="/WEB-INF/views/include/staff/sideBar.jsp"%>
		<div class="main-container">
			<%@include file="/WEB-INF/views/include/staff/bodyHeader.jsp"%>
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
														<div id="dropzone" class="dropzone-dark">


															<div class="input_file_cate">

																<input type="file" class="dz-button" />
																<!-- <br> <span class="note needsclick">(This is
																	just a demo dropzone. Selected files are <strong>not</strong>
																	actually uploaded.)
																</span> -->
															</div>
															<!-- <div class="dz-message needsclick button-container">
																<button class="upload-btn">Upload ảnh</button>
															</div> -->



														</div>
													</div>
												</div>
											</div>
											<div class="row gx-3">
												<div class="col-xxl-4 col-sm-6 col-12">
													<!-- Form Field Start -->
													<div class="mb-3">
														<label for="fullName" class="form-label">Full Name</label>
														<input type="text" class="form-control" placeholder="">
													</div>
												</div>
												<div class="col-xxl-4 col-sm-6 col-12">
													<!-- Form Field Start -->
													<div class="mb-3">
														<label for="email" class="form-label">Gmail</label> <input
															type="email" disabled="disabled" class="form-control"
															id="email" placeholder="">
													</div>
												</div>
												<div class="col-xxl-4 col-sm-6 col-12">
													<!-- Form Field Start -->
													<div class="mb-3">
														<label for="phoneNo" class="form-label">Phone</label> <input
															type="text" class="form-control" placeholder="">
													</div>
												</div>


												<div class="col-xxl-4 col-sm-6 col-12">
													<!-- Form Field Start -->
													<div class="mb-3">
														<label for="country" class="form-label">Province</label> <select
															class="form-control" id="province">
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
															class="form-control" id="ward">
															<option selected="selected">TP HCM</option>
															<option>Cần Thơ</option>
															<option>Hải Phòng</option>

														</select>
													</div>
												</div>
												<div class="col-xxl-8 col-sm-6 col-12">
													<!-- Form Field Start -->
													<div class="mb-3">
														<label for="address" class="form-label">Address</label> <input
															type="text" disabled="disabled" class="form-control"
															id="address" placeholder="">
													</div>
												</div>
												<div class="col-xxl-2 col-sm-6 col-12">
													<!-- Form Field Start -->
													<div class="mb-3">
														<label for="status" class="form-label">Status</label> <input
															type="text" disabled="disabled" class="form-control"
															id="status" placeholder="">
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
	<%@include file="/WEB-INF/views/include/staff/footer.jsp"%>
</body>
</html>