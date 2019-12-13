package com.callibrity.adventofcode.intcode.ops;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StoreIfEqualsTest {

    @Test
    void whenEqualsStoresOne(@Mock OperationContext context) {
        StoreIfEquals op = new StoreIfEquals();
        when(context.nextParameter()).thenReturn(2L, 2L);
        op.execute(context);
        verify(context, times(2)).nextParameter();
        verify(context).storeValue(1);
        verifyNoMoreInteractions(context);
    }

    @Test
    void whenNotEqualsStoresZero(@Mock OperationContext context) {
        StoreIfEquals op = new StoreIfEquals();
        when(context.nextParameter()).thenReturn(2222L, 2L);
        op.execute(context);
        verify(context, times(2)).nextParameter();
        verify(context).storeValue(0);
        verifyNoMoreInteractions(context);
    }
}