import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Date: 04.06.14
 * User: andrey.tkachuk
 */
public interface Hello extends Remote {

    String sayHello() throws RemoteException;

}
