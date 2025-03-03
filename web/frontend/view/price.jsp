<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="Model.PriceCost" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Yellow Bee Transport - Price List</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <style>
        table {
            width: 80%;
            border-collapse: collapse;
            margin: 50px auto;
        }
        table, th, td {
            border: 1px solid #ccc;
        }
        th, td {
            padding: 10px;
            text-align: center;
        }
        th {
            background-color: #f4b400;
            color: white;
        }
        tr:nth-child(even) {
            background-color: #f9f9f9;
        }
        tr:hover {
            background-color: #f1f1f1;
        }
    </style>
</head>
<body>

<jsp:include page="/frontend/common/header.jsp" />

<div class="container">
    <h3 class="text-center mt-4">Removal Price</h3>

    <% String error = (String) request.getAttribute("error"); %>
    <% if (error != null) { %>
        <p class="text-center text-danger"><%= error %></p>
    <% } else { %>

        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Describe</th>
                    <th>Price</th>
                </tr>
            </thead>
            <tbody>
                <% 
                    List<PriceCost> listCost = (List<PriceCost>) request.getAttribute("listCost");
                    if (listCost != null) {
                        for (PriceCost item : listCost) { 
                %>
                    <tr>
                        <td><%= item.getPriceCostID() %></td>
                        <td><%= item.getDescription() %></td>
                        <td><%= item.getCost() %></td>
                    </tr>
                <% 
                        }
                    } 
                %>
            </tbody>
        </table>
    <% } %>
</div>

<jsp:include page="/frontend/common/footer.jsp" />

</body>
</html>
