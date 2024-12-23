<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%@include file="/WEB-INF/views/include/admin/header.jsp"%>
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
		<%@include file="/WEB-INF/views/include/admin/sideBar.jsp"%>
		<div class="main-container">
			<%@include file="/WEB-INF/views/include/admin/bodyHeader.jsp"%>

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
										<c:when test="${mode == 'ADD'}">
											<!-- Hiển thị form thêm mới -->
											<div class="card-title">ADD STAFF</div>
										</c:when>

										<c:when test="${mode == 'VIEW'}">
											<!-- Hiển thị thông tin chi tiết -->
											<div class="card-title">VIEW STAFF</div>
											<!-- Disable các input field -->
											<%-- <input type="text" class="form-control" value="${staff.name}"
												disabled> --%>
										</c:when>

										<c:when test="${mode == 'EDIT'}">
											<!-- Hiển thị form chỉnh sửa -->
											<div class="card-title">EDIT STAFF</div>
											<!-- Populate dữ liệu vào form -->
											<%-- <input type="text" class="form-control" value="${staff.name}"> --%>
										</c:when>
									</c:choose>
								</div>
								<div class="card-body">
									<form action="admin/staffManagement/addStaff.htm" method="post">
										<div class="row gx-3">
											<div class="col-sm-6 col-12">
												<div class="card-border">
													<div class="card-border-title">Staff Images</div>
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
																		src="<c:url value='/assets/admin/assets/images/user.png'/>"
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
																			<button type="submit" class="upload-btn">Upload
																				ảnh</button>
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
													<div class="card-border-title">Add Staff</div>
													<div class="card-border-body">

														<div class="row gx-3">
															<div class="col-sm-6 col-12">
																<div class="mb-3">
																	<label class="form-label">Staff Name <span
																		class="text-red">*</span></label> <input required type="text"
																		id="fid-name" name="full-name" value=""
																		class="txt-input"> ${nameErr }
																</div>
															</div>
															<div class="col-sm-6 col-12">
																<div class="mb-3">
																	<label class="form-label">Staff Phone <span
																		class="text-red">*</span></label> <input required type="text"
																		id="fid-pass" name="phone-number" value=""
																		class="txt-input"> ${phoneErr }
																</div>
															</div>
															<div class="col-sm-6 col-12">
																<div class="mb-3">
																	<label class="form-label">Staff Gmail <span
																		class="text-red">*</span></label> <input type="text"
																		id="fid-name" name="gmail" value="" class="txt-input">${gmailErr}
																</div>
															</div>
															<div class="col-sm-6 col-12">
																<div class="mb-3">
																	<label class="form-label">Staff Province <span
																		class="text-red">*</span></label> <select id="province"
																		name="provinceId" class="form-select"
																		onchange="this.form.submit()" class="form-control">
																		<option value=""></option>
																		<c:forEach var="province" items="${provinces}">
																			<option value="${province.id}"
																				${province.id == selectedProvinceId ? 'selected' : ''}>${province.name}</option>
																		</c:forEach>
																	</select> <input type="text" id="provinceText" readonly
																		value="${selectedProvince.name}"
																		placeholder="Tỉnh/Thành phố đã chọn" />
																</div>
															</div>

															<div class="col-sm-6 col-12">
																<div class="mb-3">
																	<label class="form-label">Staff District <span
																		class="text-red">*</span></label> <select id="district"
																		name="districtId" class="form-select"
																		onchange="this.form.submit()" class="form-control">
																		<option value=""></option>
																		<c:forEach var="district" items="${districts}">
																			<option value="${district.id}"
																				${district.id == selectedDistrictId ? 'selected' : ''}>${district.name}</option>
																		</c:forEach>
																	</select> <input type="text" id="provinceText" readonly
																		value="${selectedDistrict.name}"
																		placeholder="Huyện/Quận đã chọn" />
																</div>
															</div>
															<%-- 	<div class="col-sm-6 col-12">
																<div class="mb-3">
																	<label class="form-label">Staff District <span
																		class="text-red">*</span></label> <select id="district"
																		name="districtId" class="form-select"
																		onchange="this.form.submit()"
																	 class="form-control">
																		<option value=""></option>
																		<c:forEach var="district" items="${districts}">
																			<option value="${district.id}"
																				${district.id == selectedDistrictId ? 'selected' : ''}>${district.name}</option>
																		</c:forEach>
																	</select> <input type="text" id="provinceText" readonly
																		value="${selectedDistrict.name}"
																		placeholder="Huyện/Quận đã chọn" />
																</div>
															</div> --%>
															<div class="col-sm-6 col-12">
																<div class="mb-3">
																	<label class="form-label">Staff Ward <span
																		class="text-red">*</span></label> <select id="ward"
																		name="wardId" class="form-select"
																		onchange="this.form.submit()" class="form-control">
																		<option value=""></option>
																		<c:forEach var="ward" items="${wards}">
																			<option value="${ward.id}"
																				${ward.id == selectedWardId ? 'selected' : ''}>${ward.name}</option>
																		</c:forEach>
																	</select> <input type="text" id="wardText" readonly
																		value="${selectedWard.name}"
																		placeholder="Phường/Xã đã chọn" />
																</div>
															</div>
															<div class="col-sm-12 col-12">
																<div class="mb-3">
																	<label class="form-label">Staff Address Street
																		<span class="text-red">*</span>
																	</label> <input type="text" id="streetName" name="streetName"
																		placeholder="vd: Số 23, Đường Lê Văn Việt"
																		class="txt-input w-5">
																</div>
																${streetErr }
															</div>
															<%-- <div class="col-sm-12 col-12">
																<!-- Form Field Start -->
																<div class="mb-3">
																	<label for="address" class="form-label">Address</label>
																	<textarea rows="5" cols="60" readonly="readonly">${staff.address.streetName }, ${staff.address.ward.name }, ${staff.address.ward.district.name }, ${loggedInUser.address.ward.district.province.name }</textarea>
																</div>
															</div> --%>



														</div>
														<%-- <div class="col-sm-6 col-12">
																<div class="mb-3">
																	<label class="form-label">Staff Ward <span
																		class="text-red">*</span></label>
																	<form:select path="address.ward.id" name="wardId"
																		disabled="${mode == 'VIEW'}" class="form-control">
																		<form:option value="${staff.address.ward.id}">${staff.address.ward.name }</form:option>
																		<c:forEach var="ward" items="${wards}">
																			<form:option value="${ward.id}"
																				selected="${ward.id == selectedWardId}">${ward.name}</form:option>
																		</c:forEach>
																	</form:select>
																	<form:errors path="address.ward.id" />
																</div>
															</div> --%>

														<%-- <div class="col-sm-12 col-12">
															<div class="mb-3">
																<label class="form-label">Staff streetname
																	Address <span class="text-red">*</span>
																</label> <input type="text" class="form-control" placeholder=""
																	readonly="${mode == 'VIEW'}" />
																<form:errors path="address.streetName" />
															</div>
														</div>
														<div class="col-sm-6 col-12">
															<div class="mb-3">
																<label class="form-label">Status <span
																	class="text-red">*</span></label>

																<form:input type="text" path="status"
																	class="form-control" placeholder=""
																	readonly="${mode == 'VIEW'}" />
																<form:errors path="status" />
															</div>
														</div>
														<div class="col-sm-6 col-12">
															<div class="mb-3">
																<label class="form-label">Create At <span
																	class="text-red">*</span></label>

																<form:input type="text" path="createAt"
																	class="form-control" placeholder=""
																	readonly="${mode == 'VIEW'}" />
																<form:errors path="createAt" />
															</div>
														</div>
														<div class="col-sm-6 col-12">
															<div class="mb-3">
																<label class="form-label">Update At <span
																	class="text-red">*</span></label>

																<form:input type="text" path="updateAt"
																	class="form-control" placeholder=""
																	readonly="${mode == 'VIEW'}" />
																<form:errors path="updateAt" />
															</div>
														</div> --%>

													</div>

												</div>
											</div>


											<div class="col-sm-12 col-12">
												<div class="custom-btn-group flex-end">
													<button type="button" class="btn btn-light"><a href="admin/staffManagement.htm">Cancel</a></button>
													<!-- Nút submit tương ứng với từng mode -->

													<button type="submit" class="btn btn-success"
														name="add-staff">Add Staff</button>

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
	<%@include file="/WEB-INF/views/include/admin/footer.jsp"%>
	<script>
    function loadDistricts() {
        var provinceId = document.querySelector('select[path="address.ward.district.province.id"]').value;

        // Gửi AJAX request để lấy các district theo province đã chọn
        fetch('/admin/staffManagement/getDistricts?address.ward.district.province.id=' + provinceId)
            .then(response => response.json())
            .then(data => {
                // Clear và cập nhật lại district dropdown
                var districtSelect = document.querySelector('select[path="address.ward.district.id"]');
                districtSelect.innerHTML = '<option value="">Select District</option>';
                data.forEach(function(district) {
                    var option = document.createElement('option');
                    option.value = district.id;
                    option.text = district.name;
                    districtSelect.appendChild(option);
                });

                // Cập nhật ward dropdown nếu có
                loadWards();
            });
    }

    function loadWards() {
        var districtId = document.querySelector('select[path="address.ward.district.id"]').value;

        // Gửi AJAX request để lấy các ward theo district đã chọn
        fetch('/admin/staffManagement/getWards?address.ward.district.id=' + districtId)
            .then(response => response.json())
            .then(data => {
                // Clear và cập nhật lại ward dropdown
                var wardSelect = document.querySelector('select[path="address.ward.id"]');
                wardSelect.innerHTML = '<option value="">Select Ward</option>';
                data.forEach(function(ward) {
                    var option = document.createElement('option');
                    option.value = ward.id;
                    option.text = ward.name;
                    wardSelect.appendChild(option);
                });
            });
    }
</script>
</body>
</html>