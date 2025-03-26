package servlet;

import dao.CheckingFormDAO;
import dao.CustomerDAO;
import dao.ServiceDAO;
import dao.StaffDAO;
import java.io.IOException;
import java.sql.Timestamp;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.CheckingForm;
import model.Customer;
import model.Service;
import model.Staff;
import model.User;

@WebServlet(name = "CreateCheckingFormServlet", urlPatterns = {"/create_checking_form"})
public class CreateCheckingFormServlet extends HttpServlet {
    
    private final CheckingFormDAO checkingFormDAO = new CheckingFormDAO();
    private final CustomerDAO customerDAO = new CustomerDAO();
    private final ServiceDAO serviceDAO = new ServiceDAO();
    private final StaffDAO staffDAO = new StaffDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get all services for the dropdown
        request.setAttribute("services", serviceDAO.getAllServices());
        request.getRequestDispatcher("create_checking_form.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Get session and staff info
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");
            Staff staff = staffDAO.getStaffByUserID(user.getUserID());

            // Get form data
            String customerName = request.getParameter("customerName");
            String phone = request.getParameter("phone");
            String email = request.getParameter("email");
            String address = request.getParameter("address");
            int serviceID = Integer.parseInt(request.getParameter("serviceID"));
            
            // Fix datetime format for Timestamp conversion
            String checkingTimeStr = request.getParameter("checkingTime").replace('T', ' ') + ":00";
            String transportTimeStr = request.getParameter("transportTime").replace('T', ' ') + ":00";
            
            Timestamp checkingTime = Timestamp.valueOf(checkingTimeStr);
            Timestamp transportTime = Timestamp.valueOf(transportTimeStr);
            
            String notes = request.getParameter("notes");

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

            // Get service
            Service service = serviceDAO.getServiceById(serviceID);

            // Create checking form
            CheckingForm form = new CheckingForm();
            form.setCustomer(customer);
            form.setName(customerName);
            form.setPhone(phone);
            form.setEmail(email);
            form.setAddress(address);
            form.setCheckingTime(checkingTime);
            form.setTransportTime(transportTime);
            form.setService(service);
            form.setStatus("pending");
            form.setStaff(staff);

            // Save to database
            checkingFormDAO.createCheckingForm(form);

            // Redirect to checking forms list
            response.sendRedirect("checking_forms");

        } catch (Exception e) {
            request.setAttribute("error", "Error creating checking form: " + e.getMessage());
            request.getRequestDispatcher("create_checking_form.jsp").forward(request, response);
        }
    }
} 