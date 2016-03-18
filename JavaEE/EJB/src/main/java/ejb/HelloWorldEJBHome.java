package ejb;

import javax.ejb.CreateException;
import javax.ejb.EJBHome;
import java.rmi.RemoteException;

/**
 * Date: 23.07.14
 * User: andrey.tkachuk
 */
public interface HelloWorldEJBHome extends EJBHome {
    public HelloWorldEJB create() throws RemoteException, CreateException;
}
