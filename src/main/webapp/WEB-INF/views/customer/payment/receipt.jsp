<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
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
    </style>
</head>
<body>
    <div class="receipt-container">
        <div class="receipt-header">
            <h1>Hóa đơn thanh toán</h1>
        </div>

        <div class="receipt-details">
            <h2>Thông tin thanh toán</h2>
            <table>
                <tr>
                    <th>Người mua</th>
                    <td>${payerInfo.firstName} ${payerInfo.lastName}</td>
                </tr>
                <tr>
                    <th>Thành tiền</th>
                    <td>${transaction.amount.details.subtotal} ${transaction.amount.currency}</td>
                </tr>
                <tr>
                    <th>Địa chỉ nhận</th>
                    <td>${transaction.amount.details.shipping} ${transaction.amount.currency}</td>
                </tr>
                <tr>
                    <th>Thuế</th>
                    <td>${transaction.amount.details.tax} ${transaction.amount.currency}</td>
                </tr>
                <tr>
                    <th>Tổng cộng</th>
                    <td>${transaction.amount.total} ${transaction.amount.currency}</td>
                </tr>
            </table>
        </div>
    </div>
</body>
</html>