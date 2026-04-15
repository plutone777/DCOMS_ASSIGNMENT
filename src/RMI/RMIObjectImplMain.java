package RMI;

import Bisma_DB.FamilyDetailsModule;
import Bisma_DB.LeaveBalanceData;
import Bisma_DB.LeaveBalanceModule;
import Bisma_DB.PersonalDetailsModule;
import Mayan.Employee;
import Mayan.HR_DataAccess;
import Mayan.HR_Report;
import Mayan.Login;
import Shatha_HR.HRModule;
import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class RMIObjectImplMain extends UnicastRemoteObject implements RMIInterfaceMain{
    //create objects of your classes here !!!!!!!!!!!!!!!!
    // mayan
    HR_DataAccess dao = new HR_DataAccess();  
    Login login = new Login(); 
    
    // bisma
    private PersonalDetailsModule personalModule = new PersonalDetailsModule();
    private FamilyDetailsModule familyModule = new FamilyDetailsModule();
    private LeaveBalanceModule leaveModule = new LeaveBalanceModule();
    
    public RMIObjectImplMain()throws RemoteException{
        super();
    }
    // shatha
    HRModule hrModule = new HRModule();
    
    //override your methods here !!!!!!!!!!!!!!
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
    
    // bisma
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
    
    // shatha
    @Override
    public int registerEmployee(
            String firstName, String lastName, String icOrPassportNo,
            String username, String passwordHash, String role,
            String spouseName, int numberOfChildren,
            String dependentName, String dependantRelationship, String dependentDOB,
            String relationshipStatus,
            String emergencyContact, String emergencyContactRelationship,
            String dateOfBirth, String gender,
            String address, String email, String phoneNo
    ) throws RemoteException {
        try {
            return hrModule.registerEmployee(
                firstName, lastName, icOrPassportNo, username, passwordHash, role,
                spouseName, numberOfChildren,
                dependentName, dependantRelationship, dependentDOB, relationshipStatus,
                emergencyContact, emergencyContactRelationship,
                dateOfBirth, gender, address, email, phoneNo
            );
        } catch (Exception e) {
            throw new RemoteException("registerEmployee failed: " + e.getMessage(), e);
        }
    }
    @Override
    public List<String[]> getPendingLeaveRequests() throws RemoteException {
        try {
            // HRModule returns List<LeaveRecord> — convert to List<String[]> for RMI
            // String[] is Serializable by default in Java
            List<Shatha_HR.LeaveRecord> records =
                hrModule.getPendingLeaveRequests();
            List<String[]> result = new ArrayList<>();
            for (Shatha_HR.LeaveRecord r : records) {
                result.add(r.toArray());
            }
            return result;
        } catch (Exception e) {
            throw new RemoteException("getPendingLeaveRequests failed: " + e.getMessage(), e);
        }
    }
    @Override
    public String[] getEmployeeInfoById(int employeeId) throws RemoteException {
        try {
            return hrModule.getEmployeeInfoById(employeeId);
        } catch (Exception e) {
            throw new RemoteException("getEmployeeInfoById failed: " + e.getMessage(), e);
        }
    }
    @Override
    public boolean reviewLeaveRequest(int leaveApplicationId, String decision,
                                      int hrEmployeeId) throws RemoteException {
        try {
            return hrModule.reviewLeaveRequest(leaveApplicationId, decision, hrEmployeeId);
        } catch (Exception e) {
            throw new RemoteException("reviewLeaveRequest failed: " + e.getMessage(), e);
        }
    }
    
}
