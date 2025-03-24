<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Contracts</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-4">
        <div class="d-flex justify-content-between align-items-center mb-4">
            <h2>Contracts</h2>
            <a href="${pageContext.request.contextPath}/moving-staff-dashboard" 
               class="btn btn-secondary">Back to Dashboard</a>
        </div>

        <div class="card">
            <div class="card-body">
                <c:if test="${empty contracts}">
                    <p>No contracts found.</p>
                </c:if>
                <c:if test="${not empty contracts}">
                    <div class="table-responsive">
                        <table class="table">
                            <thead>
                                <tr>
                                    <th>Contract ID</th>
                                    <th>Customer</th>
                                    <th>Service</th>
                                    <th>Transport Time</th>
                                    <th>Status</th>
                                    <th>Actions</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${contracts}" var="contract">
                                    <tr>
                                        <td>${contract.contractID}</td>
                                        <td>${contract.customerName}</td>
                                        <td>${contract.serviceName}</td>
                                        <td>${contract.transportTime}</td>
                                        <td>${contract.status}</td>
                                        <td>
                                            <button type="button" 
                                                    class="btn btn-sm btn-warning" 
                                                    data-bs-toggle="modal" 
                                                    data-bs-target="#problemModal${contract.contractID}">
                                                Report Problem
                                            </button>
                                            <a href="${pageContext.request.contextPath}/complete_contract?id=${contract.contractID}" 
                                               class="btn btn-sm btn-success">Complete</a>
                                        </td>
                                    </tr>
                                    
                                    <!-- Problem Modal -->
                                    <div class="modal fade" id="problemModal${contract.contractID}" tabindex="-1">
                                        <div class="modal-dialog">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <h5 class="modal-title">Report Transport Problem</h5>
                                                    <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                                                </div>
                                                <form action="${pageContext.request.contextPath}/moving-staff-dashboard/add-problem" method="POST">
                                                    <div class="modal-body">
                                                        <input type="hidden" name="contractId" value="${contract.contractID}">
                                                        <div class="mb-3">
                                                            <label for="problemDescription${contract.contractID}" class="form-label">Problem Description</label>
                                                            <textarea class="form-control" 
                                                                      id="problemDescription${contract.contractID}" 
                                                                      name="problemDescription" 
                                                                      required></textarea>
                                                        </div>
                                                        <div class="mb-3">
                                                            <label for="problemCost${contract.contractID}" class="form-label">Additional Cost</label>
                                                            <input type="number" 
                                                                   class="form-control" 
                                                                   id="problemCost${contract.contractID}" 
                                                                   name="problemCost" 
                                                                   step="0.01" 
                                                                   required>
                                                        </div>
                                                    </div>
                                                    <div class="modal-footer">
                                                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                                                        <button type="submit" class="btn btn-primary">Submit</button>
                                                    </div>
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </c:if>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html> 