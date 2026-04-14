package dcoms_assignment.Mayan;

import java.util.List;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;
import java.text.SimpleDateFormat;

public class HR_Report {
    
    private HR_DataAccess dao = new HR_DataAccess();
    
    public void exportLeaveApplicationsByYear(int employeeID, int year, String filePath) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        List<LeaveApplication> list = dao.getLeaveApplicationsByEmployeeAndYear(employeeID, year);
        Employee emp = dao.getEmployeeByID(employeeID);
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.append("Yearly Leave Report - " + year + "\n\n");
            if (emp != null) {
                writer.append("Employee ID: " + emp.getEmployeeID() + "\n");
                writer.append("Employee Name: " + emp.getFirstName() + " " + emp.getLastName() + "\n\n");
            }
            writer.append("LeaveApplicationID,StartDate,EndDate,"
                    + "NumberOfDays,Reason,ApplyDate,ApprovedBy,ApprovalDate\n");
            
            int totalLeaveDays = 0;
            for (LeaveApplication la : list) {
                totalLeaveDays += la.getNumberOfDays();
                
                writer.append(String.valueOf(la.getLeaveApplicationID())).append(",");
                writer.append(String.valueOf(la.getStartDate())).append(",");
                writer.append(String.valueOf(la.getEndDate())).append(",");
                writer.append(String.valueOf(la.getNumberOfDays())).append(",");
                String reason = la.getReason() != null ? la.getReason().replace("\"", "\"\"") : "";
                writer.append("\"").append(reason).append("\"").append(",");
                writer.append(la.getApplyDate() != null ? sdf.format(la.getApplyDate()) : "").append(","); 
                
                String approvedByName = "";
                if (la.getApprovedBy() != null) {
                    approvedByName = dao.getEmployeeFullName(la.getApprovedBy());
                }
                writer.append(approvedByName != null ? approvedByName : "").append(",");
                
                writer.append(la.getApprovalDate() != null ? sdf.format(la.getApprovalDate()) : "").append("\n");
            }
            writer.append("\nTotal Applications: " + list.size());
            writer.append("\nTotal Leave Days Taken: " + totalLeaveDays);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
