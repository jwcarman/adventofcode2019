package com.callibrity.adventofcode.intcode.ops;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class JumpIfFalseTest {

    @Test
    void jumpsWhenFalse(@Mock OperationContext context) {
        JumpIfFalse op = new JumpIfFalse();
        when(context.nextParameter()).thenReturn(0L, 12L);

        op.execute(context);

        verify(context, times(2)).nextParameter();
        verify(context).jump(12);
        verifyNoMoreInteractions(context);
    }

    @Test
    void doesNothingWhenTrue(@Mock OperationContext context) {
        JumpIfFalse op = new JumpIfFalse();
        when(context.nextParameter()).thenReturn(1L, 12L);

        op.execute(context);

        verify(context, times(2)).nextParameter();
        verifyNoMoreInteractions(context);
    }

}