
package Samara;

import Bisma_Client.CheckLeaveBalanceForm;
import Bisma_Client.UpdateFamilyDetailsForm;
import Bisma_Client.UpdatePersonalDetailsForm;
import RMI.RMIInterfaceMain;
import Samara.LeaveRequest;
import javax.swing.JOptionPane;
import java.rmi.Naming;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 *
 * @author kulmu
 */
public class EmployeeUI extends javax.swing.JFrame {
    
    private RMIInterfaceMain service;
    private String loggedInEmployeeId;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public EmployeeUI() {
        initComponents();
    }

    public EmployeeUI(String employeeId, RMIInterfaceMain remote) {
        this.loggedInEmployeeId = employeeId;
        this.service = remote;
        initComponents();
        
        
        lblWelcome.setText("Welcome, " + employeeId);
        
        jButton1.addActionListener(e -> updateDetails());
        jButton2.addActionListener(e -> checkBalance());
        btnApplyLeave.addActionListener(e -> applyLeave());
        btnViewStatus.addActionListener(e -> viewStatus()); 
        btnViewHistory.addActionListener(e -> viewHistory());
        jButton6.addActionListener(e -> logout());
        
        System.out.println("[Employee] Connected via shared stub.");
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblWelcome = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        btnApplyLeave = new javax.swing.JButton();
        btnViewStatus = new javax.swing.JButton();
        btnViewHistory = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblWelcome.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblWelcome.setText("Welcome, Employee!");

        jButton1.setText("Update Personal Details");
        jButton1.setToolTipText("");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Check Leave Balance");
        jButton2.setToolTipText("");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        btnApplyLeave.setText("Apply for Leave");
        btnApplyLeave.setToolTipText("");
        btnApplyLeave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnApplyLeaveActionPerformed(evt);
            }
        });

        btnViewStatus.setText("View Leave Status");
        btnViewStatus.setToolTipText("");

        btnViewHistory.setText("View Leave History");
        btnViewHistory.setToolTipText("");

        jButton6.setText("Logout");

        jButton3.setText("Update Family Details");
        jButton3.setToolTipText("");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton6)
                            .addComponent(lblWelcome)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(115, 115, 115)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnApplyLeave, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnViewStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnViewHistory, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(131, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(lblWelcome)
                .addGap(47, 47, 47)
                .addComponent(jButton1)
                .addGap(18, 18, 18)
                .addComponent(jButton3)
                .addGap(18, 18, 18)
                .addComponent(jButton2)
                .addGap(18, 18, 18)
                .addComponent(btnApplyLeave)
                .addGap(18, 18, 18)
                .addComponent(btnViewStatus)
                .addGap(18, 18, 18)
                .addComponent(btnViewHistory)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                .addComponent(jButton6)
                .addGap(27, 27, 27))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnApplyLeaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnApplyLeaveActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnApplyLeaveActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        new UpdatePersonalDetailsForm(service, loggedInEmployeeId).setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        new UpdateFamilyDetailsForm(service, loggedInEmployeeId).setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        new CheckLeaveBalanceForm(service, loggedInEmployeeId).setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    
    private void applyLeave() {
        javax.swing.JTextField startField = new javax.swing.JTextField();
        javax.swing.JTextField endField = new javax.swing.JTextField();
        javax.swing.JTextField reasonField = new javax.swing.JTextField();
        
        Object[] message = {
            "Start Date (YYYY-MM-DD):", startField,
            "End Date (YYYY-MM-DD):", endField,
            "Reason:", reasonField
        };
        
        int option = JOptionPane.showConfirmDialog(this, message, "Apply for Leave", 
                        JOptionPane.OK_CANCEL_OPTION);
        
        if (option == JOptionPane.OK_OPTION) {
            try {
                Date startDate = dateFormat.parse(startField.getText());
                Date endDate = dateFormat.parse(endField.getText());
                
                int requestId = service.applyLeave(loggedInEmployeeId, startDate, endDate, 
                                                    reasonField.getText());
                JOptionPane.showMessageDialog(this, 
                    "Leave application submitted!\nRequest ID: " + requestId + 
                    "\nStatus: PENDING (waiting for HR approval)");
                    
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), 
                                "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void viewStatus() {
        String reqIdStr = JOptionPane.showInputDialog(this, "Enter Request ID:");
        if (reqIdStr == null || reqIdStr.trim().isEmpty()) return;
        
        try {
            int requestId = Integer.parseInt(reqIdStr);
            String status = service.getLeaveStatus(requestId, loggedInEmployeeId);
            JOptionPane.showMessageDialog(this, status);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid Request ID", 
                            "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), 
                            "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void viewHistory() {
        try {
            List<LeaveRequest> history = service.getLeaveHistory(loggedInEmployeeId);
            
            if (history == null || history.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No leave history found.");
                return;
            }
            
            StringBuilder sb = new StringBuilder("Leave History:\n\n");
            for (LeaveRequest req : history) {
                sb.append("ID: ").append(req.getRequestId())
                  .append("Status: ").append(req.getStatus())
                  .append("From: ").append(dateFormat.format(req.getStartDate()))
                  .append("To: ").append(dateFormat.format(req.getEndDate()))
                  .append("\n");
                if (req.getReason() != null && !req.getReason().isEmpty()) {
                    sb.append("Reason: ").append(req.getReason()).append("\n");
                }
            }
            
            javax.swing.JTextArea textArea = new javax.swing.JTextArea(sb.toString());
            textArea.setEditable(false);
            textArea.setRows(15);
            textArea.setColumns(50);
            javax.swing.JScrollPane scrollPane = new javax.swing.JScrollPane(textArea);
            JOptionPane.showMessageDialog(this, scrollPane, "Leave History", 
                            JOptionPane.INFORMATION_MESSAGE);
                            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), 
                            "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void updateDetails() {
        JOptionPane.showMessageDialog(this, 
            "Update Personal & Family Details",
            "Info", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void checkBalance() {
        JOptionPane.showMessageDialog(this, 
            "Check Leave Balance",
            "Info", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void logout() {
        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to logout?", 
                        "Logout", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            Mayan.UserSession.clearSession();
            dispose();
        }
    }
    
    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnApplyLeave;
    private javax.swing.JButton btnViewHistory;
    private javax.swing.JButton btnViewStatus;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel lblWelcome;
    // End of variables declaration//GEN-END:variables
}
