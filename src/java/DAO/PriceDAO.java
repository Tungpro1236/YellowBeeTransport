package DAO;

import DBConnect.DBContext;
import Model.PriceCost;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PriceDAO extends DBContext {


    public List<PriceCost> getPrice() {
        List<PriceCost> priceList = new ArrayList<>();
        String query = "SELECT * FROM PriceCost";
        try {
            statement = connection.prepareStatement(query);
            result = statement.executeQuery();

            //Attempt to get user information from database
            while (result.next()) {
                int PriceCostID = result.getInt("PriceCostID");
                String Description = result.getString("Description");
                double Cost = result.getDouble("Cost");
                
                PriceCost price = PriceCost.builder()
                        .PriceCostID(PriceCostID)
                        .Description(Description)
                        .Cost(Cost)
                        .build();

                priceList.add(price);
            }
            return priceList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public static void main(String[] args) {
        PriceDAO priceDAO = new PriceDAO();
        List<PriceCost> priceList = priceDAO.getPrice();
        
        if (priceList != null && !priceList.isEmpty()) {
            System.out.println("Danh sách giá:");
            for (PriceCost price : priceList) {
                System.out.println(price);
            }
        } else {
            System.out.println("Không có dữ liệu hoặc xảy ra lỗi.");
        }
    }
}
