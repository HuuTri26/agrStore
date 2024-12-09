<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%@include file="/WEB-INF/views/include/customer/header.jsp"%>
<head>
<title>Payment Receipt</title>
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

.return-button {
	margin-top: 20px;
	text-align: center;
}

.return-button a, .return-button button {
	display: inline-block;
	padding: 10px 20px;
	font-size: 16px;
	color: white;
	background-color: #007bff;
	text-decoration: none;
	border-radius: 5px;
	border: none;
	cursor: pointer;
}

.return-button a:hover, .return-button button:hover {
	background-color: #0056b3;
}
</style>
</head>
<body class="biolife-body">
	<!-- Preloader -->

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
									<td>${payerInfo != null ? payerInfo.firstName : ''}
										${payerInfo != null ? payerInfo.lastName : ''}</td>
								</tr>
								<tr>
									<th>Thành tiền</th>
									<td><c:choose>
											<c:when test="${transaction.amount.details.subtotal != null}">${transaction.amount.details.subtotal} ${transaction.amount.currency}</c:when>
											<c:otherwise>Không có thông tin</c:otherwise>
										</c:choose></td>
								</tr>
								<tr>
									<th>Chi phí vận chuyển</th>
									<td>${transaction.amount.details.shipping != null ? transaction.amount.details.shipping : '0.00'}
										${transaction.amount.currency}</td>
								</tr>
								<tr>
									<th>Thuế</th>
									<td>${transaction.amount.details.tax != null ? transaction.amount.details.tax : '0.00'}
										${transaction.amount.currency}</td>
								</tr>
								<tr>
									<th>Tổng cộng</th>
									<td>${transaction.amount.total != null ? transaction.amount.total : '0.00'}
										${transaction.amount.currency}</td>
								</tr>

							</table>
							<div class="return-button">
								<a href="index.htm"
									class="btn btn-primary">Quay lại trang chủ</a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>