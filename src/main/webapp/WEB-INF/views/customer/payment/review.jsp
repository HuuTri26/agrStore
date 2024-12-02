<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%@include file="/WEB-INF/views/include/customer/header.jsp"%>
<head>
<meta charset="UTF-8">
<title>Review Payment</title>
<style>
table {
	border-collapse: collapse;
	width: 80%;
	margin: 0 auto;
}

th, td {
	border: 1px solid black;
	padding: 8px;
	text-align: center;
}

th {
	background-color: #f2f2f2;
}

.shipping-info {
	width: 80%;
	margin: 20px auto;
	border: 1px solid #ddd;
	padding: 15px;
}
</style>
</head>
<body class="biolife-body">
	<!-- Preloader -->
	<div id="biof-loading">
		<div class="biof-loading-center">
			<div class="biof-loading-center-absolute">
				<div class="dot dot-one"></div>
				<div class="dot dot-two"></div>
				<div class="dot dot-three"></div>
			</div>
		</div>
	</div>
	<!-- HEADER -->
	<header id="header" class="header-area style-01 layout-03">
		<div class="header-top bg-main hidden-xs">
			<%@include file="/WEB-INF/views/include/customer/bodyHeader.jsp"%>
		</div>
	</header>
	<!--Hero Section-->
	<div class="hero-section hero-background">
		<h1 class="page-title">argStore</h1>
	</div>

	<!--Navigation section-->
	<div class="container">
		<nav class="biolife-nav">
			<ul>
				<li class="nav-item"><a href="customer/customerCheckout.htm"
					class="permal-link">Chọn phương thức thanh toán</a></li>
				<li class="nav-item"><span class="current-page">Xem và
						xác nhận</span></li>
			</ul>
		</nav>
	</div>

	<!-- Review Session -->
	<div align="center">
		<h1>Xin vui lòng xem lại đơn hàng của bạn trước khi xác nhận
			thanh toán</h1>

		<div class="shipping-info">
			<h2>Thông tin người thanh toán</h2>
			<p>
				<strong>Tên:</strong> ${payerInfo.firstName} ${payerInfo.lastName}
			</p>
			<p>
				<strong>Email:</strong> ${payerInfo.email}
			</p>
		</div>

		<div class="shipping-info">
			<h2>Địa chỉ giao hàng</h2>
			<p>
				<strong>Địa chỉ:</strong> ${shippingAddress.line1}
			</p>
			<p>
				<strong>Thành phố:</strong> ${shippingAddress.city}
			</p>
			<p>
				<strong>Quốc gia:</strong> ${shippingAddress.countryCode}
			</p>
			<p>
				<strong>Mã bưu điện:</strong> ${shippingAddress.postalCode}
			</p>
		</div>

		<form action="customer/execPayment.htm" method="post">
			<table>
				<tr>
					<th>Tên sản phẩm</th>
					<th>Giá (${transaction.amount.currency})</th>
					<th>Số lượng</th>
					<th>Thành tiền</th>
				</tr>
				<c:forEach var="item" items="${transaction.itemList.items}">
					<tr>
						<td>${item.name}</td>
						<td>${item.price}</td>
						<td>${item.quantity}</td>
						<td>${item.price * item.quantity}</td>

					</tr>

				</c:forEach>
				<tr>
					<td colspan="3"><strong>Tổng cộng:</strong></td>
					<td><strong>${transaction.amount.total}
							${transaction.amount.currency}</strong></td>
				</tr>
			</table>
			<input type="hidden" name="paymentId" value="${param.paymentId }" />
			<input type="hidden" name="PayerID" value="${param.PayerID }" /> <br>
			<input type="submit" value="Xác nhận và Thanh toán">
		</form>
	</div>

	<%@include file="/WEB-INF/views/include/customer/footer.jsp"%>
	<%@include file="/WEB-INF/views/include/customer/footerMobile.jsp"%>
	<%@include file="/WEB-INF/views/include/customer/js.jsp"%>
</body>
</html>