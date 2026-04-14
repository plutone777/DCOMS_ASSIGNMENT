package RMI;

import Mayan.Employee;
import Mayan.UserSession;
import Mayan.formHrReport;
import Mayan.formLogin;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import javax.swing.JOptionPane;

public class ClientMain {
    public static void main(String[] args)
            throws RemoteException, NotBoundException, MalformedURLException {

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
