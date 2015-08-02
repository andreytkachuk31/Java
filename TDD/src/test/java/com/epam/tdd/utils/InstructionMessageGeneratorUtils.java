package com.epam.tdd.utils;

import com.epam.tdd.model.InstructionMessage;

public class InstructionMessageGeneratorUtils {

    public static final int INSTRUCTION_TYPE_VALID_VALUE = 1;
    public static final int PRODUCT_CODE_VALID_VALUE = 1;
    public static final int QUANTITY_VALID_VALUE = 1;
    public static final int UOM_VALID_VALUE = 0;
    public static final int TIMESTAMP_VALID_VALUE = 1;

    public static InstructionMessage initValidInstructionMessage() {
        return new InstructionMessage(INSTRUCTION_TYPE_VALID_VALUE, PRODUCT_CODE_VALID_VALUE, QUANTITY_VALID_VALUE, UOM_VALID_VALUE, TIMESTAMP_VALID_VALUE);
    }

    public static InstructionMessage initInstructionMessageWithInstructionType(Integer instructionType) {
        return new InstructionMessage(instructionType, PRODUCT_CODE_VALID_VALUE, QUANTITY_VALID_VALUE, UOM_VALID_VALUE, TIMESTAMP_VALID_VALUE);
    }

    public static InstructionMessage initInstructionMessageWithProductCode(Integer productCode) {
        return new InstructionMessage(INSTRUCTION_TYPE_VALID_VALUE, productCode, QUANTITY_VALID_VALUE, UOM_VALID_VALUE, TIMESTAMP_VALID_VALUE);
    }

    public static InstructionMessage initInstructionMessageWithQuantity(Integer quantity) {
        return new InstructionMessage(INSTRUCTION_TYPE_VALID_VALUE, PRODUCT_CODE_VALID_VALUE, quantity, UOM_VALID_VALUE, TIMESTAMP_VALID_VALUE);
    }

    public static InstructionMessage initInstructionMessageWithUom(Integer uom) {
        return new InstructionMessage(INSTRUCTION_TYPE_VALID_VALUE, PRODUCT_CODE_VALID_VALUE, QUANTITY_VALID_VALUE, uom, TIMESTAMP_VALID_VALUE);
    }

    public static InstructionMessage initInstructionMessageWithTimeStamp(Integer timeStamp) {
        return new InstructionMessage(INSTRUCTION_TYPE_VALID_VALUE, PRODUCT_CODE_VALID_VALUE, QUANTITY_VALID_VALUE, UOM_VALID_VALUE, timeStamp);
    }
}
