<%-- 
    Document   : customer_taskbar
    Created on : Mar 16, 2025, 3:34:16 PM
    Author     : regio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="sidebar">
    <ul>
        <li><a href="${pageContext.request.contextPath}/customer_dashboard">Overview</a></li>
        <li><a href="${pageContext.request.contextPath}/customer_contracts">Contracts</a></li>      

        <li class="dropdown">
            <a href="javascript:void(0)" class="dropbtn">Request & list quotes</a>
            <div class="dropdown-content">
                <a href="${pageContext.request.contextPath}/customer_request">Request</a>
                <a href="${pageContext.request.contextPath}/customer_priceQuote">List Quote</a>
            </div>
        </li>

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
        position: relative;
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

    /* Dropdown menu */
     .dropdown {
        position: relative;
    }

    .dropdown-content {
        display: none;
        position: absolute;
        left: 100%; /* Dịch sang phải */
        top: 10px; /* Cách menu chính một chút */
        min-width: 150px;
        background: #f39c12;
        border-radius: 5px;
        overflow: hidden;
        z-index: 1;
        text-align: center;
    }

    .dropdown-content a {
        padding: 10px;
        display: block;
        color: white;
        text-decoration: none;
        white-space: nowrap; /* Giữ nội dung không xuống dòng */
        text-align: center;
    }

    .dropdown-content a:hover {
        background: #e67e22;
    }

    /* Hiển thị dropdown khi hover */
    .dropdown:hover .dropdown-content {
        display: block;
    }
</style>
