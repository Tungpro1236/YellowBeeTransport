<%-- 
    Document   : reportAnalytics
    Created on : Mar 4, 2025, 9:12:33 AM
    Author     : admin
--%>
<%@ page import="java.util.Map" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>Report & Analytics</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">

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
                <h3 class="text-center">Report & Analytics</h3>
                
                <h4>Contract Status Overview</h4>
                <table class="table table-bordered">
                    <thead>
                        <tr>
                            <th>Status</th>
                            <th>Count</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% 
                            Map<String, Integer> contractStatusCounts = (Map<String, Integer>) request.getAttribute("contractStatusCounts");
                            if (contractStatusCounts != null) {
                                for (Map.Entry<String, Integer> entry : contractStatusCounts.entrySet()) {
                        %>
                        <tr>
                            <td><%= entry.getKey() %></td>
                            <td><%= entry.getValue() %></td>
                        </tr>
                        <% 
                                }
                            } else { 
                        %>
                        <tr>
                            <td colspan="2" class="text-center">No data available</td>
                        </tr>
                        <% } %>
                    </tbody>
                </table>
                
                <h4>Total Transport Problems: <%= request.getAttribute("totalProblems") %></h4>
                
                <h4>Top Problem Solvers</h4>
                <table class="table table-bordered">
                    <thead>
                        <tr>
                            <th>Staff ID</th>
                            <th>Resolved Problems</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% 
                            Map<Integer, Integer> topProblemSolvers = (Map<Integer, Integer>) request.getAttribute("topProblemSolvers");
                            if (topProblemSolvers != null) {
                                for (Map.Entry<Integer, Integer> entry : topProblemSolvers.entrySet()) {
                        %>
                        <tr>
                            <td><%= entry.getKey() %></td>
                            <td><%= entry.getValue() %></td>
                        </tr>
                        <% 
                                }
                            } else { 
                        %>
                        <tr>
                            <td colspan="2" class="text-center">No data available</td>
                        </tr>
                        <% } %>
                    </tbody>
                </table>
                
                <h4>Revenue Report</h4>
                <form method="post" action="ReportsAnalytics">
                    <label>Start Date:</label>
                    <input type="date" name="startDate" required>
                    <label>End Date:</label>
                    <input type="date" name="endDate" required>
                    <button type="submit">Get Revenue</button>
                </form>
                <% if (request.getAttribute("totalRevenue") != null) { %>
                    <p>Total Revenue: $<%= request.getAttribute("totalRevenue") %></p>
                <% } %>
            </div>
        </div>
    </div>
    <jsp:include page="/frontend/common/footer.jsp" />
</body>
</html>

