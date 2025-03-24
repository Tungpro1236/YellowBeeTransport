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

        // Lấy danh sách nhân viên chia theo nhóm
        List<Staff> surveyStaffList = staffDAO.getAllSurveyStaff(); // RoleID = 1
        List<Staff> movingStaffList = staffDAO.getAllMovingStaff(); // RoleID = 2

        // Lấy danh sách xe tải
        List<Truck> truckList = truckDAO.getAllTrucks();

        // Debug log dữ liệu
        System.out.println("Survey Staff List:");
        for (Staff s : surveyStaffList) {
            System.out.println(s.getStaffID() + " | Contract: " + s.getCurrentContractID() + " | Checking Form: " + s.getCurrentCheckingFormID());
        }

        System.out.println("Moving Staff List:");
        for (Staff s : movingStaffList) {
            System.out.println(s.getStaffID() + " | Contract: " + s.getCurrentContractID() + " | Checking Form: " + s.getCurrentCheckingFormID());
        }

        // Kiểm tra dữ liệu xe tải
        System.out.println("Truck List:");
        for (Truck t : truckList) {
            System.out.println(t.getTruckID() + " | Contract: " + t.getCurrentContractID());
        }

        // Đặt dữ liệu vào request
        request.setAttribute("surveyStaffList", surveyStaffList);
        request.setAttribute("movingStaffList", movingStaffList);
        request.setAttribute("truckList", truckList);

        // Chuyển hướng đến trang JSP
        request.getRequestDispatcher("/frontend/view/manager/manageStaffVehicles.jsp").forward(request, response);
    }
}
