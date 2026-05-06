package dcoms_assignment;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import rmi.RMIInterfaceMain;

public class ClientMain {
    public static void main(String[] args)throws RemoteException, NotBoundException, MalformedURLException {
        RMIInterfaceMain Obj = (RMIInterfaceMain)Naming.lookup("rmi://10.133.241.29:1044/plsceholdername");
        
        //entry point to application will be here
        // Open forms using shared stub
    new client.UpdatePersonalDetailsForm(Obj).setVisible(true);
    new client.UpdateFamilyDetailsForm(Obj).setVisible(true);
    new client.CheckLeaveBalanceForm(Obj).setVisible(true);
    }
}
