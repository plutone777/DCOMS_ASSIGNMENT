package dcoms_assignment;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface RMIInterfaceMain extends Remote{
    //declare all your methods here! Not implementation, just ur method name.
    //make sure that they throw RemoteException
    
     // ── HR FEATURE 1: Register Employee ──────────────────────────────
    
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

    // ── HR FEATURE 2a: Get all PENDING leave requests ─────────────────
    
    List<String[]> getPendingLeaveRequests() throws RemoteException;

    // ── HR FEATURE 2b: Get employee info for table row ────────────────
    
    String[] getEmployeeInfoById(int employeeId) throws RemoteException;

    // ── HR FEATURE 2c: Accept or Reject a leave request ──────────────
    
    boolean reviewLeaveRequest(int leaveApplicationId, String decision,
                               int hrEmployeeId) throws RemoteException;

    // ── EMPLOYEE METHODS (teammates declare and implement) ────────────
    boolean    employeeLogin(String username, String password)          throws RemoteException;
    int        getLeaveBalance(int employeeId)                          throws RemoteException;
    String     applyForLeave(int employeeId, String leaveType,
                             String startDate, String endDate,
                             int numberOfDays, String reason)           throws RemoteException;
    List<String[]> viewLeaveHistory(int employeeId)                    throws RemoteException;
    
    
}