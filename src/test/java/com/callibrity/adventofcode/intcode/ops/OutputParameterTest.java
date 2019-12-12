package com.callibrity.adventofcode.intcode.ops;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OutputParameterTest {

    @Test
    void outputsParameter(@Mock OperationContext context) {
        OutputParameter op  =new OutputParameter();
        when(context.nextParameter()).thenReturn(12);
        op.execute(context);
        verify(context).nextParameter();
        verify(context).output(12);
        verifyNoMoreInteractions(context);
    }

}