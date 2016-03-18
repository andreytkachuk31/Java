package com.epam.tdd.validator;

import com.epam.tdd.exception.InvalidMessageException;
import com.epam.tdd.model.InstructionMessage;
import org.junit.Test;

import static com.epam.tdd.utils.InstructionMessageGeneratorUtils.*;

public class InstructionMessageValidatorTest {

    private static final int INTSTRUCTION_TYPE_LOWER_INVALID_VALUE = 0;
    private static final int INTSTRUCTION_TYPE_UPPER_INVALID_VALUE = 100;

    private static final int PRODUCT_CODE_INVALID_VALUE = 0;

    private static final int QUANTITY_INVALID_VALUE = 0;

    private static final int UOM_LOWER_INVALID_VALUE = -1;
    private static final int UOM_UPPER_INVALID_VALUE = 256;

    private static final int TIMESTAMP_INVALID_VALUE = 0;

    private InstructionMessageValidator instructionMessageValidator = new InstructionMessageValidator();

    @Test(expected = InvalidMessageException.class)
    public void shouldThrowInvalidMessageExceptionWhenIsValidInstructionMessageWithNull() {
        isValidInstructionMessage(null);
    }

    @Test(expected = InvalidMessageException.class)
    public void shouldThrowInvalidMessageExceptionWhenIsValidInstructionMessageWithInstructionTypeNull() {
        isValidInstructionMessage(initInstructionMessageWithInstructionType(null));
    }

    @Test(expected = InvalidMessageException.class)
    public void shouldThrowInvalidMessageExceptionWhenIsValidInstructionMessageWithInstructionTypeInvalidLowerValue() {
        isValidInstructionMessage(initInstructionMessageWithInstructionType(INTSTRUCTION_TYPE_LOWER_INVALID_VALUE));
    }

    @Test(expected = InvalidMessageException.class)
    public void shouldThrowInvalidMessageExceptionWhenIsValidInstructionMessageWithInstructionTypeInvalidUpperValue() {
        isValidInstructionMessage(initInstructionMessageWithInstructionType(INTSTRUCTION_TYPE_UPPER_INVALID_VALUE));
    }

    @Test(expected = InvalidMessageException.class)
    public void shouldThrowInvalidMessageExceptionWhenIsValidInstructionMessageWithProductCodeNull() {
        isValidInstructionMessage(initInstructionMessageWithProductCode(null));
    }

    @Test(expected = InvalidMessageException.class)
    public void shouldThrowInvalidMessageExceptionWhenIsValidInstructionMessageWithProductCodeInvalid() {
        isValidInstructionMessage(initInstructionMessageWithProductCode(PRODUCT_CODE_INVALID_VALUE));
    }

    @Test(expected = InvalidMessageException.class)
    public void shouldThrowInvalidMessageExceptionWhenIsValidInstructionMessageWithQuantityNull() {
        isValidInstructionMessage(initInstructionMessageWithQuantity(null));
    }

    @Test(expected = InvalidMessageException.class)
    public void shouldThrowInvalidMessageExceptionWhenIsValidInstructionMessageWithQuantityInvalid() {
        isValidInstructionMessage(initInstructionMessageWithQuantity(QUANTITY_INVALID_VALUE));
    }

    @Test(expected = InvalidMessageException.class)
    public void shouldThrowInvalidMessageExceptionWhenIsValidInstructionMessageWithUomNull() {
        isValidInstructionMessage(initInstructionMessageWithUom(null));
    }

    @Test(expected = InvalidMessageException.class)
    public void shouldCorrectValidateWhenIsValidInstructionMessageWithUomInvalidLowerValue() {
        isValidInstructionMessage(initInstructionMessageWithUom(UOM_LOWER_INVALID_VALUE));
    }

    @Test(expected = InvalidMessageException.class)
    public void shouldCorrectValidateWhenIsValidInstructionMessageWithUomInvalidUpperValue() {
        isValidInstructionMessage(initInstructionMessageWithUom(UOM_UPPER_INVALID_VALUE));
    }

    @Test(expected = InvalidMessageException.class)
    public void shouldThrowInvalidMessageExceptionWhenIsValidInstructionMessageWithTimeStampNull() {
        isValidInstructionMessage(initInstructionMessageWithTimeStamp(null));
    }

    @Test(expected = InvalidMessageException.class)
    public void shouldThrowInvalidMessageExceptionWhenIsValidInstructionMessageWithTimeStampInvalid() {
        isValidInstructionMessage(initInstructionMessageWithTimeStamp(TIMESTAMP_INVALID_VALUE));
    }

    @Test
    public void shouldCorrectValidateWhenIsValidInstructionMessageWithValidInstructionMessage() {
        isValidInstructionMessage(initValidInstructionMessage());
    }

    private void isValidInstructionMessage(InstructionMessage instructionMessage) {
        instructionMessageValidator.validate(instructionMessage);
    }
}
