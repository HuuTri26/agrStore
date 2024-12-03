<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
<%@include file="/WEB-INF/views/include/admin/header.jsp"%>
<head>
<!-- Date Range CSS -->
<link rel="stylesheet"
	href="<c:url value='/assets/admin/assets/vendor/daterange/daterange.css'/>">

<!-- Uploader CSS -->
<link rel="stylesheet"
	href="<c:url value='/assets/admin/assets/vendor/dropzone/dropzone.min.css'/>" />
</head>
<body>
	<!-- Page wrapper start -->
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
						<div class="col-xl-12">
							<!-- Card start -->
							<div class="card">
								<div class="card-body">

									<!-- Row start -->
									<div class="row gx-3">
										<form:form action="admin/adminChangePassword.htm"
											method="post" modelAttribute="changePass">
											<div
												class="col-xxl-8 col-xl-12 col-lg-7 col-md-6 col-sm-12 col-12">
												<div class="row gx-3">

													<div class="col-xxl-4 col-sm-12 col-12">
														<!-- Form Field Start -->
														<div class="mb-3">
															<label for="" class="form-label">Mật khẩu cũ</label>
															<form:input path="password" type="password"
																class="form-control" id="currentPassword" />
															<form:errors path="password" />
															${wrongPass }
														</div>
													</div>
													<div class="col-xxl-4 col-sm-12 col-12">
														<!-- Form Field Start -->
														<div class="mb-3">
															<label for="" class="form-label">Mật khẩu mới</label> <input
																name="new-password" type="password" class="form-control"
																id="newPassword">
															${newPass }
														</div>
													</div>
													<div class="col-xxl-4 col-sm-12 col-12">
														<!-- Form Field Start -->
														<div class="mb-3">
															<label for="" class="form-label">Nhập lại mật
																khẩu mới </label> <input name="re-enter-new-password"
																type="password" class="form-control" id="renewPassword">
															${reNewPass }
														</div>
													</div>

												</div>
											</div>
											<div class="col-sm-12 col-12">
												<hr>
												<button class="btn btn-info" type="submit">ChangePassword</button>
											</div>
										</form:form>
									</div>

									<!-- Row end -->

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
					<span>..</span>
				</div>
				<!-- App footer end -->
			</div>
		</div>
	</div>
	<!-- Required jQuery first, then Bootstrap Bundle JS -->
	<%@include file="/WEB-INF/views/include/admin/footer.jsp"%>
</body>
</html>