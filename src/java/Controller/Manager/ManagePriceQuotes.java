/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.Manager;

import DAO.ContractDAO;
import DAO.PriceQuoteDAO;
import DAO.StaffDAO;
import DAO.TruckDAO;
import Model.PriceQuote;
import Model.Staff;
import Model.Truck;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@WebServlet(name = "ManagePriceQuotes", urlPatterns = {"/ManagePriceQuotes"})
public class ManagePriceQuotes extends HttpServlet {

    private final PriceQuoteDAO priceQuoteDAO = new PriceQuoteDAO();
    private final StaffDAO staffDAO = new StaffDAO();
    private final TruckDAO truckDAO = new TruckDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<PriceQuote> priceQuotes = priceQuoteDAO.getAllPendingPriceQuotes(); // Chỉ lấy những cái chưa xử lý
        List<PriceQuote> canceledQuotes = priceQuoteDAO.getAllCanceledPriceQuotes(); // Lấy danh sách bị hủy
        request.setAttribute("priceQuotes", priceQuotes);
        request.setAttribute("canceledQuotes", canceledQuotes);
        request.getRequestDispatcher("/frontend/view/manager/managePriceQuotes.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String action = request.getParameter("action");

            // Kiểm tra priceQuoteID có hợp lệ không
            String priceQuoteIDParam = request.getParameter("priceQuoteID");
            if (priceQuoteIDParam == null || priceQuoteIDParam.isEmpty()) {
                request.setAttribute("error", "Thiếu Price Quote ID.");
                request.getRequestDispatcher("/frontend/view/manager/managePriceQuotes.jsp").forward(request, response);
                return;
            }
            int priceQuoteID = Integer.parseInt(priceQuoteIDParam);

            if ("cancel".equals(action)) {
                priceQuoteDAO.cancelPriceQuote(priceQuoteID);
                response.sendRedirect("ManagePriceQuotes");
                return;
            }

            if ("create".equals(action)) {
                PriceQuote priceQuote = priceQuoteDAO.getPriceQuoteByID(priceQuoteID);

                if (priceQuote == null) {
                    request.setAttribute("error", "Price Quote không tồn tại.");
                    request.getRequestDispatcher("/frontend/view/manager/managePriceQuotes.jsp").forward(request, response);
                    return;
                }

                List<Truck> availableTrucks = truckDAO.getAvailableTrucks();
                List<Staff> availableStaff = staffDAO.getAvailableMovingStaff();

                System.out.println("checkingFormID: " + priceQuote.getCheckingFormID());
                System.out.println("truckAmount " + priceQuote.getTruckAmount());
                System.out.println("finalCost " + priceQuote.getFinalCost());

                // Gửi thông tin Price Quote sang confirmContract.jsp
                request.setAttribute("priceQuoteID", priceQuote.getPriceQuoteID());
                request.setAttribute("checkingFormID", priceQuote.getCheckingFormID());
                request.setAttribute("truckAmount", priceQuote.getTruckAmount());
                request.setAttribute("staffAmount", priceQuote.getStaffAmount());
                request.setAttribute("finalCost", priceQuote.getFinalCost());

                request.setAttribute("availableTrucks", availableTrucks);
                request.setAttribute("availableStaff", availableStaff);

                request.getRequestDispatcher("/frontend/view/manager/confirmContract.jsp").forward(request, response);
                return;
            }

            if ("confirm".equals(action)) {
                
                String[] selectedTrucks = request.getParameterValues("selectedTrucks");
                String[] selectedStaff = request.getParameterValues("selectedStaff");
                
                System.out.println("Selected Trucks: " + Arrays.toString(selectedTrucks));
System.out.println("Selected Staff: " + Arrays.toString(selectedStaff));

                if (selectedTrucks == null || selectedTrucks.length == 0
                        || selectedStaff == null || selectedStaff.length == 0) {
                    request.setAttribute("error", "Vui lòng chọn đủ xe tải và nhân viên.");
                    request.getRequestDispatcher("/frontend/view/manager/confirmContract.jsp").forward(request, response);
                    return;
                }

                BigDecimal finalCost = new BigDecimal(request.getParameter("finalCost"));
                int checkingFormID = Integer.parseInt(request.getParameter("checkingFormID"));

                ContractDAO contractDAO = new ContractDAO();
                boolean success = contractDAO.createContract(priceQuoteID, selectedTrucks, selectedStaff, finalCost, checkingFormID);

                if (success) {
                    priceQuoteDAO.updateStatus(priceQuoteID, "Confirmed");
                    response.sendRedirect("ManagePriceQuotes");
                } else {
                    request.setAttribute("error", "Lỗi khi tạo hợp đồng. Vui lòng thử lại.");
                    request.getRequestDispatcher("/frontend/view/manager/confirmContract.jsp").forward(request, response);
                }
                return;
            }

            // Nếu action không hợp lệ
            request.setAttribute("error", "Hành động không hợp lệ.");
            request.getRequestDispatcher("/frontend/view/manager/managePriceQuotes.jsp").forward(request, response);

        } catch (NumberFormatException e) {
            request.setAttribute("error", "Dữ liệu không hợp lệ.");
            request.getRequestDispatcher("/frontend/view/manager/managePriceQuotes.jsp").forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
            request.setAttribute("error", "Đã xảy ra lỗi, vui lòng thử lại.");
            request.getRequestDispatcher("/frontend/view/manager/managePriceQuotes.jsp").forward(request, response);
        }
    }
}
