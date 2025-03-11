/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.Admin;

import DAO.PriceDAO;
import Model.PriceCost;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author nguye
 */
public class AdminUpdatePrice extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String role = (String) session.getAttribute("role");

        if (role == null || !role.equals("Admin")) {
            request.getRequestDispatcher("/frontend/view/access_denied.jsp").forward(request, response);
            return;
        }
        int priceCostID = Integer.parseInt(request.getParameter("priceCostID"));
        PriceDAO dao = new PriceDAO();
        PriceCost price = dao.getPriceByID(priceCostID);
        request.setAttribute("price", price);
        request.getRequestDispatcher("frontend/view/admin/admin_updateprice.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("priceCostID"));
        String description = request.getParameter("description");
        double cost = Double.parseDouble(request.getParameter("cost"));

        PriceDAO dao = new PriceDAO();
        boolean success = dao.updatePrice(id, description, cost);

        if (success) {
            response.sendRedirect("admin_price");
        } else {
            response.sendRedirect("admin_updateprice?priceCostID=" + id + "&error=1");
        }
    }
}
