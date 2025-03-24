<%-- 
    Document   : userProfile
    Created on : Mar 23, 2025, 2:38:28 PM
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
        <title>Yellow Bee Transport - User profile</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="shortcut icon" type="image/x-icon" href="img/logo_com.png">

        <!-- CSS -->
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/style.css">

        <style>
            .profile-container {
                display: flex;
                align-items: center;
                justify-content: center;
                margin-top: 50px;
            }
            .profile-image {
                width: 180px;
                height: 180px;
                border-radius: 50%;
                object-fit: cover;
                margin-right: 30px;
                border: 5px solid #007bff;
            }
            .profile-info {
                text-align: left;
            }
            .profile-info table {
                width: 100%;
            }
            .profile-info th {
                text-align: left;
                padding-right: 50px;
                color: #007bff;
            }
            .profile-box {
                background: white;
                padding: 30px;
                border-radius: 10px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            }
        </style>
    </head>
    <body>

        <jsp:include page="/frontend/common/header.jsp" />

        <div class="container">
            <div class="profile-container">
                <!-- Ảnh đại diện -->
                <img src="<%= (user.getImage() == null 
                        || user.getImage().isEmpty()) ? "img/default-avatar.png" : user.getImage() %>" 
                     alt="Profile Picture" class="profile-image">

                <!-- Bảng thông tin user -->
                <div class="profile-box">
                    <h2>User Profile</h2>
                    <table class="profile-info">
                        <tr>
                            <th>Name:</th>
                            <td><%= user.getFullName() %></td>
                        </tr>
                        <tr>
                            <th>Email:</th>
                            <td><%= user.getEmail() %></td>
                        </tr>
                        <tr>
                            <th>Phone:</th>
                            <td><%= user.getPhone() %></td>
                        </tr>
                        <tr>
                            <th>Address:</th>
                            <td><%= user.getAddress() %></td>
                        </tr>
                        <tr>
                            <th>Gender:</th>
                            <td><%= user.getGender() %></td>
                        </tr>
                        <tr>
                            <th>Role:</th>
                            <td><%= user.getRoleName() %></td>
                        </tr>
                    </table>
                    <div class="mt-3">
                        <a href="updateProfile" class="btn btn-primary">Edit Profile</a>
                    </div>
                    <div class="text-left mt-3">
                        <a href="forgotPassword" class="btn btn-primary">Reset Password</a>
                    </div>
                    <div class="text-left mt-3">
                        <a href="customer_dashboard" class="btn btn-primary">Back to Dashboard</a>
                    </div>


                </div>
            </div>
        </div>

        <div class="mt-5">
            <jsp:include page="/frontend/common/footer.jsp" />
        </div>
        <!-- JS -->
        <script src="js/jquery-1.12.4.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/main.js"></script>
    </body>
</html>
