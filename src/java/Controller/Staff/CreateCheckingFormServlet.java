/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.Staff;

import DAO.CheckingFormDAO;
import DAO.CustomerDAO;
import DAO.ServiceDAO;
import DAO.StaffDAO;
import Model.CheckingForm;
import Model.Customer;
import Model.Service;
import Model.Staff;
import Model.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
@WebServlet("/create-checking-form")
public class CreateCheckingFormServlet extends HttpServlet {

    private final CheckingFormDAO checkingFormDAO = new CheckingFormDAO();
    private final CustomerDAO customerDAO = new CustomerDAO();
    private final ServiceDAO serviceDAO = new ServiceDAO();
    private final StaffDAO staffDAO = new StaffDAO();

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
            out.println("<title>Servlet CreateCheckingFormServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CreateCheckingFormServlet at " + request.getContextPath() + "</h1>");
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
        try {
            // Get all services for the dropdown
            request.setAttribute("services", serviceDAO.getAllServices());
        } catch (SQLException ex) {
            Logger.getLogger(CreateCheckingFormServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.getRequestDispatcher("/WEB-INF/views/staff/create_checking_form.jsp").forward(request, response);
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
        try {
            // Get session and staff info
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");
            Staff staff = staffDAO.getStaffByUserID(user.getUserId());

            // Get form data
            String customerName = request.getParameter("customerName");
            String phone = request.getParameter("phone");
            String email = request.getParameter("email");
            String address = request.getParameter("address");
            int serviceID = Integer.parseInt(request.getParameter("serviceID"));
            
            // Convert datetime strings to Timestamp
            String checkingTimeStr = request.getParameter("checkingTime");
            String transportTimeStr = request.getParameter("transportTime");
            
            // Parse the datetime strings and create Timestamps
            Timestamp checkingTime = Timestamp.valueOf(checkingTimeStr.replace('T', ' '));
            Timestamp transportTime = Timestamp.valueOf(transportTimeStr.replace('T', ' '));
            
            // Create or get customer
            Customer customer = customerDAO.getCustomerByPhone(phone);
            if (customer == null) {
                customer = new Customer();
                customer.setFullName(customerName);
                customer.setPhone(phone);
                customer.setEmail(email);
                customer.setAddress(address);
                customerDAO.createCustomer(customer);
            }

            // Create checking form
            CheckingForm form = new CheckingForm();
            form.setName(customerName);
            form.setPhone(phone);
            form.setEmail(email);
            form.setAddress(address);
            form.setCheckingTime(checkingTime);
            form.setTransportTime(transportTime);

            form.setServiceID(serviceID);
            //form.set(staff.getStaffId());
            //form.setCustomerID(customer);
            form.setStatus("pending");

            form.setServiceID(serviceID);
            form.setAssignedStaffID(staff.getStaffID());
            //form.setCustomer(customer);
            //form.setStatus("pending");


            // Save to database
            checkingFormDAO.createCheckingForm(form);

            // Redirect to checking forms list
            response.sendRedirect(request.getContextPath() + "/checking-forms");

        } catch (Exception e) {
            request.setAttribute("error", "Error creating checking form: " + e.getMessage());
            request.getRequestDispatcher("/WEB-INF/views/staff/create_checking_form.jsp").forward(request, response);
        }
    }
    

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Create Checking Form Servlet";
    }// </editor-fold>

}
