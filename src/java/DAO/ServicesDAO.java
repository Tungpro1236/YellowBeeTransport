package DAO;

import DBConnect.DBContext;
import Model.Services;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ServicesDAO extends DBContext {

    DBContext dbc = new DBContext();

    public Services getServiceById(int id) {
        Services service = null;
        String query = "SELECT * FROM Services WHERE ServiceID = ?";

        try {
            //Replace '?' in query with user credential and execute the statement
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            result = statement.executeQuery();

            //Attempt to get user information from database
            while (result.next()) {
                int serviceID = result.getInt("serviceID");
                String serviceName = result.getString("serviceName");
                String serviceDescribe = result.getString("serviceDescribe");
                String serviceImage = result.getString("serviceImage");

                service = Services.builder().
                        serviceID(serviceID).
                        serviceName(serviceName).
                        serviceDescribe(serviceDescribe).
                        serviceImage(serviceImage)
                        .build();
            }
            return service;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Services> getAllServices() throws SQLException {
        List<Services> services = new ArrayList<>();
        String sql = "SELECT * FROM Services ORDER BY ServiceID";

        try {
            statement = connection.prepareStatement(sql);
            result = statement.executeQuery();

            while (result.next()) {
                services.add(mapResultSetToService(result));
            }
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (result != null) {
                result.close();
            }
        }
        return services;
    }

    private Services mapResultSetToService(ResultSet rs) throws SQLException {
        Services services = Services.builder()
                .serviceID(rs.getInt("ServiceID"))
                .serviceName(rs.getString("ServiceName"))
                .serviceDescribe(rs.getString("ServiceDescribe"))
                .serviceImage(rs.getString("ServiceImage"))
                .build();
        return services;
    }

    public static void main(String[] args) {
        // Khởi tạo đối tượng ServicesDAO
        ServicesDAO servicesDAO = new ServicesDAO();

        // Test phương thức getServiceById
        int testId = 1;  // Thay đổi ID này theo dữ liệu trong database của bạn
        Services service = servicesDAO.getServiceById(testId);

        // In kết quả để kiểm tra
        if (service != null) {
            System.out.println("Service ID: " + service);
        } else {
            System.out.println("Không tìm thấy dịch vụ với ID: " + testId);
        }
    }

}
