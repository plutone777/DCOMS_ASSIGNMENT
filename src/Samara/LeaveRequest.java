package Samara;

import java.io.Serializable;
import java.util.Date;

public class LeaveRequest implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private int requestId;
    private String employeeId;
    private Date startDate;
    private Date endDate;
    private String reason;
    private String status;
    private Date appliedDate;
    
    public LeaveRequest() {}
    
    public LeaveRequest(int requestId, String employeeId, Date startDate, 
                        Date endDate, String reason, String status, Date appliedDate) {
        this.requestId = requestId;
        this.employeeId = employeeId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.reason = reason;
        this.status = status;
        this.appliedDate = appliedDate;
    }
    
    // Getters
    public int getRequestId() { return requestId; }
    public String getEmployeeId() { return employeeId; }
    public Date getStartDate() { return startDate; }
    public Date getEndDate() { return endDate; }
    public String getReason() { return reason; }
    public String getStatus() { return status; }
    public Date getAppliedDate() { return appliedDate; }
    
    // Setters
    public void setRequestId(int requestId) { this.requestId = requestId; }
    public void setEmployeeId(String employeeId) { this.employeeId = employeeId; }
    public void setStartDate(Date startDate) { this.startDate = startDate; }
    public void setEndDate(Date endDate) { this.endDate = endDate; }
    public void setReason(String reason) { this.reason = reason; }
    public void setStatus(String status) { this.status = status; }
    public void setAppliedDate(Date appliedDate) { this.appliedDate = appliedDate; }
    
    @Override
    public String toString() {
        return "LeaveRequest{" +
                "requestId=" + requestId +
                ", employeeId='" + employeeId + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", status='" + status + '\'' +
                '}';
    }
}