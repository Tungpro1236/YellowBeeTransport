/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.Customer;

import DAO.ContractDAO;
import DAO.RequestDAO;
import Model.PriceQuote;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;

/**
 *
 * @author regio
 */
public class CustomerPriceQuote extends HttpServlet {

    private RequestDAO requestDAO = new RequestDAO();

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
            out.println("<title>Servlet CustomerPriceQuote</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CustomerPriceQuote at " + request.getContextPath() + "</h1>");
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

        List<PriceQuote> quotes = requestDAO.getRequestsByCustomerId(userId);
        request.setAttribute("quotes", quotes);
        request.getRequestDispatcher("/frontend/view/customer/customer_listPrQuote.jsp").forward(request, response);
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
        int quoteId = Integer.parseInt(request.getParameter("quoteId"));
        String action = request.getParameter("action"); // "accept" hoặc "reject"

        RequestDAO requestDAO = new RequestDAO();
        boolean success = false;

        if ("accept".equals(action)) {
            success = requestDAO. createContractFromQuote(quoteId);
        } else if ("reject".equals(action)) {
            success = requestDAO.updatePriceQuoteStatus(quoteId, "Rejected");
        }

        if (success) {
            response.sendRedirect("customer_dashboard.jsp?success=true");
        } else {
            response.sendRedirect("customer_dashboard.jsp?error=true");
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
