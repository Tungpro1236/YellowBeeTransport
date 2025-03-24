/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.Staff;

import DAO.ContractDAO;
import DAO.TransportProblemDAO;
import Model.Contract;
import Model.TransportProblem;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import java.sql.SQLException;

/**
 *
 * @author Admin
 */
public class MovingStaffDashboardServlet extends HttpServlet {

    private ContractDAO contractDAO;
    private TransportProblemDAO transportProblemDAO;

    @Override
    public void init() throws ServletException {
        contractDAO = new ContractDAO();
        transportProblemDAO = new TransportProblemDAO();
    }

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
            out.println("<title>Servlet MovingStaffDashboardServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet MovingStaffDashboardServlet at " + request.getContextPath() + "</h1>");
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
        String pathInfo = request.getPathInfo();
        HttpSession session = request.getSession();
        Integer staffId = (Integer) session.getAttribute("staffId");

        if (staffId == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        try {
            if (pathInfo == null || pathInfo.equals("/")) {
                // Load work list and contracts for dashboard
                List<Contract> assignedContracts = contractDAO.getContractsByStaffId(staffId);
                request.setAttribute("assignedContracts", assignedContracts);
                request.getRequestDispatcher("/WEB-INF/views/staff/MovingStaff_dashboard.jsp")
                        .forward(request, response);
            } else if (pathInfo.equals("/work-list")) {
                // Show work list
                List<Contract> workList = contractDAO.getActiveContractsByStaffId(staffId);
                request.setAttribute("workList", workList);
                request.getRequestDispatcher("/WEB-INF/views/staff/work_list.jsp")
                        .forward(request, response);
            } else if (pathInfo.equals("/contracts")) {
                // Show contracts
                List<Contract> contracts = contractDAO.getContractsByStaffId(staffId);
                request.setAttribute("contracts", contracts);
                request.getRequestDispatcher("/WEB-INF/views/staff/contracts.jsp")
                        .forward(request, response);
            }
        } catch (Exception e) {
            throw new ServletException("Error processing request", e);
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
        String pathInfo = request.getPathInfo();
        HttpSession session = request.getSession();
        Integer staffId = (Integer) session.getAttribute("staffId");

        if (staffId == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        try {
            if (pathInfo.equals("/add-problem")) {
                // Add transport problem
                int contractId = Integer.parseInt(request.getParameter("contractId"));
                String description = request.getParameter("problemDescription");
                double problemCost = Double.parseDouble(request.getParameter("problemCost"));

                TransportProblem problem = new TransportProblem();
                problem.setContractID(contractId);
                problem.setProblemDescription(description);
                problem.setProblemCost(problemCost);
                problem.setStaffID(staffId);

                transportProblemDAO.createTransportProblem(problem);
                response.sendRedirect(request.getContextPath() + "/moving-staff-dashboard/contracts");
            } else if (pathInfo.equals("/complete-contract")) {
                completeContract(request, response, session);
            }
        } catch (Exception e) {
            throw new ServletException("Error processing request", e);
        }
    }

    private void completeContract(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws ServletException, IOException, SQLException {
        try {
            int contractId = Integer.parseInt(request.getParameter("id"));
            String newStatus = "Completed";
            
            boolean success = contractDAO.updateContractStatus(contractId, newStatus);
            
            if (success) {
                session.setAttribute("message", "Contract completed successfully");
            } else {
                session.setAttribute("error", "Failed to complete contract");
            }
            
            response.sendRedirect(request.getContextPath() + "/moving-staff-dashboard");
        } catch (NumberFormatException e) {
            session.setAttribute("error", "Invalid contract ID");
            response.sendRedirect(request.getContextPath() + "/moving-staff-dashboard");
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
