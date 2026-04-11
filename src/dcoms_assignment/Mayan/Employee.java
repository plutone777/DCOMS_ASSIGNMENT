package dcoms_assignment.Mayan;

public class Employee implements java.io.Serializable{

    private int employeeID;
    private String firstName;
    private String lastName;
    private String icOrPassportNo;
    private String username;
    private String passwordHash;
    private String role;

    public Employee(int employeeID, String firstName, String lastName,
                    String icOrPassportNo, String username,
                    String passwordHash, String role) {
        this.employeeID = employeeID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.icOrPassportNo = icOrPassportNo;
        this.username = username;
        this.passwordHash = passwordHash;
        this.role = role;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getIcOrPassportNo() {
        return icOrPassportNo;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return passwordHash;
    }

    public String getRole() {
        return role;
    }
}