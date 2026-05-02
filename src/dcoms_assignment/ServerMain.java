
package dcoms_assignment;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ServerMain {
    public static void main(String[] args)throws RemoteException {
        Registry reg = LocateRegistry.createRegistry(1099);
        reg.rebind("HRMSystem", new RMIObjectImplMain());
        System.out.println("RMI Server running on port 1044");
        System.out.println("Service bound as: HRMSystem");
    }
}
