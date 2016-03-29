package ws.endpoint;

import ws.HelloWorldImpl;

import javax.xml.ws.Endpoint;


/**
 * Date: 02.12.2014
 * User: andrey.tkachuk
 */
public class HelloWorldPublisher {

    public static void main(String[] args) {
        Endpoint.publish("http://localhost:9999/ws/hello", new HelloWorldImpl());

        System.out.println("Service is published!");
    }

}