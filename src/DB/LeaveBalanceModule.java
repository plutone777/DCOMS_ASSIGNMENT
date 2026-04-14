/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DB;

import RMI.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LeaveBalanceModule {
    
    public LeaveBalanceData getLeaveBalance(int employeeId, int leaveYear) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT TotalDays, UsedDays, RemainingDays " +
             "FROM APPUSER.LeaveBalance " +
             "WHERE EmployeeID=? AND CurrentYear=?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, employeeId);
            pst.setInt(2, leaveYear);
            ResultSet rs = pst.executeQuery();
            
            if (rs.next()) {
                return new LeaveBalanceData(
                    rs.getInt("TotalDays"),
                    rs.getInt("UsedDays"),
                    rs.getInt("RemainingDays")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null; // returns null if employee not found
    }
}