package servlet;

import dao.PriceQuoteDAO;
import model.PriceQuote;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/price-quote/*")
public class PriceQuoteServlet extends HttpServlet {
    private PriceQuoteDAO priceQuoteDAO;
    
    @Override
    public void init() throws ServletException {
        priceQuoteDAO = new PriceQuoteDAO();
    }
    
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
} 