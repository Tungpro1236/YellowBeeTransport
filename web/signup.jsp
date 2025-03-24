<%-- 
    Document   : signup
    Created on : Feb 13, 2025, 6:45:59 PM
    Author     : regio
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="Model.User" %>
<%@ page import="Model.Accounts" %>
<%
    List<String> errors = (List<String>) request.getAttribute("errors");
%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="x-ua-compatible" content="ie=edge">
        <title>Yellow Bee Transport - Signup</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="shortcut icon" type="image/x-icon" href="img/logo_com.png">
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/style.css">
    </head>
    <body>

        <jsp:include page="/frontend/common/header.jsp" />

        <div class="container mt-3">
            <div class="row justify-content-center">
                <div class="col-md-6">
                    <div class="card p-4">
                        <h3 class="text-center">Sign Up</h3>

                        <%-- Hiển thị lỗi nếu có --%>
                        <% if (errors != null && !errors.isEmpty()) { %>
                        <div class="alert alert-danger">
                            <ul>
                                <% for (String error : errors) { %>
                                <li><%= error %></li>
                                    <% } %>
                            </ul>
                        </div>
                        <% } %>

                        <form action="signup" method="post">
                            <div class="form-group">
                                <label>Username:</label>
                                <input type="text" name="username" class="form-control" 
                                       value="<%= request.getAttribute("username") != null ? request.getAttribute("username") : "" %>" required>
                            </div>

                            <div class="form-group">
                                <label>Password:</label>
                                <input type="password" name="password" class="form-control" required>
                            </div>

                            <div class="form-group">
                                <label>Full Name:</label>
                                <input type="text" name="fullName" class="form-control" 
                                       value="<%= request.getAttribute("fullName") != null ? request.getAttribute("fullName") : "" %>" required>
                            </div>

                            <div class="form-group">
                                <label>Email:</label>
                                <input type="email" name="email" class="form-control" 
                                       value="<%= request.getAttribute("email") != null ? request.getAttribute("email") : "" %>" required>
                            </div>

                            <div class="form-group">
                                <label>Phone:</label>
                                <input type="text" name="phone" class="form-control" 
                                       value="<%= request.getAttribute("phone") != null ? request.getAttribute("phone") : "" %>" required>
                            </div>

                            <div class="form-group">
                                <label>Address:</label>
                                <input type="text" name="address" class="form-control" 
                                       value="<%= request.getAttribute("address") != null ? request.getAttribute("address") : "" %>" required>
                            </div>

                            <div class="form-group">
                                <label>Gender:</label>
                                <select name="gender" class="form-control">
                                    <option value="1" <% if ("1".equals(request.getAttribute("gender"))) { %> selected <% } %>>Male</option>
                                    <option value="2" <% if ("2".equals(request.getAttribute("gender"))) { %> selected <% } %>>Female</option>             
                                </select>
                            </div>

                            <div class="form-group">
                                <label>Profile Image URL:</label>
                                <input type="text" name="image" class="form-control" 
                                       value="<%= request.getAttribute("image") != null ? request.getAttribute("image") : "" %>" required>
                            </div>

                            <%-- Role ẩn vì luôn là Customer (ID = 5) --%>
                            <input type="hidden" name="roleFilter" value="5">

                            <button type="submit" class="btn btn-primary btn-block">Register</button>
                        </form>

                        <p class="text-center mt-3">
                            Already have an account? <a href="login.jsp">Login here</a>.
                        </p>
                    </div>
                </div>
            </div>
        </div>

        <div class="mt-5">
            <jsp:include page="/frontend/common/footer.jsp" />
        </div>

        <script src="js/vendor/jquery-1.12.4.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/main.js"></script>

    </body>
</html>