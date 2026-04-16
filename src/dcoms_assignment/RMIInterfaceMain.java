package dcoms_assignment;

import dcoms_assignment.employee.LeaveRequest;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

public interface RMIInterfaceMain extends Remote{

    public int applyLeave(String loggedInEmployeeId, Date startDate, Date endDate, String text)
            throws RemoteException;
        
    //declare all your methods here! Not implementation, just ur method name.
    //make sure that they throw RemoteException

    public String getLeaveStatus(int requestId, String loggedInEmployeeId)
            throws RemoteException;

    public List<LeaveRequest> getLeaveHistory(String loggedInEmployeeId)
            throws RemoteException;
}