/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.Staff;

import DAO.StaffDAO;
import DAO.WorkShiftDAO;
import Model.Staff;
import Model.User;
import Model.WorkShift;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.time.YearMonth;
import java.util.List;

/**
 *
 * @author Admin
 */
@WebServlet(name = "WorkScheduleServlet", urlPatterns = {"/workschedule"})
public class WorkScheduleServlet extends HttpServlet {

    private final WorkShiftDAO workShiftDAO = new WorkShiftDAO();
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
            out.println("<title>Servlet WorkScheduleServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet WorkScheduleServlet at " + request.getContextPath() + "</h1>");
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
            // Get session and verify user role
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");
            if (user == null || user.getRoleId() != 2) { // 2 = MovingStaff
                response.sendRedirect("login");
                return;
            }

            // Get staff member
            Staff staff = staffDAO.getStaffByUserID(user.getUserId());

            // Get current month or specified month
            String monthParam = request.getParameter("month");
            YearMonth yearMonth;
            if (monthParam != null && !monthParam.isEmpty()) {
                yearMonth = YearMonth.parse(monthParam);
            } else {
                yearMonth = YearMonth.now();
            }

            // Get all shifts for the month
//            List<WorkShift> monthlyShifts = workShiftDAO.getShiftsByStaffAndMonth(staff.getStaffID(), yearMonth);
            List<WorkShift> monthlyShifts = workShiftDAO.getShiftsByMonth(staff.getStaffID(), yearMonth.getYear(), yearMonth.getMonthValue());

            // Get upcoming shifts (next 7 days)
            List<WorkShift> upcomingShifts = workShiftDAO.getUpcomingShifts(staff.getStaffID(), 7);

            // Set attributes for JSP
            request.setAttribute("currentMonth", yearMonth);
            request.setAttribute("monthlyShifts", monthlyShifts);
            request.setAttribute("upcomingShifts", upcomingShifts);

            // Forward to JSP
            request.getRequestDispatcher("work_schedule.jsp").forward(request, response);

        } catch (Exception e) {
            request.setAttribute("error", "Error loading work schedule: " + e.getMessage());
            request.getRequestDispatcher("work_schedule.jsp").forward(request, response);
        }
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
        processRequest(request, response);
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
