<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="Model.User" %>
<%@ page import="Model.Roles" %>
<%
    List<User> userList = (List<User>) request.getAttribute("userList");
    List<Roles> roleList = (List<Roles>) request.getAttribute("roleList");
    String searchQuery = (String) request.getAttribute("searchQuery");
    Integer selectedRole = request.getAttribute("roleFilter") instanceof Integer
                           ? (Integer) request.getAttribute("roleFilter")
                           : null;
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
                <div class="col-md-3">
                    <jsp:include page="/frontend/common/admin_taskbar.jsp" />
                </div>

                <div class="col-md-9">
                    <div class="container mt-3">
                    <h2>All Users</h2>

                    <form action="admin_user" method="POST" class="mb-3">
                        <div class="row">
                            <div class="col-md-4">
                                <input type="text" name="search" class="form-control" 
                                       placeholder="Search by name..." 
                                       value="<%= searchQuery != null ? searchQuery : "" %>">
                            </div>
                            <div class="col-md-3">
                                <select name="roleFilter" class="form-control">
                                    <option value="">All Roles</option>
                                    <% if (roleList != null) {
                                        for (Roles roleObj : roleList) { %>
                                    <option value="<%= roleObj.getRoleID() %>"
                                            <% if (selectedRole != null && roleObj.getRoleID() == selectedRole) { %>
                                            selected
                                            <% } %>>
                                        <%= roleObj.getRoleName() %>
                                    </option>
                                    <% } } %>
                                </select>
                            </div>
                            <div class="col-md-2">
                                <button type="submit" class="btn btn-primary">Search</button>
                            </div>
                        </div>
                    </form>

                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <th>User ID</th>
                                <th>Full Name</th>
                                <th>Email</th>
                                <th>Phone</th>
                                <th>Address</th>
                                <th>Gender</th>
                                <th>Role</th>
                                <th>Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <% if (userList != null && !userList.isEmpty()) {
                                for (User user : userList) { %>
                            <tr>
                                <td><%= user.getUserId() %></td>
                                <td><%= user.getFullName() %></td>
                                <td><%= user.getEmail() %></td>
                                <td><%= user.getPhone() %></td>
                                <td><%= user.getAddress() %></td>
                                <td><%= user.getGender() %></td> 
                                <td><%= user.getRoleName() %></td>
                                <td>
                                    <a href="admin_userdetail?id=<%= user.getUserId() %>" class="btn btn-info btn-sm">Detail</a>
                                </td>
                            </tr>
                            <% } } else { %>
                            <tr>
                                <td colspan="8" class="text-center">No users found</td>
                            </tr>
                            <% } %>
                        </tbody>
                    </table>
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