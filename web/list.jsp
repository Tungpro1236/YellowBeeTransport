<%-- 
    Document   : list
    Created on : Mar 23, 2025, 5:25:01 PM
    Author     : Admin
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Price Quotes</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-4">
        <h1>Price Quotes</h1>
        <a href="${pageContext.request.contextPath}/price-quote/create" class="btn btn-primary mb-3">Create New Price Quote</a>
        
        <table class="table table-striped">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Truck Amount</th>
                    <th>Staff Amount</th>
                    <th>Final Cost</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="priceQuote" items="${priceQuotes}">
                    <tr>
                        <td>${priceQuote.priceQuoteID}</td>
                        <td>${priceQuote.truckAmount}</td>
                        <td>${priceQuote.staffAmount}</td>
                        <td>${priceQuote.finalCost}</td>
                        <td>
                            <a href="${pageContext.request.contextPath}/price-quote/edit/${priceQuote.priceQuoteID}" 
                               class="btn btn-sm btn-warning">Edit</a>
                            <button onclick="deletePriceQuote(${priceQuote.priceQuoteID})" 
                                    class="btn btn-sm btn-danger">Delete</button>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
    <script>
        function deletePriceQuote(id) {
            if (confirm('Are you sure you want to delete this price quote?')) {
                fetch('${pageContext.request.contextPath}/price-quote/' + id, {
                    method: 'DELETE'
                }).then(response => {
                    if (response.ok) {
                        window.location.reload();
                    } else {
                        alert('Error deleting price quote');
                    }
                });
            }
        }
    </script>
</body>
</html> 
