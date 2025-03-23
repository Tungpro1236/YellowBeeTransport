<%-- 
    Document   : customer_contractDetail
    Created on : Mar 23, 2025, 12:25:42 PM
    Author     : regio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="x-ua-compatible" content="ie=edge">
        <title>Chi tiết hợp đồng</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/style.css">
    </head>
    <body>
        <jsp:include page="/frontend/common/header.jsp" />

        <div class="container mt-4">
            <h2 class="text-center">Contracts Detail</h2>

            <div class="card p-3">
                <div class="card-body">
                    <h4>Overall Info</h4>
                    <p><strong>Contracts ID:</strong> ${contract.contractId}</p>
                    <p><strong>contracts created time:</strong> ${contract.checkingForm.checkingTime}</p>
                    <p><strong>Status:</strong> ${contract.status}</p>
                    <p><strong>Overall cost:</strong> ${contract.finalCost} VND</p>
                </div>
            </div>
            <div class="card p-3 mt-3">
                <div class="card-body">
                    <h4>Quote Info</h4>
                    <p><strong>Survey Staff :</strong> ${contract.surveyStaff}</p>
                    <p><strong>Problem cost:</strong> ${contract.problemCost} VND</p>  
                    <p><strong>Final cost:</strong> ${contract.finalCost} VND</p>
                </div>
            </div>

            <div class="card p-3 mt-3">
                <div class="card-body">
                    <h4>Transport Info</h4>
                    <p><strong>Truck:</strong> ${contract.licensePlate}</p>
                    <p><strong>Moving staff:</strong> ${contract.movingStaff}</p>
                    <p><strong>Transport time:</strong> ${contract.checkingForm.transportTime}</p>
                    <p><strong>Status of Commodities:</strong> 
                        <c:choose>
                            <c:when test="${contract.problemCost > 0}">️Have damaged</c:when>
                            <c:otherwise>No damaged</c:otherwise>
                        </c:choose>
                </div>
            </div>
        </div>

        <div class="mt-5">
            <jsp:include page="/frontend/common/footer.jsp" />
        </div>

        <script src="js/jquery-1.12.4.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/main.js"></script>
    </body>
</html>
