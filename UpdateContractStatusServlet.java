package servlet;

import dao.ContractDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.User;

@WebServlet(name = "UpdateContractStatusServlet", urlPatterns = {"/complete_contract"})
public class UpdateContractStatusServlet extends HttpServlet {
    
    private final ContractDAO contractDAO = new ContractDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Get session and verify user role
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");
            if (user == null || user.getRole().getRoleID() != 2) { // 2 = MovingStaff
                response.sendRedirect("login");
                return;
            }

            // Get contract ID
            int contractId = Integer.parseInt(request.getParameter("id"));

            // Update contract status to completed
            contractDAO.updateStatus(contractId, 2); // 2 = Completed

            // Redirect back to contracts list
            response.sendRedirect("contracts");

        } catch (Exception e) {
            request.setAttribute("error", "Error completing contract: " + e.getMessage());
            request.getRequestDispatcher("contracts.jsp").forward(request, response);
        }
    }
} 