<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Create Checking Form</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/flatpickr/dist/flatpickr.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <h2>Create Checking Form</h2>
        
        <c:if test="${not empty error}">
            <div class="alert alert-danger" role="alert">
                ${error}
            </div>
        </c:if>

        <form action="${pageContext.request.contextPath}/create-checking-form" method="post" class="mt-4">
            <div class="mb-3">
                <label for="customerName" class="form-label">Customer Name</label>
                <input type="text" class="form-control" id="customerName" name="customerName" required>
            </div>

            <div class="mb-3">
                <label for="phone" class="form-label">Phone Number</label>
                <input type="tel" class="form-control" id="phone" name="phone" required>
            </div>

            <div class="mb-3">
                <label for="email" class="form-label">Email</label>
                <input type="email" class="form-control" id="email" name="email" required>
            </div>

            <div class="mb-3">
                <label for="address" class="form-label">Address</label>
                <textarea class="form-control" id="address" name="address" rows="3" required></textarea>
            </div>

            <div class="mb-3">
                <label for="serviceID" class="form-label">Service</label>
                <select class="form-select" id="serviceID" name="serviceID" required>
                    <option value="">Select a service</option>
                    <c:forEach items="${services}" var="service">
                        <option value="${service.serviceID}">${service.serviceName}</option>
                    </c:forEach>
                </select>
            </div>

            <div class="mb-3">
                <label for="checkingTime" class="form-label">Checking Time</label>
                <input type="datetime-local" class="form-control" id="checkingTime" name="checkingTime" required>
            </div>

            <div class="mb-3">
                <label for="transportTime" class="form-label">Transport Time</label>
                <input type="datetime-local" class="form-control" id="transportTime" name="transportTime" required>
            </div>

            <div class="mb-3">
                <button type="submit" class="btn btn-primary">Create Form</button>
                <a href="${pageContext.request.contextPath}/checking-forms" class="btn btn-secondary">Cancel</a>
            </div>
        </form>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/flatpickr"></script>
    <script>
        // Initialize datetime pickers
        flatpickr("#checkingTime", {
            enableTime: true,
            dateFormat: "Y-m-d H:i",
            minDate: "today"
        });

        flatpickr("#transportTime", {
            enableTime: true,
            dateFormat: "Y-m-d H:i",
            minDate: "today"
        });
    </script>
</body>
</html> 