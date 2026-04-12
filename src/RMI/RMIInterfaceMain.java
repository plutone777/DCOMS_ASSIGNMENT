/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMIInterfaceMain extends Remote {
    // Personal details
    boolean updatePersonalDetails(int employeeId, String address, String email, String phone) throws RemoteException;

    // Family details
    boolean updateFamilyDetails(int employeeId, String spouseName, int children, int emergencyContact) throws RemoteException;

    // Leave balance
    boolean checkLeaveBalance(int employeeId, int leaveYear) throws RemoteException;
}

