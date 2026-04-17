
package RMI;

import javax.rmi.ssl.SslRMIServerSocketFactory;
import javax.rmi.ssl.SslRMIClientSocketFactory;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ServerMain {
    public static void main(String[] args) throws Exception {

        System.setProperty("javax.net.ssl.keyStore", "server.keystore");
        System.setProperty("javax.net.ssl.keyStorePassword", "123456");

        Registry reg = LocateRegistry.createRegistry(
                1044,
                new SslRMIClientSocketFactory(),
                new SslRMIServerSocketFactory()
        );

        reg.rebind("HRMSService", new RMIObjectImplMain());

        System.out.println("SSL RMI Server started...");
    }
}