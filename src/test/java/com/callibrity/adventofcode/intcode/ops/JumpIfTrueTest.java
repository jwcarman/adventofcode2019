package com.callibrity.adventofcode.intcode.ops;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class JumpIfTrueTest {

    @Test
    void jumpsWhenTrue(@Mock OperationContext context) {
        JumpIfTrue op = new JumpIfTrue();
        when(context.nextParameter()).thenReturn(1, 12);

        op.execute(context);

        verify(context, times(2)).nextParameter();
        verify(context).jump(12);
        verifyNoMoreInteractions(context);
    }

    @Test
    void doesNothingWhenFalse(@Mock OperationContext context) {
        JumpIfTrue op = new JumpIfTrue();
        when(context.nextParameter()).thenReturn(0, 12);

        op.execute(context);

        verify(context, times(2)).nextParameter();
        verifyNoMoreInteractions(context);
    }

}