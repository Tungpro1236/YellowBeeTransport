<%-- 
    Document   : Manage Staff & Vehicles
    Created on : Mar 4, 2025, 9:12:33 AM
    Author     : admin
--%>
<%@page import="java.util.List, Model.Staff, Model.Truck" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="x-ua-compatible" content="ie=edge">
        <title>Manage Staff & Vehicles</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <link rel="shortcut icon" type="image/x-icon" href="${pageContext.request.contextPath}/img/logo_com.png">

        <!-- CSS here -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
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
                    <h3 class="text-center">Manage Staff & Vehicles</h3>
                    
                    <!-- Survey Staff Table -->
                    <h4>Survey Staff</h4>
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <th>Staff ID</th>
                                <th>Name</th>
                                <th>Phone</th>
                                <th>Email</th>
                                <th>Current Checking Form</th>
                            </tr>
                        </thead>
                        <tbody>
                            <% 
                                List<Staff> surveyStaffList = (List<Staff>) request.getAttribute("surveyStaffList");
                                if (surveyStaffList != null && !surveyStaffList.isEmpty()) {
                                    for (Staff staff : surveyStaffList) {
                            %>
                            <tr>
                                <td><%= staff.getStaffID() %></td>
                                <td><%= staff.getFullName() %></td>
                                <td><%= staff.getPhone() %></td>
                                <td><%= staff.getEmail() %></td>
                                <td><%= (staff.getCurrentCheckingFormID() != null) ? "CheckingForm #" + staff.getCurrentCheckingFormID() : "None" %></td>
                            </tr>
                            <% 
                                    }
                                } else { 
                            %>
                            <tr>
                                <td colspan="7" class="text-center">No survey staff available</td>
                            </tr>
                            <% } %>
                        </tbody>
                    </table>

                    <!-- Moving Staff Table -->
                    <h4>Moving Staff</h4>
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <th>Staff ID</th>
                                <th>Name</th>
                                <th>Phone</th>
                                <th>Email</th>
                                <th>Current Contract</th>
                            </tr>
                        </thead>
                        <tbody>
                            <% 
                                List<Staff> movingStaffList = (List<Staff>) request.getAttribute("movingStaffList");
                                if (movingStaffList != null && !movingStaffList.isEmpty()) {
                                    for (Staff staff : movingStaffList) {
                            %>
                            <tr>
                                <td><%= staff.getStaffID() %></td>
                                <td><%= staff.getFullName() %></td>
                                <td><%= staff.getPhone() %></td>
                                <td><%= staff.getEmail() %></td>
                                <td><%= (staff.getCurrentContractID() != null) ? "Contract #" + staff.getCurrentContractID() : "None" %></td>
                            </tr>
                            <% 
                                    }
                                } else { 
                            %>
                            <tr>
                                <td colspan="7" class="text-center">No moving staff available</td>
                            </tr>
                            <% } %>
                        </tbody>
                    </table>

                    <!-- Trucks Table -->
                    <h4>Trucks List</h4>
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <th>Truck ID</th>
                                <th>License Plate</th>
                                <th>Truck Type</th>
                                <th>Current Contract</th>
                            </tr>
                        </thead>
                        <tbody>
                            <% 
                                List<Truck> truckList = (List<Truck>) request.getAttribute("truckList");
                                if (truckList != null && !truckList.isEmpty()) {
                                    for (Truck truck : truckList) {
                            %>
                            <tr>
                                <td><%= truck.getTruckID() %></td>
                                <td><%= truck.getLicensePlate() %></td>
                                <td><%= truck.getTruckTypeID() %></td>
                                <td><%= (truck.getCurrentContractID() != null) ? "Contract #" + truck.getCurrentContractID() : "None" %></td>
                            </tr>
                            <% 
                                    }
                                } else { 
                            %>
                            <tr>
                                <td colspan="4" class="text-center">No trucks available</td>
                            </tr>
                            <% } %>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>

        <jsp:include page="/frontend/common/footer.jsp" />

        <script src="${pageContext.request.contextPath}/js/vendor/jquery-1.12.4.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/main.js"></script>
    </body>
</html>
