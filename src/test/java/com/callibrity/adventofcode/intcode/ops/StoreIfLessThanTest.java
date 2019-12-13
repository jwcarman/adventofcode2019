package com.callibrity.adventofcode.intcode.ops;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StoreIfLessThanTest {


    @Test
    void whenLessThan(@Mock OperationContext context) {
        StoreIfLessThan operation = new StoreIfLessThan();
        when(context.nextParameter()).thenReturn(1L, 2L);

        operation.execute(context);

        verify(context, times(2)).nextParameter();
        verify(context).storeValue(1);
        verifyNoMoreInteractions(context);
    }

    @Test
    void whenEqual(@Mock OperationContext context) {
        StoreIfLessThan operation = new StoreIfLessThan();
        when(context.nextParameter()).thenReturn(1L, 1L);

        operation.execute(context);

        verify(context, times(2)).nextParameter();
        verify(context).storeValue(0);
        verifyNoMoreInteractions(context);
    }

    @Test
    void whenGreaterThan(@Mock OperationContext context) {
        StoreIfLessThan operation = new StoreIfLessThan();
        when(context.nextParameter()).thenReturn(2L, 1L);

        operation.execute(context);

        verify(context, times(2)).nextParameter();
        verify(context).storeValue(0);
        verifyNoMoreInteractions(context);
    }

}