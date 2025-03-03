/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Auther;


import DAO.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

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
        // Lấy dữ liệu từ form JSP
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        // Lấy thông tin từ form đăng ký
        String fullName = request.getParameter("fullname");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        int genderID = Integer.parseInt(request.getParameter("gender"));
        int roleID = 5; // Role mặc định là Customer
        int accountStatusID = 1; // Trạng thái mặc định là Active

        UserDAO userDAO = new UserDAO();

        // Kiểm tra username đã tồn tại chưa
        if (userDAO.isUsernameOrEmailTaken(username,email)) {
            request.setAttribute("errorMessage", "Username already exists! Please choose another.");
            request.getRequestDispatcher("signup.jsp").forward(request, response);
            return;
        }

        // Tạo tài khoản trong bảng Accounts
        int accountID = userDAO.createAccount(username, password, accountStatusID);
        if (accountID == -1) {
            request.setAttribute("errorMessage", "Error creating account. Please try again.");
            request.getRequestDispatcher("signup.jsp").forward(request, response);
            return;
        }

        // Tạo user trong bảng Users
        boolean userCreated = userDAO.createUser(fullName, email, phone, address, genderID, accountID, roleID);
        if (userCreated) {
            response.sendRedirect("login.jsp"); // Chuyển hướng đến login.jsp với thông báo thành công
        } else {
            request.setAttribute("errorMessage", "Error creating user. Please try again.");
            request.getRequestDispatcher("signup.jsp").forward(request, response);
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
