package dao;

import DBConnect.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import model.WorkShift;
import model.Staff;

public class WorkShiftDAO extends DBContext {
    
    public List<WorkShift> getShiftsByMonth(int staffId, int year, int month) throws SQLException {
        List<WorkShift> shifts = new ArrayList<>();
        String sql = "SELECT ws.*, st.FullName as StaffName " +
                    "FROM WorkShifts ws " +
                    "JOIN Staff st ON ws.StaffID = st.StaffID " +
                    "WHERE ws.StaffID = ? " +
                    "AND YEAR(ws.ShiftDate) = ? " +
                    "AND MONTH(ws.ShiftDate) = ? " +
                    "ORDER BY ws.ShiftDate";
        
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, staffId);
            statement.setInt(2, year);
            statement.setInt(3, month);
            result = statement.executeQuery();
            
            while (result.next()) {
                shifts.add(mapResultSetToWorkShift(result));
            }
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (result != null) {
                result.close();
            }
        }
        return shifts;
    }
    
    public List<WorkShift> getUpcomingShifts(int staffId, int days) throws SQLException {
        List<WorkShift> shifts = new ArrayList<>();
        String sql = "SELECT ws.*, st.FullName as StaffName " +
                    "FROM WorkShifts ws " +
                    "JOIN Staff st ON ws.StaffID = st.StaffID " +
                    "WHERE ws.StaffID = ? " +
                    "AND ws.ShiftDate >= GETDATE() " +
                    "AND ws.ShiftDate <= DATEADD(day, ?, GETDATE()) " +
                    "ORDER BY ws.ShiftDate";
        
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, staffId);
            statement.setInt(2, days);
            result = statement.executeQuery();
            
            while (result.next()) {
                shifts.add(mapResultSetToWorkShift(result));
            }
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (result != null) {
                result.close();
            }
        }
        return shifts;
    }
    
    private WorkShift mapResultSetToWorkShift(ResultSet rs) throws SQLException {
        WorkShift shift = new WorkShift();
        shift.setShiftID(rs.getInt("ShiftID"));
        shift.setStaffID(rs.getInt("StaffID"));
        shift.setShiftDate(rs.getTimestamp("ShiftDate"));
        shift.setStartTime(rs.getTimestamp("StartTime"));
        shift.setEndTime(rs.getTimestamp("EndTime"));
        shift.setStatus(rs.getString("Status"));
        shift.setStaffName(rs.getString("StaffName"));
        return shift;
    }
} 