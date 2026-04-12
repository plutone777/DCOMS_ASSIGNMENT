/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import PersonalDetailsModule.PersonalDetailsModule;
import FamilyDetailsModule.FamilyDetailsModule;
import LeaveBalanceModule.LeaveBalanceModule;

public class RMIObjectImplMain extends UnicastRemoteObject implements RMIInterfaceMain {
    private PersonalDetailsModule personalModule = new PersonalDetailsModule();
    private FamilyDetailsModule familyModule = new FamilyDetailsModule();
    private LeaveBalanceModule leaveModule = new LeaveBalanceModule();

    public RMIObjectImplMain() throws RemoteException {
        super();
    }

    @Override
    public boolean updatePersonalDetails(int employeeId, String address, String email, String phone) throws RemoteException {
        return personalModule.updatePersonalDetails(employeeId, address, email, phone);
    }

    @Override
    public boolean updateFamilyDetails(int employeeId, String spouseName, int children, int emergencyContact) throws RemoteException {
        return familyModule.updateFamilyDetails(employeeId, spouseName, children, emergencyContact);
    }

    @Override
    public boolean checkLeaveBalance(int employeeId, int leaveYear) throws RemoteException {
        // You can adapt this to return a boolean or a serialized object
        return true; // placeholder
    }
}

