package Mayan;

import RMI.DBConnection;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class HR_DataAccess {
    
    public Employee getEmployeeByUsername(String username) {
        String sql = "SELECT * FROM Employee WHERE Username = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Employee(
                            rs.getInt("EmployeeID"),
                            rs.getString("FirstName"),
                            rs.getString("LastName"),
                            rs.getString("ICOrPassportNo"),
                            rs.getString("Username"),
                            rs.getString("PasswordHash"),
                            rs.getString("Role")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public Employee getEmployeeByID(int employeeID) {
        String sql = "SELECT * FROM Employee WHERE EmployeeID = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, employeeID);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Employee(
                            rs.getInt("EmployeeID"),
                            rs.getString("FirstName"),
                            rs.getString("LastName"),
                            rs.getString("ICOrPassportNo"),
                            rs.getString("Username"),
                            rs.getString("PasswordHash"),
                            rs.getString("Role")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
  
    public List<Employee> getAllEmployees() {
        List<Employee> list = new ArrayList<>();
        String sql = "SELECT * FROM Employee WHERE Role = 'Employee'";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Employee emp = new Employee(
                        rs.getInt("EmployeeID"),
                        rs.getString("FirstName"),
                        rs.getString("LastName"),
                        rs.getString("ICOrPassportNo"),
                        rs.getString("Username"),
                        rs.getString("PasswordHash"),
                        rs.getString("Role")
                );
                list.add(emp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public List<LeaveBalance> getLeaveBalancesByEmployee(int employeeID) {
        List<LeaveBalance> list = new ArrayList<>();
        String sql = "SELECT * FROM LeaveBalance WHERE EmployeeID = ? ORDER BY CurrentYear";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, employeeID);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    LeaveBalance lb = new LeaveBalance(
                            rs.getInt("BalanceID"),
                            rs.getInt("EmployeeID"),
                            rs.getInt("CurrentYear"),
                            rs.getInt("TotalDays"),
                            rs.getInt("UsedDays"),
                            rs.getInt("RemainingDays")
                    );
                    list.add(lb);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public List<Integer> getLeaveYearsByEmployee(int employeeID) {
        List<Integer> years = new ArrayList<>();
        String sql = "SELECT DISTINCT CurrentYear FROM LeaveBalance WHERE EmployeeID = ? ORDER BY CurrentYear";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, employeeID);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    years.add(rs.getInt("CurrentYear"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return years;
    }
    
    public List<LeaveApplication> getLeaveApplicationsByEmployeeAndYear(int employeeID, int year) {
        List<LeaveApplication> list = new ArrayList<>();
        String sql = "SELECT * FROM LeaveApplication " +
                     "WHERE EmployeeID = ? " +
                     "AND Status = ? " +
                     "AND StartDate >= ? AND StartDate < ? " +
                     "ORDER BY StartDate DESC";
        
        try (Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, employeeID);
            ps.setString(2, "ACCEPTED");
            ps.setDate(3, java.sql.Date.valueOf(year + "-01-01"));
            ps.setDate(4, java.sql.Date.valueOf((year + 1) + "-01-01"));
            
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Integer approvedBy = rs.getObject("ApprovedBy") != null 
                            ? rs.getInt("ApprovedBy") : null;
                    LeaveApplication la = new LeaveApplication(
                            rs.getInt("LeaveApplicationID"),
                            rs.getInt("EmployeeID"),
                            rs.getDate("StartDate"),
                            rs.getDate("EndDate"),
                            rs.getInt("NumberOfDays"),
                            rs.getString("Reason"),
                            rs.getTimestamp("ApplyDate"),
                            rs.getString("Status"),
                            approvedBy,
                            rs.getTimestamp("ApprovalDate")
                    );
                    list.add(la);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public String getEmployeeFullName(int employeeID) {
        String sql = "SELECT FirstName, LastName FROM Employee WHERE EmployeeID = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, employeeID);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("FirstName") + " " + rs.getString("LastName");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }    
    
}
