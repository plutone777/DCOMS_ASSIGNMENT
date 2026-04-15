package Shatha_HR;

import java.io.Serializable;


public class LeaveRecord implements Serializable {

    // serialVersionUID is required for Serializable classes.
    // It ensures the class version on sender and receiver match.
    private static final long serialVersionUID = 1001L;

    private String leaveApplicationId;
    private String employeeId;
    private String fullName;
    private String leaveType;
    private String startDate;
    private String endDate;
    private String numberOfDays;
    private String reason;

    public LeaveRecord(String leaveApplicationId, String employeeId,
                       String fullName, String leaveType,
                       String startDate, String endDate,
                       String numberOfDays, String reason) {
        this.leaveApplicationId = leaveApplicationId;
        this.employeeId         = employeeId;
        this.fullName           = fullName;
        this.leaveType          = leaveType;
        this.startDate          = startDate;
        this.endDate            = endDate;
        this.numberOfDays       = numberOfDays;
        this.reason             = reason;
    }

    public String getLeaveApplicationId() { return leaveApplicationId; }
    public String getEmployeeId()         { return employeeId; }
    public String getFullName()           { return fullName; }
    public String getLeaveType()          { return leaveType; }
    public String getStartDate()          { return startDate; }
    public String getEndDate()            { return endDate; }
    public String getNumberOfDays()       { return numberOfDays; }
    public String getReason()             { return reason; }

    /** Converts to String[] format used by HRForm's list box */
    public String[] toArray() {
        return new String[]{
            leaveApplicationId, employeeId, fullName, leaveType,
            startDate, endDate, numberOfDays, reason
        };
    }
}
