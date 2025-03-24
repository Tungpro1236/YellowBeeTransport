<%-- 
    Document   : customer_contracts
    Created on : Mar 16, 2025, 1:42:02 PM
    Author     : regio
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="x-ua-compatible" content="ie=edge">
        <title>Yellow Bee Transport - Contracts List</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="shortcut icon" type="image/x-icon" href="img/logo_com.png">

        <!-- CSS -->
        <

        <style>
            .thead-custom {
                background-color: #fdb813 !important;
                color: white;
            }
            .nice-select {
                display: none !important; /* Ẩn nice-select */
            }

            
        </style>
    </head>
    <body>
        <jsp:include page="/frontend/common/header.jsp" />

        <div class="container-fluid mt-3">
            <div class="row">
                <div class="col-md-3">
                    <jsp:include page="/frontend/common/customer_taskbar.jsp" />
                </div>
                <div class="col-md-9">
                    <h2 class="text-center">Contracts list</h2>
                    <form  action="customer_contracts" method="get" class="mb-3 d-flex align-items-center">
                        <label for="status" class="mr-2">Filter as Status:</label>
                        <select name="status" id="status" class="form-control w-auto">
                            <option value="1" ${param.status == 'all' ? 'selected' : ''}>All</option>
                            <option value="2" ${param.status == '1' ? 'selected' : ''}>Pending</option>
                            <option value="3" ${param.status == '2' ? 'selected' : ''}>Moving</option>
                            <option value="4" ${param.status == '3' ? 'selected' : ''}>Completed</option>
                        </select>
                        <button type="submit" class="btn btn-primary ml-2">Filter</button>
                    </form>

                    <table class="table table-bordered table-hover">
                        <thead class="thead-custom">
                            <tr>
                                <th>Contracts ID</th>
                                <th>Contracts created time</th>
                                <th>Final cost</th>
                                <th>Status</th>
                                <th>Detail</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:choose>
                                <c:when test="${empty contracts}">
                                    <tr>
                                        <td colspan="5" class="text-center text-danger">
                                            NO CONTRACTS FOUND!
                                        </td>
                                    </tr>
                                </c:when>
                                <c:otherwise>
                                    <c:forEach var="contract" items="${contracts}">
                                        <tr>
                                            <td>${contract.contractId}</td>
                                            <td><fmt:formatDate value="${contract.contractDate}" pattern="dd/MM/yyyy" /></td>
                                            <td>${contract.finalCost} VND</td>
                                            <td>
                                                <c:choose>
                                                    <c:when test="${contract.contractStatusId eq 1}">
                                                        <span class="badge badge-warning">Pending</span>
                                                    </c:when>
                                                    <c:when test="${contract.contractStatusId eq 2}">
                                                        <span class="badge badge-primary">Moving</span>
                                                    </c:when>
                                                    <c:when test="${contract.contractStatusId eq 3}">
                                                        <span class="badge badge-success">Completed</span>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <span class="badge badge-secondary">Non-Identified</span>
                                                    </c:otherwise>
                                                </c:choose>
                                            </td>
                                            <td><a href="customerDetail?contractId=${contract.contractId}" class="btn btn-info btn-sm">View detail</a></td>
                                        </tr>
                                    </c:forEach>
                                </c:otherwise>
                            </c:choose>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>

        <div class="mt-5">
            <jsp:include page="/frontend/common/footer.jsp" />
        </div>

        <!-- JS -->
        <script src="js/jquery-1.12.4.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/main.js"></script>
    </body>
</html>