package dcoms_assignment.employee;

import dcoms_assignment.DBConnection;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LeaveModule {
    
    private Connection getConnection() throws SQLException {
        return DBConnection.getConnection();
    }
    
    public int applyLeave(String employeeId, Date startDate, Date endDate, String reason) 
            throws RemoteException {
        
        if (startDate == null || endDate == null) {
            throw new RemoteException("Dates cannot be null");
        }
        if (startDate.after(endDate)) {
            throw new RemoteException("Start date cannot be after end date");
        }
        
        long diff = endDate.getTime() - startDate.getTime();
        int numberOfDays = (int) (diff / (1000 * 60 * 60 * 24)) + 1;
        
        try (Connection conn = getConnection()) {

            String checkBalanceSql = "SELECT RemainingDays FROM LeaveBalance " +
                                     "WHERE EmployeeID = ? AND CurrentYear = 2026";
            PreparedStatement checkStmt = conn.prepareStatement(checkBalanceSql);
            checkStmt.setString(1, employeeId);
            ResultSet rs = checkStmt.executeQuery();
            
            if (!rs.next()) {
                throw new RemoteException("Leave balance not found for employee: " + employeeId);
            }
            
            int remainingDays = rs.getInt("RemainingDays");
            if (remainingDays < numberOfDays) {
                throw new RemoteException("Insufficient leave balance. " +
                    "Available: " + remainingDays + ", Requested: " + numberOfDays);
            }
            

            String getIdSql = "SELECT MAX(LeaveApplicationID) + 1 AS newId FROM LeaveApplication";
            Statement stmt = conn.createStatement();
            ResultSet idRs = stmt.executeQuery(getIdSql);
            int newId = 1;
            if (idRs.next() && idRs.getInt("newId") > 0) {
                newId = idRs.getInt("newId");
            }
            

            String insertSql = "INSERT INTO LeaveApplication (LeaveApplicationID, EmployeeID, " +
                               "StartDate, EndDate, NumberOfDays, Reason, ApplyDate, Status) " +
                               "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement insertStmt = conn.prepareStatement(insertSql);
            insertStmt.setInt(1, newId);
            insertStmt.setString(2, employeeId);
            insertStmt.setDate(3, new java.sql.Date(startDate.getTime()));
            insertStmt.setDate(4, new java.sql.Date(endDate.getTime()));
            insertStmt.setInt(5, numberOfDays);
            insertStmt.setString(6, reason);
            insertStmt.setTimestamp(7, new Timestamp(System.currentTimeMillis()));
            insertStmt.setString(8, "PENDING");
            
            insertStmt.executeUpdate();
            
            System.out.println("[Thread: " + Thread.currentThread().getName() + 
                               "] Leave application #" + newId + " submitted by " + employeeId);
            return newId;
            
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RemoteException("Database error: " + e.getMessage());
        }
    }
    
    public String getLeaveStatus(int requestId, String employeeId) throws RemoteException {
        try (Connection conn = getConnection()) {
            String sql = "SELECT Status FROM LeaveApplication " +
                         "WHERE LeaveApplicationID = ? AND EmployeeID = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, requestId);
            stmt.setString(2, employeeId);
            ResultSet rs = stmt.executeQuery();
            
            if (!rs.next()) {
                return "Request not found or access denied";
            }
            
            String status = rs.getString("Status");
            return "Request #" + requestId + " is " + status;
            
        } catch (SQLException e) {
            throw new RemoteException("Database error: " + e.getMessage());
        }
    }

    public List<LeaveRequest> getLeaveHistory(String employeeId) throws RemoteException {
        List<LeaveRequest> history = new ArrayList<>();
        
        try (Connection conn = getConnection()) {
            String sql = "SELECT LeaveApplicationID, StartDate, EndDate, NumberOfDays, " +
                         "Reason, ApplyDate, Status FROM LeaveApplication " +
                         "WHERE EmployeeID = ? ORDER BY ApplyDate DESC";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, employeeId);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                LeaveRequest req = new LeaveRequest();
                req.setRequestId(rs.getInt("LeaveApplicationID"));
                req.setEmployeeId(employeeId);
                req.setStartDate(rs.getDate("StartDate"));
                req.setEndDate(rs.getDate("EndDate"));
                req.setReason(rs.getString("Reason"));
                req.setStatus(rs.getString("Status"));
                req.setAppliedDate(rs.getTimestamp("ApplyDate"));
                history.add(req);
            }
            
            return history;
            
        } catch (SQLException e) {
            throw new RemoteException("Database error: " + e.getMessage());
        }
    }
    
    public boolean updateLeaveStatus(int requestId, String newStatus, int approvedBy) {
        try (Connection conn = getConnection()) {
            conn.setAutoCommit(false);
            
            String selectSql = "SELECT EmployeeID, NumberOfDays FROM LeaveApplication " +
                               "WHERE LeaveApplicationID = ?";
            PreparedStatement selectStmt = conn.prepareStatement(selectSql);
            selectStmt.setInt(1, requestId);
            ResultSet rs = selectStmt.executeQuery();
            
            if (!rs.next()) {
                conn.rollback();
                return false;
            }
            
            String employeeId = rs.getString("EmployeeID");
            int numberOfDays = rs.getInt("NumberOfDays");
            
            String updateSql = "UPDATE LeaveApplication SET Status = ?, " +
                               "ApprovedBy = ?, ApprovalDate = ? " +
                               "WHERE LeaveApplicationID = ?";
            PreparedStatement updateStmt = conn.prepareStatement(updateSql);
            updateStmt.setString(1, newStatus);
            updateStmt.setInt(2, approvedBy);
            updateStmt.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
            updateStmt.setInt(4, requestId);
            updateStmt.executeUpdate();
            
            if ("APPROVED".equals(newStatus)) {
                String balanceSql = "UPDATE LeaveBalance SET UsedDays = UsedDays + ?, " +
                                    "RemainingDays = RemainingDays - ? " +
                                    "WHERE EmployeeID = ? AND CurrentYear = 2026";
                PreparedStatement balanceStmt = conn.prepareStatement(balanceSql);
                balanceStmt.setInt(1, numberOfDays);
                balanceStmt.setInt(2, numberOfDays);
                balanceStmt.setString(3, employeeId);
                balanceStmt.executeUpdate();
            }
            
            conn.commit();
            System.out.println("[LeaveModule] Request #" + requestId + " updated to " + newStatus);
            return true;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}