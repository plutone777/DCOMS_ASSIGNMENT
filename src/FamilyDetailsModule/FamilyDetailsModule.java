/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FamilyDetailsModule;

import DB.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class FamilyDetailsModule {
    public boolean updateFamilyDetails(int employeeId, String spouseName, int children, int emergencyContact) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "UPDATE EmployeeFamilyDetails SET SpouseName=?, NumberOfChildren=?, EmergencyContact=? WHERE EmployeeID=?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, spouseName);
            pst.setInt(2, children);
            pst.setInt(3, emergencyContact);
            pst.setInt(4, employeeId);
            pst.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
