<%-- 
    Document   : forgotPassword
    Created on : Feb 10, 2025, 6:41:37 PM
    Author     : regio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>forgot Password</title>
        <!-- <link rel="manifest" href="site.webmanifest"> -->
        <link rel="shortcut icon" type="image/x-icon" href="img/logo_com.png">
        <!-- CSS here -->
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/style.css">
        <script>
            function validateForm(event) {
                var email = document.getElementById("email").value;
                var errorDiv = document.getElementById("errorDiv");

                if (!email) {
                    errorDiv.innerHTML = "Email không được để trống!";
                    errorDiv.style.display = "block";
                    event.preventDefault(); // Ngăn form submit
                    return false;
                } else if (!/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email)) {
                    errorDiv.innerHTML = "Vui lòng nhập một địa chỉ email hợp lệ!";
                    errorDiv.style.display = "block";
                    event.preventDefault(); // Ngăn form submit
                    return false;
                }
                return true;
            }
        </script>
    </head>
    <body>
        <jsp:include page="/frontend/common/header.jsp" />
        <div class="container d-flex justify-content-center align-items-center" style="height: 100vh;">
            <div class="card p-4 shadow-lg" style="width: 400px;">
                <h3 class="text-center mb-4">Forgot Password</h3>

                <!-- Thông báo lỗi từ server (Chỉ hiển thị chữ đỏ, không có viền) -->
                <c:if test="${not empty error}">
                    <div class="text-danger text-center mb-3">${error}</div>
                </c:if>

                <%@ include file="backHome.jsp" %>

                <form action="forgotPassword" method="post" onsubmit="return validateForm(event);">
                    <div class="mb-3">
                        <label for="email" class="form-label">Enter your email</label>
                        <input type="email" class="form-control" id="email" name="email" placeholder="Email address" required>
                    </div>
                    <button type="submit" class="btn btn-primary w-100">Submit</button>
                </form>
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

