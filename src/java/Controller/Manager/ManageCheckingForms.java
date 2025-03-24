/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.Manager;

import DAO.CheckingFormDAO;
import DBConnect.DBContext;
import Model.CheckingForm;
import Model.Staff;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;

@WebServlet(name = "ManageCheckingForms", urlPatterns = {"/ManageCheckingForms"})
public class ManageCheckingForms extends HttpServlet {

    private CheckingFormDAO checkingFormDAO;

    @Override
    public void init() throws ServletException {
        DBContext db = new DBContext();
        Connection connection = db.connection;
        checkingFormDAO = new CheckingFormDAO(connection);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lấy danh sách Checking Forms
        List<CheckingForm> pendingForms = checkingFormDAO.getCheckingFormsByStatus("Pending");
        List<CheckingForm> approvedForms = checkingFormDAO.getCheckingFormsByStatus("Approved");
        List<CheckingForm> rejectedForms = checkingFormDAO.getCheckingFormsByStatus("Rejected");

        // Lấy danh sách nhân viên rảnh
        List<Staff> staffList = checkingFormDAO.getAvailableSurveyStaff();

        System.out.println("Danh sách nhân viên rảnh:");
        if (staffList != null) {
            for (Staff s : staffList) {
                System.out.println(s.getStaffID() + " - " + s.getFullName());
            }
        } else {
            System.out.println("staffList is NULL");
        }

        // Đưa dữ liệu vào request scope
        request.setAttribute("pendingForms", pendingForms);
        request.setAttribute("approvedForms", approvedForms);
        request.setAttribute("rejectedForms", rejectedForms);
        request.setAttribute("staffList", staffList);

        // Chuyển hướng đến JSP
        request.getRequestDispatcher("/frontend/view/manager/manageCheckingForms.jsp").forward(request, response);
    }

    @Override
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String action = request.getParameter("action");
    int checkingFormID = Integer.parseInt(request.getParameter("checkingFormID"));

    if ("approve".equals(action)) {
    CheckingForm form = checkingFormDAO.getCheckingFormByID(checkingFormID);
    List<Staff> availableSurveyStaff = checkingFormDAO.getAvailableSurveyStaff();

    request.setAttribute("checkingForm", form);
    request.setAttribute("availableSurveyStaff", availableSurveyStaff);
    
    request.getRequestDispatcher("/frontend/view/manager/confirmCheckingForm.jsp").forward(request, response);
    return;
}

    if ("confirmAssign".equals(action)) {
        int staffID = Integer.parseInt(request.getParameter("staffID"));
        checkingFormDAO.assignStaffToCheckingForm(checkingFormID, staffID);
        checkingFormDAO.updateCheckingFormStatus(checkingFormID, "Approved");
    } else if ("reject".equals(action)) {
        checkingFormDAO.updateCheckingFormStatus(checkingFormID, "Rejected");
    }

    // Reload danh sách sau khi thực hiện action
    response.sendRedirect("ManageCheckingForms");
}

}
