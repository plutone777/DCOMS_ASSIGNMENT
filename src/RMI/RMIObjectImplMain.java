package RMI;

import Bisma_DB.FamilyDetailsModule;
import Bisma_DB.LeaveBalanceData;
import Bisma_DB.LeaveBalanceModule;
import Bisma_DB.PersonalDetailsModule;
import Mayan.Employee;
import Mayan.HR_DataAccess;
import Mayan.HR_Report;
import Mayan.Login;
import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class RMIObjectImplMain extends UnicastRemoteObject implements RMIInterfaceMain{
    //create objects of your classes here
    // mayan
    HR_DataAccess dao = new HR_DataAccess();  
    Login login = new Login(); 
    
    //bisma
    private PersonalDetailsModule personalModule = new PersonalDetailsModule();
    private FamilyDetailsModule familyModule = new FamilyDetailsModule();
    private LeaveBalanceModule leaveModule = new LeaveBalanceModule();
    
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
    
    //bisma
    @Override
    public boolean updatePersonalDetails(int employeeId, String address, String email, String phone) throws RemoteException {
        return personalModule.updatePersonalDetails(employeeId, address, email, phone);
    }
    @Override
    public boolean updateFamilyDetails(int employeeId, String spouseName, int children, int emergencyContact) throws RemoteException {
        return familyModule.updateFamilyDetails(employeeId, spouseName, children, emergencyContact);
    }
    @Override
    public LeaveBalanceData checkLeaveBalance(int employeeId, int leaveYear) throws RemoteException {
        return leaveModule.getLeaveBalance(employeeId, leaveYear);
    }
    
}
