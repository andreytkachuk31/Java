package com.epam.tdd.model;

import com.epam.tdd.exception.QueueEmptyException;
import com.epam.tdd.validator.InstructionMessageValidator;

import java.util.PriorityQueue;
import java.util.Queue;

public class InstructionQueue {

    private Queue<InstructionMessage> instructionMessages;
    private InstructionMessageValidator instructionMessageValidator;

    public InstructionQueue() {
        this.instructionMessages = new PriorityQueue<InstructionMessage>();
    }

    public void setInstructionMessageValidator(InstructionMessageValidator instructionMessageValidator) {
        this.instructionMessageValidator = instructionMessageValidator;
    }

    public boolean isEmpty() {
        return instructionMessages.isEmpty();
    }

    public int size() {
        return instructionMessages.size();
    }

    public void add(InstructionMessage instructionMessage) {
        instructionMessageValidator.validate(instructionMessage);
        instructionMessages.add(instructionMessage);
    }

    public InstructionMessage remove() {
        if (isEmpty())
            throw new QueueEmptyException();
        return instructionMessages.poll();
    }

    public InstructionMessage retrieve() {
        if (isEmpty())
            throw new QueueEmptyException();
        return instructionMessages.peek();
    }
}
