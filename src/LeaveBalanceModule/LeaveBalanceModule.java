/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LeaveBalanceModule;

import DB.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;

public class LeaveBalanceModule {
    public void loadLeaveBalance(DefaultTableModel model, int employeeId, int leaveYear) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT TotalDays, UsedDays, RemainingDays " +
                         "FROM LeaveBalance " +
                         "WHERE EmployeeID=? AND LeaveYear=?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, employeeId);
            pst.setInt(2, leaveYear);
            ResultSet rs = pst.executeQuery();

            model.setRowCount(0); // this is what clears the table
            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("TotalDays"),
                    rs.getInt("UsedDays"),
                    rs.getInt("RemainingDays")
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
