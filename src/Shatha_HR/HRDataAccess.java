package Shatha_HR;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class HRDataAccess {

    // ─────────────────────────────────────────────────────────────────
    //  HR FEATURE 1 : Insert employee into 4 tables
    // ─────────────────────────────────────────────────────────────────
    public int registerEmployee(
            Connection conn,
            String firstName, String lastName, String icOrPassportNo,
            String username, String passwordHash, String role,
            String spouseName, int numberOfChildren,
            String dependentName, String dependantRelationship, String dependentDOB,
            String relationshipStatus,
            String emergencyContact, String emergencyContactRelationship,
            String dateOfBirth, String gender,
            String address, String email, String phoneNo
    ) throws SQLException {

        // Get next ID SQL queries for ALL 4 tables
        String sqlGetMaxEmp      = "SELECT COALESCE(MAX(EmployeeID), 0) + 1 AS NextID FROM Employee";
        String sqlGetMaxPersonal = "SELECT COALESCE(MAX(PersonalID), 0) + 1 AS NextID FROM EmployeePersonalDetails";
        String sqlGetMaxFamily   = "SELECT COALESCE(MAX(FamilyID), 0) + 1 AS NextID FROM EmployeeFamilyDetails";
        // FIX: Changed LeaveBalanceID to BalanceID (actual column name in database)
        String sqlGetMaxLeave    = "SELECT COALESCE(MAX(BalanceID), 0) + 1 AS NextID FROM LeaveBalance";
        
        String sql1 = "INSERT INTO Employee "
                    + "(EmployeeID, FirstName, LastName, ICOrPassportNo, Username, PasswordHash, Role) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        String sql2 = "INSERT INTO EmployeePersonalDetails "
                    + "(PersonalID, EmployeeID, DateOfBirth, Gender, Address, Email, PhoneNo) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        String sql3 = "INSERT INTO EmployeeFamilyDetails "
                    + "(FamilyID, EmployeeID, SpouseName, NumberOfChildren, DependentName, "
                    + " DependantRelationship, DependentDOB, RelationshipStatus, "
                    + " EmergencyContact, EmergencyContactRelationship) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        // FIX: Changed LeaveBalanceID to BalanceID (actual column name in database)
        String sql4 = "INSERT INTO LeaveBalance "
                    + "(BalanceID, EmployeeID, CurrentYear, TotalDays, UsedDays, RemainingDays) "
                    + "VALUES (?, ?, YEAR(CURRENT_DATE), 20, 0, 20)";

        // Fetch next IDs for all 4 tables BEFORE inserting
        int newEmpId, newPersonalId, newFamilyId, newBalanceId;
        
        try (PreparedStatement ps = conn.prepareStatement(sqlGetMaxEmp);
             ResultSet rs = ps.executeQuery()) {
            rs.next();
            newEmpId = rs.getInt("NextID");
        }
        try (PreparedStatement ps = conn.prepareStatement(sqlGetMaxPersonal);
             ResultSet rs = ps.executeQuery()) {
            rs.next();
            newPersonalId = rs.getInt("NextID");
        }
        try (PreparedStatement ps = conn.prepareStatement(sqlGetMaxFamily);
             ResultSet rs = ps.executeQuery()) {
            rs.next();
            newFamilyId = rs.getInt("NextID");
        }
        try (PreparedStatement ps = conn.prepareStatement(sqlGetMaxLeave);
             ResultSet rs = ps.executeQuery()) {
            rs.next();
            newBalanceId = rs.getInt("NextID");
        }
        
        // Insert into all tables with manually generated IDs
        try (PreparedStatement ps1 = conn.prepareStatement(sql1)) {
            ps1.setInt(1, newEmpId);
            ps1.setString(2, firstName);
            ps1.setString(3, lastName);
            ps1.setString(4, icOrPassportNo);
            ps1.setString(5, username);
            ps1.setString(6, passwordHash);
            ps1.setString(7, (role == null || role.isEmpty()) ? "EMPLOYEE" : role);
            ps1.executeUpdate();

            try (PreparedStatement ps2 = conn.prepareStatement(sql2)) {
                ps2.setInt(1, newPersonalId);
                ps2.setInt(2, newEmpId);
                ps2.setDate(3, parseDateOrNull(dateOfBirth));
                ps2.setString(4, gender);
                ps2.setString(5, address);
                ps2.setString(6, email);
                ps2.setString(7, phoneNo);
                ps2.executeUpdate();
            }

            try (PreparedStatement ps3 = conn.prepareStatement(sql3)) {
                ps3.setInt(1, newFamilyId);
                ps3.setInt(2, newEmpId);
                ps3.setString(3, spouseName);
                ps3.setInt(4, numberOfChildren);
                ps3.setString(5, dependentName);
                ps3.setString(6, dependantRelationship);
                ps3.setDate(7, parseDateOrNull(dependentDOB));
                ps3.setString(8, relationshipStatus);
                ps3.setString(9, emergencyContact);
                ps3.setString(10, emergencyContactRelationship);
                ps3.executeUpdate();
            }

            try (PreparedStatement ps4 = conn.prepareStatement(sql4)) {
                ps4.setInt(1, newBalanceId);
                ps4.setInt(2, newEmpId);
                ps4.executeUpdate();
            }

            System.out.println("[HR] Employee registered. ID = " + newEmpId);
            return newEmpId;

        } catch (SQLIntegrityConstraintViolationException e) {
            System.err.println("[HR] Duplicate username or IC: " + e.getMessage());
            return -1;
        }
    }

    // ─────────────────────────────────────────────────────────────────
    //  HR FEATURE 2a : Load PENDING leave requests
    // ─────────────────────────────────────────────────────────────────
    public List<LeaveRecord> getPendingLeaveRequests(Connection conn) throws SQLException {
        List<LeaveRecord> results = new ArrayList<>();

        String sql = "SELECT la.LeaveApplicationID, la.EmployeeID, "
                   + "       e.FirstName, e.LastName, "
                   + "       la.StartDate, la.EndDate, "
                   + "       la.NumberOfDays, la.Reason "
                   + "FROM LeaveApplication la "
                   + "JOIN Employee e ON la.EmployeeID = e.EmployeeID "
                   + "WHERE UPPER(la.Status) = 'PENDING' "
                   + "ORDER BY la.ApplyDate ASC";

       try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) { 

            while (rs.next()) {
                results.add(new LeaveRecord(
                    rs.getString("LeaveApplicationID"),
                    rs.getString("EmployeeID"),
                    rs.getString("FirstName") + " " + rs.getString("LastName"),
                    "General",
                    rs.getString("StartDate"),
                    rs.getString("EndDate"),
                    rs.getString("NumberOfDays"),
                    rs.getString("Reason")
                ));
            }
        }
        return results;
    }

    // ─────────────────────────────────────────────────────────────────
    //  HR FEATURE 2b : Get employee info for table row
    // ─────────────────────────────────────────────────────────────────
    public String[] getEmployeeInfoById(Connection conn, int employeeId) throws SQLException {
        String sql = "SELECT e.EmployeeID, e.FirstName, e.LastName, e.Username, "
                   + "       lb.RemainingDays, lb.CurrentYear "
                   + "FROM Employee e "
                   + "JOIN LeaveBalance lb ON e.EmployeeID = lb.EmployeeID "
                   + "WHERE e.EmployeeID = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {   
            ps.setInt(1, employeeId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int dbYear    = rs.getInt("CurrentYear");
                int thisYear  = java.time.Year.now().getValue();
                int remaining = rs.getInt("RemainingDays");

                if (dbYear != thisYear) {
                    resetLeaveBalance(conn, employeeId, thisYear);
                    remaining = 20;
                }

                return new String[]{
                    String.valueOf(rs.getInt("EmployeeID")),
                    rs.getString("FirstName") + " " + rs.getString("LastName"),
                    rs.getString("Username"),
                    String.valueOf(remaining)
                };
            }
        }
        return null;
    }

    // ─────────────────────────────────────────────────────────────────
    //  HR FEATURE 2c : Accept or Reject a leave request
    // ─────────────────────────────────────────────────────────────────
    public boolean reviewLeaveRequest(Connection conn, int leaveApplicationId, String decision,
                                      int hrEmployeeId) throws SQLException {

        String selectSql   = "SELECT EmployeeID, NumberOfDays FROM LeaveApplication "
                           + "WHERE LeaveApplicationID = ?";
        String updateLeave = "UPDATE LeaveApplication "
                           + "SET Status=?, ApprovedBy=?, ApprovalDate=CURRENT_TIMESTAMP "
                           + "WHERE LeaveApplicationID=?";
        String updateBal   = "UPDATE LeaveBalance "
                           + "SET UsedDays=UsedDays+?, RemainingDays=RemainingDays-? "
                           + "WHERE EmployeeID=? AND RemainingDays >= ?";

        int numberOfDays = 0, employeeId = 0;
        try (PreparedStatement ps = conn.prepareStatement(selectSql)) {
            ps.setInt(1, leaveApplicationId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                employeeId   = rs.getInt("EmployeeID");
                numberOfDays = rs.getInt("NumberOfDays");
            } else return false;
        }

        try (PreparedStatement ps = conn.prepareStatement(updateLeave)) {
            String dbStatus = "APPROVED".equals(decision) ? "ACCEPTED" : decision;
            ps.setString(1, dbStatus);
            ps.setInt(2, hrEmployeeId);
            ps.setInt(3, leaveApplicationId);
            ps.executeUpdate();
        }

        if ("APPROVED".equals(decision)) {
            try (PreparedStatement ps = conn.prepareStatement(updateBal)) {
                ps.setInt(1, numberOfDays);
                ps.setInt(2, numberOfDays);
                ps.setInt(3, employeeId);
                ps.setInt(4, numberOfDays);
                int rows = ps.executeUpdate();
                if (rows == 0) {
                    try (PreparedStatement rb = conn.prepareStatement(
                            "UPDATE LeaveApplication "
                          + "SET Status='PENDING', ApprovedBy=NULL, ApprovalDate=NULL "
                          + "WHERE LeaveApplicationID=?")) {
                        rb.setInt(1, leaveApplicationId);
                        rb.executeUpdate();
                    }
                    return false;
                }
            }
        }
        return true;
    }

    // ─────────────────────────────────────────────────────────────────
    //  HELPERS
    // ─────────────────────────────────────────────────────────────────
    private java.sql.Date parseDateOrNull(String s) {
        if (s == null || s.isBlank()) return null;
        try { return java.sql.Date.valueOf(s.trim()); }
        catch (IllegalArgumentException e) { return null; }
    }

    private void resetLeaveBalance(Connection conn, int empId, int year) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement(
                "UPDATE LeaveBalance "
              + "SET TotalDays=20, UsedDays=0, RemainingDays=20, CurrentYear=? "
              + "WHERE EmployeeID=?")) {
            ps.setInt(1, year);
            ps.setInt(2, empId);
            ps.executeUpdate();
        }
    }
}
