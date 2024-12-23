<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%@include file="/WEB-INF/views/include/staff/header.jsp"%>
<head>
<!-- Scrollbar CSS -->
<link rel="stylesheet"
	href="<c:url value='/assets/admin/assets/vendor/overlay-scroll/OverlayScrollbars.min.css'/>">

<!-- Data Tables -->
<link rel="stylesheet"
	href="<c:url value='/assets/admin/assets/vendor/datatables/dataTables.bs5.css'/>" />
<link rel="stylesheet"
	href="<c:url value='/assets/admin/assets/vendor/datatables/dataTables.bs5-custom.css'/>" />

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-icons/1.8.1/font/bootstrap-icons.min.css">
<style>
.dropdown {
	position: relative;
	display: inline-block;
}

.dropdown-content {
	display: none;
	position: absolute;
	background-color: #f8f9fa;
	min-width: 120px;
	box-shadow: 0 8px 24px rgba(149, 157, 165, 0.2);
	z-index: 1;
	border-radius: 8px;
	overflow: hidden;
	top: 90%;
	left: 0;
	margin-top: 5px;
	border: 1px solid #e1e4e8;
	padding: 8px;
	z-index: 10;
}

.dropdown:hover .dropdown-content {
	display: flex;
	flex-wrap: wrap;
}

.dropdown-content a {
	color: #24292e;
	padding: 12px;
	text-decoration: none;
	display: flex;
	flex-direction: column;
	align-items: center;
	justify-content: center;
	transition: all 0.2s ease;
	font-size: 12px;
	width: calc(50% - 8px);
	margin: 4px;
	border-radius: 4px;
}

.dropdown-content a:hover {
	background-color: #0366d6;
	color: white;
}

