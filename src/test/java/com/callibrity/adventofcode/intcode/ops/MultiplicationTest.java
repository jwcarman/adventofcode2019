package com.callibrity.adventofcode.intcode.ops;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MultiplicationTest {

    @Test
    void multiplies(@Mock OperationContext context) {
        Multiplication op = new Multiplication();
        when(context.nextParameter()).thenReturn(6L, 3L);

        op.execute(context);
        verify(context, times(2)).nextParameter();
        verify(context).storeValue(18);
        verifyNoMoreInteractions(context);
    }

}