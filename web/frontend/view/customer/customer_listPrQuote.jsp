<%-- 
    Document   : customer_price_quote_history
    Created on : Mar 19, 2025, 3:10:39 PM
    Author     : regio
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="x-ua-compatible" content="ie=edge">
        <title>Yellow Bee Transport - Quote History</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="shortcut icon" type="image/x-icon" href="img/logo_com.png">

        <!-- CSS -->
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/style.css">
    </head>
    <body>
        <jsp:include page="/frontend/common/header.jsp" />

        <div class="container-fluid mt-3">
            <div class="row">
                <!-- Sidebar -->
                <div class="col-md-3">
                    <jsp:include page="/frontend/common/customer_taskbar.jsp" />
                </div>

                <div class="col-md-9">
                    <h2 class="text-center"> List Quote </h2>
                    <table class="table table-bordered table-hover mt-3">
                        <thead style="background-color: #fdb813; color: white;">
                            <tr>
                                <th>Quote ID</th>
                                <th>Price</th>
                                <th>Status</th>
                                <th>Action</th>
                            </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="quote" items="${quotes}">
                            <tr>
                                <td>${quote.quoteId}</td>
                                <td>${quote.price} VND</td>
                                <td>
                                    <span class="badge badge-${quote.status == 'pending' ? 'warning' : (quote.status == 'accepted' ? 'success' : 'danger')}">
                                        ${quote.status}
                                    </span>
                                </td>
                                <td>
                            <c:if test="${quote.status == 'pending'}">
                                <form action="customer_priceQuote" method="post" class="d-inline">
                                    <input type="hidden" name="quoteId" value="${quote.quoteId}">
                                    <input type="hidden" name="status" value="accepted">
                                    <button type="submit" class="btn btn-success btn-sm">Accept</button>
                                </form>
                                <form action="customer_priceQuote" method="post" class="d-inline">
                                    <input type="hidden" name="quoteId" value="${quote.quoteId}">
                                    <input type="hidden" name="status" value="rejected">
                                    <button type="submit" class="btn btn-danger btn-sm">Reject</button>
                                </form>
                            </c:if>
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