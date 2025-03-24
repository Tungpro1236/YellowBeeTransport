<%-- 
    Document   : create_checking_form
    Created on : Mar 23, 2025, 2:54:55 PM
    Author     : Admin
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Create Checking Form - YellowBee Transport</title>
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
                max-width: 800px;
                margin: 2rem auto;
                padding: 0 1rem;
            }

            .card {
                border: none;
                border-radius: 10px;
                box-shadow: 0 2px 4px rgba(0,0,0,0.05);
            }

            .card-header {
                background-color: white;
                border-bottom: 1px solid #eee;
                padding: 1.5rem;
            }

            .form-label {
                font-weight: 500;
                color: #666;
            }

            .form-control:focus {
                border-color: var(--primary-color);
                box-shadow: 0 0 0 0.2rem rgba(255, 215, 0, 0.25);
            }

            .btn-warning {
                background-color: var(--primary-color);
                border-color: var(--primary-color);
                color: var(--secondary-color);
            }

            .btn-warning:hover {
                background-color: #e6c200;
                border-color: #e6c200;
                color: var(--secondary-color);
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
                            <a class="nav-link" href="checking_forms.jsp">
                                <i class="fas fa-list"></i> Checking Forms
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
                    <h4 class="mb-0">Create New Checking Form</h4>
                </div>
                <div class="card-body">
                    <form action="create_checking_form" method="POST">
                        <!-- Customer Information -->
                        <div class="mb-4">
                            <h5 class="mb-3">Customer Information</h5>
                            <div class="row">
                                <div class="col-md-6 mb-3">
                                    <label for="customerName" class="form-label">Customer Name</label>
                                    <input type="text" class="form-control" id="customerName" name="customerName" required>
                                </div>
                                <div class="col-md-6 mb-3">
                                    <label for="phone" class="form-label">Phone Number</label>
                                    <input type="tel" class="form-control" id="phone" name="phone" required>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-6 mb-3">
                                    <label for="email" class="form-label">Email</label>
                                    <input type="email" class="form-control" id="email" name="email">
                                </div>
                                <div class="col-md-6 mb-3">
                                    <label for="address" class="form-label">Address</label>
                                    <input type="text" class="form-control" id="address" name="address">
                                </div>
                            </div>
                        </div>

                        <!-- Service Information -->
                        <div class="mb-4">
                            <h5 class="mb-3">Service Information</h5>
                            <div class="row">
                                <div class="col-md-6 mb-3">
                                    <label for="service" class="form-label">Service Type</label>
                                    <select class="form-select" id="service" name="serviceID" required>
                                        <option value="">Select a service</option>
                                        <c:forEach items="${services}" var="service">
                                            <option value="${service.serviceID}">${service.serviceName}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label for="checkingTime">Checking Time</label>
                                    <input type="datetime-local" class="form-control" id="checkingTime" name="checkingTime" required>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="transportTime">Transport Time</label>
                                <input type="datetime-local" class="form-control" id="transportTime" name="transportTime" required>
                            </div>
                            <div class="col-md-6 mb-3">
                                <label for="notes" class="form-label">Additional Notes</label>
                                <textarea class="form-control" id="notes" name="notes" rows="3"></textarea>
                            </div>
                        </div>
                </div>

                <!-- Form Actions -->
                <div class="d-flex justify-content-end gap-2">
                    <a href="checking_forms.jsp" class="btn btn-secondary">
                        <i class="fas fa-times"></i> Cancel
                    </a>
                    <button type="submit" class="btn btn-warning">
                        <i class="fas fa-save"></i> Create Form
                    </button>
                </div>
                </form>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    <script>
        // Set minimum date for datetime inputs to current date
        const now = new Date();
        now.setMinutes(now.getMinutes() - now.getTimezoneOffset());
        document.getElementById('checkingTime').min = now.toISOString().slice(0, 16);
        document.getElementById('transportTime').min = now.toISOString().slice(0, 16);
    </script>
</body>
</html> 
