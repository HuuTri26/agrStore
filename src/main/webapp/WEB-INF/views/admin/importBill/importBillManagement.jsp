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

.add-category {
	float: right;
	font-size: 10px;
	margin-right: 4px;
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
									<div class="card-title">Import Bill Management</div>
								</div>
								<a href="importBillManagement/importBill.htm?action=create">
									<button type="button" class="btn btn-info add-category">
										<i class="bi bi-plus-square"></i> Create Import Bill
									</button>
								</a>
								<div class="card-body">

									<div class="table-responsive">
										<table id="basicExample" class="table custom-table">
											<thead>
												<tr>
													<th>Id</th>
													<th>Staff</th>
													<th>Total Quantity</th>
													<th>Total Price</th>
													<th>Import At</th>
													<th>Action</th>
												</tr>
											</thead>
											<tbody>
												<tr>
													<td>1</td>
													<td>
														<div class="media-box">
															<img
																src="<c:url value='assets/admin/assets/images/user2.png" class="media-avatar'/>"
																alt="Bootstrap Gallery">
															<div class="media-box-body">
																<a href="#" class="text-truncate">Huu tri</a>
																
															</div>
														</div>
													</td>

													<td>10</td>
													<td>2000</td>
													<td>2011/12/06</td>

													<td>
														<div class="actions">
															<div class="dropdown">
																<a href="#" class="viewRow" data-bs-toggle="modal"
																	data-bs-target="#viewRow"> <i
																	class="bi bi-list text-green"></i>
																</a>
																<div class="dropdown-content">
																	<a
																		href="importBillManagement/importBill.htm?action=view&id=${importBill.id}">
																		<i class="bi bi-eye"></i>
																	</a> <a
																		href="importBillManagement/importBill.htm?action=edit&id=${importBill.id}">
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
													<td>	<div class="media-box">
															<img
																src="<c:url value='assets/admin/assets/images/user2.png" class="media-avatar'/>"
																alt="Bootstrap Gallery">
															<div class="media-box-body">
																<a href="#" class="text-truncate">Huu tri</a>
															
															</div>
														</div></td>

													<td>10</td>
													<td>2000</td>
													<td>2011/12/06</td>

													<td>
														<div class="actions">
															<div class="dropdown">
																<a href="#" class="viewRow" data-bs-toggle="modal"
																	data-bs-target="#viewRow"> <i
																	class="bi bi-list text-green"></i>
																</a>
																<div class="dropdown-content">
																	<a
																		href="importBillManagement/importBill.htm?action=view&id=${importBill.id}">
																		<i class="bi bi-eye"></i>
																	</a> <a
																		href="importBillManagement/importBill.htm?action=edit&id=${importBill.id}">
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
</body>
</html>