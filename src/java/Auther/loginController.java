/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Auther;
import Utils.Validation;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import DAO.AccountsDAO;
import DAO.UserDAO;
import Model.Accounts;
import Model.User;

/**
 *
 * @author regio
 */
public class loginController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     *
     * /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
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
            out.println("<title>Servlet loginController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet loginController at " + request.getContextPath() + "</h1>");
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
        request.getRequestDispatcher("/login.jsp").forward(request, response);
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
        AccountsDAO aDAO = new AccountsDAO();
        UserDAO uDAO = new UserDAO();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Accounts account = aDAO.getAccount(username, password);

        // Kiểm tra trạng thái tài khoản
        if (account == null) {
            request.setAttribute("error", "Invalid username or password");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
            return;
        }
        
        if (!Validation.isValidUsername(username) || !Validation.isValidPassword(password)) {
            request.setAttribute("error", "Username or password incorrect!");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
            return;
        }
        //  Kiểm tra trạng thái tài khoản (chỉ chạy khi account != null)
        if (!aDAO.isAccountActive(account.getAccountID())) {
            request.setAttribute("error", "Your account is deactivated.");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
            return;
        }

        // Kiểm tra role
        String role = aDAO.getRoleByUsernameAndPassword(username, password);

        if (role == null) {
            request.setAttribute("error", "Unauthorized access");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
            return;
        }

        Integer userId = uDAO.getUserIdByAccountId(account.getAccountID());

        // Lưu thông tin vào session
        HttpSession session = request.getSession();
        session.setAttribute("account", account);
        session.setAttribute("username", username);
        session.setAttribute("role", role);
        session.setAttribute("userId", userId);
        //Điều hướng dựa trên role
//        switch (role) {
//            case "Admin":
//                response.sendRedirect("Admin/dashboard.jsp");
//                break;
//            case "SurveyStaff":
//                response.sendRedirect("SurveyStaff/dashboard.jsp");
//                break;
//            default:
//                response.sendRedirect("homepage");
//                break;
//        }
        response.sendRedirect("homepage");
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
