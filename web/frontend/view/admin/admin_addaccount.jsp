<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="Model.User" %>
<%@ page import="Model.Roles" %>
<%
    List<Roles> roleList = (List<Roles>) request.getAttribute("roleList");
    List<String> errors = (List<String>) request.getAttribute("errors");
    Integer selectedRole = request.getAttribute("roleFilter") instanceof Integer
                           ? (Integer) request.getAttribute("roleFilter")
                           : null;

    String roleError = null;
    if (errors != null) {
        for (String error : errors) {
            if (error.toLowerCase().contains("role")) {
                roleError = error;
                break;
            }
        }
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="x-ua-compatible" content="ie=edge">
        <title>Yellow Bee Transport</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="shortcut icon" type="image/x-icon" href="img/logo_com.png">
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/style.css">
    </head>
    <body>

        <jsp:include page="/frontend/common/header.jsp" />

        <div class="container-fluid mt-3">
            <div class="row">
                <div class="col-md-3">
                    <jsp:include page="/frontend/common/admin_taskbar.jsp" />
                </div>

                <div class="col-md-9">
                    <div class="main-content p-3">
                        <h3>Create New Account</h3>

                        <%-- Hiển thị lỗi chung --%>
                        <% if (errors != null && !errors.isEmpty()) { %>
                            <div class="alert alert-danger">
                                <ul>
                                    <% for (String error : errors) { %>
                                        <% if (!error.toLowerCase().contains("role")) { %>
                                            <li><%= error %></li>
                                        <% } %>
                                    <% } %>
                                </ul>
                            </div>
                        <% } %>

                        <form action="admin_addaccounts" method="post">
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
                                <label>Profile Image:</label>
                                <input type="text" name="image" class="form-control" 
                                       value="<%= request.getAttribute("image") != null ? request.getAttribute("image") : "" %>"required>
                            </div>

                            <div class="form-group">
                                <label>Role:</label>
                                <select name="roleFilter" class="form-control">
                                    
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
                                <% if (roleError != null) { %>
                                    <span class="text-danger"><%= roleError %></span>
                                <% } %>
                            </div>

                            <button type="submit" class="btn btn-primary">Register</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>

        <jsp:include page="/frontend/common/footer.jsp" />

        <script src="js/vendor/jquery-1.12.4.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/main.js"></script>

    </body>
</html>
