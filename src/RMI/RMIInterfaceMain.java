package RMI;

import Bisma_DB.LeaveBalanceData;
import Mayan.Employee;
import Samara.LeaveRequest;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;
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
    
    // bisma's methods
    boolean updatePersonalDetails(int employeeId, String address, String email, String phone) throws RemoteException;
    boolean updateFamilyDetails(int employeeId, String spouseName, int children, int emergencyContact) throws RemoteException;
    LeaveBalanceData checkLeaveBalance(int employeeId, int leaveYear) throws RemoteException;
    
    // shatha's methods
    int registerEmployee(
            String firstName, String lastName, String icOrPassportNo,
            String username, String passwordHash, String role,
            String spouseName, int numberOfChildren,
            String dependentName, String dependantRelationship, String dependentDOB,
            String relationshipStatus,
            String emergencyContact, String emergencyContactRelationship,
            String dateOfBirth, String gender,
            String address, String email, String phoneNo
    ) throws RemoteException;
    List<String[]> getPendingLeaveRequests() throws RemoteException;
    String[] getEmployeeInfoById(int employeeId) throws RemoteException;
    boolean reviewLeaveRequest(int leaveApplicationId, String decision,
                               int hrEmployeeId) throws RemoteException;
    
    // samara's methods
    public int applyLeave(String loggedInEmployeeId, Date startDate, Date endDate, String text)
            throws RemoteException;
    public String getLeaveStatus(int requestId, String loggedInEmployeeId)
            throws RemoteException;
    public List<LeaveRequest> getLeaveHistory(String loggedInEmployeeId)
            throws RemoteException;
}