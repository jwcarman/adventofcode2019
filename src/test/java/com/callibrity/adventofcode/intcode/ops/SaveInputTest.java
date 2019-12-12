package com.callibrity.adventofcode.intcode.ops;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SaveInputTest {

    @Test
    void storesInput(@Mock OperationContext context) {
        SaveInput op = new SaveInput();
        when(context.input()).thenReturn(21212);
        op.execute(context);
        verify(context).input();
        verify(context).storeValue(21212);
        verifyNoMoreInteractions(context);
    }


}