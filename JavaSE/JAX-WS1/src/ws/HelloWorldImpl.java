package ws;

import javax.jws.HandlerChain;
import javax.jws.WebService;

/**
 * Date: 02.12.2014
 * User: andrey.tkachuk
 */
@WebService(endpointInterface = "ws.HelloWorld")
@HandlerChain(file = "handlers.xml")
public class HelloWorldImpl implements HelloWorld {

    @Override
    public String getHelloWorldAsString(String name) {
        return "Hello World JAX-WS " + name;
    }
}