INSTRUCTIONS !!!

1. Put your actual logic in your own separate classes.

2. Add your method declarations into RMIInterfaceMain.
   Example:
   boolean login(String username, String password) throws RemoteException;
   String getEmployeeName(int id) throws RemoteException;

3. In RMIObjectImplMain, create an object of your own class.
   Example:
   LoginModule loginModule = new LoginModule();

4. Override only the methods you are responsible for.
   Example:
   @Override
   public boolean login(String username, String password) throws RemoteException {
       return loginModule.login(username, password);
   }

5. Do not edit:
   - ServerMain
   - ClientMain (maybe)
   - other members' methods

6. Database stuff will be decided soon.