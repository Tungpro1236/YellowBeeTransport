/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.UserDAO;
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
 * @author regio
 */
public class UpdateProfile extends HttpServlet {

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
            out.println("<title>Servlet UpdateProfile</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateProfile at " + request.getContextPath() + "</h1>");
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
        HttpSession session = request.getSession(false); // Không tạo session mới nếu mất
        if (session == null || session.getAttribute("userId") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        int userId = (Integer) session.getAttribute("userId"); // Lấy userId từ session

        UserDAO userDAO = new UserDAO();
        User user = userDAO.getUserById(userId);// Lấy thông tin user từ database

        if (user != null) {
            request.setAttribute("user", user);
            request.getRequestDispatcher("editProfile.jsp").forward(request, response);
        } else {
            response.sendRedirect("login.jsp"); // Nếu user null, yêu cầu đăng nhập
        }
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
        HttpSession session = request.getSession(false); // Không tạo session mới
        if (session == null || session.getAttribute("userId") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        int userId = (Integer) session.getAttribute("userId");

        // Lấy dữ liệu từ form
        String fullName = request.getParameter("fullName");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String genderIdStr = request.getParameter("genderId");

        int genderId = 0; // Giá trị mặc định
        if (genderIdStr != null && !genderIdStr.isEmpty()) {
            try {
                genderId = Integer.parseInt(genderIdStr);
            } catch (NumberFormatException e) {
                response.sendRedirect("editProfile.jsp?error=invalidGender");
                return;
            }
        } else {
            response.sendRedirect("editProfile.jsp?error=missingGender");
            return;
        }

        UserDAO userDAO = new UserDAO();
        User user = userDAO.getUserById(userId); // Lấy user từ DB

        if (user != null) {
            user.setFullName(fullName);
            user.setPhone(phone);
            user.setAddress(address);
            user.setGenderId(genderId);

            boolean isUpdated = userDAO.updateUser(user);
            if (isUpdated) {
                session.setAttribute("user", user); // Cập nhật lại session
                response.sendRedirect("userProfile.jsp?success=true");
            } else {
                response.sendRedirect("editProfile.jsp?error=true");
            }
        } else {
            response.sendRedirect("login.jsp");
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
