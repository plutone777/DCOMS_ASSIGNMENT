package RMI;

import Mayan.Employee;
import Mayan.formHrReport;
import RMI.RMIInterfaceMain;
import Samara.EmployeeUI;
import Shatha_HR.HRForm;
import javax.swing.JOptionPane;

public class Navigator {

    public static void openHome(Employee emp, RMIInterfaceMain remote) {

        if (emp.getRole().equalsIgnoreCase("HR")) {
            // navigate to HR menu
            new HRForm(remote, emp.getEmployeeID(), emp).setVisible(true);
            
            JOptionPane.showMessageDialog(null,
                    "Welcome HR " + emp.getFirstName());

        } else if (emp.getRole().equalsIgnoreCase("Employee")) {
            // navigate to Employee menu
            new EmployeeUI(String.valueOf(emp.getEmployeeID()), remote).setVisible(true);
            
            JOptionPane.showMessageDialog(null,
                    "Welcome Employee " + emp.getFirstName());

        } else {
            JOptionPane.showMessageDialog(null,
                    "Unknown role for " + emp.getFirstName());
        }
    }
}