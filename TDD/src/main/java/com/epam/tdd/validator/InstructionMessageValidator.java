package com.epam.tdd.validator;

import com.epam.tdd.exception.InvalidMessageException;
import com.epam.tdd.model.InstructionMessage;

public class InstructionMessageValidator {

    private static final int LOWER_LIMIT_INSTRUCTION_TYPE = 0;
    private static final int UPPER_LIMIT_INSTRUCTION_TYPE = 100;
    private static final int LOWER_LIMIT_PRODUCT_CODE = 0;
    private static final int LOWER_LIMIT_QUANTITY = 0;
    private static final int LOWER_LIMIT_UOM = -1;
    private static final int UPPER_LIMIT_UOM = 256;
    private static final int LOWER_LIMIT_TIMESTAMP = 0;

    public void validate(InstructionMessage instructionMessage) {
        if (instructionMessage == null)
            throw new InvalidMessageException("InstructionMessage can not be null");
        checkInstructionType(instructionMessage.getInstructionType());
        checkProductCode(instructionMessage.getProductCode());
        checkQuantity(instructionMessage.getQuantity());
        checkUom(instructionMessage.getUom());
        checkTimeStamp(instructionMessage.getTimeStamp());
    }

    private void checkInstructionType(Integer instructionType) {
        if (instructionType == null || isNotInRange(instructionType, LOWER_LIMIT_INSTRUCTION_TYPE, UPPER_LIMIT_INSTRUCTION_TYPE))
            throw new InvalidMessageException("Instruction type should be in more than 0 and less than 100. Instruction type " + instructionType + " not to be in this range");
    }

    private void checkProductCode(Integer productCode) {
        if (productCode == null || productCode <= LOWER_LIMIT_PRODUCT_CODE)
            throw new InvalidMessageException("Product code should be positive number. Product code " + productCode + " is not positive number");
    }

    private void checkQuantity(Integer quantity) {
        if (quantity == null || quantity <= LOWER_LIMIT_QUANTITY)
            throw new InvalidMessageException("Quantity should be positive number. Quantity " + quantity + " is not positive number");
    }

    private void checkUom(Integer uom) {
        if (uom == null || isNotInRange(uom, LOWER_LIMIT_UOM, UPPER_LIMIT_UOM))
            throw new InvalidMessageException("Uom should be in more than 0 or equals and less than 256. Uom  " + uom + " not to be in this range");
    }

    private void checkTimeStamp(Integer timeStamp) {
        if (timeStamp == null || timeStamp <= LOWER_LIMIT_TIMESTAMP)
            throw new InvalidMessageException("TimeStamp should be positive number. TimeStamp " + timeStamp + " is not positive number");
    }

    private boolean isNotInRange(Integer value, int lowerLimit, int upperLimit) {
        return value <= lowerLimit || value >= upperLimit;
    }
}
