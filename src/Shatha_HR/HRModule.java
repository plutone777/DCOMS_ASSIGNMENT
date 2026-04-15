package Shatha_HR;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.Callable;


public class HRModule {

    // Thread pool: 5 threads available for concurrent HR operations
    private final ExecutorService threadPool = Executors.newFixedThreadPool(5);

    // Data access object : all SQL lives in here
    private final HRDataAccess dao = new HRDataAccess();

    // ─────────────────────────────────────────────────────────────────
    //  REGISTER EMPLOYEE 
    // ─────────────────────────────────────────────────────────────────
    public int registerEmployee(
            String firstName, String lastName, String icOrPassportNo,
            String username, String passwordHash, String role,
            String spouseName, int numberOfChildren,
            String dependentName, String dependantRelationship, String dependentDOB,
            String relationshipStatus,
            String emergencyContact, String emergencyContactRelationship,
            String dateOfBirth, String gender,
            String address, String email, String phoneNo
    ) throws Exception {

        // Submit the DB operation as a Callable task to the thread pool
        Callable<Integer> task = () -> dao.registerEmployee(
            firstName, lastName, icOrPassportNo, username, passwordHash, role,
            spouseName, numberOfChildren,
            dependentName, dependantRelationship, dependentDOB, relationshipStatus,
            emergencyContact, emergencyContactRelationship,
            dateOfBirth, gender, address, email, phoneNo
        );

        Future<Integer> future = threadPool.submit(task);
        return future.get(); // wait for result
    }

    // ─────────────────────────────────────────────────────────────────
    //  GET PENDING LEAVE REQUESTS
    // ─────────────────────────────────────────────────────────────────
    public List<LeaveRecord> getPendingLeaveRequests() throws Exception {
        Callable<List<LeaveRecord>> task = () -> dao.getPendingLeaveRequests();
        Future<List<LeaveRecord>> future = threadPool.submit(task);
        return future.get();
    }

    // ─────────────────────────────────────────────────────────────────
    //  GET EMPLOYEE INFO 
    // ─────────────────────────────────────────────────────────────────
    public String[] getEmployeeInfoById(int employeeId) throws Exception {
        Callable<String[]> task = () -> dao.getEmployeeInfoById(employeeId);
        Future<String[]> future = threadPool.submit(task);
        return future.get();
    }

    // ─────────────────────────────────────────────────────────────────
    //  REVIEW LEAVE REQUEST 
    // ─────────────────────────────────────────────────────────────────
    public boolean reviewLeaveRequest(int leaveApplicationId, String decision,
                                      int hrEmployeeId) throws Exception {
        Callable<Boolean> task = () -> dao.reviewLeaveRequest(
            leaveApplicationId, decision, hrEmployeeId
        );
        Future<Boolean> future = threadPool.submit(task);
        return future.get();
    }

    // ─────────────────────────────────────────────────────────────────
    //  SHUTDOWN 
    // ─────────────────────────────────────────────────────────────────
    public void shutdown() {
        threadPool.shutdown();
    }
}
