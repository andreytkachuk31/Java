import ejb.HelloHome;
import ejb.HelloObject;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;
import java.util.Properties;

/**
 * Date: 02.04.14
 * User: andrey.tkachuk
 */
public class ClientEjbMain {

    public static void main(String[] args) {
        try {

            //The JNDI properties you set depend on which server you are using.
            //These properties are for the Remote Server.
            final Properties prop = new Properties();
            prop.put(Context.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
            prop.put(Context.PROVIDER_URL, "jnp://127.0.0.1:1099");

            //Now use those properties to create
            //a JNDI InitialContext with the server.
            InitialContext ctx = new InitialContext(prop);

            //Lookup the bean using it's deployment id
            Object obj = ctx.lookup("/Hello");

            //Be good and use RMI remote object narrowing
            //as required by the EJB specification.
            HelloHome ejbHome = (HelloHome) PortableRemoteObject.narrow(obj, HelloHome.class);

            //Use the HelloHome to create a HelloObject
            HelloObject ejbObject = ejbHome.create();

            //The part we've all been wainting for...
            String message = ejbObject.sayHello();

            //A drum roll please.
            System.out.println(message);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
