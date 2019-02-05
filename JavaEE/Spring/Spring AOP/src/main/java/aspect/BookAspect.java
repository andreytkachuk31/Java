package aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class BookAspect {

    @Before("execution(* controller.BookController.addBook(..))")
    public void before() {
        System.out.println("Start adding book...");
    }

    @Around("@annotation(annotation.PreUpdate)")
    public Object profiling(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.nanoTime();

        Object result = joinPoint.proceed();

        System.out.println("Updating took: " + (System.nanoTime() - start));
        return result;
    }
}
