package dcoms_assignment.Mayan;

public class UserSession {
    private static Employee currentUser;

    public static void setCurrentUser(Employee emp) {
        currentUser = emp;
    }

    public static Employee getCurrentUser() {
        return currentUser;
    }
    
    public static void clear() {
        currentUser = null;
    }
}
