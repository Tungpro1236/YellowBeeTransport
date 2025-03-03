package Controller;

import DAO.PriceDAO;
import Model.PriceCost;
import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class PriceServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PriceDAO priceDAO = new PriceDAO();
        List<PriceCost> listCost = priceDAO.getPrice();

        if (listCost == null || listCost.isEmpty()) {
            request.setAttribute("error", "Không có dữ liệu giá.");
        } else {
            request.setAttribute("listCost", listCost);
        }

        request.getRequestDispatcher("/frontend/view/price.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
