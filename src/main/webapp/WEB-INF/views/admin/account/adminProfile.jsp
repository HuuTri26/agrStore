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

/* /* Đặt khoảng cách giữa các hàng và cột */
/* .row {
	row-gap: 20px;
	column-gap: 15px;
} */

/* Căn chỉnh card */
/* .card {
	border-radius: 10px;
	box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.1);
	padding: 20px;
} */ */

/* Căn chỉnh input và select */
input[type="text"], input[type="email"], input[type="file"], select,
	textarea {
	width: 100%;
	padding: 10px 15px;
	border: 1px solid #ddd;
	border-radius: 8px;
	font-size: 14px;
	transition: border-color 0.3s;
}

input[type="text"]:focus, input[type="email"]:focus, input[type="file"]:focus,
	select:focus, textarea:focus {
	border-color: #007bff;
	outline: none;
}

/* Style cho label */
.form-label {
	font-size: 14px;
	font-weight: 600;
	color: #555;
	margin-bottom: 5px;
}

/* Căn chỉnh hình ảnh */
.img-fluid.change-img-avatar {
	max-width: 80px;
	max-height: 80px;
	border-radius: 50%;
	margin-right: 15px;
}

/* Style cho button */
button {
	padding: 10px 20px;
	font-size: 14px;
	font-weight: 600;
	border: none;
	border-radius: 8px;
	background-color: #007bff;
	color: #fff;
	cursor: pointer;
	transition: background-color 0.3s;
}

button:hover {
	background-color: #0056b3;
}

/* Căn chỉnh select dropdown */
.form-select {
	appearance: none;
	background-color: #fff;
	background-image:
		url('data:image/svg+xml;charset=US-ASCII,%3Csvg xmlns=\'http://www.w3.org/2000/svg\' viewBox=\'0 0 4 5\'%3E%3Cpath fill=\'%23007bff\' d=\'M2 0L0 2h4z\'/%3E%3C/svg%3E');
	background-repeat: no-repeat;
	background-position: right 10px center;
	background-size: 10px 10px;
}

/* Style cho textarea */
textarea {
	resize: none;
	padding: 10px;
}

/* Dropzone */
#dropzone {
	border: 2px dashed #007bff;
	border-radius: 8px;
	padding: 10px;
	text-align: center;
	font-size: 14px;
	color: #007bff;
}

#dropzone:hover {
	background-color: #f0f8ff;
}

