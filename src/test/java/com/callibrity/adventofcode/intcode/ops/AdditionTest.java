package com.callibrity.adventofcode.intcode.ops;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AdditionTest {

    @Test
    void multiplies(@Mock OperationContext context) {
        Addition op = new Addition();
        when(context.nextParameter()).thenReturn(6L, 3L);

        op.execute(context);
        verify(context, times(2)).nextParameter();
        verify(context).storeValue(9);
        verifyNoMoreInteractions(context);
    }

}