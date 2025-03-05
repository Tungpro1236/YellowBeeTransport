/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.Admin;

import DAO.AccountsDAO;
import DAO.UserDAO;
import Model.Accounts;
import Model.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author nguye
 */
public class AdminDetailUser extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("id"));
        HttpSession session = request.getSession();
        String role = (String) session.getAttribute("role");
        if (role == null || !role.equals("Admin")) {
            request.getRequestDispatcher("/frontend/view/access_denied.jsp").forward(request, response);
            return;
        }
        UserDAO userDAO = new UserDAO();
        AccountsDAO aDAO = new AccountsDAO();
        User user = userDAO.getUserById(userId); // Lấy user từ DB
        Accounts account = aDAO.getAccountById(userId);

        request.setAttribute("user", user);
        request.getRequestDispatcher("/frontend/view/admin/admin_userdetail.jsp").forward(request, response);

    }

}
