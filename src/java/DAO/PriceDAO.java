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

    public PriceCost getPriceByID(int priceCostID) {
        String query = "SELECT * FROM PriceCost WHERE PriceCostID = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, priceCostID);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return PriceCost.builder()
                            .PriceCostID(resultSet.getInt("PriceCostID"))
                            .Description(resultSet.getString("Description"))
                            .Cost(resultSet.getDouble("Cost"))
                            .build();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null; // Trả về null nếu không tìm thấy
    }

    public boolean updatePrice(int priceCostID, String description, double cost) {
        String query = "UPDATE PriceCost SET Description = ?, Cost = ? WHERE PriceCostID = ?";
        try {
            statement = connection.prepareStatement(query);
            statement.setString(1, description);
            statement.setDouble(2, cost);
            statement.setInt(3, priceCostID);

            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0; // Trả về true nếu có ít nhất 1 hàng bị ảnh hưởng
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deletePrice(int priceCostID) {
        String query = "DELETE FROM PriceCost WHERE PriceCostID = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, priceCostID);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean addPrice(PriceCost priceCost) {
        String query = "INSERT INTO PriceCost (Description, Cost) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, priceCost.getDescription());
            statement.setDouble(2, priceCost.getCost());

            return statement.executeUpdate() > 0; // Trả về true nếu thêm thành công
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false; // Trả về false nếu có lỗi
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
