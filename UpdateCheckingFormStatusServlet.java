package servlet;

import dao.CheckingFormDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.User;

@WebServlet(name = "UpdateCheckingFormStatusServlet", urlPatterns = {"/approve_checking_form", "/reject_checking_form"})
public class UpdateCheckingFormStatusServlet extends HttpServlet {
    
    private final CheckingFormDAO checkingFormDAO = new CheckingFormDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Get session and verify user role
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");
            if (user == null || user.getRole().getRoleID() != 1) { // 1 = SurveyStaff
                response.sendRedirect("login");
                return;
            }

            // Get form ID and determine action
            int formId = Integer.parseInt(request.getParameter("id"));
            String action = request.getServletPath();
            String newStatus = action.equals("/approve_checking_form") ? "approved" : "rejected";

            // Update form status
            checkingFormDAO.updateStatus(formId, newStatus);

            // Redirect back to checking forms list
            response.sendRedirect("checking_forms");

        } catch (Exception e) {
            request.setAttribute("error", "Error updating form status: " + e.getMessage());
            request.getRequestDispatcher("checking_forms.jsp").forward(request, response);
        }
    }
} 