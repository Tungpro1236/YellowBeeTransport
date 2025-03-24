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
        border-radius: 10px;
        height: fit-content;
        box-shadow: 2px 2px 10px rgba(0, 0, 0, 0.2); /* Đổ bóng */
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
        border-radius: 8px;
        transition: background 0.3s ease-in-out, transform 0.2s ease-in-out;
    }
    .sidebar ul li a:hover {
        background: #1abc9c;
        transform: scale(1.05); /* Nhấn nhẹ khi hover */
    }

    /* Dropdown menu */
    .dropdown {
        position: relative;
    }

    .dropdown-content {
        position: absolute;
        left: 100%; /* Dịch sang phải */
        top: 0;
        min-width: 150px;
        background: #f39c12;
        border-radius: 8px;
        overflow: hidden;
        z-index: 1;
        text-align: center;
        box-shadow: 2px 2px 10px rgba(0, 0, 0, 0.2); /* Đổ bóng */

        /* Hiệu ứng ẩn */
        opacity: 0;
        transform: translateY(10px);
        transition: opacity 0.3s ease-in-out, transform 0.3s ease-in-out;
        visibility: hidden;
    }

    .dropdown-content a {
        padding: 10px;
        display: block;
        color: white;
        text-decoration: none;
        white-space: nowrap; /* Giữ nội dung không xuống dòng */
        text-align: center;
        transition: background 0.3s ease-in-out;
    }

    .dropdown-content a:hover {
        background: #e67e22;
    }

    /* Hiển thị dropdown khi hover */
    .dropdown:hover .dropdown-content {
        opacity: 1;
        transform: translateY(0);
        visibility: visible;
    }

</style>
