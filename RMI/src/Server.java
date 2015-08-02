import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * Date: 04.06.14
 * User: andrey.tkachuk
 */
public class Server {

    public static void main(String[] args) {
        Hello hello = new HelloBean();
        try {
            Hello stub = (Hello) UnicastRemoteObject.exportObject(hello, 0);

            // Bind the remote object's stub in the registry
            Registry registry = LocateRegistry.getRegistry();
            registry.bind("Hello", stub);
            System.err.println("Server ready");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }

}

