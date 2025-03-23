<%-- 
    Document   : Manage Checking Forms
    Created on : Mar 4, 2025, 9:12:33 AM
    Author     : admin
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Date" %>
<%@ page import="Model.CheckingForm" %>
<%@ page import="Model.Staff" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="x-ua-compatible" content="ie=edge">
        <title>Manage Checking Forms</title>
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
                    <h3 class="text-center">Manage Checking Forms</h3>

                    <!-- Pending Checking Forms -->
                    <h4>Pending Checking Forms</h4>
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Customer ID</th>
                                <th>Name</th>
                                <th>Phone</th>
                                <th>Email</th>
                                <th>Address</th>
                                <th>Checking Time</th>
                                <th>Transport Time</th>
                                <th>Service ID</th>
                                <th>Status</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            <% List<CheckingForm> pendingForms = (List<CheckingForm>) request.getAttribute("pendingForms");
                               if (pendingForms != null) {
                                   for (CheckingForm form : pendingForms) { %>
                            <tr>
                                <td><%= form.getCheckingFormID() %></td>
                                <td><%= form.getCustomerID() %></td>
                                <td><%= form.getName() %></td>
                                <td><%= form.getPhone() %></td>
                                <td><%= form.getEmail() %></td>
                                <td><%= form.getAddress() %></td>
                                <td><%= form.getCheckingTime() %></td>
                                <td><%= form.getTransportTime() %></td>
                                <td><%= form.getServiceID() %></td>
                                <td><%= form.getStatus() %></td>               
                                <td>
                                    <!-- Approve Button -->
                                    <button onclick="showStaffSelection('<%= form.getCheckingFormID() %>')">Approve</button>
                                    <div id="staffSelect<%= form.getCheckingFormID() %>" style="display: none;">
                                        <form method="post" action="ManageCheckingForms">
                                            <input type="hidden" name="action" value="approve">
                                            <input type="hidden" name="checkingFormID" value="<%= form.getCheckingFormID() %>">

                                            <!-- Kiểm tra danh sách nhân viên có trống không -->
                                            <c:if test="${empty staffList}">
                                                <p style="color: red;">No staff available or staffList is null</p>
                                            </c:if>
                                                
                                            <select name="staffID" required>
                                                <c:choose>
                                                    <c:when test="${not empty staffList}">

                                                        <c:forEach var="staff" items="${staffList}">
                                                            <option value="${staff.staffID}">${staff.fullName}</option>
                                                        </c:forEach>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <option disabled>No available staff</option>
                                                    </c:otherwise>
                                                </c:choose>
                                            </select>
                                            <button type="submit">Confirm</button>
                                        </form>
                                    </div>
                                    <form method="post" action="ManageCheckingForms">
                                        <input type="hidden" name="action" value="reject">
                                        <input type="hidden" name="checkingFormID" value="<%= form.getCheckingFormID() %>">
                                        <button type="submit">Reject</button>
                                    </form>
                                </td>
                            </tr>
                            <% } } else { %>
                            <tr><td colspan="12" class="text-center">No pending forms available</td></tr>
                            <% } %>
                        </tbody>
                    </table>

                    <!-- Approved Checking Forms -->
                    <h4>Approved Checking Forms</h4>
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Customer ID</th>
                                <th>Name</th>
                                <th>Phone</th>
                                <th>Email</th>
                                <th>Address</th>
                                <th>Checking Time</th>
                                <th>Transport Time</th>
                                <th>Service ID</th>
                                <th>Assigned Staff ID</th>
                            </tr>
                        </thead>
                        <tbody>
                            <% List<CheckingForm> approvedForms = (List<CheckingForm>) request.getAttribute("approvedForms");
                               if (approvedForms != null) {
                                   for (CheckingForm form : approvedForms) { %>
                            <tr>
                                <td><%= form.getCheckingFormID() %></td>
                                <td><%= form.getCustomerID() %></td>
                                <td><%= form.getName() %></td>
                                <td><%= form.getPhone() %></td>
                                <td><%= form.getEmail() %></td>
                                <td><%= form.getAddress() %></td>
                                <td><%= form.getCheckingTime() %></td>
                                <td><%= form.getTransportTime() %></td>
                                <td><%= form.getServiceID() %></td>
                                <td><%= form.getAssignedStaffID() %></td>
                            </tr>
                            <% } } else { %>
                            <tr><td colspan="10" class="text-center">No approved forms available</td></tr>
                            <% } %>
                        </tbody>
                    </table>

                    <!-- Rejected Checking Forms -->
                    <h4>Rejected Checking Forms</h4>
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Customer ID</th>
                                <th>Name</th>
                                <th>Phone</th>
                                <th>Email</th>
                                <th>Address</th>
                                <th>Checking Time</th>
                                <th>Transport Time</th>
                                <th>Service ID</th>
                            </tr>
                        </thead>
                        <tbody>
                            <% List<CheckingForm> rejectedForms = (List<CheckingForm>) request.getAttribute("rejectedForms");
                               if (rejectedForms != null) {
                                   for (CheckingForm form : rejectedForms) { %>
                            <tr>
                                <td><%= form.getCheckingFormID() %></td>
                                <td><%= form.getCustomerID() %></td>
                                <td><%= form.getName() %></td>
                                <td><%= form.getPhone() %></td>
                                <td><%= form.getEmail() %></td>
                                <td><%= form.getAddress() %></td>
                                <td><%= form.getCheckingTime() %></td>
                                <td><%= form.getTransportTime() %></td>
                                <td><%= form.getServiceID() %></td>
                            </tr>
                            <% } } else { %>
                            <tr><td colspan="9" class="text-center">No rejected forms available</td></tr>
                            <% } %>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <jsp:include page="/frontend/common/footer.jsp" />

        <script>
            function showStaffSelection(checkingFormID) {
                var div = document.getElementById("staffSelect" + checkingFormID);
                if (div.style.display === "none") {
                    div.style.display = "block";
                } else {
                    div.style.display = "none";
                }
            }
        </script>

    </body>
</html>


