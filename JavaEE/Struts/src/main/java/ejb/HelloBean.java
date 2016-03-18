package ejb;

import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import java.rmi.RemoteException;

/**
 * Date: 02.04.14
 * User: andrey.tkachuk
 */
public class HelloBean implements SessionBean {

    private SessionContext sessionContext;

    public void setSessionContext(SessionContext sessionContext) throws EJBException, RemoteException {
        this.sessionContext = sessionContext;
    }

    public void ejbRemove() throws EJBException, RemoteException {
    }

    public void ejbActivate() throws EJBException, RemoteException {
    }

    public void ejbPassivate() throws EJBException, RemoteException {
    }

    public String sayHello() throws RemoteException {
        return "Hello World!!!!!";
    }
}
