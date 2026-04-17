package RMI;

import Mayan.Employee;
import Mayan.UserSession;
import Mayan.formLogin;
import java.rmi.Naming;

public class ClientMain {
    public static void main(String[] args)
            throws Exception {

        System.setProperty("javax.net.ssl.trustStore", "client.truststore");
        System.setProperty("javax.net.ssl.trustStorePassword", "123456");

        RMIInterfaceMain obj =
            (RMIInterfaceMain) Naming.lookup("rmi://localhost:1044/HRMSService");

        java.awt.EventQueue.invokeLater(() -> {
            try {
                Employee savedUser = UserSession.loadSession();

                if (savedUser != null) {
                    UserSession.setCurrentUser(savedUser);
                    Navigator.openHome(savedUser, obj);
                } else {
                    new formLogin(obj).setVisible(true);
                }

            } catch (Exception e) {
                e.printStackTrace();
                new formLogin(obj).setVisible(true);
            }
        });
    }
}