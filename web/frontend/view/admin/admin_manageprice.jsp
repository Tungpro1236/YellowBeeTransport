<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="Model.PriceCost" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="x-ua-compatible" content="ie=edge">
        <title>Yellow Bee Transport</title>
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <!-- <link rel="manifest" href="site.webmanifest"> -->
        <link rel="shortcut icon" type="image/x-icon" href="img/logo_com.png">
        <!-- Place favicon.ico in the root directory -->

        <!-- CSS here -->
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/owl.carousel.min.css">
        <link rel="stylesheet" href="css/magnific-popup.css">
        <link rel="stylesheet" href="css/font-awesome.min.css">
        <link rel="stylesheet" href="css/themify-icons.css">
        <link rel="stylesheet" href="css/nice-select.css">
        <link rel="stylesheet" href="css/flaticon.css">
        <link rel="stylesheet" href="css/gijgo.css">
        <link rel="stylesheet" href="css/animate.css">
        <link rel="stylesheet" href="css/slick.css">
        <link rel="stylesheet" href="css/slicknav.css">
        <link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/themes/smoothness/jquery-ui.css">

        <link rel="stylesheet" href="css/style.css">
        <!-- <link rel="stylesheet" href="css/responsive.css"> -->
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
        <div class="container-fluid mt-3">
            <div class="row">
                <div class="col-md-3">
                    <jsp:include page="/frontend/common/admin_taskbar.jsp" />
                </div>

                <div class="col-md-9">
                    <div class="container mt-3">
                        <h2>Removal Price</h2>
                        <a href="admin_addprice" class="btn btn-info btn-sm">Add Price</a>
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
                                    <th>Action</th>
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
                                    <td>
                                        <a href="admin_updateprice?priceCostID=<%= item.getPriceCostID() %>" class="btn btn-info btn-sm">Edit</a>
                                        <form action="admin_price" method="post" style="display:inline;">
                                            <input type="hidden" name="priceCostID" value="<%= item.getPriceCostID() %>">
                                            <button type="submit" name="action" value="delete" 
                                                    class="btn btn-danger btn-sm"
                                                    onclick="return confirm('Are you sure you want to delete this price cost?');">
                                                Delete
                                            </button>
                                        </form>
                                    </td>

                                    <% 
                                            }
                                        } 
                                    %>
                            </tbody>
                        </table>
                        <% } %>
                    </div>
                </div>
            </div>
        </div>
        <jsp:include page="/frontend/common/footer.jsp" />
        <script src="js/vendor/modernizr-3.5.0.min.js"></script>
        <script src="js/vendor/jquery-1.12.4.min.js"></script>
        <script src="js/popper.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/owl.carousel.min.js"></script>
        <script src="js/isotope.pkgd.min.js"></script>
        <script src="js/ajax-form.js"></script>
        <script src="js/waypoints.min.js"></script>
        <script src="js/jquery.counterup.min.js"></script>
        <script src="js/imagesloaded.pkgd.min.js"></script>
        <script src="js/scrollIt.js"></script>
        <script src="js/jquery.scrollUp.min.js"></script>
        <script src="js/wow.min.js"></script>
        <script src="js/nice-select.min.js"></script>
        <script src="js/jquery.slicknav.min.js"></script>
        <script src="js/jquery.magnific-popup.min.js"></script>
        <script src="js/plugins.js"></script>
        <!-- <script src="js/gijgo.min.js"></script> -->
        <script src="js/slick.min.js"></script>



        <!--contact js-->
        <script src="js/contact.js"></script>
        <script src="js/jquery.ajaxchimp.min.js"></script>
        <script src="js/jquery.form.js"></script>
        <script src="js/jquery.validate.min.js"></script>
        <script src="js/mail-script.js"></script>


        <script src="js/main.js"></script>
    </body>
</html>
