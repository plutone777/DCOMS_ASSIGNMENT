package dcoms_assignment;

import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;

public class RMIObjectImplMain extends UnicastRemoteObject implements RMIInterfaceMain{
    //create objects of your classes here
    public RMIObjectImplMain()throws RemoteException{
        super();
    }
}
