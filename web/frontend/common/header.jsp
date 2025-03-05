<%-- 
    Document   : header
    Created on : Feb 12, 2025, 7:56:28 PM
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
        <!--        <link rel="shortcut icon" type="image/x-icon" href="img/about/logo2.png">-->
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
        <header>
            <div class="header-area ">
                <div class="header-top_area d-none d-lg-block">
                    <div class="container">
                        <div class="row align-items-center">
                            <div class="col-xl-4 col-lg-4">
                                <div class="logo">
                                    <a href="homepage">
                                        <img src="img/about/logo2.png" alt=""width="150" height="75">
                                    </a>
                                </div>
                            </div>
                            <div class="col-xl-8 col-md-8">
                                <div class="header_right d-flex align-items-center">
                                    <div class="short_contact_list">
                                        <ul>
                                            <li><a href=""> <i class="fa fa-envelope"style="color: black"></i> yellowbee@transport.com</a></li>
                                            <li><a href=""> <i class="fa fa-phone"style="color: black"></i> 1601-609 6780</a></li>
                                        </ul>
                                    </div>

                                    <% 
                                       String username = (String) session.getAttribute("username"); 
                                       String role = (String) session.getAttribute("role"); 
                                    %>

                                    <% if (username == null) { %>
                                    <!-- Nếu chưa đăng nhập -->
                                    <div class="book_btn d-none d-lg-block">
                                        <a class="boxed-btn3-line" href="login">Login</a>
                                    </div>
                                    <% } else { %>
                                    <!-- Nếu đã đăng nhập -->
                                    <div class="user-avatar d-none d-lg-block dropdown">
                                        <button class="btn btn-warning dropdown-toggle" type="button" id="userDropdown" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                            <%= username.substring(0, 1).toUpperCase() %>
                                        </button>
                                        <div class="dropdown-menu" aria-labelledby="userDropdown">
                                            <!-- Điều hướng dashboard theo role -->
                                            <% if ("Admin".equalsIgnoreCase(role)) { %>
                                            <a class="dropdown-item" href="admin_dashboard">
                                                <i class="fa fa-dashboard"></i> Dashboard
                                            </a>
                                            <% } else if ("SurveyStaff".equalsIgnoreCase(role)) { %>
                                            <a class="dropdown-item" href="SurveyStaff/dashboard">
                                                <i class="fa fa-dashboard"></i> Dashboard
                                            </a>
                                            <% } else if ("TransportStaff".equalsIgnoreCase(role)) { %>
                                            <a class="dropdown-item" href="TransportStaff/dashboard">
                                                <i class="fa fa-dashboard"></i> Dashboard
                                            </a>
                                            <% } else { %>
                                            <a class="dropdown-item" href="customer/dashboad">
                                                <i class="fa fa-home"></i> Dashboard
                                            </a>
                                            <% } %>

                                            <!-- Profile & Logout -->
                                            <a class="dropdown-item profile-btn" href="profile.jsp">
                                                <i class="fa fa-user"></i> Profile
                                            </a>
                                            <a class="dropdown-item logout-btn" href="logout.jsp">
                                                <i class="fa fa-sign-out"></i> Logout
                                            </a>
                                        </div>
                                    </div>
                                    <% } %>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div id="sticky-header" class="main-header-area">
                    <div class="container">
                        <div class="header_bottom_border">
                            <div class="row align-items-center">
                                <div class="col-12 d-block d-lg-none">
                                    <div class="logo">
                                        <a href="index.html">
                                            <img src="img/logo.png" alt="">
                                        </a>
                                    </div>
                                </div>
                                <div class="col-xl-9 col-lg-9">
                                    <div class="main-menu  d-none d-lg-block">
                                        <nav>
                                            <ul id="navigation">
                                                <li><a  href="homepage">Home</a></li>
                                                <li><a href="service">Services <i class="ti-angle-down"></i></a>
                                                    <ul class="submenu">
                                                        <li><a href="service_detail?id=1">Home Removals</a></li>
                                                        <li><a href="service_detail?id=2">Office Removals</a></li>
                                                        <li><a href="service_detail?id=3">Trucks Rental</a></li>
                                                    </ul>
                                                </li>
                                                <li><a  href="price">Price</a></li>
                                                <li><a href="about">about</a></li>
                                                <li><a href="contact">Contact</a></li>
                                            </ul>
                                        </nav>
                                    </div>
                                </div>

                            </div>
                        </div>

                    </div>
                </div>
            </div>
        </header>
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
