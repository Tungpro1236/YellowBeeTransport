package Controller.Admin;

import DAO.AccountsDAO;
import Model.Accounts;
import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class AdminManageAccounts extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String role = (String) session.getAttribute("role");

        if (role == null || !role.equals("Admin")) {
            request.getRequestDispatcher("/frontend/view/access_denied.jsp").forward(request, response);
            return;
        }

        AccountsDAO aDAO = new AccountsDAO();
        List<Accounts> accountList = aDAO.getAllAccountsDashboard();

        if (accountList == null || accountList.isEmpty()) {
            request.setAttribute("message", "No accounts found.");
        } else {
            request.setAttribute("accountList", accountList);
        }

        request.getRequestDispatcher("/frontend/view/admin/admin_manageaccount.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String role = (String) session.getAttribute("role");

        if (role == null || !role.equals("Admin")) {
            request.getRequestDispatcher("/frontend/view/access_denied.jsp").forward(request, response);
            return;
        }

        String action = request.getParameter("action");
        String accountIdStr = request.getParameter("accountId");

        if ("delete".equals(action)) {
            if (accountIdStr == null || accountIdStr.trim().isEmpty()) {
                request.setAttribute("message", "Invalid account ID.");
            } else {
                try {
                    int accountId = Integer.parseInt(accountIdStr);
                    AccountsDAO aDAO = new AccountsDAO();
                    boolean success = aDAO.deleteAccount(accountId);

                    if (success) {
                        request.setAttribute("message", "Account disabled successfully.");
                    } else {
                        request.setAttribute("message", "Failed to disable account.");
                    }
                } catch (NumberFormatException e) {
                    request.setAttribute("message", "Invalid account ID format.");
                }
            }
        }

        // Load lại danh sách tài khoản sau khi xử lý
        doGet(request, response);
    }
}
