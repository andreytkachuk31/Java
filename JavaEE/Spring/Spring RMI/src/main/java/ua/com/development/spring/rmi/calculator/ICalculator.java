package ua.com.development.spring.rmi.calculator;

import ua.com.development.spring.rmi.action.Action;

public interface ICalculator {

    void setAction(Action act);

    String calc(String[] args);
}