/* Responsive adjustments */
@media ( max-width : 768px) {
	.img-fluid.change-img-avatar {
		max-width: 60px;
		max-height: 60px;
	}
	button {
		width: 100%;
	}
}
</style>
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
									<form action="admin/adminProfile.htm" method="post">
										<!-- Row start -->
										<div class="row gx-3">
											<div
												class="col-xxl-8 col-xl-12 col-lg-7 col-md-6 col-sm-12 col-12">
												<div class="row gx-3">

													<div class="col-sm-6 col-12">
														<div class="d-flex flex-row">
															<img
																src="<c:url value='/assets/user-images/${loggedInUser.avatar }'/>"
																class="img-fluid change-img-avatar"
																alt="Free Dashboards">
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
															<label for="fullName" class="form-label">Full
																Name</label> <input name="fullName"
																value="${loggedInUser.fullName }" type="text"
																class="form-control" placeholder=""> ${nameErr }
														</div>
													</div>
													<div class="col-xxl-4 col-sm-6 col-12">
														<!-- Form Field Start -->
														<div class="mb-3">
															<label for="email" class="form-label">Gmail</label> <input
																type="email" disabled="disabled" class="form-control"
																id="gmail" placeholder="" name="gmail"
																value="${loggedInUser.gmail }">
														</div>
													</div>
													<div class="col-xxl-4 col-sm-6 col-12">
														<!-- Form Field Start -->
														<div class="mb-3">
															<label for="phoneNo" class="form-label">Phone</label> <input
																type="text" class="form-control" placeholder=""
																name="phoneNumber" value="${loggedInUser.phoneNumber }">${phoneErr }
														</div>
													</div>
													<div class="col-xxl-2 col-sm-6 col-12">
														<!-- Form Field Start -->
														<div class="mb-3">
															<label for="status" class="form-label">Status</label> <input
																type="text" disabled="disabled" class="form-control"
																name="status" id="status" placeholder=""
																value="${loggedInUser.status }">
														</div>
													</div>
													<div class="col-xxl-2 col-sm-6 col-12">
														<!-- Form Field Start -->
														<div class="mb-3">
															<label for="status" class="form-label">Create At</label>
															<input type="text" disabled="disabled"
																class="form-control" name="createAt" id="createAt"
																placeholder="" value="${loggedInUser.createAt }">
														</div>
													</div>
													<div class="col-xxl-2 col-sm-6 col-12">
														<!-- Form Field Start -->
														<div class="mb-3">
															<label for="status" class="form-label">Update At</label>
															<input type="text" disabled="disabled"
																class="form-control" name="status" id="status"
																placeholder="" value="${loggedInUser.updateAt }">
														</div>
													</div>


													<div class="col-xxl-4 col-sm-6 col-12">
														<!-- Form Field Start -->
														<div class="mb-3">
															<label for="country" class="form-label">Province</label>
															<select id="province" name="provinceId"
																class="form-select" onchange="this.form.submit()">
																<option value="">${loggedInUser.address.ward.district.province.name }</option>
																<c:forEach var="province" items="${provinces}">
																	<option value="${province.id}"
																		${province.id == selectedProvinceId ? 'selected' : ''}>${province.name}</option>
																</c:forEach>
															</select> <input type="text" id="provinceText" readonly
																value="${selectedProvince.name}"
																placeholder="Tỉnh/Thành phố đã chọn" />
														</div>
													</div>
													<div class="col-xxl-4 col-sm-6 col-12">
														<!-- Form Field Start -->
														<div class="mb-3">
															<label for="country" class="form-label">District</label>
															<select id="district" name="districtId"
																class="form-select" onchange="this.form.submit()">
																<option value="">${loggedInUser.address.ward.district.name }</option>
																<c:forEach var="district" items="${districts}">
																	<option value="${district.id}"
																		${district.id == selectedDistrictId ? 'selected' : ''}>${district.name}</option>
																</c:forEach>
															</select> <input type="text" id="districtText" readonly
																value="${selectedDistrict.name}"
																placeholder="Quận/Huyện đã chọn" />
														</div>
													</div>
													<div class="col-xxl-4 col-sm-6 col-12">
														<!-- Form Field Start -->
														<div class="mb-3">
															<label for="country" class="form-label">Ward</label> <select
																id="ward" name="wardId" class="form-select"
																onchange="this.form.submit()">
																<option value="">${loggedInUser.address.ward.name }</option>
																<c:forEach var="ward" items="${wards}">
																	<option value="${ward.id}">${ward.name}</option>
																</c:forEach>
															</select> <input type="text" id="wardText" readonly
																value="${selectedWard.name}"
																placeholder="Xã/Phường đã chọn" />
														</div>
													</div>
													<div class="col-xxl-8 col-sm-6 col-12">
														<!-- Form Field Start -->
														<div class="mb-3">
															<label for="address" class="form-label">StreetName</label>
															<input type="text" id="streetName" name="streetName"
																placeholder="Nhập tên đường cụ thể" class="form-control"
																class="txt-input w-5"> ${streetErr }
														</div>
													</div>
													<div class="col-xxl-8 col-sm-8 col-12">
														<!-- Form Field Start -->
														<div class="mb-3">
															<label for="address" class="form-label">Address</label>
															<textarea rows="5" cols="100" readonly="readonly">${loggedInUser.address.streetName }, ${loggedInUser.address.ward.name }, ${loggedInUser.address.ward.district.name }, ${loggedInUser.address.ward.district.province.name }</textarea>
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
												<button class="btn btn-info" name="save" type="submit">Save</button>
											</div>
										</div>
										<!-- Row end -->
									</form>
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