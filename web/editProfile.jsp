<%-- 
    Document   : editProfile
    Created on : Mar 23, 2025, 3:54:38 PM
    Author     : regio
--%>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="Model.User" %>
<%@ page session="true" %>

<%
    User user = (User) request.getAttribute("user");
    if (user == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="x-ua-compatible" content="ie=edge">
        <title>Edit Profile - Yellow Bee Transport</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="shortcut icon" type="image/x-icon" href="img/logo_com.png">
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/style.css">

        <style>


            .avatar {
                width: 120px;
                height: 120px;
                border-radius: 50%;

            }


        </style>
    </head>
    <body>
        <jsp:include page="/frontend/common/header.jsp" />

        <div class="container">
            <div class="avatar-container">
                <img id="profileImage" src="<%= (user.getImage() != null && !user.getImage().isEmpty()) 
                   ? user.getImage() : "img/default-avatar.png" %>" alt="Avatar" class="avatar">

            </div>

            <form action="updateProfile" method="post" >
                <input type="hidden" name="userId" value="<%= user.getUserId() %>">
                <input type="hidden" name="currentImage" value="<%= user.getImage() %>">

                <div class="mb-3">
                    <label class="form-label">Full Name</label>
                    <input type="text" name="fullName" class="form-control" value="<%= user.getFullName() %>" required>
                </div>

                <div class="mb-3">
                    <label class="form-label">Email</label>
                    <input type="email" class="form-control" value="<%= user.getEmail() %>" readonly>
                </div>

                <div class="mb-3">
                    <label class="form-label">Phone</label>
                    <input type="text" name="phone" class="form-control" value="<%= user.getPhone() %>">
                </div>

                <div class="mb-3">
                    <label class="form-label">Address</label>
                    <input type="text" name="address" class="form-control" value="<%= user.getAddress() %>">
                </div>

                <div class="mb-3">
                    <label class="form-label">Gender</label>
                    <select name="gender" >
                        <option value="1" <% if ("1".equals(request.getAttribute("gender"))) { %> selected <% } %>>Male</option>
                        <option value="2" <% if ("2".equals(request.getAttribute("gender"))) { %> selected <% } %>>Female</option>
                    </select>
                </div>

                <div class="mb-3">
                    <label class="form-label">Role</label>
                    <input type="text" class="form-control" value="<%= user.getRoleName() %>" readonly>
                </div>

                <div class="form-group">
                    <label>Profile Image URL:</label>
                    <input type="text" name="image" class="form-control" 
                           value="<%= request.getAttribute("image") != null ? request.getAttribute("image") : "" %>" required>
                </div>
                <button type="submit" class="btn btn-primary">Save Changes</button>
            </form>
        </div>



        <div class="mt-5">
            <jsp:include page="/frontend/common/footer.jsp" />
        </div>

        <script src="js/jquery-1.12.4.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/main.js"></script>
    </body>
</html>
