<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Contracts - YellowBee Transport</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
    <style>
        :root {
            --primary-color: #FFD700;
            --secondary-color: #000000;
        }
        
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f8f9fa;
        }
        
        .navbar {
            background-color: var(--primary-color);
            box-shadow: 0 2px 4px rgba(0,0,0,0.1);
        }
        
        .navbar-brand {
            color: var(--secondary-color) !important;
            font-weight: bold;
            font-size: 1.5rem;
        }
        
        .nav-link {
            color: var(--secondary-color) !important;
            font-weight: 500;
            transition: all 0.3s ease;
        }
        
        .nav-link:hover {
            color: #666 !important;
            transform: translateY(-2px);
        }
        
        .container {
            max-width: 1200px;
            margin: 2rem auto;
            padding: 0 1rem;
        }
        
        .card {
            border: none;
            border-radius: 10px;
            box-shadow: 0 2px 4px rgba(0,0,0,0.05);
            margin-bottom: 1.5rem;
        }
        
        .card-header {
            background-color: white;
            border-bottom: 1px solid #eee;
            padding: 1.5rem;
        }
        
        .table {
            margin-bottom: 0;
        }
        
        .table th {
            border-top: none;
            font-weight: 600;
            color: #666;
        }
        
        .status-badge {
            padding: 0.5rem 1rem;
            border-radius: 20px;
            font-size: 0.875rem;
        }
        
        .status-pending {
            background-color: #fff3cd;
            color: #856404;
        }
        
        .status-completed {
            background-color: #d4edda;
            color: #155724;
        }
        
        .action-btn {
            padding: 0.5rem 1rem;
            border-radius: 5px;
            font-size: 0.875rem;
            transition: all 0.3s ease;
        }
        
        .action-btn:hover {
            transform: translateY(-2px);
        }
        
        .search-box {
            max-width: 300px;
        }
        
        .contract-details {
            font-size: 0.875rem;
            color: #666;
        }
    </style>
</head>
<body>
    <!-- Navigation Bar -->
    <nav class="navbar navbar-expand-lg navbar-light">
        <div class="container">
            <a class="navbar-brand" href="staff_dashboard.jsp">
                <i class="fas fa-truck"></i> YellowBee Transport
            </a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav ms-auto">
                    <li class="nav-item">
                        <a class="nav-link" href="staff_dashboard.jsp">
                            <i class="fas fa-home"></i> Dashboard
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="work_schedule.jsp">
                            <i class="fas fa-calendar-alt"></i> Schedule
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="profile.jsp">
                            <i class="fas fa-user"></i> Profile
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="logout">
                            <i class="fas fa-sign-out-alt"></i> Logout
                        </a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>

    <!-- Main Content -->
    <div class="container">
        <div class="card">
            <div class="card-header">
                <h4 class="mb-0">My Contracts</h4>
            </div>
            <div class="card-body">
                <!-- Search and Filter -->
                <div class="row mb-4">
                    <div class="col-md-6">
                        <div class="input-group search-box">
                            <input type="text" class="form-control" placeholder="Search by customer name or contract ID...">
                            <button class="btn btn-outline-secondary" type="button">
                                <i class="fas fa-search"></i>
                            </button>
                        </div>
                    </div>
                    <div class="col-md-6 text-end">
                        <select class="form-select d-inline-block w-auto">
                            <option value="">All Status</option>
                            <option value="pending">Pending</option>
                            <option value="completed">Completed</option>
                        </select>
                    </div>
                </div>

                <!-- Contracts Table -->
                <div class="table-responsive">
                    <table class="table table-hover">
                        <thead>
                            <tr>
                                <th>Contract ID</th>
                                <th>Customer</th>
                                <th>Service</th>
                                <th>Transport Time</th>
                                <th>Final Cost</th>
                                <th>Status</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${contracts}" var="contract">
                                <tr>
                                    <td>${contract.contractID}</td>
                                    <td>
                                        <div>${contract.checkingForm.customer.fullName}</div>
                                        <div class="contract-details">${contract.checkingForm.phone}</div>
                                    </td>
                                    <td>${contract.checkingForm.service.serviceName}</td>
                                    <td>
                                        <fmt:formatDate value="${contract.checkingForm.transportTime}" pattern="dd/MM/yyyy HH:mm"/>
                                    </td>
                                    <td>
                                        <fmt:formatNumber value="${contract.finalCost}" type="currency" currencySymbol="₫"/>
                                    </td>
                                    <td>
                                        <span class="status-badge status-${contract.contractStatus.description.toLowerCase()}">
                                            ${contract.contractStatus.description}
                                        </span>
                                    </td>
                                    <td>
                                        <div class="btn-group">
                                            <a href="view_contract.jsp?id=${contract.contractID}" 
                                               class="btn btn-sm btn-outline-primary action-btn">
                                                <i class="fas fa-eye"></i>
                                            </a>
                                            <c:if test="${contract.contractStatus.contractStatusID == 1}">
                                                <a href="report_problem.jsp?id=${contract.contractID}" 
                                                   class="btn btn-sm btn-outline-danger action-btn">
                                                    <i class="fas fa-exclamation-triangle"></i>
                                                </a>
                                                <button class="btn btn-sm btn-outline-success action-btn"
                                                        onclick="completeContract(${contract.contractID})">
                                                    <i class="fas fa-check"></i>
                                                </button>
                                            </c:if>
                                        </div>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>

                <!-- Pagination -->
                <nav aria-label="Page navigation" class="mt-4">
                    <ul class="pagination justify-content-center">
                        <li class="page-item disabled">
                            <a class="page-link" href="#" tabindex="-1">Previous</a>
                        </li>
                        <li class="page-item active"><a class="page-link" href="#">1</a></li>
                        <li class="page-item"><a class="page-link" href="#">2</a></li>
                        <li class="page-item"><a class="page-link" href="#">3</a></li>
                        <li class="page-item">
                            <a class="page-link" href="#">Next</a>
                        </li>
                    </ul>
                </nav>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    <script>
        function completeContract(contractId) {
            if (confirm('Are you sure you want to mark this contract as completed?')) {
                // Add AJAX call to complete contract
                window.location.href = 'complete_contract?id=' + contractId;
            }
        }
    </script>
</body>
</html> 