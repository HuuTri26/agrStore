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
										<c:when test="${mode == 'CREATE'}">
											<!-- Hiển thị thông tin chi tiết -->
											<div class="card-title">CREATE IMPORT BILL</div>
											<!-- Disable các input field -->
											<%-- <input type="text" class="form-control"
												value="${product.name}" disabled> --%>
										</c:when>

										<c:when test="${mode == 'VIEW'}">
											<!-- Hiển thị thông tin chi tiết -->
											<div class="card-title">VIEW IMPORT BILL</div>
											<!-- Disable các input field -->
											<%-- <input type="text" class="form-control"
												value="${product.name}" disabled> --%>
										</c:when>

										<c:when test="${mode == 'EDIT'}">
											<!-- Hiển thị form chỉnh sửa -->
											<div class="card-title">EDIT IMPORT BILl</div>
											<!-- Populate dữ liệu vào form -->
											<%-- <input type="text" class="form-control"
												value="${product.name}"> --%>
										</c:when>
									</c:choose>
								</div>
								<div class="card-body">

									<div class="row gx-3">
										<div class="col-sm-12 col-12">
											<div class="card-border">
												<div class="card-border-title">Import bill</div>
												<div class="card-border-body">

													<div class="row gx-3">

														<div class="col-sm-6 col-12">
															<div class="mb-3">
																<label class="form-label">Employee Name<span
																	class="text-red">*</span></label> <input
																	value="${importBill.account.fullName }" type="text"
																	class="form-control" placeholder="" readonly>
															</div>
														</div>
														<div class="col-sm-6 col-12">
															<div class="mb-3">
																<label class="form-label">Total Price <span
																	class="text-red">*</span></label> <input
																	class="form-control text-danger fw-bold"
																	value="<fmt:formatNumber value="${importBill.totalPrice }"
															pattern="#,###.## VND;VND -#,###.##" type="currency"
															currencySymbol="VND" />"
																	type="text" class="form-control" placeholder=""
																	readonly>
															</div>
														</div>
														<div class="col-sm-6 col-12">
															<div class="mb-3">
																<label class="form-label">Total Quantity <span
																	class="text-red">*</span></label> <input
																	value="${importBill.totalQuantity }" type=number
																	class="form-control" placeholder="" readonly>
															</div>
														</div>
														<div class="col-sm-6 col-12">
															<div class="mb-3">
																<label class="form-label">Import at <span
																	class="text-red">*</span></label> <input
																	value="${importBill.createAt }" type=text
																	class="form-control" placeholder="" readonly>
															</div>
														</div>
													</div>


												</div>

											</div>
										</div>
										<div class="col-sm-12 col-12">
											<div class="card-border">
												<div class="card-border-title">Import bill Item</div>
												<div class="card-border-body">

													<div class="row gx-3">
														<div class="table-responsive">
															<table id="basicExample" class="table custom-table">
																<thead>
																	<tr>
																		<th>STT</th>
																		<th>Product Image</th>
																		<th>Product Name</th>
																		<th>Quantity</th>
																		<th>Price</th>
																	</tr>
																</thead>
																<tbody>
																	<c:forEach var="importBillDetail"
																		items="${importBillDetails }" varStatus="status">
																		<tr>
																			<%-- <td>${importBillDetail.importBillDetailId }</td> --%>
																			<td>${status.index + 1}</td>
																			<td><div class="media-box">
																					<img
																						src="<c:url value='assets/admin/assets/product-images/${importBillDetail.product.image }" class="media-avatar'/>"
																						alt="Bootstrap Gallery">
																				</div></td>
																			<td>${importBillDetail.product.productName}</td>
																			<td>${importBillDetail.quantity }</td>
																			<td><fmt:formatNumber
																					value="${importBillDetail.price }"
																					pattern="#,###.## VND;VND -#,###.##"
																					type="currency" currencySymbol="VND" /></td>

																		</tr>
																	</c:forEach>
																	<%-- <tr>
																		<td>1</td>
																		<td><div class="media-box">
																				<img
																					src="<c:url
																	value='assets/admin/assets/images/user2.png"
																	class="media-avatar'/>"
																					alt="Bootstrap Gallery">
																			</div></td>
																		<td>Táo</td>
																		<td>10</td>
																		<td><fmt:formatNumber value="85000"
																				pattern="#,###.##
																	VND;VND -#,###.##"
																				type="currency" currencySymbol="VND" /></td>
																	</tr> --%>
																</tbody>
															</table>
														</div>

													</div>

												</div>
											</div>
										</div>

										<div class="col-sm-12 col-12">
											<div class="custom-btn-group flex-end">
												<button type="button" class="btn btn-light">Cancel</button>
												<c:choose>
													<c:when test="${mode == 'CREATE'}">
														<button type="submit" class="btn btn-success">Create
															ImportBill</button>
													</c:when>
													<c:when test="${mode == 'EDIT'}">
														<button type="submit" class="btn btn-primary">Update
															Importbill</button>
													</c:when>
												</c:choose>
											</div>
										</div>
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