package dcoms_assignment;

import dcoms_assignment.Mayan.Employee;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface RMIInterfaceMain extends Remote{
    //declare all your methods here! Not implementation, just ur method name.
    //make sure that they throw RemoteException
    
    // mayan's methods
    Employee validateLogin(String username, String password) throws RemoteException;
    Employee getEmployee(String username) throws RemoteException;
    List<Employee> getAllEmployees() throws RemoteException;
    List<Integer> getLeaveYearsByEmployee(int employeeID) throws RemoteException;
    String exportLeaveReport(int employeeID, int year) throws RemoteException;    
}