/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.Admin;

import DAO.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.Map;

/**
 *
 * @author nguye
 */
public class AdminDashboardServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String role = (String) session.getAttribute("role");
        if (role == null || !role.equals("Admin")) {
            request.getRequestDispatcher("/frontend/view/access_denied.jsp").forward(request, response);
            return;
        }
        UserDAO userDAO = new UserDAO();
        int totalUsers = userDAO.getTotalUserCount();
        Map<String, Integer> userRoleData = userDAO.getUserCountByRole();

        request.setAttribute("totalUsers", totalUsers);
        request.setAttribute("userRoleData", userRoleData);
        request.getRequestDispatcher("/frontend/view/admin/admin_dashboard.jsp").forward(request, response);
    }

}
