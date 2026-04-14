/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DB;
import java.io.Serializable;

public class LeaveBalanceData implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private int totalDays;
    private int usedDays;
    private int remainingDays;

    public LeaveBalanceData(int totalDays, int usedDays, int remainingDays) {
        this.totalDays = totalDays;
        this.usedDays = usedDays;
        this.remainingDays = remainingDays;
    }

    public int getTotalDays() { return totalDays; }
    public int getUsedDays() { return usedDays; }
    public int getRemainingDays() { return remainingDays; }
}