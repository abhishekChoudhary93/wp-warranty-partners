<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<title>Warranty Partners - YOU FILE A CLAIM, WE DO THE REST</title>
<!--mobile apps-->
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords"
	content="Terrain Responsive web template, Bootstrap Web Templates, Flat Web Templates, Android Compatible web template, 
	Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyEricsson, Motorola web design" />
<!-- fonts -->
<link href='//fonts.googleapis.com/css?family=Ubuntu+Condensed'
	rel='stylesheet' type='text/css'>
<link
	href='//fonts.googleapis.com/css?family=Titillium+Web:400,200,300,600,700,900'
	rel='stylesheet' type='text/css'>
<!--link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.6.2/css/font-awesome.min.css" media="all" rel="stylesheet" type="text/css"-->
<!-- /fonts -->
<!-- css files -->
<link href="css/bootstrap.min.css" rel="stylesheet" type="text/css"
	media="all" />
<link href="css/font-awesome.min.css" rel="stylesheet" type="text/css"
	media="all" />
<link href="css/pricetable.css" rel="stylesheet" type="text/css"
	media="all" />
<link href="css/main.css" rel="stylesheet" type="text/css" media="all" />
<link href="css/pogo-slider.min.css" rel="stylesheet" type="text/css"
	media="all" />
<link href="css/style.css" rel="stylesheet" type="text/css" media="all" />
<!-- /css files -->
<!-- js files -->
<script src="js/modernizr.js"></script>
<!-- /js files -->

