<%-- 
    Document   : managerDashboard
    Created on : Feb 18, 2025, 8:58:25 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="x-ua-compatible" content="ie=edge">
        <title>Manager Dashboard - Yellow Bee Transport</title>
        <meta name="description" content="Manager Dashboard for Yellow Bee Transport">
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

        <!-- Manager Dashboard Area -->
        <div class="manager_dashboard_area">
            <div class="container">
                <div class="row">
                    <div class="col-xl-12 text-center">
                        <h3>Welcome to the Manager Dashboard</h3>
                        <p>Manage your transport services effectively and efficiently.</p>
                    </div>
                </div>

                <div class="dashboard_functionality">
                    <div class="row">
                        
                        <!-- Manage Checking Forms -->
                        <div class="col-md-4">
                            <div class="dashboard_card">
                                <h4>Manage Checking Forms</h4>
                                <p>List of survey requests. Approve and assign staff.</p>
                                <a href="${pageContext.request.contextPath}/ManageCheckingForms" class="boxed-btn3">Manage Checking Forms</a>
                            </div>
                        </div>

                        <!-- Manage Price Quotes -->
                        <div class="col-md-4">
                            <div class="dashboard_card">
                                <h4>Manage Price Quotes</h4>
                                <p>Review and approve price quotes from surveys.</p>
                                <a href="${pageContext.request.contextPath}/ManagePriceQuotes" class="boxed-btn3">Manage Price Quotes</a>
                            </div>
                        </div>
                            
                            <!-- Manage Contracts -->
                            <div class="col-md-4">
                                <div class="dashboard_card">
                                <h4>Manage Contracts</h4>
                                <p>Create, update, and send contracts to customers.</p>
                                <a href="${pageContext.request.contextPath}/ManageContracts" class="boxed-btn3">Manage Contracts</a>
                            </div>
                            </div>
                    </div>

                    <div class="row mt-4">
                       
                        <!-- Manage Staff & Vehicles -->
                        <div class="col-md-4">
                            <div class="dashboard_card">
                                <h4>Manage Staff & Vehicles</h4>
                                <p>Manage employees and vehicles to contracts.</p>
                                <a href="${pageContext.request.contextPath}/ManageStaffVehicles" class="boxed-btn3">Manage Staff & Vehicles</a>
                            </div>
                        </div>

                        <!-- Manage Transport Problems -->
                        <div class="col-md-4">
                            <div class="dashboard_card">
                                <h4>Manage Transport Problems</h4>
                                <p>Handle reports of transport issues and compensations.</p>
                                <a href="${pageContext.request.contextPath}/ManageTransportProblems" class="boxed-btn3">Manage Transport Problems</a>
                            </div>
                        </div>
                            
                             <!-- Reports & Analytics -->
                             <div class="col-md-4">
                                 <div class="dashboard_card">
                                <h4>Reports & Analytics</h4>
                                <p>View revenue reports, performance statistics, and compensations.</p>
                                <a href="${pageContext.request.contextPath}/ReportsAnalytics" class="boxed-btn3">View Reports</a>
                            </div>
                             </div>
                    </div>
                </div>
            </div>
        </div>

        <jsp:include page="/frontend/common/footer.jsp" />

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