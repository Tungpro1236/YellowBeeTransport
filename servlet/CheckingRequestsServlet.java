package servlet;

import dao.CheckingFormDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.CheckingForm;
import model.Staff;
import model.User;

@WebServlet(name = "CheckingRequestsServlet", urlPatterns = {"/checking_requests"})
public class CheckingRequestsServlet extends HttpServlet {
    
    private final CheckingFormDAO checkingFormDAO = new CheckingFormDAO();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");
            
            if (user == null || user.getRoleID() != 2) { // 2 = Checking Staff role
                response.sendRedirect("login.jsp");
                return;
            }
            
            String status = request.getParameter("status");
            List<CheckingForm> checkingRequests;
            
            if (status != null && !status.equals("all")) {
                checkingRequests = checkingFormDAO.getCheckingFormsByStatus(status);
            } else {
                checkingRequests = checkingFormDAO.getAllCheckingForms();
            }
            
            request.setAttribute("checkingRequests", checkingRequests);
            request.getRequestDispatcher("checking_requests.jsp").forward(request, response);
            
        } catch (SQLException e) {
            throw new ServletException("Error retrieving checking requests", e);
        }
    }
} 