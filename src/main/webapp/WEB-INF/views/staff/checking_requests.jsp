<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Checking Requests - YellowBee</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet">
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <div class="container">
            <a class="navbar-brand" href="checking_staff_dashboard.jsp">YellowBee Transport</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav ms-auto">
                    <li class="nav-item">
                        <a class="nav-link" href="checking_staff_dashboard.jsp">Dashboard</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="profile.jsp">Profile</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="logout">Logout</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>

    <div class="container mt-4">
        <div class="d-flex justify-content-between align-items-center mb-4">
            <h2>Checking Requests</h2>
            <div class="d-flex gap-2">
                <div class="dropdown">
                    <button class="btn btn-secondary dropdown-toggle" type="button" data-bs-toggle="dropdown">
                        Filter by Status
                    </button>
                    <ul class="dropdown-menu">
                        <li><a class="dropdown-item" href="?status=all">All</a></li>
                        <li><a class="dropdown-item" href="?status=pending">Pending</a></li>
                        <li><a class="dropdown-item" href="?status=quoted">Quoted</a></li>
                        <li><a class="dropdown-item" href="?status=completed">Completed</a></li>
                    </ul>
                </div>
            </div>
        </div>

        <div class="card">
            <div class="card-body">
                <div class="table-responsive">
                    <table class="table">
                        <thead>
                            <tr>
                                <th>Request ID</th>
                                <th>Customer</th>
                                <th>Service</th>
                                <th>Checking Time</th>
                                <th>Transport Time</th>
                                <th>Status</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${checkingRequests}" var="request">
                                <tr>
                                    <td>${request.checkingFormID}</td>
                                    <td>${request.customerName}</td>
                                    <td>${request.serviceName}</td>
                                    <td>${request.checkingTime}</td>
                                    <td>${request.transportTime}</td>
                                    <td>
                                        <span class="badge bg-${request.status == 'pending' ? 'warning' : 
                                                              request.status == 'quoted' ? 'info' :
                                                              request.status == 'completed' ? 'success' : 'primary'}">
                                            ${request.status}
                                        </span>
                                    </td>
                                    <td>
                                        <div class="btn-group">
                                            <button type="button" class="btn btn-sm btn-info" 
                                                    onclick="viewDetails(${request.checkingFormID})">
                                                <i class="fas fa-eye"></i>
                                            </button>
                                            <c:if test="${request.status == 'pending'}">
                                                <button type="button" class="btn btn-sm btn-primary" 
                                                        onclick="addPriceQuote(${request.checkingFormID})">
                                                    <i class="fas fa-dollar-sign"></i> Add Quote
                                                </button>
                                            </c:if>
                                        </div>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>

    <!-- Price Quote Modal -->
    <div class="modal fade" id="priceQuoteModal" tabindex="-1">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">Add Price Quote</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                </div>
                <form id="priceQuoteForm" action="add_price_quote" method="POST">
                    <div class="modal-body">
                        <input type="hidden" id="checkingFormId" name="checkingFormId">
                        <div class="mb-3">
                            <label for="basePrice" class="form-label">Base Price</label>
                            <input type="number" class="form-control" id="basePrice" name="basePrice" required>
                        </div>
                        <div class="mb-3">
                            <label for="additionalFees" class="form-label">Additional Fees</label>
                            <input type="number" class="form-control" id="additionalFees" name="additionalFees" required>
                        </div>
                        <div class="mb-3">
                            <label for="notes" class="form-label">Notes</label>
                            <textarea class="form-control" id="notes" name="notes" rows="3"></textarea>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                        <button type="submit" class="btn btn-primary">Submit Quote</button>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    <script>
        function viewDetails(checkingFormId) {
            window.location.href = 'view_checking_form?id=' + checkingFormId;
        }

        function addPriceQuote(checkingFormId) {
            document.getElementById('checkingFormId').value = checkingFormId;
            new bootstrap.Modal(document.getElementById('priceQuoteModal')).show();
        }
    </script>
</body>
</html> 