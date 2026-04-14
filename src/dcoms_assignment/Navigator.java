package dcoms_assignment;

import dcoms_assignment.Mayan.Employee;
import dcoms_assignment.Mayan.formHrReport;
import dcoms_assignment.RMIInterfaceMain;
import javax.swing.JOptionPane;

public class Navigator {

    public static void openHome(Employee emp, RMIInterfaceMain remote) {

        if (emp.getRole().equalsIgnoreCase("HR")) {
            // navigate to HR menu
            new formHrReport(emp, remote).setVisible(true);
            JOptionPane.showMessageDialog(null,
                    "Welcome HR " + emp.getFirstName());

        } else if (emp.getRole().equalsIgnoreCase("Employee")) {
            // navigate to Employee menu
            
            JOptionPane.showMessageDialog(null,
                    "Welcome Employee " + emp.getFirstName());

        } else {
            JOptionPane.showMessageDialog(null,
                    "Unknown role for " + emp.getFirstName());
        }
    }
}