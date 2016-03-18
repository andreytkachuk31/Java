package ejb;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

/**
 * Date: 23.07.14
 * User: andrey.tkachuk
 */
public class HelloWorldEJBBean implements SessionBean {

    public String helloWorld() {
        return "Hello world, I am an EJB2 session";
    }

    public void ejbActivate() throws EJBException {}
    public void ejbPassivate() throws EJBException  {}
    public void ejbRemove() throws EJBException {}
    public void setSessionContext(SessionContext sessionContext) throws EJBException {}
    public void ejbCreate() throws CreateException {}


}
