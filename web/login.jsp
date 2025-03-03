<%-- 
    Document   : homepage
    Created on : Feb 12, 2025, 8:05:26 PM
    Author     : nguye
--%>



<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title> Login to your account</title>
        <!-- <link rel="manifest" href="site.webmanifest"> -->
        <link rel="shortcut icon" type="image/x-icon" href="img/logo_com.png">
        <!-- CSS here -->
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/style.css">

        <style>
            .avatar-circle {
                width: 40px;
                height: 40px;
                background-color: #007bff;
                color: white;
                font-weight: bold;
                display: flex;
                justify-content: center;
                align-items: center;
                border-radius: 50%;
                font-size: 20px;
                margin-right: 10px;
            }
            .user-info {
                display: flex;
                align-items: center;
                justify-content: center;
                margin-bottom: 10px;
            }
        </style>
    </head>

    <body>

        <div class="container d-flex justify-content-center align-items-center" style="height: 100vh;">
            <div class="card p-4 shadow-lg" style="width: 400px;">
                <h3 class="text-center mb-4">Login</h3>

                <c:if test="${not empty error}">
                    <div class="alert alert-danger text-center">${error}</div>
                </c:if>                

                <%@ include file="backHome.jsp" %>
                <form action="login" method="post">
                    <div class="mb-3">
                        <label for="username" class="form-label">Username</label>
                        <input type="text" class="form-control" id="username" name="username" placeholder="Enter username" required>
                    </div>
                    <div class="mb-3">
                        <label for="password" class="form-label">Password</label>
                        <input type="password" class="form-control" id="password" name="password" placeholder="Enter password" required>
                    </div>
                    <div class="text-left mt-2">
                        <a href="forgotPassword">Forgot your password?</a>
                    </div>

                    <button type="submit" class="btn btn-primary w-100">Login</button>
                </form>

                <div class="text-center mt-2">
                    <a href="signup">Don't have accounts yet? Sign up</a>

                </div>
            </div>



            <script src="js/jquery-1.12.4.min.js"></script>
            <script src="js/bootstrap.min.js"></script>
            <script src="js/main.js"></script>

    </body>
</html>




