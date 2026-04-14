package dcoms_assignment.Mayan;

import java.io.*;

public class UserSession {
    private static Employee currentUser;
    private static final String FILE_NAME = "session.dat";

    public static void setCurrentUser(Employee emp) {
        currentUser = emp;
    }

    public static Employee getCurrentUser() {
        return currentUser;
    }
    
    public static void clear() {
        currentUser = null;
    }
    
    public static void saveSession(Employee emp) {
        try (ObjectOutputStream oos =
                     new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(emp);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Employee loadSession() {
        try (ObjectInputStream ois =
                     new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            return (Employee) ois.readObject();
        } catch (Exception e) {
            return null; 
        }
    }

    public static void clearSession() {
        new File(FILE_NAME).delete();
    }
}
