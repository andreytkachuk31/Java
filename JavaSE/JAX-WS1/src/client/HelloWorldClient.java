package client;

/**
 * Date: 02.12.2014
 * User: andrey.tkachuk
 */
public class HelloWorldClient {

    public static void main(String[] args) {
        HelloWorldImplService helloWorldImplService = new HelloWorldImplService();
        HelloWorld helloWorldImplPort = helloWorldImplService.getHelloWorldImplPort();
        System.out.println(helloWorldImplPort.getHelloWorldAsString("Andrey"));
    }
}