<%-- 
    Document   : sidebar
    Created on : Mar 2, 2025, 3:19:02 AM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="sidebar">
    <ul>
        <li><a href="${pageContext.request.contextPath}/ManagerDashboard">Dashboard</a></li>
        <li><a href="${pageContext.request.contextPath}/ManageCheckingForms">Manage Checking Forms</a></li>
        <li><a href="${pageContext.request.contextPath}/ManagePriceQuotes">Manage Price Quotes</a></li>
        <li><a href="${pageContext.request.contextPath}/ManageContracts">Manage Contracts</a></li>
        <li><a href="${pageContext.request.contextPath}/ManageStaffVehicles">Manage Staff & Vehicles</a></li>
        <li><a href="${pageContext.request.contextPath}/ManageTransportProblems">Manage Transport Problems</a></li>
        <li><a href="${pageContext.request.contextPath}/ReportsAnalytics">Reports & Analytics</a></li>
    </ul>
</div>

<style>
    .sidebar {
        width: 250px;
        background: #2c3e50;
        padding: 10px;
        color: white;
        border-radius: 5px;
        height: fit-content;
    }
    .sidebar ul {
        list-style: none;
        padding: 0;
    }
    .sidebar ul li {
        margin: 10px 0;
    }
    .sidebar ul li a {
        color: white;
        text-decoration: none;
        display: block;
        padding: 10px;
        background: #FFA500;
        border-radius: 5px;
        transition: 0.3s;
    }
    .sidebar ul li a:hover {
        background: #1abc9c;
    }
</style>
