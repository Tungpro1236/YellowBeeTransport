/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller.Manager;

import DAO.StaffDAO;
import DAO.TruckDAO;
import Model.Staff;
import Model.Truck;
import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "ManageStaffVehicles", urlPatterns = {"/ManageStaffVehicles"})
public class ManageStaffVehicles extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        StaffDAO staffDAO = new StaffDAO();
        TruckDAO truckDAO = new TruckDAO();

        // Lấy danh sách nhân viên và xe
        List<Staff> staffList = staffDAO.getAllStaff();
        List<Truck> truckList = truckDAO.getAllTrucks();

        // Đặt dữ liệu vào request
        request.setAttribute("staffList", staffList);
        request.setAttribute("truckList", truckList);

        // Chuyển hướng đến trang JSP
        request.getRequestDispatcher("/frontend/view/manager/manageStaffVehicles.jsp").forward(request, response);
    }
}
