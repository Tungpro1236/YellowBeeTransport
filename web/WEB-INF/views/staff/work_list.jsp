<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Work List</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-4">
        <div class="d-flex justify-content-between align-items-center mb-4">
            <h2>Work List</h2>
            <a href="${pageContext.request.contextPath}/moving-staff-dashboard" 
               class="btn btn-secondary">Back to Dashboard</a>
        </div>

        <div class="card">
            <div class="card-body">
                <c:if test="${empty workList}">
                    <p>No active contracts in your work list.</p>
                </c:if>
                <c:if test="${not empty workList}">
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
                                <c:forEach items="${workList}" var="contract">
                                    <tr>
                                        <td>${contract.contractID}</td>
                                        <td>${contract.customerName}</td>
                                        <td>${contract.serviceName}</td>
                                        <td>${contract.transportTime}</td>
                                        <td>${contract.status}</td>
                                        <td>
                                            <a href="${pageContext.request.contextPath}/moving-staff-dashboard/contracts/${contract.contractID}" 
                                               class="btn btn-sm btn-primary">View Details</a>
                                            <a href="${pageContext.request.contextPath}/complete_contract?id=${contract.contractID}" 
                                               class="btn btn-sm btn-success">Complete</a>
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

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html> 