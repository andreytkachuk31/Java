package ua.com.development;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ua.com.development.domain.User;

public class AopDemo {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-aop-config.xml");
        User user = applicationContext.getBean("user", User.class);
        user.prePersist();
    }
}
