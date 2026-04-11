package dcoms_assignment;

import dcoms_assignment.Mayan.Employee;
import dcoms_assignment.Mayan.HR_DataAccess;
import dcoms_assignment.Mayan.Login;
import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;

public class RMIObjectImplMain extends UnicastRemoteObject implements RMIInterfaceMain{
    //create objects of your classes here
    HR_DataAccess dao = new HR_DataAccess();  
    Login login = new Login(); 
    
    public RMIObjectImplMain()throws RemoteException{
        super();
    }
    
    //override your methods here
    @Override
    public Employee validateLogin(String username, String password) throws RemoteException {
        return login.validateLogin(username, password);
    }

    @Override
    public Employee getEmployee(String username) throws RemoteException {
        return dao.getEmployeeByUsername(username);
    }
    
}
