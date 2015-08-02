package ejb;

import javax.ejb.EJBObject;
import java.rmi.RemoteException;

/**
 * Date: 02.04.14
 * User: andrey.tkachuk
 */
public interface HelloObject extends EJBObject {

    public String sayHello() throws RemoteException;
}
