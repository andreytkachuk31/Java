package service;

import org.springframework.stereotype.Service;

@Service
public class HelloService {

    public String helloWorld() {
        return "Hello World!";
    }
}
