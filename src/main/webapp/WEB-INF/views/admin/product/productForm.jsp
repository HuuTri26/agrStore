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

									<div class="row gx-3">
										<div class="col-sm-6 col-12">
											<div class="card-border">
												<div class="card-border-title">Product Images</div>
												<div class="card-border-body">

													<div id="dropzone" class="dropzone-dark">
														<form action="upload" method="post"
															enctype="multipart/form-data"
															class="dropzone needsclick dz-clickable" id="demo-upload">

															<div class="dz-message needsclick">
																<button type="button" class="dz-button">Chọn
																	ảnh</button>

															</div>

														</form>
													</div>

												</div>
											</div>
										</div>

										<form:form action="productManagement/product.htm"
											method="post" modelAttribute="product">

											<div class="col-sm-6 col-12">
												<div class="card-border">
													<div class="card-border-title">Add Product</div>
													<div class="card-border-body">
													
														<form:hidden path="productId" />
														<form:hidden path="status" />
														<form:hidden path="createAt" />

														<div class="row gx-3">
															<div class="col-sm-6 col-12">
																<div class="mb-3">
																	<label class="form-label">Product Name <span
																		class="text-red">*</span></label>
																	<form:input path="productName" class="form-control"
																		placeholder="Nhập tên của sản phẩm"
																		readonly="${mode == 'VIEW'}" />
																	<form:errors path="productName" />
																</div>
															</div>
															<div class="col-sm-6 col-12">
																<div class="mb-3">
																	<label class="form-label">Product Category <span
																		class="text-red">*</span></label>
																	<form:select path="category.categoryId"
																		class="form-control" disabled="${mode == 'VIEW'}">
																		<form:option value="">-- Select
																			Product Category --</form:option>

																		<c:forEach var="category" items="${categories }">
																			<form:option value="${category.categoryId }">${category.categoryName }</form:option>
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
																		type="number" readonly="${mode == 'VIEW'}" />
																	<form:errors path="price" />
																</div>
															</div>
															<div class="col-sm-6 col-12">
																<div class="mb-3">
																	<label class="form-label">Product Unit <span
																		class="text-red">*</span></label>
																	<form:input path="unit" class="form-control"
																		placeholder="Nhập đơn vị tính của sản phẩm"
																		readonly="${mode == 'VIEW'}" />
																	<form:errors path="unit" />
																</div>
															</div>
															<div class="col-sm-12 col-12">
																<div class="mb-0">
																	<label class="form-label">Product Description <span
																		class="text-red">*</span></label>
																	<form:textarea path="descript" rows="4"
																		class="form-control"
																		placeholder="Nhập mô tả của sản phẩm"
																		readonly="${mode == 'VIEW'}"></form:textarea>
																</div>
															</div>
														</div>

													</div>
												</div>
											</div>

											<div class="col-sm-12 col-12">
												<div class="custom-btn-group flex-end">
													<button type="button" class="btn btn-light">Cancel</button>
													<button name="${mode }" class="btn btn-success">${mode }</button>
													<%-- <c:choose>
														<c:when test="${mode == 'ADD'}">
															<button type="submit" class="btn btn-success">Add
																Category</button>
														</c:when>
														<c:when test="${mode == 'EDIT'}">
														<button type="submit" class="btn btn-primary">Update
															Category</button>
													</c:when>
													</c:choose> --%>
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
	<%@include file="/WEB-INF/views/include/admin/footer.jsp"%>
</body>
</html>