/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controller.Manager;

import DAO.ReportDAO;
import java.io.IOException;
import java.util.Map;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ReportsAnalytics")
public class ReportsAnalytics extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ReportDAO reportDAO = new ReportDAO();
        
        // Lấy dữ liệu báo cáo
        int totalProblemsUnresolved = reportDAO.getTotalTransportProblemsUnresolved();  // Tổng số Transport Problems chưa giải quyết
        
        // Lấy số liệu tổng số Checking Forms theo trạng thái
        int totalPendingCheckingForms = reportDAO.getTotalCheckingFormsByStatus("Pending");
        int totalApprovedCheckingForms = reportDAO.getTotalCheckingFormsByStatus("Approved");
        
        // Lấy số liệu tổng số Contracts theo trạng thái
        int totalPendingContracts = reportDAO.getTotalContractsByStatus("Pending");
        int totalCompletedContracts = reportDAO.getTotalContractsByStatus("Completed");
        
        // Đặt dữ liệu vào request scope
        request.setAttribute("totalProblemsUnresolved", totalProblemsUnresolved);
        request.setAttribute("totalPendingCheckingForms", totalPendingCheckingForms);
        request.setAttribute("totalApprovedCheckingForms", totalApprovedCheckingForms);
        request.setAttribute("totalPendingContracts", totalPendingContracts);
        request.setAttribute("totalCompletedContracts", totalCompletedContracts);
        
        // Chuyển hướng sang trang JSP báo cáo
        request.getRequestDispatcher("/frontend/view/manager/reportsAnalytics.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lấy khoảng thời gian từ form báo cáo doanh thu
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        
        ReportDAO reportDAO = new ReportDAO();
        double totalRevenue = reportDAO.getTotalRevenue(startDate, endDate);
        
        // Đặt doanh thu vào request scope
        request.setAttribute("totalRevenue", totalRevenue);
        
        // Gọi doGet để cập nhật toàn bộ báo cáo và hiển thị lại trang JSP
        doGet(request, response);
    }
}
