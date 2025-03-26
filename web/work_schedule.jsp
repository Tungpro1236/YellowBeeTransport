<%-- 
    Document   : work_schedule
    Created on : Mar 23, 2025, 2:56:56 PM
    Author     : Admin
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Work Schedule - YellowBee Transport</title>
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
        
        .schedule-calendar {
            background-color: white;
            border-radius: 10px;
            padding: 1.5rem;
            margin-bottom: 1.5rem;
        }
        
        .calendar-header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 1rem;
        }
        
        .calendar-nav {
            display: flex;
            gap: 1rem;
        }
        
        .calendar-grid {
            display: grid;
            grid-template-columns: repeat(7, 1fr);
            gap: 0.5rem;
        }
        
        .calendar-day {
            aspect-ratio: 1;
            padding: 0.5rem;
            border: 1px solid #eee;
            border-radius: 5px;
            cursor: pointer;
            transition: all 0.3s ease;
        }
        
        .calendar-day:hover {
            background-color: #f8f9fa;
        }
        
        .calendar-day.today {
            background-color: var(--primary-color);
            color: var(--secondary-color);
        }
        
        .calendar-day.has-shift {
            border-color: var(--primary-color);
        }
        
        .shift-list {
            list-style: none;
            padding: 0;
            margin: 0;
            font-size: 0.875rem;
        }
        
        .shift-item {
            padding: 0.25rem 0.5rem;
            margin-bottom: 0.25rem;
            background-color: #f8f9fa;
            border-radius: 3px;
            font-size: 0.75rem;
        }
        
        .shift-time {
            color: #666;
            font-size: 0.75rem;
        }
        
        .weekday-header {
            text-align: center;
            font-weight: 600;
            color: #666;
            padding: 0.5rem;
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
                        <a class="nav-link" href="contracts.jsp">
                            <i class="fas fa-file-contract"></i> Contracts
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
        <!-- Schedule Calendar -->
        <div class="schedule-calendar">
            <div class="calendar-header">
                <h4 class="mb-0">Work Schedule</h4>
                <div class="calendar-nav">
                    <button class="btn btn-outline-secondary" onclick="previousMonth()">
                        <i class="fas fa-chevron-left"></i>
                    </button>
                    <h5 class="mb-0" id="currentMonth">March 2024</h5>
                    <button class="btn btn-outline-secondary" onclick="nextMonth()">
                        <i class="fas fa-chevron-right"></i>
                    </button>
                </div>
            </div>
            
            <div class="calendar-grid">
                <div class="weekday-header">Sun</div>
                <div class="weekday-header">Mon</div>
                <div class="weekday-header">Tue</div>
                <div class="weekday-header">Wed</div>
                <div class="weekday-header">Thu</div>
                <div class="weekday-header">Fri</div>
                <div class="weekday-header">Sat</div>
                
                <c:forEach items="${calendarDays}" var="day">
                    <div class="calendar-day ${day.isToday ? 'today' : ''} ${day.hasShift ? 'has-shift' : ''}"
                         onclick="showShifts('${day.date}')">
                        <div class="d-flex justify-content-between align-items-start">
                            <span>${day.dayOfMonth}</span>
                            <c:if test="${day.hasShift}">
                                <i class="fas fa-clock text-warning"></i>
                            </c:if>
                        </div>
                        <c:if test="${day.hasShift}">
                            <ul class="shift-list">
                                <c:forEach items="${day.shifts}" var="shift">
                                    <li class="shift-item">
                                        <div>${shift.startTime} - ${shift.endTime}</div>
                                        <div class="shift-time">${shift.contract.checkingForm.customer.fullName}</div>
                                    </li>
                                </c:forEach>
                            </ul>
                        </c:if>
                    </div>
                </c:forEach>
            </div>
        </div>

        <!-- Upcoming Shifts -->
        <div class="card">
            <div class="card-header">
                <h4 class="mb-0">Upcoming Shifts</h4>
            </div>
            <div class="card-body">
                <div class="table-responsive">
                    <table class="table table-hover">
                        <thead>
                            <tr>
                                <th>Date</th>
                                <th>Time</th>
                                <th>Customer</th>
                                <th>Service</th>
                                <th>Location</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${upcomingShifts}" var="shift">
                                <tr>
                                    <td>
                                        <fmt:formatDate value="${shift.shiftDate}" pattern="dd/MM/yyyy"/>
                                    </td>
                                    <td>
                                        ${shift.startTime} - ${shift.endTime}
                                    </td>
                                    <td>${shift.contract.checkingForm.customer.fullName}</td>
                                    <td>${shift.contract.checkingForm.service.serviceName}</td>
                                    <td>${shift.contract.checkingForm.address}</td>
                                    <td>
                                        <a href="view_contract.jsp?id=${shift.contract.contractID}" 
                                           class="btn btn-sm btn-outline-primary">
                                            <i class="fas fa-eye"></i>
                                        </a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    <script>
        function previousMonth() {
            // Add logic to navigate to previous month
        }

        function nextMonth() {
            // Add logic to navigate to next month
        }

        function showShifts(date) {
            // Add logic to show shifts for selected date
            console.log('Showing shifts for:', date);
        }
    </script>
</body>
</html> 
