/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.Customer;

import DAO.ContractDAO;
import Model.Contract;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author regio
 */
public class CustomerContracts extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CustomerContracts</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CustomerContracts at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String role = (String) session.getAttribute("role");
        Integer userId = (Integer) session.getAttribute("userId");
        if (role == null || !role.equals("Customer")) {
            request.getRequestDispatcher("/frontend/view/access_denied.jsp").forward(request, response);
            return;
        }
        ContractDAO contractDAO = new ContractDAO();
        String statusParam = request.getParameter("status");
        List<Contract> contracts;
        
        if (statusParam == null || statusParam.equals("1,2,3")) {
            // Nếu không có status hoặc chọn tất cả, lấy hợp đồng có trạng thái 1, 2, 3
            contracts = contractDAO.getContractsByMultipleStatus(Arrays.asList(1, 2, 3));
        } else {
            List<Integer> statusList = Arrays.stream(statusParam.split(","))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
            contracts = contractDAO.getContractsByMultipleStatus(statusList);
        }

        request.setAttribute("contracts", contracts);
        request.setAttribute("selectedStatus", statusParam);
        request.getRequestDispatcher("/frontend/view/customer/customer_contracts.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int contractId = Integer.parseInt(request.getParameter("contractId"));
        int newStatus = Integer.parseInt(request.getParameter("status"));

        ContractDAO contractDAO = new ContractDAO();
        boolean success = contractDAO.updateContractStatus(contractId, newStatus);

        if (success) {
            response.sendRedirect("customer_contracts");
        } else {
            response.getWriter().write("Lỗi cập nhật trạng thái hợp đồng!");
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
