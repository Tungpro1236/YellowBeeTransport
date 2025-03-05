<%-- 
    Document   : admin_taskbar
    Created on : Mar 2, 2025, 7:02:10 PM
    Author     : nguye
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="sidebar">
    <ul>
        <li><a href="${pageContext.request.contextPath}/admin_dashboard">Dashboard</a></li>
        <li><a href="${pageContext.request.contextPath}/admin_user">Manage User</a></li>
        <li><a href="${pageContext.request.contextPath}/admin_accounts">Manage Accounts</a></li>
        <li><a href="${pageContext.request.contextPath}/ManageContracts">Manage Price</a></li>
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
    .sidebar h3 {
        text-align: center;
        margin-bottom: 15px;
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
        background:  #FFA500;
        border-radius: 5px;
        transition: 0.3s;
    }
    .sidebar ul li a:hover {
        background: #1abc9c;
    }
</style>