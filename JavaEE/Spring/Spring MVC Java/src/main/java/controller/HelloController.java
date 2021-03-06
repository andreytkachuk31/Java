package controller;

import annotation.MyInject;
import model.User;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.HelloService;

import java.util.Locale;
import java.util.concurrent.Callable;

@Controller
@RequestMapping("hello")
public class HelloController {

    @MyInject
    private HelloService helloService;

    @RequestMapping
    @ResponseBody
    public String helloWorld() {
        return this.helloService.helloWorld();
    }

    @RequestMapping("/user")
    @ResponseBody
    public User helloUser() {
        return new User(1, "Name", "Surname");
    }

    @RequestMapping("/cookie")
    @ResponseBody
    public String helloCookie(@CookieValue("SESSION") String session) {
        return session;
    }

    @RequestMapping("/async")
    @ResponseBody
    public Callable<String> helloAsync() {
        return () -> "hello async";
    }

    @RequestMapping("/locale")
    @ResponseBody
    public Locale helloLocale() {
        return LocaleContextHolder.getLocale();
    }
}
