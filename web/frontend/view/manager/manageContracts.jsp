<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.List" %>
<%@ page import="Model.Contract" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="x-ua-compatible" content="ie=edge">
        <title>Manage Contracts</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="shortcut icon" type="image/x-icon" href="${pageContext.request.contextPath}/img/logo_com.png">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
        <style>
            .report-table {
                margin-bottom: 20px;
            }
            .report-table th {
                font-weight: bold;
            }
        </style>
    </head>
    <body>
        <jsp:include page="/frontend/common/header.jsp" />
        <div class="container mt-4">
            <div class="row">
                <div class="col-md-3">
                    <jsp:include page="/frontend/common/sidebar.jsp" />
                </div>
                <div class="col-md-9">
                    <h3 class="text-center">Manage Contracts</h3>

                    <h4>Pending Contracts</h4>
                    <table class="table table-bordered report-table">
                        <thead>
                            <tr>
                                <th>Contract ID</th>
                                <th>Checking Form ID</th>
                                <th>Price Quote ID</th>
                                <th>Final Cost</th>
                                <th>Contract Date</th>
                                <th>Staff Assigned</th>
                                <th>Trucks Assigned</th>
                                <th>Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="contract" items="${pendingContracts}">
                                <tr>
                                    <td>${contract.contractId}</td>
                                    <td>${contract.checkingFormId}</td>
                                    <td>${contract.priceQuoteId}</td>
                                    <td>${contract.finalCost}</td>
                                    <td>${contract.contractDate}</td>
                                    <td>${contract.staffNames}</td>
                                    <td>${contract.licensePlates}</td>
                                    <td>
                                        <!-- Nút Completed -->
                                        <form method="post" action="ManageContracts" style="display:inline-block; margin-right:5px;">
                                            <input type="hidden" name="contractId" value="${contract.contractId}" />
                                            <input type="hidden" name="action" value="updateStatus" />
                                            <input type="hidden" name="newStatus" value="Completed" />
                                            <button type="submit" class="btn btn-success btn-sm" onclick="return confirm('Are you sure to mark this contract as Completed?');">Completed</button>
                                        </form>
                                        <!-- Nút Canceled -->
                                        <form method="post" action="ManageContracts" style="display:inline-block;">
                                            <input type="hidden" name="contractId" value="${contract.contractId}" />
                                            <input type="hidden" name="action" value="updateStatus" />
                                            <input type="hidden" name="newStatus" value="Cancelled" />
                                            <button type="submit" class="btn btn-warning btn-sm" onclick="return confirm('Are you sure to cancel this contract?');">Canceled</button>
                                        </form>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>


                    <!-- Completed Contracts Table -->
                    <h4>Completed Contracts</h4>
                    <table class="table table-bordered report-table">
                        <thead>
                            <tr>
                                <th>Contract ID</th>
                                <th>Checking Form ID</th>
                                <th>Price Quote ID</th>
                                <th>Final Cost</th>
                                <th>Contract Date</th>
                                <th>Staff Assigned</th>
                                <th>Trucks Assigned</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="contract" items="${completedContracts}">
                                <tr>
                                    <td>${contract.contractId}</td>
                                    <td>${contract.checkingFormId}</td>
                                    <td>${contract.priceQuoteId}</td>
                                    <td>${contract.finalCost}</td>
                                    <td>${contract.contractDate}</td>
                                    <td>${contract.staffNames}</td>
                                    <td>${contract.licensePlates}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>

                    <!-- Canceled Contracts Table -->
                    <h4>Canceled Contracts</h4>
                    <table class="table table-bordered report-table">
                        <thead>
                            <tr>
                                <th>Contract ID</th>
                                <th>Checking Form ID</th>
                                <th>Price Quote ID</th>
                                <th>Final Cost</th>
                                <th>Contract Date</th>
                                <th>Staff Assigned</th>
                                <th>Trucks Assigned</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="contract" items="${canceledContracts}">
                                <tr>
                                    <td>${contract.contractId}</td>
                                    <td>${contract.checkingFormId}</td>
                                    <td>${contract.priceQuoteId}</td>
                                    <td>${contract.finalCost}</td>
                                    <td>${contract.contractDate}</td>
                                    <td>${contract.staffNames}</td>
                                    <td>${contract.licensePlates}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>

                </div>
            </div>
        </div>
        <jsp:include page="/frontend/common/footer.jsp" />
        <script src="${pageContext.request.contextPath}/js/jquery-1.12.4.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>