.dropdown-content i {
	font-size: 1.5em;
	margin-bottom: 4px;
}

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
						<div class="col-sm-12 col-12">
							<div class="card">
								<div class="card-header">
									<c:choose>

										<c:when test="${mode == 'VIEW'}">
											<!-- Hiển thị thông tin chi tiết -->
											<div class="card-title">VIEW CUSSTOMER</div>
											<!-- Disable các input field -->
											<%-- <input type="text" class="form-control" value="${staff.name}"
												disabled> --%>
										</c:when>

										<c:when test="${mode == 'EDIT'}">
											<!-- Hiển thị form chỉnh sửa -->
											<div class="card-title">EDIT CUSTOMER</div>
											<!-- Populate dữ liệu vào form -->
											<%-- <input type="text" class="form-control" value="${staff.name}"> --%>
										</c:when>
									</c:choose>
								</div>
								<div class="card-body">
									<form>
										<div class="row gx-3">
											<div class="col-sm-6 col-12">
												<div class="card-border">
													<div class="card-border-title">Cusstomer Images</div>
													<div class="card-border-body">
														<form>
															<c:choose>
																<c:when test="${mode == 'ADD'}">
																	<!-- Hiển thị form thêm mới -->
																	<div id="dropzone" class="dropzone-dark">

																		<div class="input_file_cate">

																			<input type="file" class="dz-button" />
																			<!-- <br> <span class="note needsclick">(This is
																	just a demo dropzone. Selected files are <strong>not</strong>
																	actually uploaded.)
																</span> -->
																		</div>
																		<div class="dz-message needsclick button-container">
																			<button class="upload-btn">Upload ảnh</button>
																		</div>



																	</div>
																</c:when>

																<c:when test="${mode == 'VIEW'}">
																	<!-- Hiển thị thông tin chi tiết -->




																	<img
																		src="<c:url value='/assets/user-images/${customer.avatar}'/>"
																		class="img-fluid change-img-avatar"
																		alt="Free Dashboards">


																	<!-- <br> <span class="note needsclick">(This is
																	just a demo dropzone. Selected files are <strong>not</strong>
																	actually uploaded.)
																</span> -->









																	<!-- <br> <span class="note needsclick">(This is
																	just a demo dropzone. Selected files are <strong>not</strong>
																	actually uploaded.)
																</span> -->


																	<!-- Disable các input field -->
																	<%-- <input type="text" class="form-control"
												value="${category.name}" disabled> --%>
																</c:when>

																<c:when test="${mode == 'EDIT'}">
																	<!-- Hiển thị form chỉnh sửa -->
																	<div id="dropzone" class="dropzone-dark">


																		<div class="input_file_cate">

																			<input type="file" class="dz-button" />
																			<!-- <br> <span class="note needsclick">(This is
																	just a demo dropzone. Selected files are <strong>not</strong>
																	actually uploaded.)
																</span> -->
																		</div>
																		<div class="dz-message needsclick button-container">
																			<button class="upload-btn">Upload ảnh</button>
																		</div>



																	</div>
																	<!-- Populate dữ liệu vào form -->
																	<%-- <input type="text" class="form-control"
												value="${category.name}"> --%>
																</c:when>
															</c:choose>
														</form>
													</div>
												</div>
											</div>
											<div class="col-sm-6 col-12">
												<div class="card-border">
													<div class="card-border-title">Customer</div>
													<div class="card-border-body">
														<div class="row gx-3">
															<div class="col-sm-6 col-12">
																<div class="mb-3">
																	<label class="form-label">Customer Name <span
																		class="text-red">*</span></label> <input type="text"
																		class="form-control" placeholder=""
																		value="${customer.fullName }" readonly>
																</div>
															</div>
															<div class="col-sm-6 col-12">
																<div class="mb-3">
																	<label class="form-label">Customer Phone <span
																		class="text-red">*</span></label> <input type="text"
																		class="form-control" placeholder=""
																		value="${customer.phoneNumber }" readonly>
																</div>
															</div>
															<div class="col-sm-6 col-12">
																<div class="mb-3">
																	<label class="form-label">Customer Gmail <span
																		class="text-red">*</span></label> <input type="email"
																		class="form-control" placeholder=""
																		value="${customer.gmail }" readonly>
																</div>
															</div>
															<div class="col-sm-6 col-12">
																<div class="mb-3">
																	<!-- <label class="form-label">Customer Ward <span
																	class="text-red">*</span></label> <select class="form-control">
																	<option value="Select Ward">Select
																		ward</option>
																	<option value="Mobiles">Ward</option>
																	<option value="Books">Ward</option>
																	<option value="Games">Ward</option>
																</select> -->
																	<label class="form-label">Xã <span
																		class="text-red">*</span></label> <input type="text"
																		class="form-control" placeholder=""
																		value="${customer.address.ward.name }" readonly>

																</div>
															</div>

															<div class="col-sm-6 col-12">
																<div class="mb-3">
																	<!-- <label class="form-label">Customer District <span
																	class="text-red">*</span></label> <select class="form-control">
																	<option value="Select District">Select
																		District</option>
																	<option value="Mobiles">Disstrict1</option>
																	<option value="Books">District2</option>
																	<option value="Games">Disstrict3</option>
																</select> -->
																	<label class="form-label">Huyện <span
																		class="text-red">*</span></label> <input type="text"
																		class="form-control" placeholder=""
																		value="${customer.address.ward.district.name }"
																		readonly>

																</div>
															</div>

															<div class="col-sm-6 col-12">
																<div class="mb-3">
																	<!-- <label class="form-label">Customer Province <span
																	class="text-red">*</span></label> <select class="form-control">
																	<option value="Select Province">Select
																		Province</option>
																	<option value="Mobiles">Province1</option>
																	<option value="Books">Province2</option>
																	<option value="Games">Province3</option>
																</select> -->
																	<label class="form-label">Tỉnh <span
																		class="text-red">*</span></label> <input type="text"
																		class="form-control" placeholder=""
																		value="${customer.address.ward.district.province.name }"
																		readonly>

																</div>
															</div>
															<div class="col-sm-12 col-12">
																<div class="mb-3">
																	<label class="form-label">Customer Address <span
																		class="text-red">*</span></label> <input type="text"
																		class="form-control" placeholder=""
																		value="${customer.address.streetName }" readonly>
																</div>
															</div>
															<div class="col-sm-12 col-12">
																<div class="mb-3">
																	<label class="form-label">Customer streetname
																		Address <span class="text-red">*</span>
																	</label>
																	
																	<textarea rows="5" cols="60" readonly="readonly">${UltilityImpl.XSSEscape4HTML(customer.address.streetName) }, ${UltilityImpl.XSSEscape4HTML(customer.address.ward.name) }, ${UltilityImpl.XSSEscape4HTML(customer.address.ward.district.name) }, ${UltilityImpl.XSSEscape4HTML(customer.address.ward.district.province.name) }</textarea>
																</div>
															</div>
															<div class="col-sm-6 col-12">
																<div class="mb-3">
																	<%-- <label class="form-label">Status <span
																					class="text-red">*</span></label> <input type="text"
																					class="form-control" value="${customer.status }"> --%>

																	<c:choose>
																		<c:when test="${customer.status == true}">
																			<c:set var="statusText" value="Còn hoạt động" />
																		</c:when>
																		<c:when test="${customer.status == false}">
																			<c:set var="statusText" value="Ngừng hoạt động" />
																		</c:when>
																		<c:otherwise>
																			<c:set var="statusText"
																				value="Trạng thái không xác định" />
																		</c:otherwise>
																	</c:choose>
																	<label class="form-label">Status <span
																		class="text-red">*</span></label> <input type="text"
																		class="form-control" value="${statusText}" readonly>

																</div>
															</div>
															<div class="col-sm-6 col-12">
																<div class="mb-3">
																	<label class="form-label">Create At <span
																		class="text-red">*</span></label> <input type="text"
																		class="form-control" placeholder=""
																		value="${customer.createAt }" readonly>
																</div>
															</div>

															<!-- <div class="col-sm-6 col-12">
															<div class="mb-3">
																<label class="form-label">Product Provider <span
																	class="text-red">*</span></label> <select class="form-control">
																	<option value="Select Product Category">Select
																		Product Provider</option>
																	<option value="Mobiles">Provider1</option>
																	<option value="Books">Provider2</option>
																	<option value="Games">Provider3</option>
																</select>
															</div>
														</div> -->
															<!-- <div class="col-sm-6 col-12">
															<div class="mb-3">
																<label class="form-label">Product Price <span
																	class="text-red">*</span></label> <input type="text"
																	class="form-control" placeholder="Enter Product Price">
															</div>
														</div> -->
															<!-- 	<div class="col-sm-6 col-12">
															<div class="mb-3">
																<label class="form-label">Quantity <span
																	class="text-red">*</span></label> <input type="number"
																	class="form-control" placeholder="Enter Product Price">
															</div>
														</div> -->
															<!-- <div class="col-sm-6 col-12">
															<div class="mb-3">
																<label class="form-label">Product Unit <span
																	class="text-red">*</span></label> <input type="text"
																	class="form-control" placeholder="Enter Product Price">
															</div>
														</div> -->
															<!-- <div class="col-sm-6 col-12">
															<div class=" mb-3">
																<label class="form-label">Product Discount</label>
																<div class="input-group">
																	<input type="text" class="form-control"
																		placeholder="Set Product Discount"> <span
																		class="input-group-text">%</span>
																</div>
															</div>
														</div> -->
															<!-- 	<div class="col-sm-12 col-12">
															<div class="mb-0">
																<label class="form-label">Staff Description <span
																	class="text-red">*</span></label>
																<textarea rows="4" class="form-control"
																	placeholder="Enter Description"></textarea>
															</div>
														</div> -->
														</div>





													</div>
												</div>

											</div>
											<div class="col-sm-12 col-12">
												<div class="custom-btn-group flex-end">
													<button type="button" class="btn btn-light">
														<a href="staff/customerManagement.htm">Back</a>
													</button>
													<!-- Nút submit tương ứng với từng mode -->
													<c:choose>
														<c:when test="${mode == 'ADD'}">
															<button type="submit" class="btn btn-success">Add
																Staff</button>
														</c:when>
														<c:when test="${mode == 'EDIT'}">
															<button type="submit" class="btn btn-primary">Update
																Staff</button>
														</c:when>
													</c:choose>
												</div>
											</div>
										</div>
									</form>
								</div>
							</div>
						</div>
					</div>
					<!-- Row end -->

				</div>
				<!-- Content wrapper end -->
				<!-- App Footer start -->
				<div class="app-footer">
					<span>Nhom10</span>
				</div>
				<!-- App footer end -->

			</div>
		</div>
		<!-- Content wrapper scroll end -->

		<!-- *************
				************ Main container end *************
			************* -->


	</div>
	<%@include file="/WEB-INF/views/include/staff/footer.jsp"%>
</body>
</html>