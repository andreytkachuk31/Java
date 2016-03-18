package com.epam;

import com.epam.group.JunitGroupTests;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.powermock.api.mockito.PowerMockito.*;
import static org.powermock.api.mockito.PowerMockito.verifyStatic;

/**
 * @author Andrii_Tkachuk
 * @since 10/15/2015
 */
@RunWith(PowerMockRunner.class)
@Category(JunitGroupTests.class)
@PrepareForTest(Calculator.class)
public class JunitCalculatorTest {

    private static final Integer DEFAULT_RESULT = 4;

    @Before
    public void before() {
        spy(Calculator.class);
    }

    @Test
    public void shouldPrintDefaultResultWhenCallMainWithoutParams() {
        main();

        verifyLog(DEFAULT_RESULT);
    }

    @Test
    public void shouldPrintDefaultResultWhenCallMainWithOneParam() {
        main("1");

        verifyLog(DEFAULT_RESULT);
    }

    @Test
    public void shouldPrintDefaultResultWhenCallMainWithTwoParams() {
        main("1", "5");

        verifyLog(DEFAULT_RESULT);
    }

    @Test
    public void shouldPrintResultWhenCallMainWithTwoParamsAndAddOperation() {
        main("2", "3", "add");

        verifyLog(5);
    }

    @Test
    public void shouldPrintResultWhenCallMainWithTwoParamsAndSubOperation() {
        main("4", "1", "sub");

        verifyLog(3);
    }

    @Test
    public void shouldPrintResultWhenCallMainWithTwoParamsAndDiv() {
        main("6", "3", "div");

        verifyLog(2);
    }

    @Test
    public void shouldPrintResultWhenCallMainWithTwoParamsAndMult() {
        main("2", "5", "mult");

        verifyLog(10);
    }

    @Test
    public void shouldPrintResultWhenCallMainWithTwoSpacesParamsAndAdd() {
        main("  2  \t", "\t 5  ", "add");

        verifyLog(7);
    }

    @Test
    public void shouldPrintResultWhenCallMainWithTwoParamsAndSubWithSpaces() {
        main("2", "1", " sub \t\n");

        verifyLog(1);
    }

    @Test(expected = NumberFormatException.class)
    public void shouldThrowExceptionWhenCallMainWithSecondNotNumericParamAndAdd() {
        main("10", "asdas", "add");
    }

    @Test
    public void shouldPrintResultWhenCallMainWithTwoParamsAndUnknown() {
        main("2", "5", "unknown");

        verifyLog(DEFAULT_RESULT);
    }

    @Test
    public void shouldPrintResultWhenCallMainWithMoreParams() {
        main("4", "2", "div", "more");

        verifyLog(2);
    }

    private static void main(String... args) {
        Calculator.main(args);
    }

    private void verifyLog(int result) {
        verifyStatic();
        Calculator.log(result);
    }
}
