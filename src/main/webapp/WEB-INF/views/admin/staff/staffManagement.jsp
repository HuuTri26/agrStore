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
}

button {
	border: none;
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
									<div class="card-title">Staff Management</div>
									<a href="admin/staffManagement/addStaff.htm">
										<button type="button" class="btn btn-info add-category">
											<i class="bi bi-plus-square"></i> Add
										</button>
									</a>
								</div>
								<div class="card-body">

									<div class="table-responsive">
										<table id="basicExample" class="table custom-table">
											<thead>
												<tr>
													<th>Id</th>
													<th>Image</th>
													<th>FullName</th>
													<th>Gmail</th>
													<th>Phone Numbber</th>
													<th>Address</th>
													<th>Status</th>
													<th>Create At</th>
													<th>Action</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach var="staff" items="${staffs }">
													<tr>
														<td>${staff.accountId }</td>
														<td><div class="media-box">
																<img src="/assets/user-images/${staff.avatar}"
																	class="media-avatar rounded-circle img-fluid"
																	alt="User Avatar"
																	style="width: 45px; height: 45px; object-fit: cover;" />
																<!-- 	<div class="media-box-body">
																<div class="text-truncate">Karan Kumar</div>
																<p>ID: #Max00987</p> -->
															</div></td>
														<td>${staff.fullName }</td>
														<td>${staff.gmail }</td>
														<td>${staff.phoneNumber }</td>
														<td>${staff.address.streetName }</td>

														<td><span class="badge min-70"
															style="background-color: ${staff.status ? 'green' : 'red'}; color: white;">
																${staff.status ? 'Active' : 'Disable'} </span></td>

														<td>${staff.createAt }</td>
														<td>
															<!-- <div class="actions">
																<div class="dropdown">
																	<a href="#" class="viewRow" data-bs-toggle="modal"
																		data-bs-target="#viewRow"> <i
																		class="bi bi-list text-green"></i>
																	</a>
																	<div class="dropdown-content">
																		<a href="categoryDetail.htm"><i class="bi bi-eye"></i>
																		<a href="customerManagement/customerDetail.htm"><i class="bi bi-eye"></i>
																		</a> <a href="categoryAdd.htm"><i
																			class="bi bi-plus-circle"></i> </a> <a
																			href="categoryEdit.htm"><i class="bi bi-pencil"></i>
																		</a> <a href="categoryActive.htm"><i
																			class="bi bi-check-circle active-icon"></i> </a>
																	</div>
																</div>
																<a href="categoryDelete.htm" class="deleteRow"> <i
																	class="bi bi-trash text-red"></i>
																</a>
															</div> -->
															<div class="actions">
																<a
																	href="admin/staffManagement/staff.htm?action=view&id=${staff.accountId}">
																	<i class="bi bi-eye text-green"></i>
																</a> 
																<a
																	href="admin/staffManagement/staff.htm?action=edit&id=${staff.accountId}">
																	<i class="bi bi-pencil text-green"></i>
																</a>
																<a
																	href="admin/staffManagement/disableStaff.htm?id=${staff.accountId }"
																	class="deleteRow"> <i
																	class="bi ${staff.status ? 'bi-x-circle  inactive-icon' : 'bi-check-circle active-icon'}"></i>
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
</body>
</html>