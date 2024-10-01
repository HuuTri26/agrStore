<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%@include file="/WEB-INF/views/include/customer/header.jsp"%>
<head>
<style>
.height-100 {
	height: 100vh;
}

#otp {
	display: flex;
	flex-direction: row;
	justify-content: center;
	flex-wrap: nowrap;
}

#otp input {
	all: unset; /* Loại bỏ toàn bộ style mặc định của input */
	width: 40px;
	height: 40px;
	margin: 5px;
	text-align: center;
	color: black;
	background-color: white;
	font-size: 20px;
	border: 1px solid #ccc; /* Thêm lại viền */
	padding: 5px;
	box-sizing: border-box;
}

.card {
	width: 200vh;
	border: none;
	height: 100vh;
	box-shadow: 0px 5px 20px 0px #d2dae3;
	z-index: 1;
	display: flex;
	justify-content: center;
	align-items: center;
	flex-direction: column;
}

.card h6 {
	color: red;
	font-size: 20px;
}

/* .inputs input {
	width: 40px;
	height: 40px;
} */
input[type=number]::-webkit-inner-spin-button, input[type=number]::-webkit-outer-spin-button
	{
	-webkit-appearance: none;
	-moz-appearance: none;
	appearance: none;
	margin: 0
}

.card-2 {
	background-color: #fff;
	padding: 10px;
	width: 350px;
	height: 100px;
	bottom: -50px;
	left: 20px;
	position: absolute;
	border-radius: 5px;
}

.card-2 .content {
	margin-top: 50px;
}

.card-2 .content a {
	color: red;
}

.form-control:focus {
	box-shadow: none;
	border: 2px solid red;
}

.validate {
	border-radius: 20px;
	height: 40px;
	background-color: red;
	border: 1px solid red;
	width: 140px;
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
				<li class="nav-item"><a href="#" class="permal-link">Trang
						chủ</a></li>
				<li class="nav-item"><span class="current-page">OTP Sign
						up</span></li>
			</ul>
		</nav>
	</div>


	<!-- Main content -->


	<div
		class="container height-100 d-flex justify-content-center align-items-center">
		<div class="position-relative">
			<div class="card p-2 text-center">
				<h6>
					Nhập mã OTP gửi đến mail của bạn <br> để xác thực gmail

				</h6>
				<div>
					<span>code được gửi đén mail: </span> <small>...@gmail.com</small>
				</div>
				<div id="otp"
					class="inputs d-flex flex-row justify-content-center mt-2">
					<input class="m-2 text-center form-control rounded" type="text"
						id="first" maxlength="1" /> <input
						class="m-2 text-center form-control rounded" type="text"
						id="second" maxlength="1" /> <input
						class="m-2 text-center form-control rounded" type="text"
						id="third" maxlength="1" /> <input
						class="m-2 text-center form-control rounded" type="text"
						id="fourth" maxlength="1" /> <input
						class="m-2 text-center form-control rounded" type="text"
						id="fifth" maxlength="1" /> <input
						class="m-2 text-center form-control rounded" type="text"
						id="sixth" maxlength="1" />
				</div>
				<div class="mt-4">
					<a href="userSignUp.htm">
						<button class="btn btn-danger px-4 validate">Xác thực</button>
					</a>
				</div>
			</div>
		</div>
	</div>


	<!-- FOOTER -->
	<%@include file="/WEB-INF/views/include/customer/footer.jsp"%>

	<!--Footer For Mobile-->
	<!--Mobile Global Menu-->
	<!--Quickview Popup-->
	<%@include file="/WEB-INF/views/include/customer/footerMobile.jsp"%>

	<!-- Scroll Top Button and JS -->
	<%@include file="/WEB-INF/views/include/customer/js.jsp"%>
	<script>
		document.addEventListener("DOMContentLoaded", function(event) {

			function OTPInput() {
				const inputs = document.querySelectorAll('#otp > *[id]');
				for (let i = 0; i < inputs.length; i++) {
					inputs[i].addEventListener('keydown',
							function(event) {
								if (event.key === "Backspace") {
									inputs[i].value = '';
									if (i !== 0)
										inputs[i - 1].focus();
								} else {
									if (i === inputs.length - 1
											&& inputs[i].value !== '') {
										return true;
									} else if (event.keyCode > 47
											&& event.keyCode < 58) {
										inputs[i].value = event.key;
										if (i !== inputs.length - 1)
											inputs[i + 1].focus();
										event.preventDefault();
									} else if (event.keyCode > 64
											&& event.keyCode < 91) {
										inputs[i].value = String
												.fromCharCode(event.keyCode);
										if (i !== inputs.length - 1)
											inputs[i + 1].focus();
										event.preventDefault();
									}
								}
							});
				}
			}
			OTPInput();
		});
	</script>
</body>
</html>