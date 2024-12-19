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
									<div class="card-title">
										<h2 class="mb-4">Create Import Bill</h2>
									</div>
								</div>
								<div class="card-body">

									<div class="row gx-3">
										<div class="container mt-4">


											<!-- Chọn nhà cung cấp -->
											<div class="row">
												<div class="col-md-6">

													<form:form
														action="admin/importBillManagement/getProductsByProvider.htm"
														method="POST">
														<!-- Dropdown chọn nhà cung cấp -->
														<div class="form-group">
															<label for="supplier" class="form-label">Choose
																Provider</label> <select name="providerId" id="supplier"
																class="form-select" onchange="this.form.submit()">
																<c:forEach items="${providers}" var="provider">
																	<option value="${provider.id}"
																		${provider.id == selectedProviderId ? 'selected' : ''}>
																		${provider.providerName}</option>
																</c:forEach>
															</select>
														</div>
													</form:form>

													<%-- <div class="form-group">
															<label for="supplier" class="form-label">Choose
																Provider</label> <select name="providerId" id="supplier"
																class="form-select" onchange="getProductsByProvider()">
																<c:forEach items="${providers}" var="provider">
																	<option value="${provider.id}"
																		${provider.id == selectedProviderId ? 'selected' : ''}>
																		${provider.providerName}</option>
																</c:forEach>
															</select>
														</div> --%>
												</div>
												<div class="col-md-6">
													<label for="supplier" class="form-label">Staff Name</label>
													<div class="form-group">
														<%-- <input name="currentAccount"
															value="${loggedInUser.accountId }" readOnly
															disabled="disabled" hidden />  --%>
														<input
															name="currentAccountName"
															value="${loggedInUser.fullName }" readOnly
															disabled="disabled" />

													</div>

												</div>
											</div>

											<!-- Danh sách sản phẩm của nhà cung cấp -->
											<div class="table-container">
												<h4>Product List From Provider</h4>

												<form:form id="productForm"
													action="admin/importBillManagement/getSelectedProducts.htm"
													method="POST">
													<table class="table table-bordered table-hover" border="1"
														id="productTable">
														<thead class="table-light">
															<tr>
																<th>Choose</th>
																<th>ProductID</th>
																<th>Product Image</th>
																<th>ProductName</th>
																<th>Selling Price</th>
																<th>Unit</th>
															</tr>
														</thead>
														<tbody id="productList">
															<c:forEach items="${products }" var="product">
																<tr>
																	<td><input type="checkbox"
																		name="selectedProductIds" value="${product.productId}" /></td>
																	<td>${product.productId }</td>
																	<td><div class="media-box">
																			<img
																				src="<c:url value='assets/product-images/${product.image }" class="media-avatar'/>"
																				alt="Bootstrap Gallery">
																		</div></td>
																	<td>${product.productName }</td>
																	<td><fmt:formatNumber value="${product.price }"
																			pattern="#,###.## VND;VND -#,###.##" type="currency"
																			currencySymbol="VND" /></td>
																	<td>${product.unit }</td>
																</tr>
															</c:forEach>
															<!-- Dữ liệu mẫu -->
															<%-- <tr>
															<td><input type="checkbox" /></td>
															<td>SP001</td>
															<td><div class="media-box">
																	<img
																		src="<c:url value='assets/admin/assets/images/user2.png" class="media-avatar'/>"
																		alt="Bootstrap Gallery">
																</div></td>
															<td>Sản phẩm A</td>
															<td><fmt:formatNumber value="85000"
																	pattern="#,###.## VND;VND -#,###.##" type="currency"
																	currencySymbol="VND" /></td>
															<td>Cái</td>
														</tr> --%>
															<!-- Sẽ được fill bằng JavaScript -->
														</tbody>
													</table>
													<%-- <form:form action="importBillManagement/getTempProducts.htm"
													method="POST" id="formGetTempProducts"> --%>
													<div class="row mt-3">
														<div class="col-md-12 text-end">
															<button id="btnGetSelectedProduct"
																class="btn btn-primary">GET</button>
														</div>
													</div>
													<%-- </form:form> --%>
												</form:form>
											</div>

											<!-- Danh sách sản phẩm đã chọn -->
											<div class="selected-products">
												<h4>Product Chosen</h4>
												<!-- thêm ở đây -->
												<form id="importForm"
													action="admin/importBillManagement/updateQuantityProduct.htm"
													method="POST">
													<table class="table table-bordered" border="1"
														id="selectedTable">
														<thead class="table-light">
															<tr>
																<th>Product Id</th>
																<th>Product Image</th>
																<th>Product Name</th>
																<th>Unit</th>
																<th>Import Price</th>
																<th>Quantity</th>
																<!-- <th>ToTal</th> -->
																<th>Delete</th>
															</tr>
														</thead>
														<tbody id="selectedProducts">
															<%-- <c:forEach items="${selectedProductList }"
																var="selectedProduct">
																<tr>
																	<td><input type="hidden" name="productId"
																		value="${selectedProduct.productId}" />${selectedProduct.productId }</td>
																	<td><div class="media-box">
																			<img
																				src="<c:url value='assets/admin/assets/images/user2.png" class="media-avatar'/>"
																				alt="Bootstrap Gallery">
																		</div></td>
																	<td>${selectedProduct.productName }</td>
																	<td>${selectedProduct.unit }</td>
																	<td><fmt:formatNumber
																			value="${selectedProduct.price }"
																			pattern="#,###.## VND;VND -#,###.##" type="currency"
																			currencySymbol="VND" /></td>
																	<td class="product-quantity" data-title="Import price">
																		<input type="text" name="importPrice" />
																	</td>
																	<td class="product-quantity" data-title="Quantity">
																		<input type="number" min="1" value="1" name="quantity" />
																	</td>
																	<td><fmt:formatNumber value="85000"
																			pattern="#,###.## VND;VND -#,###.##" type="currency"
																			currencySymbol="VND" /></td>
																	<td>
																		<!-- <button type="button">Delete</button> --> <a
																		href="importBillManagement/getSelectedProductsAfterDelete.htm?productId=${selectedProduct.productId}"
																		class="btn btn-danger">Delete</a>
																	</td>
																</tr>
															</c:forEach> --%>
															<c:forEach items="${importBillDetailList }"
																var="importBillDetail">
																<tr>
																	<td><input type="hidden" name="productId"
																		value="${importBillDetail.product.productId}" />${importBillDetail.product.productId }</td>
																	<td><div class="media-box">
																			<img
																				src="<c:url value='assets/product-images/${importBillDetail.product.image }" class="media-avatar'/>"
																				alt="Bootstrap Gallery">
																		</div></td>
																	<td>${importBillDetail.product.productName }</td>
																	<td>${importBillDetail.product.unit }</td>
																	<td class="product-quantity" data-title="Import price">
																		<input type="number" name="importPrice"
																		value="${importBillDetail.price }" />
																	</td>
																	<td class="product-quantity" data-title="Quantity">
																		<input type="number" min="1"
																		value="${importBillDetail.quantity }" name="quantity" />
																	</td>
																	<td>
																		<!-- <button type="button">Delete</button> --> <a
																		href="admin/importBillManagement/getSelectedProductsAfterDelete.htm?productId=${importBillDetail.product.productId}"
																		class="btn btn-danger">Delete</a>
																	</td>
																</tr>
															</c:forEach>

															<%-- <tr>
																<td>SP002</td>
																<td>
																	<div class="media-box">
																		<img
																			src="<c:url value='assets/admin/assets/images/user2.png" class="media-avatar'/>"
																			alt="Bootstrap Gallery">
																	</div>
																</td>
																<td>Sản phẩm B</td>
																<td>Cái</td>
																<td><fmt:formatNumber value="85000"
																		pattern="#,###.## VND;VND -#,###.##" type="currency"
																		currencySymbol="VND" /></td>
																<td class="product-quantity" data-title="Quantity">
																	<input type="number" min="1" value="1" />
																</td>
																<td><fmt:formatNumber value="85000"
																		pattern="#,###.## VND;VND -#,###.##" type="currency"
																		currencySymbol="VND" /></td>
																<td><button type="button">Delete</button></td>
															</tr> --%>
														</tbody>
														<tfoot>
															<tr>
																<td colspan="5" class="text-end"><strong>Total
																		import:</strong></td>
																<td colspan="2" class="text-danger fw-bold fs-5"><fmt:formatNumber
																		value="${totalImportPrice }"
																		pattern="#,###.## VND;VND -#,###.##" type="currency"
																		currencySymbol="VND" /></td>
															</tr>
														</tfoot>
													</table>
													<h5 class="text-danger small">${errors}</h5>

													<div class="row mt-3">
														<div class="col-md-12 text-end">
															<!-- <button type="button" class="btn btn-secondary">Cancel</button> -->
															<button type="submit" class="btn btn btn-warning">Cập
																nhật số lượng</button>
															<!-- <button type="submit" class="btn btn-success">Confirm
																Import</button> -->
														</div>
													</div>
												</form>
												<!-- thêm mới -->
												<form:form id="successImportForm"
													action="admin/importBillManagement/successImportBill.htm">
													<button type="submit" class="btn btn-success">CONFIRM IMPORT</button>
												</form:form>

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
	<script type="text/javascript">
		
	</script>

</body>
</html>