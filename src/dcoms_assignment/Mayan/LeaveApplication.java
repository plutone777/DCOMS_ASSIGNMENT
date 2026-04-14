package dcoms_assignment.Mayan;

import java.sql.Date;
import java.sql.Timestamp;

public class LeaveApplication implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    
    private int leaveApplicationID;
    private int employeeID;
    private Date startDate;
    private Date endDate;
    private int numberOfDays;
    private String reason;
    private Timestamp applyDate;
    private String status;
    private Integer approvedBy;      
    private Timestamp approvalDate;

    public LeaveApplication(int leaveApplicationID, int employeeID,
                            Date startDate, Date endDate, int numberOfDays,
                            String reason, Timestamp applyDate, String status,
                            Integer approvedBy, Timestamp approvalDate) {
        
        this.leaveApplicationID = leaveApplicationID;
        this.employeeID = employeeID;
        this.startDate = startDate;
        this.endDate = endDate;
        this.numberOfDays = numberOfDays;
        this.reason = reason;
        this.applyDate = applyDate;
        this.status = status;
        this.approvedBy = approvedBy;
        this.approvalDate = approvalDate;
    }


    public int getLeaveApplicationID() {
        return leaveApplicationID;
    }

    public void setLeaveApplicationID(int leaveApplicationID) {
        this.leaveApplicationID = leaveApplicationID;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getNumberOfDays() {
        return numberOfDays;
    }

    public void setNumberOfDays(int numberOfDays) {
        this.numberOfDays = numberOfDays;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Timestamp getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(Timestamp applyDate) {
        this.applyDate = applyDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(Integer approvedBy) {
        this.approvedBy = approvedBy;
    }

    public Timestamp getApprovalDate() {
        return approvalDate;
    }

    public void setApprovalDate(Timestamp approvalDate) {
        this.approvalDate = approvalDate;
    }
}
