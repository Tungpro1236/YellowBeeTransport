<%-- 
    Document   : confirmContract
    Created on : Mar 23, 2025, 10:47:36 PM
    Author     : admin
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.List"%>
<%@page import="Model.PriceQuote"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>Confirm Contract</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    </head>

    <body>
        <jsp:include page="/frontend/common/header.jsp" />

        <div class="container mt-4">
            <div class="row">
                <!-- Sidebar -->
                <div class="col-md-3">
                    <jsp:include page="/frontend/common/sidebar.jsp" />
                </div>

                <!-- Main Content -->
                <div class="col-md-9">
                    <h3 class="text-center">Confirm Contract for Price Quote #${priceQuoteID}</h3>
                    <form method="post" action="ManagePriceQuotes">
                        <input type="hidden" name="action" value="confirm">
                        <input type="hidden" name="priceQuoteID" value="${priceQuoteID}">
                        
                        <h5>Checking Form ID: ${checkingFormID}</h5>

                        <h5>Required Trucks: ${truckAmount}</h5>
                        <h5>Required Staff: ${staffAmount}</h5>
                        <h5>Final Cost: ${finalCost}</h5>

                        <h4>Available Trucks</h4>
                        <table class="table table-bordered">
                            <thead>
                                <tr>
                                    <th>Select</th>
                                    <th>Truck ID</th>
                                    <th>License Plate</th>
                                    <th>Truck Payload</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="truck" items="${availableTrucks}">
                                    <tr>
                                        <td><input type="checkbox" name="selectedTrucks" value="${truck.truckID}"></td>
                                        <td>${truck.truckID}</td>
                                        <td>${truck.licensePlate}</td>
                                        <td>${truck.truckPayload}</td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>

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
                                <c:forEach var="staff" items="${availableStaff}">
                                    <tr>
                                        <td><input type="checkbox" name="selectedStaff" value="${staff.userID}"></td>
                                        <td>${staff.userID}</td>
                                        <td>${staff.fullName}</td>
                                        <td>${staff.phone}</td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>

                        <button type="submit" class="btn btn-primary">Confirm Contract</button>
                    </form>
                </div>
            </div>
        </div>

        <jsp:include page="/frontend/common/footer.jsp" />
        
        <script src="${pageContext.request.contextPath}/js/jquery-1.12.4.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>
