<%-- 
    Document   : customer_feedback
    Created on : Mar 16, 2025, 1:43:59 PM
    Author     : regio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Phản hồi</title>
</head>
<body>
    <h2>Đánh giá & Phản hồi</h2>
    
    <form action="submitFeedback" method="post">
        <label for="rating">Đánh giá:</label>
        <select name="rating" id="rating">
            <option value="5">Rất tốt</option>
            <option value="4">Tốt</option>
            <option value="3">Bình thường</option>
            <option value="2">Kém</option>
            <option value="1">Rất kém</option>
        </select>

        <br>
        <label for="comments">Nhận xét:</label>
        <textarea name="comments" id="comments" rows="4" cols="50"></textarea>

        <br>
        <input type="submit" value="Gửi đánh giá">
    </form>
</body>
</html>
