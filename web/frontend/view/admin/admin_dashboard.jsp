<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.Map" %>

<%
    Object userRoleDataObj = request.getAttribute("userRoleData");
    Map<String, Integer> userRoleData = null;
    if (userRoleDataObj instanceof Map) {
        userRoleData = (Map<String, Integer>) userRoleDataObj;
    }
%>

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

        <div class="container-fluid mt-3">
            <div class="row">
                <!-- Sidebar -->
                <div class="col-md-3">
                    <jsp:include page="/frontend/common/admin_taskbar.jsp" />
                </div>

                <!-- Main content -->
                <div class="col-md-9">
                    <div class="main-content p-3">
                        <h2>Welcome to Admin Dashboard</h2>
                        <div class="card p-3">
                            <h4 class="mb-3">User Statistics</h4>
                            <p>Total users: <span style="color: red; font-weight: bold;">${totalUsers}</span>, 
                                including:</p>

                            <ul>
                                <%
                                    if (userRoleData != null && !userRoleData.isEmpty()) {
                                        for (Map.Entry<String, Integer> entry : userRoleData.entrySet()) {
                                %>
                                <li>
                                    <span><%= entry.getKey() %>:</span>
                                    <span style="color: blue; font-weight: bold;"><%= entry.getValue() %></span> users
                                </li>
                                <%
                                        }
                                    } else {
                                %>
                                <li>No data available</li>
                                    <%
                                        }
                                    %>
                            </ul>
                        </div>                    
                    </div>
                </div>
            </div>
        </div>

        <jsp:include page="/frontend/common/footer.jsp" />
        <script src="js/vendor/jquery-1.12.4.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
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