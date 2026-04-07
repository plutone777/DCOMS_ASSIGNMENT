
package dcoms_assignment;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ServerMain {
    public static void main(String[] args)throws RemoteException {
        Registry reg = LocateRegistry.createRegistry(1044);
        reg.rebind("plsceholdername", new RMIObjectImplMain());
    }
}
