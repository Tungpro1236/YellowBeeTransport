<%-- 
    Document   : homepage
    Created on : Feb 12, 2025, 8:05:26 PM
    Author     : nguye
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="x-ua-compatible" content="ie=edge">
        <title>Yellow Bee Transport</title>
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <!-- <link rel="manifest" href="site.webmanifest"> -->
        <link rel="shortcut icon" type="image/x-icon" href="img/logo_com.png">
        <!-- Place favicon.ico in the root directory -->

        <!-- CSS here -->
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/owl.carousel.min.css">
        <link rel="stylesheet" href="css/magnific-popup.css">
        <link rel="stylesheet" href="css/font-awesome.min.css">
        <link rel="stylesheet" href="css/themify-icons.css">
        <link rel="stylesheet" href="css/nice-select.css">
        <link rel="stylesheet" href="css/flaticon.css">
        <link rel="stylesheet" href="css/gijgo.css">
        <link rel="stylesheet" href="css/animate.css">
        <link rel="stylesheet" href="css/slick.css">
        <link rel="stylesheet" href="css/slicknav.css">
        <link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/themes/smoothness/jquery-ui.css">

        <link rel="stylesheet" href="css/style.css">
        <!-- <link rel="stylesheet" href="css/responsive.css"> -->
    </head>


    <body>
        <jsp:include page="/frontend/common/header.jsp" />

        <div class="slider_area">
            <div class="single_slider  d-flex align-items-center slider_bg_1">
                <div class="container">
                    <div class="row align-items-center justify-content-center">
                        <div class="col-xl-8">
                            <div class="slider_text text-center justify-content-center">
                                <p>Yellow Bee Transport</p>
                                <h3>Buzzing Through Hassle </h3>
                                <h3>Free Moves</h3>

                                <a class="boxed-btn3" href="service">Our Services</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="service_area">
            <div class="container">
                <div class="row">
                    <div class="col-xl-12">
                        <div class="section_title mb-50 text-center">
                            <h3>
                                Services We Offer
                            </h3>
                        </div>
                    </div>
                </div>
                <div class="container">
                    <div class="row">
                        <div class="col-md-6 col-lg-4">
                            <div class="single_service">
                                <div class="thumb">
                                    <img src="img/service/service1.png" alt="">
                                </div>
                                <div class="service_info">
                                    <h3><a href="service_detail?id=1">Home Removal</a></h3>
                                    <p>At Yellow Bee Transport, we ensure a stress-free home moving experience, 
                                        handling your belongings with care from start to finish.</p>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-6 col-lg-4">
                            <div class="single_service">
                                <div class="thumb">
                                    <img src="img/service/service2.png" alt="">
                                </div>
                                <div class="service_info">
                                    <h3><a href="service_detail?id=2">Office Removal</a></h3>
                                    <p>Minimize downtime with our efficient office removal service, 
                                        designed to get your business back up and running swiftly.</p>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-6 col-lg-4">

                            <div class="single_service">
                                <div class="thumb">
                                    <img src="img/service/2.png" alt="">
                                </div>
                                <div class="service_info">
                                    <h3><a href="service_detail?id=3">Truck Rental</a></h3>
                                    <p>Need flexibility? Our reliable truck rental service 
                                        offers the right vehicle for your moving needs, on your schedule.</p>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>
            </div>
        </div>

        <div class="chose_area">
            <div class="row">
                <div class="col-xl-12">
                    <div class="section_title mb-50 text-center">
                        <h3>
                            Removal Process
                        </h3>
                    </div>
                </div>
            </div>
            <div class="about_image" style="max-width: 100%; height: auto; text-align: center;">
                <img src="img/service/how2.png" alt="" style="max-width: 100%; height: auto; display: block; margin: 0 auto;">
            </div>
        </div>

        <div class="chose_area ">
            <div class="container">
                <div class="features_main_wrap">
                    <div class="row align-items-center">
                        <div class="col-xl-5 col-lg-5 col-md-6">
                            <div class="about_image">
                                <img src="img/banner/accordion.png" alt="">
                            </div>
                        </div>
                        <div class="col-xl-6 col-lg-6 col-md-6">
                            <div class="features_info">
                                <h3>Why Choose Us?</h3>
                                <p>We are committed to providing a stress-free moving experience with professional, reliable, and efficient services tailored to your needs.</p>
                                <ul>
                                    <li> Professional and experienced team for secure and safe moving.</li>
                                    <li> Transparent pricing with no hidden costs.</li>
                                    <li> Flexible scheduling to suit your timeline.</li>
                                </ul>
                                <div class="about_btn">
                                    <a class="boxed-btn3-line" href="about">About Us</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

       <!-- contact_action_area  -->
<div class="contact_action_area">
    <div class="container">
        <div class="row align-items-center">
            <div class="col-xl-7 col-md-6">
                <div class="action_heading">
                    <h3>100% Secure and Safe</h3>
                    <p>We prioritize your safety and ensure all your belongings are handled with the utmost care and protection.</p>
                </div>
            </div>
            <div class="col-xl-5 col-md-6">
                <div class="call_add_action">
                    <a href="contact" class="boxed-btn3">+10 672 457 356</a>
                </div>
            </div>
        </div>
        
    </div>
</div>


        <jsp:include page="/frontend/common/footer.jsp" />

        <script src="js/vendor/modernizr-3.5.0.min.js"></script>
        <script src="js/vendor/jquery-1.12.4.min.js"></script>
        <script src="js/popper.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/owl.carousel.min.js"></script>
        <script src="js/isotope.pkgd.min.js"></script>
        <script src="js/ajax-form.js"></script>
        <script src="js/waypoints.min.js"></script>
        <script src="js/jquery.counterup.min.js"></script>
        <script src="js/imagesloaded.pkgd.min.js"></script>
        <script src="js/scrollIt.js"></script>
        <script src="js/jquery.scrollUp.min.js"></script>
        <script src="js/wow.min.js"></script>
        <script src="js/nice-select.min.js"></script>
        <script src="js/jquery.slicknav.min.js"></script>
        <script src="js/jquery.magnific-popup.min.js"></script>
        <script src="js/plugins.js"></script>
        <!-- <script src="js/gijgo.min.js"></script> -->
        <script src="js/slick.min.js"></script>



        <!--contact js-->
        <script src="js/contact.js"></script>
        <script src="js/jquery.ajaxchimp.min.js"></script>
        <script src="js/jquery.form.js"></script>
        <script src="js/jquery.validate.min.js"></script>
        <script src="js/mail-script.js"></script>


        <script src="js/main.js"></script>




    </body>

</html>