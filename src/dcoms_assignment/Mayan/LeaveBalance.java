package dcoms_assignment.Mayan;

import java.io.Serializable;

public class LeaveBalance implements Serializable {

    private int balanceID;
    private int employeeID;
    private int year;
    private int totalDays;
    private int usedDays;
    private int remainingDays;

    public LeaveBalance(int balanceID, int employeeID, int year,
                        int totalDays, int usedDays, int remainingDays) {
        this.balanceID = balanceID;
        this.employeeID = employeeID;
        this.year = year;
        this.totalDays = totalDays;
        this.usedDays = usedDays;
        this.remainingDays = remainingDays;
    }

    public int getBalanceID() {
        return balanceID;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public int getYear() {
        return year;
    }

    public int getTotalDays() {
        return totalDays;
    }

    public int getUsedDays() {
        return usedDays;
    }

    public int getRemainingDays() {
        return remainingDays;
    }

    @Override
    public String toString() {
        return "LeaveBalance{" +
                "balanceID=" + balanceID +
                ", employeeID=" + employeeID +
                ", year=" + year +
                ", totalDays=" + totalDays +
                ", usedDays=" + usedDays +
                ", remainingDays=" + remainingDays +
                '}';
    }
}
