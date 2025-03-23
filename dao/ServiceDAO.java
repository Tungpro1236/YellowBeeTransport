package dao;

import DBConnect.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Service;

public class ServiceDAO extends DBContext {
    
    public List<Service> getAllServices() throws SQLException {
        List<Service> services = new ArrayList<>();
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
    
    public Service getServiceById(int serviceId) throws SQLException {
        String sql = "SELECT * FROM Services WHERE ServiceID = ?";
        
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, serviceId);
            result = statement.executeQuery();
            
            if (result.next()) {
                return mapResultSetToService(result);
            }
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (result != null) {
                result.close();
            }
        }
        return null;
    }
    
    private Service mapResultSetToService(ResultSet rs) throws SQLException {
        Service service = new Service();
        service.setServiceID(rs.getInt("ServiceID"));
        service.setServiceName(rs.getString("ServiceName"));
        service.setServiceDescribe(rs.getString("ServiceDescribe"));
        service.setServiceImage(rs.getString("ServiceImage"));
        return service;
    }
} 