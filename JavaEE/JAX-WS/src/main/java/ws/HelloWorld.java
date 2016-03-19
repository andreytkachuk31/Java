package ws;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

/**
 * Date: 06.12.2014
 * User: andrey.tkachuk
 */
@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC, use= SOAPBinding.Use.LITERAL)
public class HelloWorld {

    @WebMethod(operationName="getHelloWorld")
    public String getHelloWorld(String name) {
        return "Hello World JAX-WS " + name;
    }

}
