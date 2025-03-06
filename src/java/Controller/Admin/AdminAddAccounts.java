/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.Admin;

import DAO.AccountsDAO;
import DAO.RoleDAO;
import DAO.UserDAO;
import Model.Roles;
import Model.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nguye
 */
public class AdminAddAccounts extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RoleDAO roleDAO = new RoleDAO();
        List<Roles> roleList = roleDAO.getAllRoles();
        request.setAttribute("roleList", roleList != null ? roleList : List.of());
        request.getRequestDispatcher("/frontend/view/admin/admin_addaccount.jsp").forward(request, response);
    }

   @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<String> errors = new ArrayList<>();

        // Lấy dữ liệu từ form
        String roleFilter = request.getParameter("roleFilter");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String fullName = request.getParameter("fullName");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String image = request.getParameter("image");
        String genderParam = request.getParameter("gender");

        // Kiểm tra dữ liệu đầu vào
        if (roleFilter == null || roleFilter.isEmpty()) {
            errors.add("Vui lòng chọn vai trò.");
        }
        if (username == null || username.isEmpty()) {
            errors.add("Tên đăng nhập không được để trống.");
        }
        if (password == null || password.isEmpty()) {
            errors.add("Mật khẩu không được để trống.");
        }
        if (fullName == null || fullName.isEmpty()) {
            errors.add("Họ và tên không được để trống.");
        }
        if (email == null || !email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            errors.add("Email không hợp lệ.");
        }
        if (phone == null || !phone.matches("^\\d{10,11}$")) {
            errors.add("Số điện thoại không hợp lệ.");
        }
        if (genderParam == null || genderParam.isEmpty()) {
            errors.add("Vui lòng chọn giới tính.");
        }

        int roleId = roleFilter != null ? Integer.parseInt(roleFilter) : 0;
        int genderId = genderParam != null ? Integer.parseInt(genderParam) : 0;
        int status = 1;

        // Nếu có lỗi, hiển thị lại form với thông báo lỗi
        if (!errors.isEmpty()) {
            RoleDAO roleDAO = new RoleDAO();
            List<Roles> roleList = roleDAO.getAllRoles();
            request.setAttribute("errors", errors);
            request.setAttribute("roleList", roleList);
            request.setAttribute("username", username);
            request.setAttribute("fullName", fullName);
            request.setAttribute("email", email);
            request.setAttribute("phone", phone);
            request.setAttribute("address", address);
            request.setAttribute("image", image);
            request.setAttribute("roleFilter", roleId);
            request.setAttribute("gender", genderId);
            request.getRequestDispatcher("/frontend/view/admin/admin_addaccount.jsp").forward(request, response);
            return;
        }

        // Tiến hành thêm tài khoản nếu không có lỗi
        UserDAO userDAO = new UserDAO();
        AccountsDAO accDAO = new AccountsDAO();
        int accountId = accDAO.addAccount(username, password, status);

        if (accountId > 0) {
            userDAO.addUser(fullName, email, phone, address, genderId, image, accountId, roleId);
            response.sendRedirect("admin_accounts");
        } else {
            errors.add("Có lỗi xảy ra khi thêm tài khoản.");
            request.setAttribute("errors", errors);
            request.getRequestDispatcher("/frontend/view/admin/admin_addaccount.jsp").forward(request, response);
        }
    }

}

