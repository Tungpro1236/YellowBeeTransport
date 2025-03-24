<%-- 
    Document   : manageContracts
    Created on : Mar 1, 2025, 8:37:39 AM
    Author     : admin
--%>

<%@ page import="java.util.List" %>
<%@ page import="Model.Contract" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="x-ua-compatible" content="ie=edge">
        <title>Manage Contracts</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <link rel="shortcut icon" type="image/x-icon" href="${pageContext.request.contextPath}/img/logo_com.png">

        <!-- CSS here -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/owl.carousel.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/magnific-popup.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/font-awesome.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/themify-icons.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/nice-select.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/flaticon.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/gijgo.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/animate.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/slick.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/slicknav.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">

            </head>

    
    <body>
    <jsp:include page="/frontend/common/header.jsp" />

    <div class="container mt-4">
        <div class="row">
            <div class="col-md-3">
                <jsp:include page="/frontend/common/sidebar.jsp" />
            </div>

            <div class="col-md-9">
                <h3 class="text-center">Manage Contracts</h3>
                <table class="table table-bordered">
                    <thead>
                        <tr>
                            <th>Contract ID</th>
                            <th>Checking Form ID</th>
                            <th>Price Quote ID</th>
                            <th>Truck ID</th>
                            <th>Staff ID</th>
                            <th>Final Cost</th>
                            <th>Contract Status ID</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            List<Contract> contracts = (List<Contract>) request.getAttribute("contracts");
                            if (contracts != null) {
                                for (Contract contract : contracts) {
                        %>
                        <tr>
                            <td><%= contract.getContractID() %></td>
                            <td><%= contract.getCheckingFormID() %></td>
                            <td><%= contract.getPriceQuoteID() %></td>
                            <td><%= contract.getTruckID() %></td>
                            <td><%= contract.getStaffID() %></td>
                            <td><%= contract.getFinalCost() %></td>
                            <td><%= contract.getContractStatusID() %></td>
                        </tr>
                        <% 
                                }
                            } else { 
                        %>
                        <tr>
                            <td colspan="7" class="text-center">No contracts available</td>
                        </tr>
                        <% } %>
                    </tbody>
                </table>
            </div>
        </div>
    </div>


        <jsp:include page="/frontend/common/footer.jsp" />

         <script>
        function confirmDelete(contractID) {
            if (confirm("Are you sure you want to cancel this contract?")) {
                document.getElementById("cancelForm" + contractID).submit();
            }
        }
    </script>


        
        <script src="${pageContext.request.contextPath}/js/vendor/modernizr-3.5.0.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/vendor/jquery-1.12.4.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/popper.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/owl.carousel.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/isotope.pkgd.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/ajax-form.js"></script>
        <script src="${pageContext.request.contextPath}/js/waypoints.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/jquery.counterup.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/imagesloaded.pkgd.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/scrollIt.js"></script>
        <script src="${pageContext.request.contextPath}/js/jquery.scrollUp.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/wow.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/nice-select.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/jquery.slicknav.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/jquery.magnific-popup.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/plugins.js"></script>
        <script src="${pageContext.request.contextPath}/js/slick.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/main.js"></script>
    </body>
</html>