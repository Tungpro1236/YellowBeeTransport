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
import java.util.List;

/**
 *
 * @author nguye
 */
public class AdminManagePrice extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String role = (String) session.getAttribute("role");

        if (role == null || !role.equals("Admin")) {
            request.getRequestDispatcher("/frontend/view/access_denied.jsp").forward(request, response);
            return;
        }
        PriceDAO priceDAO = new PriceDAO();
        List<PriceCost> listCost = priceDAO.getPrice();

        if (listCost == null || listCost.isEmpty()) {
            request.setAttribute("error", "Không có dữ liệu giá.");
        } else {
            request.setAttribute("listCost", listCost);
        }
        request.getRequestDispatcher("/frontend/view/admin/admin_manageprice.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PriceDAO priceDAO = new PriceDAO();
        String action = request.getParameter("action");

        if ("delete".equals(action)) {
            int priceCostID = Integer.parseInt(request.getParameter("priceCostID"));
            boolean deleted = priceDAO.deletePrice(priceCostID);

            if (deleted) {
                response.sendRedirect("admin_price");
            } else {
                response.sendRedirect("admin_price");
            }
        }
    }

}
