/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Auther;

import DAO.AccountsDAO;
import DAO.UserDAO;
import Utils.Validation;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 *
 * @author regio
 */

public class signupController extends HttpServlet {

    
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet signupController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet signupController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            request.getRequestDispatcher("/signup.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<String> errors = new ArrayList<>();

        // Lấy dữ liệu từ form đăng ký
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String fullName = request.getParameter("fullName");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String image = request.getParameter("image");
        String genderParam = request.getParameter("gender");

        // Validate dữ liệu đầu vào
        if (!Validation.isValidUsername(username)) {
            errors.add("Username phải có ít nhất 5 ký tự, chỉ chứa chữ và số.");
        }
        if (!Validation.isValidPassword(password)) {
            errors.add("Password phải có ít nhất 3 ký tự, chỉ chứa chữ và số.");
        }
        if (!Validation.isValidName(fullName)) {
            errors.add("Họ tên phải bắt đầu bằng chữ hoa và chỉ chứa chữ.");
        }
        if (!Validation.isValidEmail(email)) {
            errors.add("Email không hợp lệ.");
        }
        if (!Validation.isValidAddress(address)) {
            errors.add("Địa chỉ không hợp lệ.");
        }
        if (!Validation.isValidPhoneNumber(phone)) {
            errors.add("Số điện thoại phải có đúng 10 chữ số.");
        }
        if (!Validation.isValidImageURL(image)) {
            errors.add("URL ảnh không hợp lệ.");
        }
        if (genderParam == null || genderParam.isEmpty()) {
            errors.add("Vui lòng chọn giới tính.");
        }

        int genderId = genderParam != null ? Integer.parseInt(genderParam) : 0;
        int roleId = 5; // Role 5: Customer
        int status = 1; // Mặc định tài khoản active

        // Kiểm tra username, email, phone đã tồn tại chưa
        AccountsDAO accDAO = new AccountsDAO();
        UserDAO userDAO = new UserDAO();

        if (accDAO.isUsernameExist(username)) {
            errors.add("Username đã tồn tại. Vui lòng chọn username khác.");
        }
        if (userDAO.isMailExist(email)) {
            errors.add("Email đã tồn tại. Vui lòng sử dụng email khác.");
        }
        if (userDAO.isPhoneExist(phone)) {
            errors.add("Số điện thoại đã tồn tại. Vui lòng sử dụng số khác.");
        }

        // Nếu có lỗi, trả về trang đăng ký kèm thông báo
        if (!errors.isEmpty()) {
            request.setAttribute("errors", errors);
            request.setAttribute("username", username);
            request.setAttribute("fullName", fullName);
            request.setAttribute("email", email);
            request.setAttribute("phone", phone);
            request.setAttribute("address", address);
            request.setAttribute("image", image);
            request.setAttribute("gender", genderId);
            request.getRequestDispatcher("/signup.jsp").forward(request, response);
            return;
        }

        // Nếu không có lỗi, tiến hành đăng ký tài khoản
        int accountId = accDAO.addAccount(username, password, status);
        if (accountId > 0) {
            userDAO.addUser(fullName, email, phone, address, genderId, image, accountId, roleId);
            response.sendRedirect("login"); // Sau khi đăng ký thành công, chuyển đến trang đăng nhập
        } else {
            errors.add("Lỗi xảy ra khi tạo tài khoản.");
            request.setAttribute("errors", errors);
            request.getRequestDispatcher("/signup.jsp").forward(request, response);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
