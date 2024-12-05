
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!doctype html>
<html lang="en">
<%@include file="/WEB-INF/views/include/admin/header.jsp"%>

<body>

	<!-- Loading wrapper start -->
	<div id="loading-wrapper">
		<div class="spinner">
			<div class="line1"></div>
			<div class="line2"></div>
			<div class="line3"></div>
			<div class="line4"></div>
			<div class="line5"></div>
			<div class="line6"></div>
		</div>
	</div>
	<!-- Loading wrapper end -->

	<!-- Page wrapper start -->
	<div class="page-wrapper">

		<!-- side bar -->
		<%@include file="/WEB-INF/views/include/admin/sideBar.jsp"%>

		<!-- *************
				************ Main container start *************
			************* -->
		<div class="main-container">

			<!-- Page header starts -->
			<%@include file="/WEB-INF/views/include/admin/bodyHeader.jsp"%>
			<!-- Page header ends -->

			<!-- Content wrapper scroll start -->
			<div class="content-wrapper-scroll">

				<!-- Content wrapper start -->
				<div class="content-wrapper">

					<!-- Row start -->
					<div class="row gx-3">
						<div class="col-xxl-3 col-sm-6 col-12">
							<div class="stats-tile">
								<div class="sale-icon shade-blue">
									<i class="bi bi-pie-chart"></i>
								</div>
								<div class="sale-details">
									<h4 class="text-blue">
										<fmt:formatNumber value="${totalCostInWeek}" pattern="#,###" />
									</h4>
									<p>Import cost</p>
								</div>
								<div class="sale-graph">
									<div id="sparklineLine1"></div>
								</div>
							</div>
						</div>
						<div class="col-xxl-3 col-sm-6 col-12">
							<div class="stats-tile">
								<div class="sale-icon shade-yellow">
									<i class="bi bi-emoji-smile"></i>
								</div>
								<div class="sale-details">
									<h4 class="text-yellow">
										<fmt:formatNumber value="${doanhThuTrongNgay}" pattern="#,###" />
									</h4>
									<p>Revenue</p>
								</div>
								<div class="sale-graph">
									<div id="sparklineLine2"></div>
								</div>
							</div>
						</div>
						<div class="col-xxl-3 col-sm-6 col-12">
							<div class="stats-tile">
								<div class="sale-icon shade-red">
									<i class="bi bi-box-seam"></i>
								</div>
								<div class="sale-details">
									<h4 class="text-red">${soDonHangTrongMotNgay }</h4>
									<p>Order Number</p>
								</div>
								<div class="sale-graph">
									<div id="sparklineLine3"></div>
								</div>
							</div>
						</div>
						<div class="col-xxl-3 col-sm-6 col-12">
							<div class="stats-tile">
								<div class="sale-icon shade-green">
									<i class="bi bi-handbag"></i>
								</div>
								<div class="sale-details">
									<h4 class="text-green">${soTaiKhoanKhachHang }</h4>
									<p>Customer</p>
								</div>
								<div class="sale-graph">
									<div id="sparklineLine4"></div>
								</div>
							</div>
						</div>
					</div>
					<!-- Row end -->

					<!-- Row start -->
					<!-- Row end -->

					<!-- Row start -->
					<!-- Row end -->

					<!-- Row start -->
					<%-- <div class="row gx-3">
						<div class="col-xxl-12">
							<div class="card">
								<div class="card-header">
									<div class="card-title">Top những khách hàng mua nhiều
										hàng nhất</div>
								</div>
								<div class="card-body">

									<div class="table-responsive">
										<table class="table v-middle">
											<thead>
												<tr>
													<th>Id</th>
													<th>CustomerName</th>
													<th>Tong so tien da mua</th>
												</tr>
											</thead>
											<tbody>
												<tr>
													<td>
														<div class="media-box">
															<img
																src="<c:url value='/assets/admin/assets/images/user3.png'/>"
																class="media-avatar" alt="Bootstrap Gallery">
															<div class="media-box-body">
																<div class="text-truncate">1</div>
															</div>
														</div>
													</td>
													<td>
														<div class="media-box">
																<img
																src="<c:url value='/assets/admin/assets/images/food/img3.jpg'/>"
																class="media-avatar" alt="Free Admin Themes">
															<div class="media-box-body">
																<div class="text-truncate">Trí</div>
															</div>
														</div>
													</td>
													<td><span class="text-green td-status"><i
															class="bi bi-check-circle"></i> 1800</span></td>
													<!-- <td><span class="badge shade-green min-90">Delivered</span>
													</td> -->
												</tr>

											</tbody>
										</table>
									</div>

								</div>
							</div>
						</div>
					</div> --%>
					<!-- Row end -->

					<!-- đơn hàng hôm nay -->
					<div class="card">
						<div class="card-header">
							<div class="card-title">Order today</div>
						</div>
						<div class="card-body">

							<div class="table-responsive">
								<table class="table custom-table">
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
										<c:forEach var="orderBill" items="${orderBillToday }">
											<tr>
												<td>${orderBill.orderBillId }</td>
												<td>${orderBill.account.fullName }</td>
												<%-- <td>${employeeNameMap[orderBill.employeeId]}</td> --%>
												<td>${orderBill.totalQuantity }</td>
												<td><fmt:formatNumber value="${orderBill.totalPrice}"
														pattern="#,##0" /></td>
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
															<!-- Không hiển thị gì nếu không thuộc các trạng thái trên -->
														</c:otherwise>
													</c:choose></td>


												<td>
													<div class="actions">

														<a
															href="admin/orderManagement/order.htm?action=view&id=${orderBill.orderBillId}">
															<i class="bi bi-eye"></i>
														</a>
														<!-- <a href="admin/categoryDelete.htm" class="deleteRow"> <i
																	class="bi bi-trash text-red"></i>
																</a> -->
													</div>
												</td>


											</tr>
										</c:forEach>

									</tbody>
								</table>
							</div>
						</div>
					</div>
					<!-- end -->
					<div class="row gx-3">
						<div class="col-xxl-12">
							<div class="card">
								<div class="card-header">
									<div class="card-title">Most purchased products</div>
								</div>
								<div class="card-body">

									<div class="table-responsive">
										<table class="table v-middle">
											<thead>
												<tr>
													<th>Id</th>
													<th>ProductName</th>
													<th>Sell price</th>
													<!-- th>Số lượng đã bán</th> -->
													<th>Quantity sold</th>

												</tr>
											</thead>
											<tbody>
												<c:forEach items="${favoriteProduct }" var="product"
													varStatus="status">
													<tr>
														<td>
															<div class="media-box">
																<img
																	src="<c:url value='/assets/product-images/${product.image }' />"
																	class="media-avatar" alt="Bootstrap Gallery">
																<div class="media-box-body">
																	<div class="text-truncate">${product.productId }</div>
																</div>
															</div>
														</td>
														<td>
															<div class="media-box">

																<div class="media-box-body">
																	<div class="text-truncate">${product.productName }</div>
																</div>
															</div>
														</td>
														<td><span class="td-status"> <fmt:formatNumber
																	value="${product.price }"
																	pattern="#,###.## VND;VND -#,###.##" type="currency"
																	currencySymbol="VND" />

														</span></td>
														<td><span class="text-green td-status">${soLuongDanBanCuaSanPhamYeuThich[status.index]}</span></td>

														<!-- <td><span class="badge shade-green min-90">Delivered</span>
													</td> -->
													</tr>
												</c:forEach>


											</tbody>
										</table>
									</div>

								</div>
							</div>
						</div>
					</div>

					<%-- <div class="col-lg-12">
						<div class="card">
							<div class="card-body">
								<h5 class="card-title">Oder Status</h5>

								<!-- Doughnut Chart -->
								<canvas id="doughnutChart" style="max-height: 365px;"></canvas>
								<script>
                      document.addEventListener("DOMContentLoaded", () => {
                        new Chart(document.querySelector('#doughnutChart'), {
                          type: 'doughnut',
                          data: {
                            labels: [
                              'Completed',
                              'Ongoing',
                              'Pending'
                            ],
                            datasets: [{
                              label: 'My First Dataset',
                              data: [300, 50, 100],
                              backgroundColor: [
                                'rgb(255, 99, 132)',
                                'rgb(54, 162, 235)',
                                'rgb(255, 205, 86)'
                              ],
                              hoverOffset: 4
                            }]
                          }
                        });
                      });
                    </script>
								<!-- End Doughnut CHart -->

							</div>
						</div>
					</div> --%>


					<!-- Row end -->

				</div>
				<!-- Content wrapper end -->

				<!-- App Footer start -->
				<div class="app-footer">
					<span>...</span>
				</div>
				<!-- App footer end -->

			</div>
			<!-- Content wrapper scroll end -->

		</div>
		<!-- *************
				************ Main container end *************
			************* -->

	</div>
	<!-- Page wrapper end -->

	<!-- *************
			************ Required JavaScript Files *************
		************* -->
	<!-- Required jQuery first, then Bootstrap Bundle JS -->
	<%@include file="/WEB-INF/views/include/admin/footer.jsp"%>

</body>

</html>