</head>
<body>

	<header id="headerNavLogo"></header>


	<div class="container" style="margin-top: 10%; margin-bottom: 5%">
		<c:if test="${not empty invalidLogin}">
			<div class="alert alert-danger">
				<c:out value="${invalidLogin}" />
			</div>
		</c:if>
		<c:if test="${not empty registerComplete}">
			<div class="alert alert-danger">
				<c:out value="${registerComplete}" />
			</div>
		</c:if>
		<c:if test="${not empty failedRegistration}">
			<div class="alert alert-danger">
				<c:out value="${failedRegistration}" />
			</div>
		</c:if>
		<div class="well">
			<h3></h3>
			<form action="login" method="post" class="form-horizontal"
				style="padding-left: 25%; padding-right: 25%;">
				<div class="form-group">
					<label for="userType" class="col-sm-2 control-label">User
						Type</label>
					<div class="radio">
						<div class="col-sm-2">
							<label> <input type="radio" name="userType"
								value="customer" checked> Customer

							</label>
						</div>
					</div>
					<div class="radio">
						<div class="col-sm-2 col-sm-offset-2">
							<label> <input type="radio" name="userType"
								value="dealer"> Dealer
							</label>
						</div>
					</div>
				</div>
				<div class="form-group">
					<label for="username" class="col-sm-2 control-label">Username</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" name="username" required
							placeholder="E.g john">
					</div>
				</div>
				<div class="form-group marginClass">
					<label for="password" class="col-sm-2 control-label">Password</label>
					<div class="col-sm-10 ">
						<input type="password" required class="form-control"
							name="password" placeholder="Password">
					</div>
				</div>

				<div class="form-group">
					<div class="col-sm-2 col-sm-offset-5">
						<input type="submit" class="btn btn-danger btn-lg" value="Sign in" />
					</div>
				</div>
			</form>
		</div>
	</div>
	<center>
		<h2 style="margin-top: -2%; margin-bottom: 2%;">Register Here</h2>
	</center>
	<div class="container">

		<div class="row well" style="margin-left: 0.5%;">


			<form action="register" method="POST" class="form-horizontal"
				style="padding-left: 25%; padding-right: 25%;">
				<div class="form-group">
					<label for="name" class="col-sm-2 control-label">Name</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" name="name"
							placeholder="e.g. John Wills" required />
					</div>
				</div>
				<div class="form-group">
					<label for="username" class="col-sm-2 control-label">Username</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" name="username"
							placeholder="e.g. johnWills23" required />
					</div>
				</div>
				<div class="form-group">
					<label for="email" class="col-sm-2 control-label">Email</label>
					<div class="col-sm-10">
						<input type="email" placeholder="e.g. johnwills@gmail.com"
							class="form-control" name="email"
							placeholder="e.g. johnwills@gmail.com" required>
					</div>
				</div>
				<div class="form-group">
					<label for="contactNo" class="col-sm-2 control-label">Phone
					</label>
					<div class="col-sm-10">
						<input type="number" placeholder="e.g. 8888888888 (No '-' in between)" class="form-control"
							name="contactNo" required>
					</div>
				</div>
				<div class="form-group">
					<label for="address" class="col-sm-2 control-label">Address</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" name="address"
							placeholder="e.g. 27, Park Street, New Town" required>
					</div>
				</div>
				<div class="form-group">
					<label for="userType" class="col-sm-2 control-label">User
						Type</label>
					<div class="radio">
						<div class="col-sm-2">
							<label> <input type="radio" name="userType"
								value="customer" checked> Customer

							</label>
						</div>
					</div>
					<div class="radio">
						<div class="col-sm-2 col-sm-offset-2">
							<label> <input type="radio" name="userType"
								value="dealer"> Dealer
							</label>
						</div>

					</div>
				</div>
				<div class="form-group">
					<label for="password" class="col-sm-2 control-label">Password</label>
					<div class="col-sm-10">
						<input type="password" class="form-control" name="password"
							id="password1" placeholder="**********" required>
					</div>
				</div>
				<div class="form-group">
					<label for="password" class="col-sm-2 control-label">Re-Enter</label>
					<div class="col-sm-10">
						<input type="password" class="form-control" name="password2"
							id="password2" placeholder="**********" required>
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-10" style="margin-left: 39%;">
						<button type="submit" class="btn btn-lg"
							style="background-color: #161616; color: white;">Register</button>
					</div>
				</div>
			</form>
			<script type="text/javascript">
				var password = document.getElementById("password1"), confirm_password = document
						.getElementById("password2");

				function validatePassword() {
					if (password.value != confirm_password.value) {
						confirm_password
								.setCustomValidity("Passwords Don't Match");
					} else {
						confirm_password.setCustomValidity('');
					}
				}

				password.onchange = validatePassword;
				confirm_password.onkeyup = validatePassword;
			</script>
		</div>
	</div>



	<footer id="footerNavLogo"></footer>
	<!-- /footer section -->
	<!-- back to top -->
	<!-- 	<a href="#0" class="cd-top">Top</a> -->
	<!-- /back to top -->
	<!-- js files -->
	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/SmoothScroll.min.js"></script>
	<!-- js for back to top -->
	<script src="js/top.js"></script>
	<!-- /js for back to top -->
	<!-- contact form -->
	<script src="js/jqBootstrapValidation.js"></script>
	<!-- <script src="js/contact_me.js"></script> -->
	<!-- /contact form -->
	<!-- scroll to top -->
	<script>
		// 		$(document).ready(function() {
		// 			// Add smooth scrolling to all links in navbar + footer link
		// 			$("section.footer a[href='#myPage']").on('click', function(event) {

		// 				// Make sure this.hash has a value before overriding default behavior
		// 				if (this.hash !== "") {

		// 					// Store hash
		// 					var hash = this.hash;

		// 					// Using jQuery's animate() method to add smooth page scroll
		// 					// The optional number (900) specifies the number of milliseconds it takes to scroll to the specified area
		// 					$('html, body').animate({
		// 						scrollTop : $(hash).offset().top
		// 					}, 900, function() {

		// 						// Add hash (#) to URL when done scrolling (default click behavior)
		// 						window.location.hash = hash;
		// 					});
		// 				} // End if
		// 			});
		// 		})
	</script>
	<!-- /scroll to top -->
	<!-- js for pricing table -->
	<script src="js/pricetable.js"></script>
	<!-- /js for pricing table -->
	<!-- js for info -->
	<script src="js/wmBox.js"></script>
	<script src="js/info.js"></script>
	<!-- /js for info -->
	<!-- js for search button -->
	<script src="js/classie.js"></script>
	<script src="js/uisearch.js"></script>
	<script>
		// 		new UISearch(document.getElementById('sb-search'));
	</script>
	<!-- /js for search button -->
	<!-- js for banner files -->
	<script src="js/jquery.pogo-slider.min.js"></script>
	<script src="js/main.js"></script>
	<!-- /js for banner files -->
	<!-- js for pricing table pop up -->
	<script src="js/jquery.magnific-popup.js" type="text/javascript"></script>
	<script>
		// 		$(document).ready(function() {
		// 			$('.popup-with-zoom-anim').magnificPopup({
		// 				type : 'inline',
		// 				fixedContentPos : false,
		// 				fixedBgPos : true,
		// 				overflowY : 'auto',
		// 				closeBtnInside : true,
		// 				preloader : false,
		// 				midClick : true,
		// 				removalDelay : 300,
		// 				mainClass : 'my-mfp-zoom-in'
		// 			});
		// 		});
	</script>
	<!-- /js for pricing table pop up -->
	<!-- /js files -->
	<script type="text/javascript">
		$('#headerNavLogo').load('header.html');
		$('#footerNavLogo').load('footer.html');
	</script>
</body>

</html>