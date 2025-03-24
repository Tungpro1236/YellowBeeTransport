<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Confirm Checking Form</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
</head>
<body>
    <jsp:include page="/frontend/common/header.jsp" />

    <div class="container mt-4">
        <div class="row">
            <div class="col-md-3">
                <jsp:include page="/frontend/common/sidebar.jsp" />
            </div>

            <div class="col-md-9">
                <h3 class="text-center">Confirm Checking Form</h3>

                <h5>Checking Form ID: ${checkingForm.checkingFormID}</h5>
                <h5>Customer Name: ${checkingForm.name}</h5>
                <h5>Phone: ${checkingForm.phone}</h5>
                <h5>Email: ${checkingForm.email}</h5>
                <h5>Address: ${checkingForm.address}</h5>
                <h5>Checking Time: ${checkingForm.checkingTime}</h5>

                <form method="post" action="ManageCheckingForms">
                    <input type="hidden" name="action" value="confirmAssign">
                    <input type="hidden" name="checkingFormID" value="${checkingForm.checkingFormID}">

                    <h4>Available Staff</h4>
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <th>Select</th>
                                <th>Staff ID</th>
                                <th>Full Name</th>
                                <th>Phone</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="staff" items="${availableSurveyStaff}">
                                <tr>
                                    <td><input type="radio" name="staffID" value="${staff.staffID}" required></td>
                                    <td>${staff.staffID}</td>
                                    <td>${staff.fullName}</td>
                                    <td>${staff.phone}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>

                    <button type="submit" class="btn btn-primary">Confirm</button>
                </form>
            </div>
        </div>
    </div>

    <jsp:include page="/frontend/common/footer.jsp" />
</body>
</html>
