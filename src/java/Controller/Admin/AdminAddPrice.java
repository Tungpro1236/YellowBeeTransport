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

/**
 *
 * @author nguye
 */
public class AdminAddPrice extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Chuyển hướng đến trang nhập giá
        request.getRequestDispatcher("frontend/view/admin/admin_addprice.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String description = request.getParameter("description");
        double cost = Double.parseDouble(request.getParameter("cost"));

        PriceDAO priceDAO = new PriceDAO();
        PriceCost newPrice = PriceCost.builder()
                .Description(description)
                .Cost(cost)
                .build();

        boolean success = priceDAO.addPrice(newPrice);

        if (success) {
            response.sendRedirect("admin_price");
        } else {
            response.sendRedirect("admin_addprice");
        }
    }
}
