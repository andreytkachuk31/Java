package ua.com.development.spring.rmi;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Server {

    public static void main(String[] args) {
        new ClassPathXmlApplicationContext("spring-rmi-config.xml");
    }

}
