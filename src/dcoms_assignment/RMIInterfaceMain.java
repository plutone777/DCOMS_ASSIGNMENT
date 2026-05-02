package dcoms_assignment;

import dcoms_assignment.employee.LeaveRequest;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

public interface RMIInterfaceMain extends Remote{

    //declare all your methods here! Not implementation, just ur method name.
    //make sure that they throw RemoteException

    // samara's methods
    int applyLeave(String employeeId, Date startDate, Date endDate, String reason) throws RemoteException;
    String getLeaveStatus(int requestId, String employeeId) throws RemoteException;
    List<LeaveRequest> getLeaveHistory(String employeeId) throws RemoteException;
}