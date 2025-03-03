/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.Admin;

import DAO.AccountsDAO;
import DAO.userDAO;
import Model.Accounts;
import Model.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author nguye
 */
public class AdminDetailUser extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("id"));

        userDAO userDAO = new userDAO();
        AccountsDAO aDAO = new AccountsDAO();
        User user = userDAO.getUserById(userId); // Lấy user từ DB
        Accounts account = aDAO.getAccountById(userId);

        request.setAttribute("user", user);
        request.getRequestDispatcher("/frontend/view/admin/admin_userdetail.jsp").forward(request, response);

    }

}
