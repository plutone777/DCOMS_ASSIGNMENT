package dcoms_assignment;

import dcoms_assignment.Mayan.formLogin;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class ClientMain {
    public static void main(String[] args)throws RemoteException, NotBoundException, MalformedURLException {
//        RMIInterfaceMain Obj = (RMIInterfaceMain)Naming.lookup("rmi://10.133.241.29:1044/plsceholdername");

        RMIInterfaceMain obj =
            (RMIInterfaceMain) Naming.lookup("rmi://localhost:1044/HRMSService");
        
        //entry point to application will be here
        java.awt.EventQueue.invokeLater(() -> {
            new formLogin(obj).setVisible(true);
        });
        
    }
}
