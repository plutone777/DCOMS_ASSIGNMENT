package dcoms_assignment;

import dcoms_assignment.HR.HRModule;
import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class RMIObjectImplMain extends UnicastRemoteObject implements RMIInterfaceMain{
    //create objects of your classes here
    public RMIObjectImplMain()throws RemoteException{
        super();
    }
    //override your methods here
   private static final long serialVersionUID = 1L;

    // ── INSTRUCTION 3: Create objects of your classes here ───────────

  

    // Your HR object (Shorouq)
    // HRModule handles multithreading — it uses a thread pool internally
    HRModule hrModule = new HRModule();


    // ── INSTRUCTION 4: Override only the methods you are responsible for

    // ── FRIEND'S METHODS (Mayan — override only) ─────────────────────
   

    // ── YOUR HR METHODS (Shorouq — override only) ────────────────────

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
            List<dcoms_assignment.HR.LeaveRecord> records =
                hrModule.getPendingLeaveRequests();
            List<String[]> result = new ArrayList<>();
            for (dcoms_assignment.HR.LeaveRecord r : records) {
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

    // ── EMPLOYEE STUBS — teammates replace these bodies ──────────────

    @Override
    public boolean employeeLogin(String username, String password) throws RemoteException {
        // TODO: teammate implements
        return false;
    }

    @Override
    public int getLeaveBalance(int employeeId) throws RemoteException {
        // TODO: teammate implements
        return 0;
    }

    @Override
    public String applyForLeave(int employeeId, String leaveType,
                                String startDate, String endDate,
                                int numberOfDays, String reason) throws RemoteException {
        // TODO: teammate implements
        return "NOT_IMPLEMENTED";
    }

    @Override
    public List<String[]> viewLeaveHistory(int employeeId) throws RemoteException {
        // TODO: teammate implements
        return new ArrayList<>();
    }
}
