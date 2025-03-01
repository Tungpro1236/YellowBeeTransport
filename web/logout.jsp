<%-- 
    Document   : logout
    Created on : Feb 23, 2025, 2:36:43 PM
    Author     : regio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    // Hủy session của người dùng
    session.invalidate();
    
    // Chuyển hướng về trang login hoặc homepage
    response.sendRedirect("homepage");
%>

