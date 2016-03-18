package com.epam.tdd.model;

import com.epam.tdd.exception.InvalidMessageException;
import com.epam.tdd.exception.QueueEmptyException;
import com.epam.tdd.utils.InstructionMessageGeneratorUtils;
import com.epam.tdd.validator.InstructionMessageValidator;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class InstructionQueueTest {

    private static final int COUNT_ADD_ELEMENT_TO_QUEUE = 10;

    private InstructionQueue instructionQueue = new InstructionQueue();

    @Before
    public void before() {
        InstructionMessageValidator mockAlwaysValidInstructionMessageValidator = mock(InstructionMessageValidator.class);
        doNothing().when(mockAlwaysValidInstructionMessageValidator).validate(any(InstructionMessage.class));
        instructionQueue.setInstructionMessageValidator(mockAlwaysValidInstructionMessageValidator);
    }

    @Test
    public void shouldReturnTrueWhenIsEmptyIfQueueOnlyCreated() {
        assertEquals(true, instructionQueue.isEmpty());
    }

    @Test
    public void shouldReturnTrueWhenIsEmptyIfQueueEmpty() {
        fillQueue(COUNT_ADD_ELEMENT_TO_QUEUE);
        clearQueue();
        assertEquals(true, instructionQueue.isEmpty());
    }

    @Test
    public void shouldReturnFalseWhenIsEmptyIfQueueNotEmpty() {
        fillQueue(COUNT_ADD_ELEMENT_TO_QUEUE);
        assertEquals(false, instructionQueue.isEmpty());
    }

    @Test
    public void shouldReturnZeroWhenSizeIfQueueOnlyCreated() {
        assertEquals(0, instructionQueue.size());
    }

    @Test
    public void shouldReturnZeroWhenSizeIfQueueEmpty() {
        fillQueue(COUNT_ADD_ELEMENT_TO_QUEUE);
        clearQueue();
        assertEquals(0, instructionQueue.size());
    }

    @Test(expected = InvalidMessageException.class)
    public void shouldThrowInvalidMessageExceptionWhenAddWithInvalidInstructionMessage() {
        InstructionMessageValidator mockAlwaysInvalidInstructionMessageValidator = mock(InstructionMessageValidator.class);
        doThrow(InvalidMessageException.class).when(mockAlwaysInvalidInstructionMessageValidator).validate(any(InstructionMessage.class));
        instructionQueue.setInstructionMessageValidator(mockAlwaysInvalidInstructionMessageValidator);
        instructionQueue.add(null);
    }

    @Test
    public void shouldInsertInstructionMessagesWhenAddWithValidInstructionMessage() {
        fillQueue(COUNT_ADD_ELEMENT_TO_QUEUE);
        assertEquals(COUNT_ADD_ELEMENT_TO_QUEUE, instructionQueue.size());
    }

    @Test(expected = QueueEmptyException.class)
    public void shouldThrowQueueEmptyExceptionWhenRemoveIfQueueOnlyCreated() {
        instructionQueue.remove();
    }

    @Test(expected = QueueEmptyException.class)
    public void shouldThrowQueueEmptyExceptionThrowWhenRemoveIfQueueEmpty() {
        fillQueue(COUNT_ADD_ELEMENT_TO_QUEUE);
        clearQueue();
        instructionQueue.remove();
    }

    @Test
    public void shouldRemoveElementWhenRemoveFromQueue() {
        fillQueue(COUNT_ADD_ELEMENT_TO_QUEUE);
        instructionQueue.remove();
        assertEquals(COUNT_ADD_ELEMENT_TO_QUEUE - 1, instructionQueue.size());
    }

    @Test
    public void shouldRemoveInstructionMessageAccordingMultiplePrioritiesMessageWhenRemove() {
        InstructionMessage instructionMessageMiddle = mock(InstructionMessage.class);
        InstructionMessage instructionMessageLow = mock(InstructionMessage.class);
        mockLargerMessageCompareToLesserMessage(instructionMessageMiddle, instructionMessageLow);
        instructionQueue.add(instructionMessageLow);
        instructionQueue.add(instructionMessageMiddle);
        assertEquals(instructionMessageMiddle, instructionQueue.remove());
    }

    @Test
    public void shouldRemoveInstructionMessageAccordingMixedPrioritiesMessageWhenRemove() {
        InstructionMessage instructionMessageHigh = mock(InstructionMessage.class);
        InstructionMessage instructionMessageMiddle = mock(InstructionMessage.class);
        InstructionMessage instructionMessageLow = mock(InstructionMessage.class);
        mockLargerMessageCompareToLesserMessage(instructionMessageHigh, instructionMessageMiddle);
        mockLargerMessageCompareToLesserMessage(instructionMessageHigh, instructionMessageLow);
        mockLargerMessageCompareToLesserMessage(instructionMessageMiddle, instructionMessageLow);
        instructionQueue.add(instructionMessageMiddle);
        instructionQueue.add(instructionMessageLow);
        instructionQueue.add(instructionMessageHigh);
        assertEquals(instructionMessageHigh, instructionQueue.remove());
    }

    @Test
    public void shouldRemoveInstructionMessageAccordingShufflePrioritiesMessageWhenRemove() {
        InstructionMessage instructionMessageHigh = mock(InstructionMessage.class);
        InstructionMessage instructionMessageMiddleFirst = mock(InstructionMessage.class);
        InstructionMessage instructionMessageMiddleSecond = mock(InstructionMessage.class);
        InstructionMessage instructionMessageLowFirst = mock(InstructionMessage.class);
        InstructionMessage instructionMessageLowSecond = mock(InstructionMessage.class);
        mockLargerMessageCompareToLesserMessage(instructionMessageHigh, instructionMessageMiddleFirst);
        mockLargerMessageCompareToLesserMessage(instructionMessageHigh, instructionMessageMiddleSecond);
        mockLargerMessageCompareToLesserMessage(instructionMessageHigh, instructionMessageLowFirst);
        mockLargerMessageCompareToLesserMessage(instructionMessageHigh, instructionMessageLowSecond);
        mockLargerMessageCompareToLesserMessage(instructionMessageMiddleFirst, instructionMessageLowFirst);
        mockLargerMessageCompareToLesserMessage(instructionMessageMiddleFirst, instructionMessageLowSecond);
        mockLargerMessageCompareToLesserMessage(instructionMessageMiddleSecond, instructionMessageLowFirst);
        mockLargerMessageCompareToLesserMessage(instructionMessageMiddleSecond, instructionMessageLowSecond);
        instructionQueue.add(instructionMessageMiddleFirst);
        instructionQueue.add(instructionMessageLowSecond);
        instructionQueue.add(instructionMessageHigh);
        instructionQueue.add(instructionMessageLowSecond);
        instructionQueue.add(instructionMessageMiddleSecond);
        assertEquals(instructionMessageHigh, instructionQueue.remove());
    }

    @Test(expected = QueueEmptyException.class)
    public void shouldThrowQueueEmptyExceptionWhenRetrieveIfQueueOnlyCreated() {
        instructionQueue.retrieve();
    }

    @Test(expected = QueueEmptyException.class)
    public void shouldThrowQueueEmptyExceptionWhenRetrieveIfQueueEmpty() {
        fillQueue(COUNT_ADD_ELEMENT_TO_QUEUE);
        clearQueue();
        instructionQueue.retrieve();
    }

    @Test
    public void shouldNotDecreaseQueueWhenRetrieve() {
        fillQueue(COUNT_ADD_ELEMENT_TO_QUEUE);
        instructionQueue.retrieve();
        assertEquals(COUNT_ADD_ELEMENT_TO_QUEUE, instructionQueue.size());
    }

    @Test
    public void shouldRetrieveInstructionMessageAccordingMultiplePrioritiesMessageWhenRetrieve() {
        InstructionMessage instructionMessageMiddle = mock(InstructionMessage.class);
        InstructionMessage instructionMessageLow = mock(InstructionMessage.class);
        mockLargerMessageCompareToLesserMessage(instructionMessageMiddle, instructionMessageLow);
        instructionQueue.add(instructionMessageLow);
        instructionQueue.add(instructionMessageMiddle);
        assertEquals(instructionMessageMiddle, instructionQueue.retrieve());
    }

    @Test
    public void shouldRetrieveInstructionMessageAccordingMixedPrioritiesMessageWhenRetrieve() {
        InstructionMessage instructionMessageHigh = mock(InstructionMessage.class);
        InstructionMessage instructionMessageMiddle = mock(InstructionMessage.class);
        InstructionMessage instructionMessageLow = mock(InstructionMessage.class);
        mockLargerMessageCompareToLesserMessage(instructionMessageHigh, instructionMessageMiddle);
        mockLargerMessageCompareToLesserMessage(instructionMessageHigh, instructionMessageLow);
        mockLargerMessageCompareToLesserMessage(instructionMessageMiddle, instructionMessageLow);
        instructionQueue.add(instructionMessageMiddle);
        instructionQueue.add(instructionMessageLow);
        instructionQueue.add(instructionMessageHigh);
        assertEquals(instructionMessageHigh, instructionQueue.retrieve());
    }

    @Test
    public void shouldRetrieveInstructionMessageAccordingShufflePrioritiesMessageWhenRetrieve() {
        InstructionMessage instructionMessageHigh = mock(InstructionMessage.class);
        InstructionMessage instructionMessageMiddleFirst = mock(InstructionMessage.class);
        InstructionMessage instructionMessageMiddleSecond = mock(InstructionMessage.class);
        InstructionMessage instructionMessageLowFirst = mock(InstructionMessage.class);
        InstructionMessage instructionMessageLowSecond = mock(InstructionMessage.class);
        mockLargerMessageCompareToLesserMessage(instructionMessageHigh, instructionMessageMiddleFirst);
        mockLargerMessageCompareToLesserMessage(instructionMessageHigh, instructionMessageMiddleSecond);
        mockLargerMessageCompareToLesserMessage(instructionMessageHigh, instructionMessageLowFirst);
        mockLargerMessageCompareToLesserMessage(instructionMessageHigh, instructionMessageLowSecond);
        mockLargerMessageCompareToLesserMessage(instructionMessageMiddleFirst, instructionMessageLowFirst);
        mockLargerMessageCompareToLesserMessage(instructionMessageMiddleFirst, instructionMessageLowSecond);
        mockLargerMessageCompareToLesserMessage(instructionMessageMiddleSecond, instructionMessageLowFirst);
        mockLargerMessageCompareToLesserMessage(instructionMessageMiddleSecond, instructionMessageLowSecond);
        instructionQueue.add(instructionMessageMiddleFirst);
        instructionQueue.add(instructionMessageLowSecond);
        instructionQueue.add(instructionMessageHigh);
        instructionQueue.add(instructionMessageLowSecond);
        instructionQueue.add(instructionMessageMiddleSecond);
        assertEquals(instructionMessageHigh, instructionQueue.retrieve());
    }

    private void fillQueue(int countAddElementToQueue) {
        for (int i = 0; i < countAddElementToQueue; i++)
            instructionQueue.add(InstructionMessageGeneratorUtils.initValidInstructionMessage());
    }

    private void clearQueue() {
        while (!instructionQueue.isEmpty())
            instructionQueue.remove();
    }

    private void mockLargerMessageCompareToLesserMessage(InstructionMessage instructionMessageMore, InstructionMessage instructionMessageLess) {
        when(instructionMessageMore.compareTo(instructionMessageLess)).thenReturn(-1);
        when(instructionMessageLess.compareTo(instructionMessageMore)).thenReturn(1);
    }
}
