package ua.com.development.spring.rmi;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ua.com.development.spring.rmi.calculator.ICalculator;

public class Client {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-rmi-config.xml");
        ICalculator calc = (ICalculator) context.getBean("remoteCalculator");
        System.out.print(calc.calc(new String[] {"30", "60"}));
    }
}
