/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.Customer;

import DAO.RequestDAO;
import DAO.StaffDAO;
import Model.Staff;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 *
 * @author regio
 */
public class CustomerRequest extends HttpServlet {

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
            out.println("<title>Servlet CustomerRequest</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CustomerRequest at " + request.getContextPath() + "</h1>");
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
        HttpSession session = request.getSession();
        String role = (String) session.getAttribute("role");
        Integer userId = (Integer) session.getAttribute("userId");
        if (role == null || !role.equals("Customer")) {
            request.getRequestDispatcher("/frontend/view/access_denied.jsp").forward(request, response);
            return;
        }
        request.getRequestDispatcher("/frontend/view/customer/customer_request.jsp").forward(request, response);
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
        // Lấy userId từ session
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId");

        // Nếu chưa đăng nhập, chuyển hướng về trang lỗi
        if (userId == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        // Lấy thông tin từ form
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
      

        // Định dạng thời gian theo chuẩn HTML datetime-local
        

        // Lấy dữ liệu từ form
        String checkingTimeStr = request.getParameter("checkingTime");
        String transportTimeStr = request.getParameter("transportTime");

        // Kiểm tra nếu giá trị null
        if (checkingTimeStr == null || transportTimeStr == null) {
            response.getWriter().write("Lỗi: Chưa nhập đủ thông tin thời gian!");
            return;
        }

        // Chuyển đổi từ String sang LocalDateTime rồi sang Timestamp
        Timestamp checkingTime = null;
        Timestamp transportTime = null;

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
            LocalDateTime checkingLocalDateTime = LocalDateTime.parse(checkingTimeStr, formatter);
            LocalDateTime transportLocalDateTime = LocalDateTime.parse(transportTimeStr, formatter);
            checkingTime = Timestamp.valueOf(checkingLocalDateTime);
            transportTime = Timestamp.valueOf(transportLocalDateTime);
        } catch (Exception e) {
            request.setAttribute("error", "Lỗi: Định dạng thời gian không hợp lệ!");
            request.getRequestDispatcher("/frontend/view/customer/customer_request.jsp").forward(request, response);
            return;
        }

        // Gọi DAO để lưu vào database
        RequestDAO requestDAO = new RequestDAO();
        boolean success = requestDAO.createCheckingForm(userId, name,
                phone, email, address, checkingTime, transportTime);

        // Điều hướng sau khi xử lý xong
        if (success) {
            response.sendRedirect("customer_dashboard.jsp");  // Chuyển hướng đến trang thông báo thành công
        } else {
            request.setAttribute("error", "Lỗi khi tạo yêu cầu khảo sát!");
            request.getRequestDispatcher("/frontend/view/customer/customer_request.jsp").forward(request, response);
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
