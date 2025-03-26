package Controller.Staff;

import dao.ContractDAO;
import dao.TransportProblemDAO;
import model.Contract;
import model.TransportProblem;
import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/moving-staff-dashboard/*")
public class MovingStaffDashboardServlet extends HttpServlet {
    private ContractDAO contractDAO;
    private TransportProblemDAO transportProblemDAO;
    
    @Override
    public void init() throws ServletException {
        contractDAO = new ContractDAO();
        transportProblemDAO = new TransportProblemDAO();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        HttpSession session = request.getSession();
        Integer staffId = (Integer) session.getAttribute("staffId");
        
        if (staffId == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        
        try {
            if (pathInfo == null || pathInfo.equals("/")) {
                // Load work list and contracts for dashboard
                List<Contract> assignedContracts = contractDAO.getContractsByStaffId(staffId);
                request.setAttribute("assignedContracts", assignedContracts);
                request.getRequestDispatcher("/WEB-INF/views/staff/MovingStaff_dashboard.jsp")
                      .forward(request, response);
            } else if (pathInfo.equals("/work-list")) {
                // Show work list
                List<Contract> workList = contractDAO.getActiveContractsByStaffId(staffId);
                request.setAttribute("workList", workList);
                request.getRequestDispatcher("/WEB-INF/views/staff/work_list.jsp")
                      .forward(request, response);
            } else if (pathInfo.equals("/contracts")) {
                // Show contracts
                List<Contract> contracts = contractDAO.getContractsByStaffId(staffId);
                request.setAttribute("contracts", contracts);
                request.getRequestDispatcher("/WEB-INF/views/staff/contracts.jsp")
                      .forward(request, response);
            }
        } catch (Exception e) {
            throw new ServletException("Error processing request", e);
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        HttpSession session = request.getSession();
        Integer staffId = (Integer) session.getAttribute("staffId");
        
        if (staffId == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        
        try {
            if (pathInfo.equals("/add-problem")) {
                // Add transport problem
                int contractId = Integer.parseInt(request.getParameter("contractId"));
                String description = request.getParameter("problemDescription");
                double problemCost = Double.parseDouble(request.getParameter("problemCost"));
                
                TransportProblem problem = new TransportProblem();
                problem.setContractID(contractId);
                problem.setProblemDescription(description);
                problem.setProblemCost(problemCost);
                problem.setStaffID(staffId);
                
                transportProblemDAO.createTransportProblem(problem);
                response.sendRedirect(request.getContextPath() + "/moving-staff-dashboard/contracts");
            }
        } catch (Exception e) {
            throw new ServletException("Error processing request", e);
        }
    }
} 