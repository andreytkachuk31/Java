package com.epam;

import java.util.Optional;

import static java.util.Optional.empty;
import static java.util.Optional.of;

public class Calculator {

    private static final Integer DEFAULT_RESULT = 4;

    public static void main(String[] args) {
        if (isNotCorrectLineArguments(args)) {
            log(DEFAULT_RESULT);
            return;
        }
        int param1 = Integer.valueOf(args[0].trim());
        int param2 = Integer.valueOf(args[1].trim());
        String operation = args[2].trim();

        Optional<Integer> result = perform(param1, param2, operation);
        if (result.isPresent())
            log(result.get());
        else
            log(DEFAULT_RESULT);
    }

    private static Optional<Integer> perform(int param1, int param2, String operation) {
        Optional<Integer> result = empty();

        switch (operation) {
            case "add":
                result = of(param1 + param2);
                break;
            case "sub":
                result = of(param1 - param2);
                break;
            case "div":
                result = of(param1 / param2);
                break;
            case "mult":
                result = of(param1 * param2);
                break;
            default:
                break;
        }
        return result;
    }

    private static boolean isNotCorrectLineArguments(String[] args) {
        if (args == null || args.length == 0)
            return true;
        if (args.length < 3)
            return true;
        return false;
    }

    protected static void log(int result) {
        System.out.println(result);
    }
}
