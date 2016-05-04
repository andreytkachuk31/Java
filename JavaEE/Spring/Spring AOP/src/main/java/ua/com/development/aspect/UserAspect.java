package ua.com.development.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class UserAspect {

    @Before(value = "@annotation(ua.com.development.domain.PrePersist)")
    public void before() {
        System.out.println("Hello Aspect!");
    }
}
