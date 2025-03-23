package dao;

import DBConnect.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.CheckingForm;
import model.Customer;
import model.Service;
import model.Staff;

public class CheckingFormDAO extends DBContext {
    
    public void createCheckingForm(CheckingForm form) throws SQLException {
        String sql = "INSERT INTO CheckingForm (CustomerID, Name, Phone, Email, Address, "
                + "CheckingTime, TransportTime, ServiceID, Status, StaffID) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, form.getCustomer().getUserID());
            statement.setString(2, form.getName());
            statement.setString(3, form.getPhone());
            statement.setString(4, form.getEmail());
            statement.setString(5, form.getAddress());
            statement.setTimestamp(6, form.getCheckingTime());
            statement.setTimestamp(7, form.getTransportTime());
            statement.setInt(8, form.getService().getServiceID());
            statement.setString(9, form.getStatus());
            statement.setInt(10, form.getStaff().getStaffID());
            
            statement.executeUpdate();
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
    }
    
    public void updateStatus(int formId, String status) throws SQLException {
        String sql = "UPDATE CheckingForm SET Status = ? WHERE CheckingFormID = ?";
        
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, status);
            statement.setInt(2, formId);
            
            statement.executeUpdate();
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
    }
    
    public List<CheckingForm> getCheckingFormsByStaff(int staffId) throws SQLException {
        List<CheckingForm> forms = new ArrayList<>();
        String sql = "SELECT cf.*, c.*, s.*, st.* FROM CheckingForm cf "
                + "JOIN Users c ON cf.CustomerID = c.UserID "
                + "JOIN Services s ON cf.ServiceID = s.ServiceID "
                + "JOIN Staff st ON cf.StaffID = st.StaffID "
                + "WHERE cf.StaffID = ? ORDER BY cf.CheckingTime DESC";
        
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, staffId);
            result = statement.executeQuery();
            
            while (result.next()) {
                forms.add(mapResultSetToCheckingForm(result));
            }
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (result != null) {
                result.close();
            }
        }
        return forms;
    }
    
    public CheckingForm getCheckingFormById(int formId) throws SQLException {
        String sql = "SELECT cf.*, c.*, s.*, st.* FROM CheckingForm cf "
                + "JOIN Users c ON cf.CustomerID = c.UserID "
                + "JOIN Services s ON cf.ServiceID = s.ServiceID "
                + "JOIN Staff st ON cf.StaffID = st.StaffID "
                + "WHERE cf.CheckingFormID = ?";
        
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, formId);
            result = statement.executeQuery();
            
            if (result.next()) {
                return mapResultSetToCheckingForm(result);
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
    
    private CheckingForm mapResultSetToCheckingForm(ResultSet rs) throws SQLException {
        CheckingForm form = new CheckingForm();
        form.setCheckingFormID(rs.getInt("CheckingFormID"));
        form.setName(rs.getString("Name"));
        form.setPhone(rs.getString("Phone"));
        form.setEmail(rs.getString("Email"));
        form.setAddress(rs.getString("Address"));
        form.setCheckingTime(rs.getTimestamp("CheckingTime"));
        form.setTransportTime(rs.getTimestamp("TransportTime"));
        form.setStatus(rs.getString("Status"));
        
        // Map Customer
        Customer customer = new Customer();
        customer.setUserID(rs.getInt("CustomerID"));
        customer.setFullName(rs.getString("FullName"));
        customer.setPhone(rs.getString("Phone"));
        customer.setEmail(rs.getString("Email"));
        customer.setAddress(rs.getString("Address"));
        form.setCustomer(customer);
        
        // Map Service
        Service service = new Service();
        service.setServiceID(rs.getInt("ServiceID"));
        service.setServiceName(rs.getString("ServiceName"));
        service.setServiceDescribe(rs.getString("ServiceDescribe"));
        form.setService(service);
        
        // Map Staff
        Staff staff = new Staff();
        staff.setStaffID(rs.getInt("StaffID"));
        form.setStaff(staff);
        
        return form;
    }
}