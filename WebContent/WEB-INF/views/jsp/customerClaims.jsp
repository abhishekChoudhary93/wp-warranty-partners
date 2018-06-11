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
	<div class="container" style="margin: 10%; font-size: 130%;">
		<div class="well">
			<h3>How Claims Work</h3>
			<p>You are already frustrated that you need to make a claim.
				Let’s make the claim process easy for you. We just need you to do 2
				things…file the claim and send your product back to us.</p>
			<p>Let's Begin!</p>
		</div>
		<div class="well">
			<h3>File a Claim</h3>
			<c:if test="${not empty cannotFileClaim}">
				<div class="alert alert-danger">
					<c:out value="${cannotFileClaim}" />
				</div>
			</c:if>
			<form class="form-horizontal" action="addClaim" method="POST">
				<div class="form-group">
					<label for="name" class="col-sm-offset-2 col-sm-2 control-label">Your
						Name</label>
					<div class="col-sm-6">
						<input type="text" class="form-control" id="name" name="name"
							placeholder="E.g. John Snow" required>
					</div>
				</div>
				<div class="form-group">
					<label for="warrantyRegNumber"
						class="col-sm-offset-2 col-sm-2 control-label">Warranty
						Reg #</label>
					<div class="col-sm-6">
						<input type="text" class="form-control" id="warrantyRegNumber"
							name="warrantyRegNumber" placeholder="e.g. 2213BBJK2312" required>
					</div>
				</div>
				<div class="form-group">
					<label for="warrantyNumber"
						class="col-sm-offset-2 col-sm-2 control-label">Warranty #</label>
					<div class="col-sm-6">
						<input type="text" class="form-control" id="warrantyNumber"
							name="warrantyNumber" placeholder="e.g. 2213BBJK2312" required>
					</div>
				</div>
				<div class="form-group">
					<label for="contactNo"
						class="col-sm-offset-2 col-sm-2 control-label">Contact
						Number</label>
					<div class="col-sm-6">
						<input type="number" name="contactNo" class="form-control"
							id="contactNo" placeholder="E.g. 889977226" required>
					</div>
				</div>
				<div class="form-group">
					<label for="address" class="col-sm-offset-2 col-sm-2 control-label">Address</label>
					<div class="col-sm-6">
						<input type="text" class="form-control" id="address"
							name="address" placeholder="E.g. 14 Street, Park Avenue, CA"
							required>
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-4 col-sm-6">
						<button type="submit" class="btn btn-lg col-sm-3">File</button>
					</div>
				</div>
			</form>
		</div>
		<div class="container">
			<br>
			<h2>Your Claims</h2>
			<table class="table table-striped">
				<thead>
					<tr>
						<th>Claim ID</th>
						<th>Warranty ID(For Product)</th>
						<th>Status</th>
						<th>Date of Filing Claim</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${claims}" var="claim">
						<tr>
							<td style="word-break: break-all;">${claim.claimId}</td>
							<td style="word-break: break-all;">${claim.warrantyId}</td>
							<td style="word-break: break-all;">${claim.status}</td>
							<td style="word-break: break-all;">${claim.date}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
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
		$('#headerNavLogo').load('customerHeader.html');
		$('#footerNavLogo').load('loggedFooter.html');
	</script>
</body>

</html>
