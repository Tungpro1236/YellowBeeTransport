package Controller.Admin;

import DAO.UserDAO;
import DAO.RoleDAO;
import Model.User;
import Model.Roles;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class AdminManageUser extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String role = (String) session.getAttribute("role");

        if (role == null || !role.equals("Admin")) {
            request.getRequestDispatcher("/frontend/view/access_denied.jsp").forward(request, response);
            return;
        }

        UserDAO userDAO = new UserDAO();
        RoleDAO roleDAO = new RoleDAO();

        List<User> userList = userDAO.getAllUsers();
        List<Roles> roleList = roleDAO.getAllRoles();

        request.setAttribute("userList", userList);
        request.setAttribute("roleList", roleList != null ? roleList : List.of());
        request.getRequestDispatcher("/frontend/view/admin/admin_manageuser.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String role = (String) session.getAttribute("role");

        if (role == null || !role.equals("Admin")) {
            request.getRequestDispatcher("/frontend/view/access_denied.jsp").forward(request, response);
            return;
        }

        String searchQuery = request.getParameter("search");
        String roleFilter = request.getParameter("roleFilter");

        UserDAO userDAO = new UserDAO();
        RoleDAO roleDAO = new RoleDAO();

        List<User> userList;
        Integer roleId = null;

        if (searchQuery != null && !searchQuery.trim().isEmpty()) {
            userList = userDAO.searchUserByName(searchQuery);
        } else if (roleFilter != null && !roleFilter.trim().isEmpty()) {
            try {
                roleId = Integer.parseInt(roleFilter);
                userList = userDAO.getUsersByRole(roleId);
            } catch (NumberFormatException e) {
                roleId = null;
                userList = userDAO.getAllUsers();
            }
        } else {
            userList = userDAO.getAllUsers();
        }

        List<Roles> roleList = roleDAO.getAllRoles();

        request.setAttribute("userList", userList);
        request.setAttribute("roleList", roleList != null ? roleList : List.of());
        request.setAttribute("searchQuery", searchQuery);
        request.setAttribute("roleFilter", roleId);

        request.getRequestDispatcher("/frontend/view/admin/admin_manageuser.jsp").forward(request, response);
    }
}
