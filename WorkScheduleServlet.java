package servlet;

import dao.WorkShiftDAO;
import dao.StaffDAO;
import java.io.IOException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.User;
import model.Staff;
import model.WorkShift;

@WebServlet(name = "WorkScheduleServlet", urlPatterns = {"/work_schedule"})
public class WorkScheduleServlet extends HttpServlet {
    
    private final WorkShiftDAO workShiftDAO = new WorkShiftDAO();
    private final StaffDAO staffDAO = new StaffDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Get session and verify user role
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");
            if (user == null || user.getRole().getRoleID() != 2) { // 2 = MovingStaff
                response.sendRedirect("login");
                return;
            }

            // Get staff member
            Staff staff = staffDAO.getStaffByUserID(user.getUserID());

            // Get current month or specified month
            String monthParam = request.getParameter("month");
            YearMonth yearMonth;
            if (monthParam != null && !monthParam.isEmpty()) {
                yearMonth = YearMonth.parse(monthParam);
            } else {
                yearMonth = YearMonth.now();
            }

            // Get all shifts for the month
            List<WorkShift> monthlyShifts = workShiftDAO.getShiftsByStaffAndMonth(staff.getStaffID(), yearMonth);

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
} 