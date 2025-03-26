package servlet;

import dao.CheckingFormDAO;
import dao.PriceQuoteDAO;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.CheckingForm;
import model.PriceQuote;
import model.User;

@WebServlet(name = "AddPriceQuoteServlet", urlPatterns = {"/add_price_quote"})
public class AddPriceQuoteServlet extends HttpServlet {
    
    private final PriceQuoteDAO priceQuoteDAO = new PriceQuoteDAO();
    private final CheckingFormDAO checkingFormDAO = new CheckingFormDAO();
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");
            
            if (user == null || user.getRoleID() != 2) { // 2 = Checking Staff role
                response.sendRedirect("login.jsp");
                return;
            }
            
            // Get form data
            int checkingFormId = Integer.parseInt(request.getParameter("checkingFormId"));
            double basePrice = Double.parseDouble(request.getParameter("basePrice"));
            double additionalFees = Double.parseDouble(request.getParameter("additionalFees"));
            String notes = request.getParameter("notes");
            
            // Create price quote
            PriceQuote quote = new PriceQuote();
            quote.setCheckingFormID(checkingFormId);
            quote.setBasePrice(basePrice);
            quote.setAdditionalFees(additionalFees);
            quote.setTotalPrice(basePrice + additionalFees);
            quote.setNotes(notes);
            quote.setStaffID(user.getUserID());
            
            // Save price quote and update checking form status
            priceQuoteDAO.createPriceQuote(quote);
            checkingFormDAO.updateStatus(checkingFormId, "quoted");
            
            // Redirect back to checking requests with success message
            session.setAttribute("successMessage", "Price quote added successfully");
            response.sendRedirect("checking_requests");
            
        } catch (SQLException e) {
            request.getSession().setAttribute("errorMessage", "Error adding price quote: " + e.getMessage());
            response.sendRedirect("checking_requests");
        } catch (NumberFormatException e) {
            request.getSession().setAttribute("errorMessage", "Invalid price format");
            response.sendRedirect("checking_requests");
        }
    }
} 