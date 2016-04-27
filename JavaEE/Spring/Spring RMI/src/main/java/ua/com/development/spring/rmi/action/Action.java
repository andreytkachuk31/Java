package ua.com.development.spring.rmi.action;

public interface Action {

    long performAction(long operand1, long operand2);

    String getName();
}
