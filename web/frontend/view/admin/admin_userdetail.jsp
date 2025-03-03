<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="Model.User" %>
<%@ page import="Model.Accounts" %>
<%@ page import="java.util.*" %>

<%
     User user = (User) request.getAttribute("user");
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
                <!-- Taskbar (col-3) -->
                <div class="col-md-2">
                    <jsp:include page="/frontend/common/admin_taskbar.jsp" />
                </div>

                <!-- Nội dung chính (col-9) -->
                <div class="col-md-10">
                    <div class="container mt-3">
                        <% if (user != null) { %>
                        <h2>User Details</h2>
                        <div class="container">
                            <!-- Hiển thị ảnh -->
                            <img src="<%= user.getImage() %>" alt="User Image" class="user-image">

                            <!-- Hiển thị thông tin user -->
                            <table class="info-table">
                                <tr><th>User ID:</th><td><%= user.getUserId() %></td></tr>
                                <tr><th>Full Name:</th><td><%= user.getFullName() %></td></tr>
                                <tr><th>Email:</th><td><%= user.getEmail() %></td></tr>
                                <tr><th>Phone:</th><td><%= user.getPhone() %></td></tr>
                                <tr><th>Address:</th><td><%= user.getAddress() %></td></tr>
                                <tr><th>Gender:</th><td><%= (user.getGenderId() == 1 ? "Male" : "Female") %></td></tr>
                                <tr><th>Role:</th><td><%= user.getRoleName() %></td></tr>
                            </table>
                        </div>
                        <% } else { %>
                        <h2>User not found!</h2>
                        <% } %>

                        <a href="admin_user">Back to User List</a>
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