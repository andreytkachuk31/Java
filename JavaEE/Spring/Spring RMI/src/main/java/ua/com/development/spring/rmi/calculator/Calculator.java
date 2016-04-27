package ua.com.development.spring.rmi.calculator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ua.com.development.spring.rmi.action.Action;

@Component
public class Calculator implements ICalculator {

    @Autowired
    @Qualifier("actionAdd")
    private Action action;

    @Override
    public void setAction(Action action) {
        this.action = action;
    }

    @Override
    public String calc(String[] args) {
        long op1 = Long.parseLong(args[0]);
        long op2 = Long.parseLong(args[1]);

        return op1 + action.getName() + op2 + " = " + action.performAction(op1, op2);
    }

}
