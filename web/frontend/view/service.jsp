<%-- 
    Document   : service
    Created on : Feb 13, 2025, 7:37:02 AM
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
        <div class="bradcam_area bradcam_bg_1">
            <div class="container">
                <div class="row">
                    <div class="col-xl-12">
                        <div class="bradcam_text text-center">
                            <h3>Our Services</h3>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="container">
            <div class="row">
                <!-- Service 1 -->
                <div class="col-12 mb-4">
                    <div class="row align-items-center">
                        <div class="col-md-4">
                            <img src="img/service/service1.png" class="img-fluid" alt="">
                        </div>
                        <div class="col-md-8">
                            <h3><a href="service_detail?id=1">Home Removal</a></h3>
                            <p>At Yellow Bee Transport, we ensure a stress-free home moving experience, 
                                handling your belongings with care from start to finish.</p>
                            <ul>
                                <li>★ Reliable and efficient service</li>
                                <li>★ Experienced and professional staff</li>
                                <li>★ Affordable and transparent pricing</li>
                            </ul>
                            <a href="service_detail?id=1" class="btn float-end" style="background-color: #FF3414; color: #fff;">Watch more</a>
                        </div>
                    </div>
                </div>

                <!-- Service 2 -->
                <div class="col-12 mb-4">
                    <div class="row align-items-center">
                        <div class="col-md-4">
                            <img src="img/service/service2.png" class="img-fluid" alt="">
                        </div>
                        <div class="col-md-8">
                            <h3><a href="service_detail?id=2">Office Removal</a></h3>
                            <p>Minimize downtime with our efficient office removal service, 
                                designed to get your business back up and running swiftly.</p>
                            <ul>
                                <li>★ Fast and secure relocation</li>
                                <li>★ Minimal business interruption</li>
                                <li>★ Customized moving solutions</li>
                            </ul>
                            <a href="service_detail?id=2" class="btn float-end" style="background-color: #FF3414; color: #fff;">Watch more</a>
                        </div>
                    </div>
                </div>

                <!-- Service 3 -->
                <div class="col-12 mb-4">
                    <div class="row align-items-center">
                        <div class="col-md-4">
                            <img src="img/service/2.png" class="img-fluid" alt="">
                        </div>
                        <div class="col-md-8">
                            <h3><a href="service_detail?id=3">Truck Rental</a></h3>
                            <p>Need flexibility? Our reliable truck rental service 
                                offers the right vehicle for your moving needs, on your schedule.</p>
                            <ul>
                                <li>★ Flexible rental durations</li>
                                <li>★ Well-maintained vehicles</li>
                                <li>★ Competitive rental rates</li>
                            </ul>
                            <a href="service_detail?id=3" class="btn float-end" style="background-color: #FF3414; color: #fff;">Watch more</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="mt-5">
            <jsp:include page="/frontend/common/footer.jsp" />
        </div>
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
