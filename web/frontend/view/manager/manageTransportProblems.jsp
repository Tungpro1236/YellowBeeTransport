<%-- 
    Document   : manageTransportProblems
    Created on : Mar 23, 2025
    Author     : admin
--%>
<%@ page import="java.util.List" %>
<%@ page import="Model.TransportProblemForm" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="x-ua-compatible" content="ie=edge">
        <title>Manage Transport Problems</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <link rel="shortcut icon" type="image/x-icon" href="${pageContext.request.contextPath}/img/logo_com.png">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    </head>
    
    <body>
        <jsp:include page="/frontend/common/header.jsp" />

        <div class="container mt-4">
            <div class="row">
                <div class="col-md-3">
                    <jsp:include page="/frontend/common/sidebar.jsp" />
                </div>
                <div class="col-md-9">
                    <h3 class="text-center">Manage Transport Problems</h3>
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Contract ID</th>
                                <th>Description</th>
                                <th>Problem Cost</th>
                                <th>Staff ID</th>
                                <th>Report Date</th>
                                <th>Status</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            <% 
                                List<TransportProblemForm> problemList = (List<TransportProblemForm>) request.getAttribute("problemList");
                                if (problemList != null) {
                                    for (TransportProblemForm problem : problemList) {
                            %>
                            <tr>
                                <td><%= problem.getTpfID() %></td>
                                <td><%= problem.getContractID() %></td>
                                <td><%= problem.getProblemDescription() %></td>
                                <td><%= problem.getProblemCost() %></td>
                                <td><%= problem.getStaffID() %></td>
                                <td><%= problem.getReportDate() %></td>
                                <td><%= problem.getStatus() %></td>
                                <td>
                                    <form method="post" action="ManageTransportProblems">
                                        <input type="hidden" name="problemID" value="<%= problem.getTpfID() %>">
                                        <button type="submit" name="action" value="compensate" class="btn btn-success">Compensate</button>
                                        <button type="submit" name="action" value="cancel" class="btn btn-danger">Cancel</button>
                                    </form>
                                </td>
                            </tr>
                            <% 
                                    } 
                                } else { 
                            %>
                            <tr>
                                <td colspan="8" class="text-center">No transport problems available</td>
                            </tr>
                            <% } %>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>

        <jsp:include page="/frontend/common/footer.jsp" />

        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/main.js"></script>
    </body>
</html>

