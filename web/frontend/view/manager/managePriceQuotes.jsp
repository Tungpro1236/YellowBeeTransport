<%-- 
    Document   : managePriceQuotes
    Created on : Mar 18, 2025, 8:52:40 AM
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
        <title>Manage Price Quotes</title>
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
                    <h3 class="text-center">Manage Price Quotes</h3>

                    <!-- PENDING PRICE QUOTES -->
                    <h4>Pending Price Quotes</h4>
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Checking Form</th>
                                <th>Trucks</th>
                                <th>Staff</th>
                                <th>Final Cost</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="quote" items="${priceQuotes}">
                                <tr>
                                    <td>${quote.priceQuoteID}</td>
                                    <td>${quote.checkingFormID}</td>
                                    <td>${quote.truckAmount}</td>
                                    <td>${quote.staffAmount}</td>
                                    <td>${quote.finalCost}</td>
                                    <td>
                                        <!-- Create Contract -->
                                        <form method="post" action="ManagePriceQuotes">
                                            <input type="hidden" name="action" value="create">
                                            <input type="hidden" name="priceQuoteID" value="${quote.priceQuoteID}">
                                            <input type="hidden" name="truckAmount" value="${quote.truckAmount}">
                                            <input type="hidden" name="staffAmount" value="${quote.staffAmount}">
                                            <button type="submit" class="btn btn-success btn-sm">Create Contract</button>
                                        </form>

                                        <!-- Cancel Price Quote -->
                                        <form method="post" action="ManagePriceQuotes">
                                            <input type="hidden" name="action" value="cancel">
                                            <input type="hidden" name="priceQuoteID" value="${quote.priceQuoteID}">
                                            <button type="submit" class="btn btn-warning btn-sm">Cancel</button>
                                        </form>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>

                    <!-- CANCELED PRICE QUOTES -->
                    <h4>Canceled Price Quotes</h4>
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Checking Form</th>
                                <th>Trucks</th>
                                <th>Staff</th>
                                <th>Final Cost</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="quote" items="${canceledQuotes}">
                                <tr>
                                    <td>${quote.priceQuoteID}</td>
                                    <td>${quote.checkingFormID}</td>
                                    <td>${quote.truckAmount}</td>
                                    <td>${quote.staffAmount}</td>
                                    <td>${quote.finalCost}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>

                    <!-- HIỂN THỊ DANH SÁCH CHỌN TRUCK VÀ STAFF -->
                    <c:if test="${not empty availableTrucks and not empty availableStaff}">
                        <h4>Select Trucks and Staff for Contract</h4>

                        <form method="post" action="ManagePriceQuotes">
                            <input type="hidden" name="action" value="confirm">
                            <input type="hidden" name="priceQuoteID" value="${priceQuoteID}">

                            <h5>Available Trucks</h5>
                            <table class="table table-bordered">
                                <thead>
                                    <tr>
                                        <th>Select</th>
                                        <th>Truck ID</th>
                                        <th>License Plate</th>
                                        <th>Capacity</th>
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

                            <h5>Available Staff</h5>
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
                    </c:if>


                </div>
            </div>
        </div>

        <jsp:include page="/frontend/common/footer.jsp" />

        <script src="${pageContext.request.contextPath}/js/jquery-1.12.4.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>
