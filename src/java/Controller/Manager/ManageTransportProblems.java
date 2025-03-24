/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controller.Manager;

import DAO.TransportProblemDAO;
import Model.TransportProblemForm;
import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ManageTransportProblems")
public class ManageTransportProblems extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TransportProblemDAO problemDAO = new TransportProblemDAO();
        List<TransportProblemForm> problemList = problemDAO.getAllTransportProblems();
        
        request.setAttribute("problemList", problemList);
        request.getRequestDispatcher("/frontend/view/manager/manageTransportProblems.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        int tpfID = Integer.parseInt(request.getParameter("tpfID"));
        int contractID = Integer.parseInt(request.getParameter("contractID"));
        double problemCost = Double.parseDouble(request.getParameter("problemCost"));
        TransportProblemDAO problemDAO = new TransportProblemDAO();

        if ("compensate".equals(action)) {
            problemDAO.applyCompensation(contractID, problemCost);
            problemDAO.updateProblemStatus(tpfID, "Compensated");
        } else if ("cancel".equals(action)) {
            problemDAO.cancelContract(contractID);
            problemDAO.updateProblemStatus(tpfID, "Cancelled");
        }

        response.sendRedirect("ManageTransportProblems");
    }
}
