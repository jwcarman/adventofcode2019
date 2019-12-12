package com.callibrity.adventofcode.intcode.ops;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StopTest {

    @Test
    void whenEqualsStoresOne(@Mock OperationContext context) {
        Stop op = new Stop();

        op.execute(context);

        verify(context).stop();
        verifyNoMoreInteractions(context);
    }

}