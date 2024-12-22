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
											<div class="card-title">VIEW PRODUCT</div>
											<!-- Disable các input field -->
											<%-- <input type="text" class="form-control"
												value="${product.name}" disabled> --%>
										</c:when>

										<c:when test="${mode == 'EDIT'}">
											<!-- Hiển thị form chỉnh sửa -->
											<div class="card-title">EDIT PRODUCT</div>
											<!-- Populate dữ liệu vào form -->
											<%-- <input type="text" class="form-control"
												value="${product.name}"> --%>
										</c:when>
									</c:choose>
								</div>
								<div class="card-body">
									<form:form action="staff/productManagement/product.htm"
										method="post" modelAttribute="product">
										<div class="row gx-3">
											<form action="staff/product/uploadImg.htm" method="post"
												enctype="multipart/form-data">
												<!-- Product Image Section -->
												<div class="col-sm-6 col-12">
													<div class="card-border">
														<div class="card-border-title">Product Images</div>
														<div class="card-border-body">

															<c:choose>
																<c:when test="${mode == 'ADD' || mode == 'EDIT'}">
																	<div id="dropzone" class="dropzone-dark">
																		<div class="input_file_cate">
																			<input name="image" type="file" class="dz-button" />
																		</div>
																		<div class="dz-message needsclick button-container">

																			<button type="submit" class="upload-btn">Upload

																				Image</button>
																		</div>
																		${imgError}
																	</div>
																</c:when>
																<c:when test="${mode == 'VIEW'}">
																	<img
																		src="<c:url value='/assets/product-images/${product.image }'/>"
																		class="img-fluid change-img-avatar"
																		alt="Product Image">
																</c:when>
															</c:choose>
														</div>
													</div>
												</div>
											</form>





											<%-- <form:form action="admin/productManagement/product.htm"
											method="post" modelAttribute="product"> --%>


											<!-- Product Details Section -->
											<div class="col-sm-6 col-12">
												<div class="card-border">
													<div class="card-border-title">Add Product</div>
													<div class="card-border-body">
														<form:hidden path="productId" />

														<div class="row gx-3">
															<div class="col-sm-6 col-12">
																<div class="mb-3">
																	<label class="form-label">Product Name <span
																		class="text-red">*</span></label>
																	<form:input path="productName" class="form-control"
																		placeholder="" readonly="${mode == 'VIEW'}" />
																	<form:errors path="productName" />
																</div>
															</div>
															<div class="col-sm-6 col-12">
																<div class="mb-3">
																	<label class="form-label">Product Category <span
																		class="text-red">*</span></label>
																	<form:select path="category.categoryId"
																		class="form-control" disabled="${mode == 'VIEW'}">
																		<form:option value="${product.category.categoryId}">${product.category.categoryName}</form:option>
																		<c:forEach var="category" items="${categories}">
																			<form:option value="${category.categoryId}">${category.categoryName}</form:option>
																		</c:forEach>
																	</form:select>
																	<form:errors path="category.categoryId" />
																</div>
															</div>
															<div class="col-sm-6 col-12">
																<div class="mb-3">
																	<label class="form-label">Product Price <span
																		class="text-red">*</span></label>
																	<form:input path="price" class="form-control"
																		type="number" min="0" step="1000"
																		readonly="${mode == 'VIEW'}" />
																	<form:errors path="price" />
																</div>
															</div>
															<div class="col-sm-6 col-12">
																<div class="mb-3">
																	<label class="form-label">Product Unit <span
																		class="text-red">*</span></label>
																	<form:input path="unit" class="form-control"
																		placeholder="" readonly="${mode == 'VIEW'}" />
																	<form:errors path="unit" />
																</div>
															</div>
															<div class="col-sm-6 col-12">
																<div class="mb-3">
																	<label class="form-label">Update At <span
																		class="text-red">*</span></label>
																	<form:input path="updateAt" class="form-control"
																		readonly="true" placeholder="${sysDate}" />
																</div>
															</div>
															<form:hidden path="status" />
															<div class="col-sm-6 col-12">
																<div class="mb-3">
																	<label class="form-label">Quantity <span
																		class="text-red">*</span></label>
																	<form:input path="quantity" class="form-control"
																		placeholder="" type="number"
																		readonly="${mode == 'VIEW'}" />
																</div>
															</div>
															<div class="col-sm-6 col-12">
																<div class="mb-3">
																	<label class="form-label">Provider <span
																		class="text-red">*</span></label>
																	<form:select path="provider.id" class="form-control"
																		disabled="${mode == 'VIEW'}">
																		<form:option value="${product.provider.id}">${product.provider.providerName}</form:option>
																		<c:forEach var="provider" items="${providers}">
																			<form:option value="${provider.id}">${provider.providerName}</form:option>
																		</c:forEach>
																	</form:select>
																</div>
															</div>
															<div class="col-sm-6 col-12">
																<div class="mb-3">
																	<label class="form-label">Create At <span
																		class="text-red">*</span></label>
																	<form:input path="createAt" class="form-control"
																		placeholder="${sysDate}" readonly="true" />
																</div>
															</div>
															<div class="col-sm-12 col-12">
																<div class="mb-0">
																	<label class="form-label">Product Description <span
																		class="text-red">*</span>
																	</label>
																	<form:textarea path="descript" rows="4"
																		class="form-control" placeholder=""
																		readonly="${mode == 'VIEW'}"></form:textarea>
																</div>
															</div>
															<div class="col-sm-12 col-12">
																<div class="custom-btn-group flex-end">
																	<button type="button" class="btn btn-light"><a href="staff/productManagement.htm">Cancel</a></button>
																	<c:choose>
																		<c:when test="${mode == 'ADD'}">
																			<button name="${mode}" class="btn btn-success">Add
																				Product</button>
																		</c:when>
																		<c:when test="${mode == 'EDIT'}">
																			<button name="${mode}" class="btn btn-primary">Update
																				Product</button>
																		</c:when>
																	</c:choose>
																</div>
															</div>
														</div>
													</div>
												</div>
											</div>
										</div>
									</form:form>
								</div>
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