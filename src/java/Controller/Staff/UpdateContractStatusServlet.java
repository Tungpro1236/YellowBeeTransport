/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.Staff;

import DAO.ContractDAO;
import Model.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;

/**
 *
 * @author Admin
 */
@WebServlet(name = "UpdateContractStatusServlet", urlPatterns = {"/complete_contract"})
public class UpdateContractStatusServlet extends HttpServlet {

    private final ContractDAO contractDAO = new ContractDAO();

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
            out.println("<title>Servlet UpdateContractStatusServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateContractStatusServlet at " + request.getContextPath() + "</h1>");
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
        try {
            // Get session and verify user role
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");
            if (user == null || user.getRoleId() != 2) { // 2 = MovingStaff
                response.sendRedirect(request.getContextPath() + "/login");
                return;
            }

            // Get contract ID and status
            int contractId = Integer.parseInt(request.getParameter("id"));
            int statusId = Integer.parseInt(request.getParameter("status")); // 1 = Pending, 2 = Completed

            // Validate status
            if (statusId != 1 && statusId != 2) {
                throw new IllegalArgumentException("Invalid status ID");
            }

            // Update contract status
            boolean success = contractDAO.updateContractStatus(contractId, statusId);
            if (!success) {
                throw new SQLException("Failed to update contract status");
            }

            // Redirect back to contracts list
            response.sendRedirect(request.getContextPath() + "/contracts");

        } catch (SQLException e) {
            request.setAttribute("error", "Database error: " + e.getMessage());
            request.getRequestDispatcher("/WEB-INF/views/staff/contracts.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("error", "Error updating contract status: " + e.getMessage());
            request.getRequestDispatcher("/WEB-INF/views/staff/contracts.jsp").forward(request, response);
        }
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
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Update Contract Status Servlet";
    }// </editor-fold>

}
