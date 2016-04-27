package ua.com.development.spring.rmi.action;

import org.springframework.stereotype.Component;

@Component
public class ActionMultiply implements Action {

    @Override
    public long performAction(long operand1, long operand2) {
        return operand1 * operand2;
    }

    @Override
    public String getName() {
        return " * ";
    }
}
