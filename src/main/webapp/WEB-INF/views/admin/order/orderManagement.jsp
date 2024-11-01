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
								<div class="card-body">

									<div class="table-responsive">
										<table id="basicExample" class="table custom-table">
											<thead>
												<tr>
													<th>ID</th>
													<th>CustomerName</th>
													<th>StaffName</th>
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
														<td>${orderBill.employeeId }</td>
														<td>${orderBill.totalQuantity }</td>
														<td>${orderBill.totalPrice }</td>
														<td>${orderBill.orderTime }</td>
														<td><c:choose>
																<c:when test="${orderBill.statusOrder == 1}">
            Chờ xác nhận
        </c:when>
																<c:when test="${orderBill.statusOrder == 2}">
            Đã xác nhận
        </c:when>
																<c:when test="${orderBill.statusOrder == 3}">
            Chờ giao hàng
        </c:when>
																<c:when test="${orderBill.statusOrder == 4}">
            Hoàn thành
        </c:when>
																<c:otherwise>
            Trạng thái không xác định
        </c:otherwise>
															</c:choose></td>


														<td>
															<div class="actions">
																<div class="dropdown">
																	<a href="#" class="viewRow" data-bs-toggle="modal"
																		data-bs-target="#viewRow"> <i
																		class="bi bi-list text-green"></i>
																	</a>
																	<div class="dropdown-content">
																		<a
																			href="orderManagement/order.htm?action=view&id=${orderBill.orderBillId}">
																			<i class="bi bi-eye"></i>
																		</a> <a
																			href="orderManagement/order.htm?action=edit&id=${orderBill.orderBillId}">
																			<i class="bi bi-pencil"></i>
																		</a> <a href="categoryActive.htm"><i
																			class="bi bi-check-circle active-icon"></i> </a>
																	</div>
																</div>
																<a href="categoryDelete.htm" class="deleteRow"> <i
																	class="bi bi-trash text-red"></i>
																</a>
															</div>
														</td>


													</tr>
												</c:forEach>
												<%-- <tr>
													<td>1</td>
													<td>Trí</td>
													<td>Toan</td>
													<td>5</td>
													<td>2000</td>
													<td>2011/12/06</td>
													<td>

														<div class="btn-group">
															<button type="button"
																class="btn btn-light btn-status dropdown-toggle border"
																data-bs-toggle="dropdown" aria-expanded="false">
																Trạng thái</button>
															<ul class="dropdown-menu">
																<li><a class="dropdown-item status-pending"><i
																		class="fas fa-clock me-2"></i>Chờ xác nhận</a></li>
																<li><a class="dropdown-item status-confirmed"><i
																		class="fas fa-check me-2"></i>Đã xác nhận</a></li>
																<li><a class="dropdown-item status-shipping"><i
																		class="fas fa-truck me-2"></i>Chờ giao hàng</a></li>
																<li><a class="dropdown-item status-completed"><i
																		class="fas fa-check-circle me-2"></i>Hoàn thành</a></li>
															</ul>
														</div>
													</td>


													<td>
														<div class="actions">
															<div class="dropdown">
																<a href="#" class="viewRow" data-bs-toggle="modal"
																	data-bs-target="#viewRow"> <i
																	class="bi bi-list text-green"></i>
																</a>
																<div class="dropdown-content">
																	<a
																		href="orderManagement/order.htm?action=view&id=${order.id}">
																		<i class="bi bi-eye"></i>
																	</a> <a
																		href="orderManagement/order.htm?action=edit&id=${order.id}">
																		<i class="bi bi-pencil"></i>
																	</a> <a href="categoryActive.htm"><i
																		class="bi bi-check-circle active-icon"></i> </a>
																</div>
															</div>
															<a href="categoryDelete.htm" class="deleteRow"> <i
																class="bi bi-trash text-red"></i>
															</a>
														</div>
													</td>


												</tr> --%>
												<%-- <tr>
													<td>1</td>
													<td>Trí</td>
													<td>Toan</td>
													<td>5</td>
													<td>2000</td>
													<td>2011/12/06</td>
													<td>

														<div class="btn-group">
															<button type="button"
																class="btn btn-light btn-status dropdown-toggle border"
																data-bs-toggle="dropdown" aria-expanded="false">
																Trạng thái</button>
															<ul class="dropdown-menu">
																<li><a class="dropdown-item status-pending"><i
																		class="fas fa-clock me-2"></i>Chờ xác nhận</a></li>
																<li><a class="dropdown-item status-confirmed"><i
																		class="fas fa-check me-2"></i>Đã xác nhận</a></li>
																<li><a class="dropdown-item status-shipping"><i
																		class="fas fa-truck me-2"></i>Chờ giao hàng</a></li>
																<li><a class="dropdown-item status-completed"><i
																		class="fas fa-check-circle me-2"></i>Hoàn thành</a></li>
															</ul>
														</div>
													</td>


													<td>
														<div class="actions">
															<div class="dropdown">
																<a href="#" class="viewRow" data-bs-toggle="modal"
																	data-bs-target="#viewRow"> <i
																	class="bi bi-list text-green"></i>
																</a>
																<div class="dropdown-content">
																	<a
																		href="orderManagement/order.htm?action=view&id=${order.id}">
																		<i class="bi bi-eye"></i>
																	</a> <a
																		href="orderManagement/order.htm?action=edit&id=${order.id}">
																		<i class="bi bi-pencil"></i>
																	</a> <a href="categoryActive.htm"><i
																		class="bi bi-check-circle active-icon"></i> </a>
																</div>
															</div>
															<a href="categoryDelete.htm" class="deleteRow"> <i
																class="bi bi-trash text-red"></i>
															</a>
														</div>
													</td>


												</tr>
												<tr>
													<td>1</td>
													<td>Trí</td>
													<td>Toan</td>
													<td>5</td>
													<td>2000</td>
													<td>2011/12/06</td>
													<td>

														<div class="btn-group">
															<button type="button"
																class="btn btn-light btn-status dropdown-toggle border"
																data-bs-toggle="dropdown" aria-expanded="false">
																Trạng thái</button>
															<ul class="dropdown-menu">
																<li><a class="dropdown-item status-pending"><i
																		class="fas fa-clock me-2"></i>Chờ xác nhận</a></li>
																<li><a class="dropdown-item status-confirmed"><i
																		class="fas fa-check me-2"></i>Đã xác nhận</a></li>
																<li><a class="dropdown-item status-shipping"><i
																		class="fas fa-truck me-2"></i>Chờ giao hàng</a></li>
																<li><a class="dropdown-item status-completed"><i
																		class="fas fa-check-circle me-2"></i>Hoàn thành</a></li>
															</ul>
														</div>
													</td>


													<td>
														<div class="actions">
															<div class="dropdown">
																<a href="#" class="viewRow" data-bs-toggle="modal"
																	data-bs-target="#viewRow"> <i
																	class="bi bi-list text-green"></i>
																</a>
																<div class="dropdown-content">
																	<a
																		href="orderManagement/order.htm?action=view&id=${order.id}">
																		<i class="bi bi-eye"></i>
																	</a> <a
																		href="orderManagement/order.htm?action=edit&id=${order.id}">
																		<i class="bi bi-pencil"></i>
																	</a> <a href="categoryActive.htm"><i
																		class="bi bi-check-circle active-icon"></i> </a>
																</div>
															</div>
															<a href="categoryDelete.htm" class="deleteRow"> <i
																class="bi bi-trash text-red"></i>
															</a>
														</div>
													</td>


												</tr> --%>
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
	<script>
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
    </script>
</body>
</html>