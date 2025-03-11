package Controller.Admin;

import DAO.AccountsDAO;
import DAO.RoleDAO;
import DAO.UserDAO;
import Model.Roles;
import Utils.Validation;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

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

        // Retrieve form data
        String roleFilter = request.getParameter("roleFilter");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String fullName = request.getParameter("fullName");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String image = request.getParameter("image");
        String genderParam = request.getParameter("gender");

        // Validate input data
        if (roleFilter == null || roleFilter.isEmpty()) {
            errors.add("Please select a role.");
        }
        if (!Validation.isValidUsername(username)) {
            errors.add("Invalid username. It must be at least 5 characters long and contain only letters and numbers.");
        }
        if (!Validation.isValidPassword(password)) {
            errors.add("Invalid password. It must be at least 3 characters long and contain only letters and numbers.");
        }
        if (!Validation.isValidName(fullName)) {
            errors.add("Invalid full name. It must start with a capital letter and contain only letters.");
        }
        if (!Validation.isValidEmail(email)) {
            errors.add("Invalid email format.");
        }
        if (!Validation.isValidAddress(address)) {
            errors.add("Invalid address format.");
        }
        if (!Validation.isValidPhoneNumber(phone)) {
            errors.add("Invalid phone number. It must contain exactly 10 digits.");
        }
        if(!Validation.isValidImageURL(image)){
            errors.add("Invalid image url");
        }
        if (genderParam == null || genderParam.isEmpty()) {
            errors.add("Please select a gender.");
        }

        int roleId = roleFilter != null ? Integer.parseInt(roleFilter) : 0;
        int genderId = genderParam != null ? Integer.parseInt(genderParam) : 0;
        int status = 1;

        // Check for existing username, email, and phone
        AccountsDAO accDAO = new AccountsDAO();
        UserDAO userDAO = new UserDAO();

        if (accDAO.isUsernameExist(username)== true) {
            errors.add("Username already exists. Please choose another one.");
        }
        if (userDAO.isMailExist(email)==true) {
            errors.add("Email already exists. Please use a different email.");
        }
        if (userDAO.isPhoneExist(phone)==true) {
            errors.add("Phone number already exists. Please use a different number.");
        }

        // If errors exist, return to form with messages
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

        // Proceed to add account if no errors
        int accountId = accDAO.addAccount(username, password, status);
        if (accountId > 0) {
            userDAO.addUser(fullName, email, phone, address, genderId, image, accountId, roleId);
            response.sendRedirect("admin_accounts");
        } else {
            errors.add("An error occurred while adding the account.");
            request.setAttribute("errors", errors);
            request.getRequestDispatcher("/frontend/view/admin/admin_addaccount.jsp").forward(request, response);
        }
    }
}
