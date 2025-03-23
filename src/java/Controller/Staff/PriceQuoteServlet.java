/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.Staff;

import DAO.PriceQuoteDAO;
import Model.PriceQuote;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author Admin
 */
public class PriceQuoteServlet extends HttpServlet {

    private PriceQuoteDAO priceQuoteDAO;

    @Override
    public void init() throws ServletException {
        priceQuoteDAO = new PriceQuoteDAO();
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
            out.println("<title>Servlet PriceQuoteServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet PriceQuoteServlet at " + request.getContextPath() + "</h1>");
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
        try {
            if (pathInfo == null || pathInfo.equals("/")) {
                // List all price quotes
                request.setAttribute("priceQuotes", priceQuoteDAO.getAllPriceQuotes());
                request.getRequestDispatcher("/WEB-INF/views/priceQuote/list.jsp").forward(request, response);
            } else if (pathInfo.equals("/create")) {
                // Show create form
                request.getRequestDispatcher("/WEB-INF/views/priceQuote/form.jsp").forward(request, response);
            } else if (pathInfo.startsWith("/edit/")) {
                // Show edit form
                int id = Integer.parseInt(pathInfo.substring(6));
                PriceQuote priceQuote = priceQuoteDAO.getPriceQuoteById(id);
                if (priceQuote != null) {
                    request.setAttribute("priceQuote", priceQuote);
                    request.getRequestDispatcher("/WEB-INF/views/priceQuote/form.jsp").forward(request, response);
                } else {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND);
                }
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
        try {
            // Get form parameters
            int truckAmount = Integer.parseInt(request.getParameter("truckAmount"));
            int staffAmount = Integer.parseInt(request.getParameter("staffAmount"));
            int priceCostID = Integer.parseInt(request.getParameter("priceCostID"));
            int checkingFormID = Integer.parseInt(request.getParameter("checkingFormID"));

            // Calculate final cost
            double finalCost = priceQuoteDAO.calculateFinalCost(truckAmount, staffAmount, priceCostID);

            // Create price quote object
            PriceQuote priceQuote = new PriceQuote();
            priceQuote.setTruckAmount(truckAmount);
            priceQuote.setStaffAmount(staffAmount);
            priceQuote.setFinalCost(finalCost);
            priceQuote.setPriceCostID(priceCostID);
            priceQuote.setCheckingFormID(checkingFormID);

            String pathInfo = request.getPathInfo();
            if (pathInfo == null || pathInfo.equals("/create")) {
                // Create new price quote
                priceQuoteDAO.createPriceQuote(priceQuote);
            } else if (pathInfo.startsWith("/edit/")) {
                // Update existing price quote
                int id = Integer.parseInt(pathInfo.substring(6));
                priceQuote.setPriceQuoteID(id);
                priceQuoteDAO.updatePriceQuote(priceQuote);
            }

            response.sendRedirect(request.getContextPath() + "/price-quote");
        } catch (Exception e) {
            throw new ServletException("Error processing request", e);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String pathInfo = request.getPathInfo();
            if (pathInfo != null && pathInfo.length() > 1) {
                int id = Integer.parseInt(pathInfo.substring(1));
                priceQuoteDAO.deletePriceQuote(id);
                response.setStatus(HttpServletResponse.SC_OK);
            } else {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            }
        } catch (Exception e) {
            throw new ServletException("Error processing request", e);
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
