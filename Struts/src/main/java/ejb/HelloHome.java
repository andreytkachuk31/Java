package ejb;

import javax.ejb.CreateException;
import javax.ejb.EJBHome;
import java.rmi.RemoteException;

/**
 * Date: 02.04.14
 * User: andrey.tkachuk
 */
public interface HelloHome extends EJBHome {

    public HelloObject create() throws RemoteException, CreateException;

}
