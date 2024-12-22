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

.btn-status {
	min-width: 160px;
	text-align: left;
	position: relative;
	padding: 8px 15px;
}

.dropdown-menu {
	min-width: 160px;
	padding: 5px 0;
}

.dropdown-item {
	padding: 8px 15px;
	transition: all 0.3s ease;
	cursor: pointer;
}

.dropdown-item:hover {
	background-color: #f8f9fa;
	padding-left: 20px;
}

.status-pending {
	color: #ffc107;
}

.status-confirmed {
	color: #17a2b8;
}

.status-shipping {
	color: #fd7e14;
}

.status-completed {
	color: #28a745;
}
/* Custom select styling */
.btn-group {
	position: relative;
	display: inline-block;
}

.btn-status {
	min-width: 200px;
	padding: 10px 15px;
	background: #fff;
	border: 1px solid #dee2e6;
	border-radius: 4px;
	text-align: left;
	font-size: 14px;
	cursor: pointer;
	transition: all 0.2s ease;
}

.btn-status:hover {
	background-color: #f8f9fa;
	border-color: #c1c9d0;
}

.btn-status:focus {
	box-shadow: 0 0 0 0.2rem rgba(0, 123, 255, .25);
	outline: none;
}

/* Dropdown arrow */
.dropdown-toggle::after {
	position: absolute;
	right: 12px;
	top: 50%;
	transform: translateY(-50%);
}

/* Dropdown menu */
select.btn-status {
	appearance: none;
	-webkit-appearance: none;
	-moz-appearance: none;
	background-image:
		url("data:image/svg+xml;charset=UTF-8,%3csvg xmlns='http://www.w3.org/2000/svg' viewBox='0 0 24 24' fill='none' stroke='currentColor' stroke-width='2' stroke-linecap='round' stroke-linejoin='round'%3e%3cpolyline points='6 9 12 15 18 9'%3e%3c/polyline%3e%3c/svg%3e");
	background-repeat: no-repeat;
	background-position: right 12px center;
	background-size: 16px;
	padding-right: 40px;
}

/* Option styling */
.btn-status option {
	padding: 10px 15px;
	font-size: 14px;
	line-height: 1.5;
	color: #495057;
	background-color: #fff;
}

.btn-status option:hover {
	background-color: #007bff;
	color: #fff;
}

/* Status pending specific style */
.status-pending {
	color: #6c757d;
}

.status-pending:hover {
	background-color: #007bff;
	color: #fff;
}

/* Status processing specific style */
.status-processing {
	color: #ffc107;
}

.status-processing:hover {
	background-color: #007bff;
	color: #fff;
}

/* Status completed specific style */
.status-completed {
	color: #28a745;
}

.status-completed:hover {
	background-color: #007bff;
	color: #fff;
}

/* Status cancelled specific style */
.status-cancelled {
	color: #dc3545;
}

.status-cancelled:hover {
	background-color: #007bff;
	color: #fff;
}

/* Icon styling */
.status-pending i {
	color: #ffc107;
	margin-right: 8px;
}

.status-processing i {
	color: #ffc107;
	margin-right: 8px;
}

.status-completed i {
	color: #28a745;
	margin-right: 8px;
}

.status-cancelled i {
	color: #dc3545;
	margin-right: 8px;
}

/* Mobile responsiveness */
@media ( max-width : 576px) {
	.btn-status {
		min-width: 100%;
	}
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

							<!-- Card start -->
							<div class="card">
								<div class="card-header">
									<div class="card-title">Order Management</div>
								</div>
								<div class="card-header">
									<h5
										class="${status == 200 ? 'text-success' : 'text-danger'} small">${notification }</h5>
								</div>

								<div class="card-body">

									<div class="table-responsive">
										<table id="basicExample" class="table custom-table">
											<thead>
												<tr>
													<th>ID</th>
													<th>CustomerName</th>
													<!-- <th>StaffName</th> -->

													<th>Total Quantity</th>
													<th>Total Price</th>
													<th>Order At</th>
													<th>Order Status</th>
													<th>Action</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach var="orderBill" items="${orderBills }">
													<tr>
														<td>${orderBill.orderBillId }</td>
														<td>${orderBill.account.fullName }</td>
														<td>${orderBill.totalQuantity }</td>
														<td><fmt:formatNumber value="${orderBill.totalPrice}"
																pattern="#,##0" /></td>
														<td>${orderBill.orderTime }</td>

														<td>
															<!-- Form để cập nhật trạng thái của đơn hàng -->
															<form
																action="admin/orderManagement/order/updateOrderStatus.htm"
																method="post">
																<input type="hidden" name="orderBillId"
																	value="${orderBill.orderBillId}" /> <select
																	name="statusOrder" onchange="this.form.submit()">
																	<option value="1"
																		${orderBill.statusOrder == 1 ? 'selected' : ''}>Chờ
																		xác nhận</option>
																	<option value="2"
																		${orderBill.statusOrder == 2 ? 'selected' : ''}>Đã
																		xác nhận</option>
																	<option value="3"
																		${orderBill.statusOrder == 3 ? 'selected' : ''}>Chờ
																		giao hàng</option>
																	<option value="4"
																		${orderBill.statusOrder == 4 ? 'selected' : ''}>Hoàn
																		thành</option>
																	<option value="5"
																		${orderBill.statusOrder == 5 ? 'selected' : ''}>Hủy</option>

																</select>
																<!-- <button type="submit">Cập nhật</button> -->
															</form>
														</td>


														<td>
															<div class="actions">

																<a
																	href="admin/orderManagement/order.htm?action=view&id=${orderBill.orderBillId}">
																	<i class="bi bi-eye"></i>
																</a> <a
																	href="admin/orderManagement/deleteOrderBillUnConfirm.htm?orderBillId=${orderBill.orderBillId}"
																	class="deleteRow"> <i class="bi bi-trash text-red"></i>
																</a>
															</div>
														</td>


													</tr>
												</c:forEach>


											</tbody>
										</table>
									</div>
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
					<span>© Bootstrap Gallery 2023</span>
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
	<!-- Chuyển trạng thái order -->
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.2/js/bootstrap.bundle.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/js/all.min.js"></script>
	<!-- <script>
        // Khởi tạo dropdown
        const dropdown = new bootstrap.Dropdown(document.querySelector('.btn-status'));
        const button = document.querySelector('.btn-status');

        // Thêm event listener cho các items
        document.querySelectorAll('.dropdown-item').forEach(item => {
            item.addEventListener('click', function(e) {
                e.preventDefault();
                
                // Cập nhật text và style của button
                button.textContent = this.textContent;
                button.className = 'btn btn-light btn-status dropdown-toggle border';
                
                // Thêm màu tương ứng cho button
                if (this.classList.contains('status-pending')) button.classList.add('status-pending');
                if (this.classList.contains('status-confirmed')) button.classList.add('status-confirmed');
                if (this.classList.contains('status-shipping')) button.classList.add('status-shipping');
                if (this.classList.contains('status-completed')) button.classList.add('status-completed');
                
                // Đóng dropdown
                dropdown.hide();
            });
        });
    </script> -->
</body>
</html>