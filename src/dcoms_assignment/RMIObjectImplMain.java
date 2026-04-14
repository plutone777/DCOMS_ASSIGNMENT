package dcoms_assignment;

import dcoms_assignment.Mayan.Employee;
import dcoms_assignment.Mayan.HR_DataAccess;
import dcoms_assignment.Mayan.HR_Report;
import dcoms_assignment.Mayan.Login;
import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class RMIObjectImplMain extends UnicastRemoteObject implements RMIInterfaceMain{
    //create objects of your classes here
    HR_DataAccess dao = new HR_DataAccess();  
    Login login = new Login(); 
    
    public RMIObjectImplMain()throws RemoteException{
        super();
    }
    
    //override your methods here
    //mayan
    @Override
    public Employee validateLogin(String username, String password) throws RemoteException {
        return login.validateLogin(username, password);
    }

    @Override
    public Employee getEmployee(String username) throws RemoteException {
        return dao.getEmployeeByUsername(username);
    }
    
    @Override
    public List<Employee> getAllEmployees() throws RemoteException {
        return dao.getAllEmployees();
    }

    @Override
    public List<Integer> getLeaveYearsByEmployee(int employeeID) throws RemoteException {
        return dao.getLeaveYearsByEmployee(employeeID);
    }

    @Override
    public String exportLeaveReport(int employeeID, int year) throws RemoteException {
        HR_Report report = new HR_Report();
        String filePath = "leave_report_" + employeeID + "_" + year + ".csv";
        report.exportLeaveApplicationsByYear(employeeID, year, filePath);
        return filePath;
    }
    
}
