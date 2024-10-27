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
									<div class="card-title"><h2 class="mb-4">Create Import Bill</h2></div>
								</div>
								<div class="card-body">

									<div class="row gx-3">
										<div class="container mt-4">
											

											<!-- Chọn nhà cung cấp -->
											<div class="row">
												<div class="col-md-6">
													<div class="form-group">
														<label for="supplier" class="form-label">Chosse Provider</label> <select class="form-select" id="supplier"
															onchange="loadProducts()">
															<option value="">Provider1</option>
															<option value="">Provider2</option>
														<%-- 	<c:forEach items="${suppliers}" var="supplier">
																<option value="${supplier.id}">${supplier.name}</option>
															</c:forEach> --%>
														</select>
													</div>
												</div>
												<div class="col-md-6">
													<label for="supplier" class="form-label">Staff Name</label>
													<div class="form-group">
														<input value="Bui Huu Tri" readOnly disabled="disabled" />
													</div>

												</div>
											</div>

											<!-- Danh sách sản phẩm của nhà cung cấp -->
											<div class="table-container">
												<h4>Product List From Provider</h4>

												<table class="table table-bordered table-hover" border="1"
													id="productTable">
													<thead class="table-light">
														<tr>
															<th>Choose</th>
															<th>ProductID</th>
															<th>ProductName</th>
															<th>Import Price</th>
															<th>Unit</th>
														</tr>
													</thead>
													<tbody id="productList">
														<!-- Dữ liệu mẫu -->
														<tr>
															<td><input type="checkbox" /></td>
															<td>SP001</td>
															<td>Sản phẩm A</td>
															<td>100,000</td>
															<td>Cái</td>
														</tr>
														<tr>
															<td><input type="checkbox" /></td>
															<td>SP002</td>
															<td>Sản phẩm B</td>
															<td>150,000</td>
															<td>Cái</td>
														</tr>
														<!-- Sẽ được fill bằng JavaScript -->
													</tbody>
												</table>
												<div class="row mt-3">
													<div class="col-md-12 text-end">
														<button class="btn btn-primary">Get</button>
													</div>
												</div>
											</div>

											<!-- Danh sách sản phẩm đã chọn -->
											<div class="selected-products">
												<h4>Product Chosen</h4>
												<form id="importForm" action="/admin/import/save"
													method="POST">
													<table class="table table-bordered" border="1"
														id="selectedTable">
														<thead class="table-light">
															<tr>
																<th>Product Id</th>
																<th>Product Name</th>
																<th>Unit</th>
																<th>Import Price</th>
																<th>Quantity</th>
																<th>ToTal</th>
																<th>Delete</th>
															</tr>
														</thead>
														<tbody id="selectedProducts">
															<!-- Dữ liệu mẫu -->
															<tr>
																<td>SP001</td>
																<td>Sản phẩm A</td>
																<td>Cái</td>
																<td>100,000</td>
																<td class="product-quantity" data-title="Quantity">
																	<input type="number" min="1" value="1" />
																</td>
																<td><span class="amount">100,000</span> VNĐ</td>
																<td><button type="button">Delete</button></td>
															</tr>
															<tr>
																<td>SP002</td>
																<td>Sản phẩm B</td>
																<td>Cái</td>
																<td>150,000</td>
																<td class="product-quantity" data-title="Quantity">
																	<input type="number" min="1" value="1" />
																</td>
																<td><span class="amount">150,000</span> VNĐ</td>
																<td><button type="button">Delete</button></td>
															</tr>
															<!-- Thêm các sản phẩm khác từ cơ sở dữ liệu ở đây -->
															<!-- Sẽ được fill bằng JavaScript -->
														</tbody>
														<tfoot>
															<tr>
																<td colspan="5" class="text-end"><strong>Total Import:</strong></td>
																<td colspan="2"><span id="totalAmount">0</span> VNĐ</td>
															</tr>
														</tfoot>
													</table>

													<div class="row mt-3">
														<div class="col-md-12 text-end">
															<button type="button" class="btn btn-secondary">Cancel</button>
															<button type="submit" class="btn btn-primary">Confirm Import</button>
														</div>
													</div>
												</form>
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