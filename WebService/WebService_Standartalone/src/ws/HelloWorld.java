package ws;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

/**
 * Service Endpoint Interface
 *
 * Date: 02.12.2014
 * User: andrey.tkachuk
 */
@WebService
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT, use= SOAPBinding.Use.LITERAL) //optional
public interface HelloWorld {

    @WebMethod
    String getHelloWorldAsString(String name);

}
