package dcoms_assignment;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class ClientMain {
    public static void main(String[] args)throws RemoteException, NotBoundException, MalformedURLException {
        RMIInterfaceMain Obj = (RMIInterfaceMain)Naming.lookup("rmi://10.133.241.29:1044/plsceholdername");
        
        //entry point to application will be here
    }
}
