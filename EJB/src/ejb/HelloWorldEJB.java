package ejb;

import javax.ejb.EJBObject;
import java.rmi.RemoteException;

public interface HelloWorldEJB extends EJBObject {
    public String helloWorld() throws RemoteException;
}