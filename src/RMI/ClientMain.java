package RMI;

import Mayan.Employee;
import Mayan.UserSession;
import Mayan.formLogin;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import javax.rmi.ssl.SslRMIClientSocketFactory;

public class ClientMain {

    public static void main(String[] args) throws Exception {

        System.setProperty("javax.net.ssl.trustStore", "client.truststore");
        System.setProperty("javax.net.ssl.trustStorePassword", "password123");

        // CONNECT USING SSL SOCKET FACTORY (NOT Naming.lookup)
        Registry registry = LocateRegistry.getRegistry(
                "localhost",
                1044,
                new SslRMIClientSocketFactory()
        );

        RMIInterfaceMain obj =
                (RMIInterfaceMain) registry.lookup("HRMSService");

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