package com.callibrity.adventofcode.intcode.ops;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StoreInputTest {

    @Test
    void storesInput(@Mock OperationContext context) {
        StoreInput op = new StoreInput();
        when(context.input()).thenReturn(21212L);
        op.execute(context);
        verify(context).input();
        verify(context).storeValue(21212);
        verifyNoMoreInteractions(context);
    }


}