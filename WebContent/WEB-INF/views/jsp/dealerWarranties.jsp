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
<script type="application/x-javascript">
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
    addEventListener("load", function() {
        setTimeout(hideURLbar, 0);
    }, false);

    function hideURLbar() {
        window.scrollTo(0, 1);
    }
   





























































</script>
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
<link href="css/typo.css" rel="stylesheet" type="text/css" media="all" />
<!-- /css files -->
<!-- js files -->
<script src="js/modernizr.js"></script>
<!-- /js files -->
</head>

<body id="myPage" data-spy="scroll" data-target=".navbar"
	data-offset="60">
	<header id="headerNavLogo"></header>
	<div class="container" style="margin: 10%; font-size: 120%;">
		<div class="row" style="margin-top: -4%;">

			<div class="col-sm-6 well">
				<c:if test="${not empty cannotAddWarranty}">
					<div class="alert alert-danger">
						<c:out value="${cannotAddWarranty}" />
					</div>
				</c:if>
				<form action="getWarrantyNumberForDealer" method="POST" style="">
					<div class="form-group">
						<label for="consumerName">Consumer Name</label> <input required
							type="text" name="consumerName" class="form-control"
							id="consumerName" placeholder="e.g John Snow">
					</div>
					<div class="form-group">
						<label for="address">Address</label> <input required type="text"
							name="address" class="form-control" id="address"
							placeholder="e.g Park Street">
					</div>
					<div class="form-group">
						<label for="orderNumber">Order No.</label> <input required
							type="text" name="orderNumber" class="form-control"
							id="orderNumber" placeholder="e.g 11222EHE222">
					</div>
					<div class="form-group">
						<label for="purchasePrice">Purchase Price</label> <input required
							type="number" name="purchasePrice" class="form-control"
							id="purchasePrice" placeholder="e.g 21.99">
					</div>
					<div class="form-group">
						<label for="productName">Product Name</label> <input required
							type="text" name="productName" class="form-control"
							id="productName" placeholder="e.g LG AC">
					</div>
					<div class="form-group">
						<label for="productSerial">Product Serial Number</label> <input
							required type="text" name="productSerial" class="form-control"
							id="productSerial" placeholder="117HAK456">
					</div>
					<div class="form-group">
						<label for="dateOfPurchase">Date of Purchase</label> <input
							required type="date" name="dateOfPurchase" class="form-control"
							id="dateOfPurchase" placeholder="e.g John Snow">
					</div>
					<center>
						<button type="submit" class="btn btn-default"
							style="font-size: 130%;">Add Warranty</button>
					</center>
				</form>
			</div>
			<div class="col-sm-5 col-sm-offset-1">
				<h2>
					Add Warranties/<a href="#showWarranties">Show Warranties</a>
				</h2>
				<br>
				<h3>How Warranties work</h3>
				<ol>
					<li>Add warranties manually(form at left)/using API (read <a
						href="apiDocumentation">api documentation</a>)
					</li>
					<li>You will be getting a unique Warranty Number</li>
					<li>Provide that Warranty # to your customer and ask him to
						register at www.warrantypartners.com/loginPage</li>
					<li>After registering, the user has to register warranty by
						providing Warranty # and Product Serial Number</li>
					<li>You are done then !! We will manage the rest !</li>
				</ol>

				<img alt="" src="images/complete.png" height="50%" width="50%"
					style="margin-left: 10%;">
			</div>
		</div>
		<br> <br>
		<div class="row well" id="showWarranties">
			<div class="col-sm-12">
				<h2>Warranties Sold by You</h2>
				<table class="table">
					<thead>
						<tr>
							<th>Warranty #</th>
							<th>Customer Name</th>
							<th>Address</th>
							<th>Purchase Price</th>
							<th>Order Number</th>
							<th>Product Name</th>
							<th>Product Serial</th>
							<th>Purchase Date</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${warranties}" var="warranty">
							<tr>
								<td style="word-break: break-all;">${warranty.warrantyNumber }</td>
								<td style="word-break: break-all;">${warranty.consumerName }</td>
								<td style="word-break: break-all;">${warranty.address }</td>
								<td style="word-break: break-all;">${warranty.purchasePrice}</td>
								<td style="word-break: break-all;">${warranty.orderNumber }</td>
								<td style="word-break: break-all;">${warranty.productName }</td>
								<td style="word-break: break-all;">${warranty.productSerial }</td>
								<td style="word-break: break-all;">${warranty.dateOfPurchase }</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>

		</div>
	</div>
	<footer id="footerNavLogo"></footer>
	<!-- /footer section -->
	<!-- back to top -->
	<a href="#0" class="cd-top">Top</a>
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
	<!-- /contact form -->
	<!-- scroll to top -->
	<script>
		$(document).ready(function() {
			// Add smooth scrolling to all links in navbar + footer link
			$("section.footer a[href='#myPage']").on('click', function(event) {

				// Make sure this.hash has a value before overriding default behavior
				if (this.hash !== "") {

					// Store hash
					var hash = this.hash;

					// Using jQuery's animate() method to add smooth page scroll
					// The optional number (900) specifies the number of milliseconds it takes to scroll to the specified area
					$('html, body').animate({
						scrollTop : $(hash).offset().top
					}, 900, function() {

						// Add hash (#) to URL when done scrolling (default click behavior)
						window.location.hash = hash;
					});
				} // End if
			});
		})
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
	<script>
		new UISearch(document.getElementById('sb-search'));
	</script>
	<!-- /js for search button -->
	<!-- js for banner files -->
	<script src="js/jquery.pogo-slider.min.js"></script>
	<script src="js/main.js"></script>
	<!-- /js for banner files -->
	<!-- js for pricing table pop up -->
	<script src="js/jquery.magnific-popup.js" type="text/javascript"></script>
	<script>
		$(document).ready(function() {
			$('.popup-with-zoom-anim').magnificPopup({
				type : 'inline',
				fixedContentPos : false,
				fixedBgPos : true,
				overflowY : 'auto',
				closeBtnInside : true,
				preloader : false,
				midClick : true,
				removalDelay : 300,
				mainClass : 'my-mfp-zoom-in'
			});
		});
	</script>
	<!-- /js for pricing table pop up -->
	<!-- /js files -->
	<script type="text/javascript">
		$('#headerNavLogo').load('dealerHeader.html');
		$('#footerNavLogo').load('loggedFooter.html');
	</script>
</body>

</html>
