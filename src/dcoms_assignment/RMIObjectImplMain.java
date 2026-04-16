package dcoms_assignment;

import dcoms_assignment.employee.LeaveModule;
import dcoms_assignment.employee.LeaveRequest;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;
import java.util.List;

public class RMIObjectImplMain extends UnicastRemoteObject implements RMIInterfaceMain{
    //create objects of your classes here
    private LeaveModule leaveModule;
    
    public RMIObjectImplMain()throws RemoteException{
        super();
        leaveModule = new LeaveModule();
    }
    
     // ========== ВАША РЕАЛИЗАЦИЯ ==========
    @Override
    public int applyLeave(String employeeId, Date startDate, Date endDate, String reason) 
            throws RemoteException {
        return leaveModule.applyLeave(employeeId, startDate, endDate, reason);
    }
    
    @Override
    public String getLeaveStatus(int requestId, String employeeId) 
            throws RemoteException {
        return leaveModule.getLeaveStatus(requestId, employeeId);
    }
    
    @Override
    public List<LeaveRequest> getLeaveHistory(String employeeId) 
            throws RemoteException {
        return leaveModule.getLeaveHistory(employeeId);
    }
    //override your methods here

}
