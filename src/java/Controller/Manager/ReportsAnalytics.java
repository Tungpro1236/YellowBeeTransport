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
        Map<String, Integer> contractStatusCounts = reportDAO.getContractStatusCounts();
        int totalProblems = reportDAO.getTotalTransportProblems();
        Map<Integer, Integer> topProblemSolvers = reportDAO.getTopProblemSolvers();
        
        // Thêm dữ liệu vào request scope
        request.setAttribute("contractStatusCounts", contractStatusCounts);
        request.setAttribute("totalProblems", totalProblems);
        request.setAttribute("topProblemSolvers", topProblemSolvers);
        
        // Chuyển hướng sang trang JSP
        request.getRequestDispatcher("/frontend/view/manager/reportsAnalytics.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        ReportDAO reportDAO = new ReportDAO();
        
        double totalRevenue = reportDAO.getTotalRevenue(startDate, endDate);
        request.setAttribute("totalRevenue", totalRevenue);
        
        // Gửi dữ liệu lại trang JSP
        doGet(request, response);
    }
}
