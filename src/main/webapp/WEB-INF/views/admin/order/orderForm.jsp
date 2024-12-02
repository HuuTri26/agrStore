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
											<div class="card-title">VIEW ORDER</div>
											<!-- Disable các input field -->
											<input type="text" class="form-control"
												value="${orderBill.orderBillId}" disabled>
										</c:when>

										<c:when test="${mode == 'EDIT'}">
											<!-- Hiển thị form chỉnh sửa -->
											<div class="card-title">EDIT ORDER</div>
											<!-- Populate dữ liệu vào form -->
											<input type="text" class="form-control"
												value="${orderBill.orderBillId}" disabled>
										</c:when>
									</c:choose>
								</div>
								<div class="card-body">
									<form>
										<div class="row gx-3">
											<div class="col-sm-12 col-12">
												<div class="card-border">
													<div class="card-border-title">Order</div>
													<div class="card-border-body">

														<div class="row gx-3">
															<div class="col-sm-6 col-12">
																<div class="mb-3">
																	<label class="form-label">Customer Name<span
																		class="text-red">*</span></label> <input type="text"
																		class="form-control"
																		value="${orderBill.account.fullName }" readonly>
																</div>
															</div>
															<div class="col-sm-6 col-12">
																<div class="mb-3">
																	<label class="form-label">Employee Name<span
																		class="text-red">*</span></label> <input type="text"
																		class="form-control" value="${employee.fullName }"
																		readonly>
																</div>
															</div>
															<div class="col-sm-6 col-12">
																<div class="mb-3">
																	<%-- <label class="form-label">Total Price <span
																	class="text-red">*</span></label> <input type="text"
																	class="form-control" value="${orderBill.totalPrice }"
																	readonly> --%>
																	<label class="form-label">Total Price <span
																		class="text-red">*</span></label> <input type="text"
																		class="form-control"
																		value="<fmt:formatNumber value="${orderBill.totalPrice }"
															pattern="#,###.## VND;VND -#,###.##" type="currency"
															currencySymbol="VND" />"
																		readonly>
																</div>
															</div>
															<div class="col-sm-6 col-12">
																<div class="mb-3">
																	<label class="form-label">Total Quantity <span
																		class="text-red">*</span></label> <input type="number"
																		class="form-control"
																		value="${orderBill.totalQuantity }" readonly>
																</div>
															</div>

															<!-- <div class="col-sm-6 col-12">
														<div class="mb-3">
															<label class="form-label">Status Order <span
																class="text-red">*</span></label> <input type="number"
																class="form-control" placeholder="">
														</div>
													</div> -->
															<div class="col-sm-6 col-12">
																<div class="mb-3">
																	<label class="form-label">Status Order <span
																		class="text-red">*</span></label>
																	<form:form method="PUT" modelAttribute="orderBill"
																		action="admin/orderManagement/order.htm">
																		<form:select path="statusOrder" class="form-control">
																			<form:options items="${statusOrderMap}" />
																		</form:select>
																	</form:form>
																</div>
															</div>

															<div class="col-sm-6 col-12">
																<div class="mb-3">
																	<label class="form-label">Order Time <span
																		class="text-red">*</span></label> <input type="text"
																		class="form-control" value="${orderBill.orderTime }" readOnly>
																</div>
															</div>
															<div class="col-sm-12 col-12">
																<div class="custom-btn-group flex-end">
																	<button type="button" class="btn btn-light">
																		<a href="admin/orderManagement.htm">Cancel</a>
																	</button>
																	<c:choose>
																		<c:when test="${mode == 'ADD'}">
																			<button type="submit" class="btn btn-success">Add
																			</button>
																		</c:when>
																		<c:when test="${mode == 'EDIT'}">
																			<button type="submit" class="btn btn-primary">Update
																			</button>
																		</c:when>
																	</c:choose>
																</div>
															</div>

														</div>
													</div>
												</div>
											</div>
										</div>

									</form>
									<div class="col-sm-12 col-12">
										<div class="card-border">
											<div class="card-border-title">Oder Item</div>
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
																<c:forEach var="orderBillDetail"
																	items="${orderBillDetailEntities }" varStatus="status">
																	<tr>
																		<td>${status.index + 1}</td>
																		<td><div class="media-box">
																				<img src="/assets/product-images/${orderBillDetail.product.image}"
																					class="media-avatar rounded-circle img-fluid"
																					alt="User Avatar"
																					style="width: 45px; height: 45px; object-fit: cover;" />
																			</div></td>
																		<td>${orderBillDetail.product.productName}</td>
																		<td>${orderBillDetail.quantity }</td>
																		<td><fmt:formatNumber
																				value="${orderBillDetail.price }" pattern="#,##0" /></td>
																		<%-- <td>${orderBillDetail.price }</td> --%>


																	</tr>
																</c:forEach>
																<!-- <tr>
																		<td>1</td>
																		<td><div class="media-box">
																				<img
																					src="<c:url value='assets/admin/assets/images/user2.png" class="media-avatar'/>"
																					alt="Bootstrap Gallery">
																			</div></td>
																		<td>Táo</td>
																		<td>10</td>
																		<td><fmt:formatNumber value="85000"
															pattern="#,###.## VND;VND -#,###.##" type="currency"
															currencySymbol="VND" /></td>


																	</tr> -->
															</tbody>
														</table>
													</div>

												</div>

											</div>
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