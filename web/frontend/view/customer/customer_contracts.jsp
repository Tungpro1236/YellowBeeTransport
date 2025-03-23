<%-- 
    Document   : customer_contracts
    Created on : Mar 16, 2025, 1:42:02 PM
    Author     : regio
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>

<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="x-ua-compatible" content="ie=edge">
        <title>Yellow Bee Transport - Contracts List</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="shortcut icon" type="image/x-icon" href="img/logo_com.png">

        <!-- CSS -->
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/style.css">
        <style>
            .thead-custom {
                background-color: #fdb813 !important;
                color: white;
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
                    <form action="customer_contracts" method="get" class="mb-3 d-flex align-items-center">
                        <label for="status" class="mr-2">Lọc theo trạng thái:</label>
                        <select name="status" id="status" >
                            <option value="1">Tất cả</option>
                            <option value="2">Đang chuẩn bị</option>
                            <option value="3">Đang vận chuyển</option>
                            <option value="4">Hoàn tất</option>
                        </select>
                        <button type="submit" class="btn btn-primary ml-2">Lọc</button>
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
                            <c:forEach var="contract" items="${contracts}">
                                <tr>
                                    <td>${contract.contractId}</td>
                                    <td>${contract.checkingForm.checkingTime}</td>
                                    <td>${contract.finalCost} VND</td>
                                    <td><a href="contract_detail.jsp?contractId=${contract.contractId}">Xem chi tiết</a></td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${contract.contractStatusId == 1}">
                                                <span class="badge badge-warning">Đang chuẩn bị</span>
                                            </c:when>
                                            <c:when test="${contract.contractStatusId == 2}">
                                                <span class="badge badge-primary">Đang vận chuyển</span>
                                            </c:when>
                                            <c:when test="${contract.contractStatusId == 3}">
                                                <span class="badge badge-success">Hoàn tất</span>
                                            </c:when>
                                            <c:otherwise>
                                                <span class="badge badge-secondary">Không xác định</span>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                </tr>
                            </c:forEach>
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