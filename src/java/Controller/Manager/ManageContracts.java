/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.Manager;

import DAO.ContractDAO;
import Model.Contract;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ManageContracts", urlPatterns = {"/ManageContracts"})
public class ManageContracts extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            ContractDAO contractDAO = new ContractDAO();
            // Lấy danh sách hợp đồng theo trạng thái
            List<Contract> pendingContracts = contractDAO.getContractsByStatus("Pending");
            List<Contract> completedContracts = contractDAO.getContractsByStatus("Completed");
            List<Contract> canceledContracts = contractDAO.getContractsByStatus("Canceled");
            
            System.out.println("Danh sách Contract Pending:");
        if (pendingContracts != null) {
            for (Contract c : pendingContracts) {
                System.out.println(c.getContractId() + " - " + c.getFinalCost());
            }
        } else {
            System.out.println("Contract Pending is NULL");
        }
            
            // Đặt dữ liệu vào request scope
            request.setAttribute("pendingContracts", pendingContracts);
            request.setAttribute("completedContracts", completedContracts);
            request.setAttribute("canceledContracts", canceledContracts);
            
            RequestDispatcher dispatcher = request.getRequestDispatcher("/frontend/view/manager/manageContracts.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Error loading contracts: " + e.getMessage());
            RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
            dispatcher.forward(request, response);
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        
        if ("updateStatus".equals(action)) {
            try {
                int contractId = Integer.parseInt(request.getParameter("contractId"));
                String newStatus = request.getParameter("newStatus");
                
                ContractDAO contractDAO = new ContractDAO();
                boolean updated = contractDAO.updateContractStatus(contractId, newStatus);
                System.out.println("Updated :"+ updated);
                // Sau khi cập nhật, chuyển về trang ManageContracts
                response.sendRedirect("ManageContracts");
                return;
            } catch (Exception e) {
                e.printStackTrace();
                request.setAttribute("errorMessage", "Error updating contract status: " + e.getMessage());
                RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
                dispatcher.forward(request, response);
                return;
            }
        }
        
        // Nếu action không được nhận dạng, chuyển về doGet
        doGet(request, response);
    }
}
