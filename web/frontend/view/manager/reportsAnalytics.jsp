<%-- 
    Document   : reportAnalytics
    Created on : Mar 4, 2025, 9:12:33 AM
    Author     : admin
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.Map" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>Report & Analytics</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="shortcut icon" type="image/x-icon" href="${pageContext.request.contextPath}/img/logo_com.png">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <style>
        /* Tô đậm tiêu đề bảng và thêm khoảng cách giữa các bảng */
        .table {
            margin-bottom: 40px;
        }
        .table {
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
                <h3 class="text-center">Report & Analytics</h3>
                
                <!-- Checking Forms Overview -->
                <h4>Checking Forms Overview</h4>
                <table class="table">
                    <thead>
                        <tr>
                            <th>Status</th>
                            <th>Total</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>Pending Checking Forms</td>
                            <td><c:out value="${totalPendingCheckingForms}" default="0"/></td>
                        </tr>
                        <tr>
                            <td>Approved Checking Forms</td>
                            <td><c:out value="${totalApprovedCheckingForms}" default="0"/></td>
                        </tr>
                    </tbody>
                </table>
                
                <!-- Contracts Overview -->
                <h4>Contracts Overview</h4>
                <table class="table">
                    <thead>
                        <tr>
                            <th>Status</th>
                            <th>Total</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>Pending Contracts</td>
                            <td><c:out value="${totalPendingContracts}" default="0"/></td>
                        </tr>
                        <tr>
                            <td>Completed Contracts</td>
                            <td><c:out value="${totalCompletedContracts}" default="0"/></td>
                        </tr>
                    </tbody>
                </table>
                
                <!-- Transport Problems Overview -->
                <h4>Transport Problems Overview</h4>
                <table class="table">
                    <thead>
                        <tr>
                            <th>Total Unresolved Transport Problems</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td><c:out value="${totalProblemsUnresolved}" default="0"/></td>
                        </tr>
                    </tbody>
                </table>
                
                <!-- Revenue Report --> 
                <h4>Revenue Report</h4>
                <form method="post" action="ReportsAnalytics">
                    <label>Start Date:</label>
                    <input type="date" name="startDate" required>
                    <label>End Date:</label>
                    <input type="date" name="endDate" required>
                    <button type="submit">Get Revenue</button>
                </form>
                <c:if test="${not empty totalRevenue}">
                    <p>Total Revenue: $<c:out value="${totalRevenue}"/></p>
                </c:if>
            </div>
        </div>
    </div>
    <jsp:include page="/frontend/common/footer.jsp" />
</body>
</html>
