<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!--
Author: W3layouts
Author URL: http://w3layouts.com
License: Creative Commons Attribution 3.0 Unported
License URL: http://creativecommons.org/licenses/by/3.0/
-->
<!DOCTYPE html>
<html>
<head>
<title>Warranty Partners - YOU FILE A CLAIM, WE DO THE REST</title>
<!--mobile apps-->
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<script type="application/x-javascript">
	 addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } 
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
<!-- /css files -->
<!-- js files -->
<script src="js/modernizr.js"></script>
<!-- /js files -->
</head>
<body id="myPage" data-spy="scroll" data-target=".navbar"
	data-offset="60">


	<header id="headerNavLogo"></header>



	<!-- /navigation -->
	<!-- inner pages banner section -->
	<section class="banner-inner"></section>
	<!-- /inner pages banner section -->
	<!-- inner headers -->
	<section class="inner-header">
		<div class="container">
			<!-- Page Heading/Breadcrumbs -->
			<div class="row">
				<div class="col-sm-12">
					<h2 class="page-header">About Us</h2>
					<ol class="breadcrumb">
						<li><a href="index.html">Home</a></li>
						<li class="active">About</li>
					</ol>
				</div>
			</div>
			<!-- /.row -->
		</div>
	</section>
	<!-- /inner headers -->
	<!-- about section -->
	<section class="about-w3ls">
		<div class="container">
			<!-- Intro Content -->
			<div class="row">
				<div class="col-md-6">
					<div class="hover01 column">
						<div>
							<figure>
								<img class="img-responsive" src="images/about-img.jpg"
									alt="w3layouts" title="w3layouts">
							</figure>
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<h3>OUR GOAL</h3>
					<p>Assuring years of worry free use of your electronics
						purchase. In its 12th year as a leading provider of Extended
						Warranties, Warranty Partners has built its reputation on
						excellent customer care for buyers of high-end electronics. This
						is a service business and we strive to make every interaction with
						Warranty Partners the easiest, most courteous and expeditious
						experience possible, 24/7.Our staff is required to have a
						“servant’s heart”. They must be focused on delivering the best
						possible experience for our clients.</p>
				</div>
			</div>
			<!-- /.row -->
		</div>
	</section>
	<!-- /about section -->

	<!-- footer section -->


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
	<script src="js/uisearch.js"></script>
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
		$('#headerNavLogo').load('header.html');
		$('#footerNavLogo').load('footer.html');
	</script>




</body>
</html>