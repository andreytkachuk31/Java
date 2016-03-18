package com.epam.tdd.model;

import org.junit.Test;

import static com.epam.tdd.utils.InstructionMessageGeneratorUtils.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class InstructionMessageTest {

    private static final int HIGH_UPPER_PRIORITY_VALUE = 1;
    private static final int HIGH_LOWER_PRIORITY_VALUE = 10;
    private static final int MIDDLE_UPPER_PRIORITY_VALUE = 11;
    private static final int MIDDLE_LOWER_PRIORITY_VALUE = 90;
    private static final int LOW_UPPER_PRIORITY_VALUE = 91;
    private static final int LOW_LOWER_PRIORITY_VALUE = 99;

    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerExceptionWhenCompareToWithNull() {
        InstructionMessage instructionMessage = initInstructionMessageWithInstructionType(INSTRUCTION_TYPE_VALID_VALUE);
        instructionMessage.compareTo(null);
    }

    @Test
    public void shouldReturnZeroWhenCompareToWithHighPriorities() {
        InstructionMessage instructionMessageFirst = initInstructionMessageWithInstructionType(HIGH_UPPER_PRIORITY_VALUE);
        InstructionMessage instructionMessageSecond = initInstructionMessageWithInstructionType(HIGH_LOWER_PRIORITY_VALUE);
        assertCompareToBothEqualsMessages(instructionMessageFirst, instructionMessageSecond);
    }

    @Test
    public void shouldReturnZeroWhenCompareToWithMiddlePriorities() {
        InstructionMessage instructionMessageFirst = initInstructionMessageWithInstructionType(MIDDLE_UPPER_PRIORITY_VALUE);
        InstructionMessage instructionMessageSecond = initInstructionMessageWithInstructionType(MIDDLE_LOWER_PRIORITY_VALUE);
        assertCompareToBothEqualsMessages(instructionMessageFirst, instructionMessageSecond);
    }

    @Test
    public void shouldReturnZeroWhenCompareToWithLowPriorities() {
        InstructionMessage instructionMessageFirst = initInstructionMessageWithInstructionType(LOW_UPPER_PRIORITY_VALUE);
        InstructionMessage instructionMessageSecond = initInstructionMessageWithInstructionType(LOW_LOWER_PRIORITY_VALUE);
        assertCompareToBothEqualsMessages(instructionMessageFirst, instructionMessageSecond);
    }

    @Test
    public void shouldCompareHighUpperWithMiddleUpperPriorities() {
        InstructionMessage instructionMessageHigh = initInstructionMessageWithInstructionType(HIGH_UPPER_PRIORITY_VALUE);
        InstructionMessage instructionMessageMiddle = initInstructionMessageWithInstructionType(MIDDLE_UPPER_PRIORITY_VALUE);
        assertCompareToTheMessageSymmetricallyLessThanOtherMessage(instructionMessageHigh, instructionMessageMiddle);
    }

    @Test
    public void shouldCompareHighUpperWithMiddleLowerPriorities() {
        InstructionMessage instructionMessageHigh = initInstructionMessageWithInstructionType(HIGH_UPPER_PRIORITY_VALUE);
        InstructionMessage instructionMessageMiddle = initInstructionMessageWithInstructionType(MIDDLE_LOWER_PRIORITY_VALUE);
        assertCompareToTheMessageSymmetricallyLessThanOtherMessage(instructionMessageHigh, instructionMessageMiddle);
    }

    @Test
    public void shouldCompareHighUpperWithLowUpperPriorities() {
        InstructionMessage instructionMessageHigh = initInstructionMessageWithInstructionType(HIGH_UPPER_PRIORITY_VALUE);
        InstructionMessage instructionMessageLow = initInstructionMessageWithInstructionType(LOW_UPPER_PRIORITY_VALUE);
        assertCompareToTheMessageSymmetricallyLessThanOtherMessage(instructionMessageHigh, instructionMessageLow);
    }

    @Test
    public void shouldCompareHighUpperWithLowLowerPriorities() {
        InstructionMessage instructionMessageHigh = initInstructionMessageWithInstructionType(HIGH_UPPER_PRIORITY_VALUE);
        InstructionMessage instructionMessageLow = initInstructionMessageWithInstructionType(LOW_LOWER_PRIORITY_VALUE);
        assertCompareToTheMessageSymmetricallyLessThanOtherMessage(instructionMessageHigh, instructionMessageLow);
    }

    @Test
    public void shouldCompareHighLowerWithMiddleUpperPriorities() {
        InstructionMessage instructionMessageHigh = initInstructionMessageWithInstructionType(HIGH_LOWER_PRIORITY_VALUE);
        InstructionMessage instructionMessageMiddle = initInstructionMessageWithInstructionType(MIDDLE_UPPER_PRIORITY_VALUE);
        assertCompareToTheMessageSymmetricallyLessThanOtherMessage(instructionMessageHigh, instructionMessageMiddle);
    }

    @Test
    public void shouldCompareHighLowerWithMiddleLowerPriorities() {
        InstructionMessage instructionMessageHigh = initInstructionMessageWithInstructionType(HIGH_LOWER_PRIORITY_VALUE);
        InstructionMessage instructionMessageMiddle = initInstructionMessageWithInstructionType(MIDDLE_LOWER_PRIORITY_VALUE);
        assertCompareToTheMessageSymmetricallyLessThanOtherMessage(instructionMessageHigh, instructionMessageMiddle);
    }

    @Test
    public void shouldCompareHighLowerWithLowUpperPriorities() {
        InstructionMessage instructionMessageHigh = initInstructionMessageWithInstructionType(HIGH_LOWER_PRIORITY_VALUE);
        InstructionMessage instructionMessageLow = initInstructionMessageWithInstructionType(LOW_UPPER_PRIORITY_VALUE);
        assertCompareToTheMessageSymmetricallyLessThanOtherMessage(instructionMessageHigh, instructionMessageLow);
    }

    @Test
    public void shouldCompareHighLowerWithLowLowerPriorities() {
        InstructionMessage instructionMessageHigh = initInstructionMessageWithInstructionType(HIGH_LOWER_PRIORITY_VALUE);
        InstructionMessage instructionMessageLow = initInstructionMessageWithInstructionType(LOW_LOWER_PRIORITY_VALUE);
        assertCompareToTheMessageSymmetricallyLessThanOtherMessage(instructionMessageHigh, instructionMessageLow);
    }

    @Test
    public void shouldCompareMiddleUpperWithLowUpperPriorities() {
        InstructionMessage instructionMessageMiddle = initInstructionMessageWithInstructionType(MIDDLE_UPPER_PRIORITY_VALUE);
        InstructionMessage instructionMessageLow = initInstructionMessageWithInstructionType(LOW_UPPER_PRIORITY_VALUE);
        assertCompareToTheMessageSymmetricallyLessThanOtherMessage(instructionMessageMiddle, instructionMessageLow);
    }

    @Test
    public void shouldCompareMiddleUpperWithLowLowerPriorities() {
        InstructionMessage instructionMessageMiddle = initInstructionMessageWithInstructionType(MIDDLE_UPPER_PRIORITY_VALUE);
        InstructionMessage instructionMessageLow = initInstructionMessageWithInstructionType(LOW_LOWER_PRIORITY_VALUE);
        assertCompareToTheMessageSymmetricallyLessThanOtherMessage(instructionMessageMiddle, instructionMessageLow);
    }

    @Test
    public void shouldCompareMiddleLowerWithLowUpperPriorities() {
        InstructionMessage instructionMessageMiddle = initInstructionMessageWithInstructionType(MIDDLE_LOWER_PRIORITY_VALUE);
        InstructionMessage instructionMessageLow = initInstructionMessageWithInstructionType(LOW_UPPER_PRIORITY_VALUE);
        assertCompareToTheMessageSymmetricallyLessThanOtherMessage(instructionMessageMiddle, instructionMessageLow);
    }

    @Test
    public void shouldCompareMiddleLowerWithLowLowerPriorities() {
        InstructionMessage instructionMessageMiddle = initInstructionMessageWithInstructionType(MIDDLE_LOWER_PRIORITY_VALUE);
        InstructionMessage instructionMessageLow = initInstructionMessageWithInstructionType(LOW_LOWER_PRIORITY_VALUE);
        assertCompareToTheMessageSymmetricallyLessThanOtherMessage(instructionMessageMiddle, instructionMessageLow);
    }

    @Test
    public void shouldReturnFalseWhenEqualsWithNull() {
        InstructionMessage instructionMessage = initValidInstructionMessage();
        assertEquals(false, instructionMessage.equals(null));
    }

    @Test
    public void shouldReturnFalseWhenEqualsWithOtherTypeObject() {
        InstructionMessage instructionMessage = initValidInstructionMessage();
        assertEquals(false, instructionMessage.equals(new Object()));
    }

    @Test
    public void shouldReturnFalseWhenEqualsWithNotEqualsInstructionTypes() {
        InstructionMessage instructionMessageFirst = initInstructionMessageWithInstructionType(INSTRUCTION_TYPE_VALID_VALUE);
        InstructionMessage instructionMessageSecond = initInstructionMessageWithInstructionType(INSTRUCTION_TYPE_VALID_VALUE + 1);
        assertEquals(false, instructionMessageFirst.equals(instructionMessageSecond));
    }

    @Test
    public void shouldReturnFalseWhenEqualsWithNotEqualsProductCodes() {
        InstructionMessage instructionMessageFirst = initInstructionMessageWithProductCode(PRODUCT_CODE_VALID_VALUE);
        InstructionMessage instructionMessageSecond = initInstructionMessageWithProductCode(PRODUCT_CODE_VALID_VALUE + 1);
        assertEquals(false, instructionMessageFirst.equals(instructionMessageSecond));
    }

    @Test
    public void shouldReturnFalseWhenEqualsWithNotEqualsQuantities() {
        InstructionMessage instructionMessageFirst = initInstructionMessageWithQuantity(QUANTITY_VALID_VALUE);
        InstructionMessage instructionMessageSecond = initInstructionMessageWithQuantity(QUANTITY_VALID_VALUE + 1);
        assertEquals(false, instructionMessageFirst.equals(instructionMessageSecond));
    }

    @Test
    public void shouldReturnFalseWhenEqualsWithNotEqualsUoms() {
        InstructionMessage instructionMessageFirst = initInstructionMessageWithUom(UOM_VALID_VALUE);
        InstructionMessage instructionMessageSecond = initInstructionMessageWithUom(UOM_VALID_VALUE + 1);
        assertEquals(false, instructionMessageFirst.equals(instructionMessageSecond));
    }

    @Test
    public void shouldReturnFalseWhenEqualsWithNotEqualsTimeStamps() {
        InstructionMessage instructionMessageFirst = initInstructionMessageWithTimeStamp(TIMESTAMP_VALID_VALUE);
        InstructionMessage instructionMessageSecond = initInstructionMessageWithTimeStamp(TIMESTAMP_VALID_VALUE + 1);
        assertEquals(false, instructionMessageFirst.equals(instructionMessageSecond));
    }

    @Test
    public void shouldReturnTrueWhenEqualsWithSameObject() {
        InstructionMessage instructionMessage = initValidInstructionMessage();
        assertEquals(true, instructionMessage.equals(instructionMessage));
    }

    @Test
    public void shouldReturnTrueWhenEqualsWithEqualsAllFilelds() {
        InstructionMessage instructionMessageFirst = initValidInstructionMessage();
        InstructionMessage instructionMessageSecond = initValidInstructionMessage();
        assertEquals(true, instructionMessageFirst.equals(instructionMessageSecond));
    }

    @Test
    public void shouldReturnTrueWhenHashCodeWithEqualsObjects() {
        InstructionMessage instructionMessageFirst = initValidInstructionMessage();
        InstructionMessage instructionMessageSecond = initValidInstructionMessage();
        assertEquals(true, instructionMessageFirst.hashCode() == instructionMessageSecond.hashCode());
    }

    private void assertCompareToBothEqualsMessages(InstructionMessage instructionMessageFirst, InstructionMessage instructionMessageSecond) {
        assertEquals(0, instructionMessageFirst.compareTo(instructionMessageSecond));
        assertEquals(0, instructionMessageSecond.compareTo(instructionMessageFirst));
    }

    private void assertCompareToTheMessageSymmetricallyLessThanOtherMessage(InstructionMessage instructionMessageLess, InstructionMessage instructionMessageGreater) {
        int result = instructionMessageLess.compareTo(instructionMessageGreater);
        int reverseResult = instructionMessageGreater.compareTo(instructionMessageLess);
        assertTrue(result < 0);
        assertTrue(reverseResult > 0);
    }
}
