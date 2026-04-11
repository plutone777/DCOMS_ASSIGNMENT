package dcoms_assignment.Mayan;

import dcoms_assignment.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
    
}
