<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%@include file="/WEB-INF/views/include/customer/header.jsp"%>
<head>
<title>Payment Receipt</title>
</head>
<style>
.receipt-container {
	width: 80%;
	margin: 0 auto;
	padding: 20px;
	border: 1px solid #ddd;
	background-color: #f9f9f9;
}

.receipt-header {
	text-align: center;
	margin-bottom: 20px;
}

.receipt-details {
	margin-bottom: 20px;
}

.receipt-details table {
	width: 100%;
	border-collapse: collapse;
}

.receipt-details th, .receipt-details td {
	padding: 10px;
	text-align: left;
	border-bottom: 1px solid #ddd;
}

.receipt-details th {
	background-color: #f2f2f2;
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
	<div class="page-contain login-page">

		<!-- Main content -->
		<div id="main-content" class="main-content">
			<div class="container">

				<div class="row">
					<div class="receipt-container">
						<div class="receipt-header">
							<h1>Hóa đơn thanh toán</h1>
						</div>

						<div class="receipt-details">
							<h2>Thông tin thanh toán</h2>
							<table>
								<tr>
									<th>Người mua</th>
									<td>${payerInfo.firstName}${payerInfo.lastName}</td>
								</tr>
								<tr>
									<th>Thành tiền</th>
									<td>${transaction.amount.details.subtotal}
										${transaction.amount.currency}</td>
								</tr>
								<tr>
									<th>Địa chỉ nhận</th>
									<td>${transaction.amount.details.shipping}
										${transaction.amount.currency}</td>
								</tr>
								<tr>
									<th>Thuế</th>
									<td>${transaction.amount.details.tax}
										${transaction.amount.currency}</td>
								</tr>
								<tr>
									<th>Tổng cộng</th>
									<td>${transaction.amount.total}${transaction.amount.currency}</td>
								</tr>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>