package com.epam.tdd.model;

import java.util.Objects;

public class InstructionMessage implements Comparable<InstructionMessage> {

    private static final int HIGH_UPPER_PRIORITY = 11;
    private static final int MIDDLE_LOWER_PRIORITY = 10;
    private static final int MIDDLE_UPPER_PRIORITY = 91;
    private static final int LOW_LOWER_PRIORITY = 90;

    private Integer instructionType;
    private Integer productCode;
    private Integer quantity;
    private Integer uom;
    private Integer timeStamp;

    public InstructionMessage(Integer instructionType, Integer productCode, Integer quantity, Integer uom, Integer timeStamp) {
        this.instructionType = instructionType;
        this.productCode = productCode;
        this.quantity = quantity;
        this.uom = uom;
        this.timeStamp = timeStamp;
    }

    public Integer getInstructionType() {
        return instructionType;
    }

    public Integer getProductCode() {
        return productCode;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Integer getUom() {
        return uom;
    }

    public Integer getTimeStamp() {
        return timeStamp;
    }

    @Override
    public int compareTo(InstructionMessage instructionMessage) {
        Integer instructionType1 = this.getInstructionType();
        Integer instructionType2 = instructionMessage.getInstructionType();
        if (isEqualsPriority(instructionType1, instructionType2))
            return 0;
        return instructionType1 - instructionType2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        InstructionMessage other = (InstructionMessage) o;
        return Objects.equals(this.instructionType, other.instructionType)
                && Objects.equals(this.productCode, other.productCode)
                && Objects.equals(this.quantity, other.quantity)
                && Objects.equals(this.uom, other.uom)
                && Objects.equals(this.timeStamp, other.timeStamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.instructionType, this.productCode, this.quantity, this.uom, this.timeStamp);
    }

    private boolean isEqualsPriority(Integer instructionType1, Integer instructionType2) {
        return isEqualsHighPriority(instructionType1, instructionType2) || isEqualsMiddlePriority(instructionType1, instructionType2) || isEqualsLowerPriority(instructionType1, instructionType2);
    }

    private boolean isEqualsHighPriority(Integer instructionType1, Integer instructionType2) {
        return instructionType1 < HIGH_UPPER_PRIORITY && instructionType2 < HIGH_UPPER_PRIORITY;
    }

    private boolean isEqualsMiddlePriority(Integer instructionType1, Integer instructionType2) {
        return isMoreThenMiddleLowerPriority(instructionType1, instructionType2) && isLessThenMiddleUpperPriority(instructionType1, instructionType2);
    }

    private boolean isMoreThenMiddleLowerPriority(Integer instructionType1, Integer instructionType2) {
        return (instructionType1 > MIDDLE_LOWER_PRIORITY && instructionType2 > MIDDLE_LOWER_PRIORITY);
    }

    private boolean isLessThenMiddleUpperPriority(Integer instructionType1, Integer instructionType2) {
        return (instructionType1 < MIDDLE_UPPER_PRIORITY && instructionType2 < MIDDLE_UPPER_PRIORITY);
    }

    private boolean isEqualsLowerPriority(Integer instructionType1, Integer instructionType2) {
        return instructionType1 > LOW_LOWER_PRIORITY && instructionType2 > LOW_LOWER_PRIORITY;
    }
}
