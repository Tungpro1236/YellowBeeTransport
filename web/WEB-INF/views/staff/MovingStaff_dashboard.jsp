<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Moving Staff Dashboard</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-4">
        <h2>Moving Staff Dashboard</h2>
        
        <div class="row mt-4">
            <div class="col-md-6">
                <div class="card">
                    <div class="card-header">
                        <h5>Assigned Contracts</h5>
                    </div>
                    <div class="card-body">
                        <c:if test="${empty assignedContracts}">
                            <p>No contracts assigned.</p>
                        </c:if>
                        <c:if test="${not empty assignedContracts}">
                            <div class="table-responsive">
                                <table class="table">
                                    <thead>
                                        <tr>
                                            <th>Contract ID</th>
                                            <th>Customer</th>
                                            <th>Service</th>
                                            <th>Status</th>
                                            <th>Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${assignedContracts}" var="contract">
                                            <tr>
                                                <td>${contract.contractID}</td>
                                                <td>${contract.customerName}</td>
                                                <td>${contract.serviceName}</td>
                                                <td>${contract.status}</td>
                                                <td>
                                                    <a href="${pageContext.request.contextPath}/moving-staff-dashboard/contracts/${contract.contractID}" 
                                                       class="btn btn-sm btn-primary">View Details</a>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </c:if>
                    </div>
                </div>
            </div>
            
            <div class="col-md-6">
                <div class="card">
                    <div class="card-header">
                        <h5>Quick Actions</h5>
                    </div>
                    <div class="card-body">
                        <div class="d-grid gap-2">
                            <a href="${pageContext.request.contextPath}/moving-staff-dashboard/work-list" 
                               class="btn btn-primary">View Work List</a>
                            <a href="${pageContext.request.contextPath}/moving-staff-dashboard/contracts" 
                               class="btn btn-secondary">View All Contracts</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html> 