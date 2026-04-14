/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DB;

import RMI.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class PersonalDetailsModule {
   public boolean updatePersonalDetails(int employeeId, String address, String email, String phone) {
    try (Connection conn = DBConnection.getConnection()) {
        String sql = "UPDATE APPUSER.EmployeePersonalDetails SET Address=?, Email=?, PhoneNo=? WHERE EmployeeID=?";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, address);
        pst.setString(2, email);
        pst.setString(3, phone);
        pst.setInt(4, employeeId);

        int rows = pst.executeUpdate();   // returns number of rows updated
        return rows > 0;                  // true only if at least one row updated
    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
}

}

