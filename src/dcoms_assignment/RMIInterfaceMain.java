package dcoms_assignment;

import dcoms_assignment.Mayan.Employee;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMIInterfaceMain extends Remote{
    //declare all your methods here! Not implementation, just ur method name.
    //make sure that they throw RemoteException
    
    Employee validateLogin(String username, String password) throws RemoteException;
    Employee getEmployee(String username) throws RemoteException;
    
}