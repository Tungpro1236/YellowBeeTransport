<%-- 
    Document   : MovingStaff_dashboard
    Created on : Mar 23, 2025, 5:55:54 PM
    Author     : Admin
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>Moving Staff Dashboard</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.2/font/bootstrap-icons.css" rel="stylesheet">
</head>
<body>
    <div class="container-fluid">
        <div class="row">
            <!-- Sidebar -->
            <nav class="col-md-3 col-lg-2 d-md-block bg-light sidebar collapse">
                <div class="position-sticky pt-3">
                    <ul class="nav flex-column">
                        <li class="nav-item">
                            <a class="nav-link active" href="${pageContext.request.contextPath}/moving-staff-dashboard">
                                <i class="bi bi-house-door"></i> Dashboard
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/moving-staff-dashboard/work-list">
                                <i class="bi bi-list-check"></i> Work List
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/moving-staff-dashboard/contracts">
                                <i class="bi bi-file-text"></i> Contracts
                            </a>
                        </li>
                    </ul>
                </div>
            </nav>

            <!-- Main content -->
            <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
                <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
                    <h1 class="h2">Moving Staff Dashboard</h1>
                </div>

                <!-- Today's Work -->
                <div class="card mb-4">
                    <div class="card-header">
                        <h4>Today's Work</h4>
                    </div>
                    <div class="card-body">
                        <c:choose>
                            <c:when test="${not empty assignedContracts}">
                                <div class="table-responsive">
                                    <table class="table table-striped">
                                        <thead>
                                            <tr>
                                                <th>Contract ID</th>
                                                <th>Customer Name</th>
                                                <th>Address</th>
                                                <th>Service</th>
                                                <th>Status</th>
                                                <th>Actions</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach var="contract" items="${assignedContracts}">
                                                <tr>
                                                    <td>${contract.contractID}</td>
                                                    <td>${contract.checkingForm.name}</td>
                                                    <td>${contract.checkingForm.address}</td>
                                                    <td>${contract.checkingForm.service.serviceName}</td>
                                                    <td>
                                                        <span class="badge bg-${contract.contractStatus.description == 'Completed' ? 'success' : 'warning'}">
                                                            ${contract.contractStatus.description}
                                                        </span>
                                                    </td>
                                                    <td>
                                                        <button type="button" class="btn btn-primary btn-sm"
                                                                onclick="viewContract(${contract.contractID})">
                                                            View Details
                                                        </button>
                                                        <button type="button" class="btn btn-warning btn-sm"
                                                                onclick="reportProblem(${contract.contractID})">
                                                            Report Problem
                                                        </button>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </c:when>
                            <c:otherwise>
                                <p class="text-muted">No work assigned for today.</p>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </main>
        </div>
    </div>

    <!-- Report Problem Modal -->
    <div class="modal fade" id="problemModal" tabindex="-1">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">Report Transport Problem</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                </div>
                <div class="modal-body">
                    <form id="problemForm" action="${pageContext.request.contextPath}/moving-staff-dashboard/add-problem" method="post">
                        <input type="hidden" id="contractId" name="contractId">
                        
                        <div class="mb-3">
                            <label for="problemDescription" class="form-label">Problem Description</label>
                            <textarea class="form-control" id="problemDescription" name="problemDescription" 
                                    rows="3" required></textarea>
                        </div>
                        
                        <div class="mb-3">
                            <label for="problemCost" class="form-label">Additional Cost (VND)</label>
                            <input type="number" class="form-control" id="problemCost" name="problemCost" 
                                   min="0" step="1000" required>
                        </div>
                        
                        <button type="submit" class="btn btn-primary">Submit Report</button>
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
    <script>
        function viewContract(contractId) {
            window.location.href = '${pageContext.request.contextPath}/moving-staff-dashboard/contracts?id=' + contractId;
        }
        
        function reportProblem(contractId) {
            document.getElementById('contractId').value = contractId;
            const modal = new bootstrap.Modal(document.getElementById('problemModal'));
            modal.show();
        }
    </script>
</body>
</html> 