package Mayan;

public class Login {

    private HR_DataAccess dao = new HR_DataAccess();

    public Employee validateLogin(String username, String password) {
        Employee emp = dao.getEmployeeByUsername(username);

        if (emp != null && emp.getPassword().equals(password)) {
            return emp; 
        }

        return null; 
    }
    
}
