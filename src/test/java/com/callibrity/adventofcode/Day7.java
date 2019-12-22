package com.callibrity.adventofcode;

import com.callibrity.adventofcode.intcode.IntCodeInterpreter;
import com.callibrity.adventofcode.intcode.io.BlockingQueueInputSupplier;
import com.callibrity.adventofcode.intcode.io.BlockingQueueOutputConsumer;
import com.google.common.util.concurrent.Uninterruptibles;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.iterators.PermutationIterator;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
public class Day7 {

    @Test
    void part1() {
        PermutationIterator<Integer> permutations = new PermutationIterator<>(Arrays.asList(0, 1, 2, 3, 4));
        long maxThrust = 0;
        while (permutations.hasNext()) {
            final List<Integer> phaseSettings = permutations.next();
            final long thrust = calculateThrust(phaseSettings);
            maxThrust = Math.max(maxThrust, thrust);
        }
        log.info("Maximum thrust is {}", maxThrust);
    }

    @Test
    void part2() {
        PermutationIterator<Integer> permutations = new PermutationIterator<>(Arrays.asList(5, 6, 7, 8, 9));
        long maxThrust = 0;
        while (permutations.hasNext()) {
            final List<Integer> phaseSettings = permutations.next();
            final long thrust = calculateFeedbackThrust(phaseSettings);
            maxThrust = Math.max(maxThrust, thrust);
        }
        log.info("Maximum thrust is {}", maxThrust);

    }

    private IntCodeInterpreter createInterpreter() {
        return new IntCodeInterpreter(3, 8, 1001, 8, 10, 8, 105, 1, 0, 0, 21, 38, 47, 72, 97, 122, 203, 284, 365, 446, 99999, 3, 9, 1001, 9, 3, 9, 1002, 9, 5, 9, 1001, 9, 4, 9, 4, 9, 99, 3, 9, 102, 3, 9, 9, 4, 9, 99, 3, 9, 1001, 9, 2, 9, 102, 5, 9, 9, 101, 3, 9, 9, 1002, 9, 5, 9, 101, 4, 9, 9, 4, 9, 99, 3, 9, 101, 5, 9, 9, 1002, 9, 3, 9, 101, 2, 9, 9, 102, 3, 9, 9, 1001, 9, 2, 9, 4, 9, 99, 3, 9, 101, 3, 9, 9, 102, 2, 9, 9, 1001, 9, 4, 9, 1002, 9, 2, 9, 101, 2, 9, 9, 4, 9, 99, 3, 9, 1001, 9, 2, 9, 4, 9, 3, 9, 101, 2, 9, 9, 4, 9, 3, 9, 102, 2, 9, 9, 4, 9, 3, 9, 1001, 9, 1, 9, 4, 9, 3, 9, 102, 2, 9, 9, 4, 9, 3, 9, 101, 2, 9, 9, 4, 9, 3, 9, 1001, 9, 1, 9, 4, 9, 3, 9, 101, 2, 9, 9, 4, 9, 3, 9, 101, 1, 9, 9, 4, 9, 3, 9, 1001, 9, 2, 9, 4, 9, 99, 3, 9, 1001, 9, 1, 9, 4, 9, 3, 9, 101, 1, 9, 9, 4, 9, 3, 9, 101, 2, 9, 9, 4, 9, 3, 9, 102, 2, 9, 9, 4, 9, 3, 9, 101, 2, 9, 9, 4, 9, 3, 9, 101, 1, 9, 9, 4, 9, 3, 9, 1002, 9, 2, 9, 4, 9, 3, 9, 101, 1, 9, 9, 4, 9, 3, 9, 102, 2, 9, 9, 4, 9, 3, 9, 102, 2, 9, 9, 4, 9, 99, 3, 9, 1001, 9, 2, 9, 4, 9, 3, 9, 1002, 9, 2, 9, 4, 9, 3, 9, 1001, 9, 2, 9, 4, 9, 3, 9, 102, 2, 9, 9, 4, 9, 3, 9, 102, 2, 9, 9, 4, 9, 3, 9, 101, 2, 9, 9, 4, 9, 3, 9, 1001, 9, 1, 9, 4, 9, 3, 9, 101, 1, 9, 9, 4, 9, 3, 9, 1002, 9, 2, 9, 4, 9, 3, 9, 102, 2, 9, 9, 4, 9, 99, 3, 9, 101, 1, 9, 9, 4, 9, 3, 9, 101, 1, 9, 9, 4, 9, 3, 9, 102, 2, 9, 9, 4, 9, 3, 9, 102, 2, 9, 9, 4, 9, 3, 9, 101, 2, 9, 9, 4, 9, 3, 9, 101, 2, 9, 9, 4, 9, 3, 9, 1001, 9, 2, 9, 4, 9, 3, 9, 1001, 9, 2, 9, 4, 9, 3, 9, 102, 2, 9, 9, 4, 9, 3, 9, 1001, 9, 1, 9, 4, 9, 99, 3, 9, 101, 2, 9, 9, 4, 9, 3, 9, 101, 2, 9, 9, 4, 9, 3, 9, 1001, 9, 2, 9, 4, 9, 3, 9, 102, 2, 9, 9, 4, 9, 3, 9, 102, 2, 9, 9, 4, 9, 3, 9, 101, 1, 9, 9, 4, 9, 3, 9, 1002, 9, 2, 9, 4, 9, 3, 9, 1002, 9, 2, 9, 4, 9, 3, 9, 101, 2, 9, 9, 4, 9, 3, 9, 1001, 9, 2, 9, 4, 9, 99);
    }

    private void runInterpreter(IntCodeInterpreter interpreter, BlockingQueue<Long> input, BlockingQueue<Long> output, CountDownLatch latch) {
        new Thread(() -> {
            interpreter.execute(new BlockingQueueInputSupplier(input), new BlockingQueueOutputConsumer(output));
            latch.countDown();
        }).start();
    }

    private Long calculateFeedbackThrust(List<Integer> phaseSettings) {
        List<BlockingQueue<Long>> inputQueues = phaseSettings.stream().map(phaseSetting -> new LinkedBlockingDeque<>(Collections.singletonList(phaseSetting.longValue()))).collect(Collectors.toList());
        inputQueues.get(0).add(0L);
        final CountDownLatch latch = new CountDownLatch(inputQueues.size());
        IntStream.range(0, inputQueues.size()).forEach(i -> {
            runInterpreter(createInterpreter(), inputQueues.get(i), inputQueues.get((i + 1) % inputQueues.size()), latch);
        });
        Uninterruptibles.awaitUninterruptibly(latch);
        return inputQueues.get(0).remove();
    }

    private Long calculateThrust(List<Integer> phaseSettings) {
        return phaseSettings.stream().mapToLong(i -> i).reduce(0, (inputSignal, phaseSetting) -> {
            final BlockingQueue<Long> inputQueue = new LinkedBlockingDeque<>(Arrays.asList(phaseSetting, inputSignal));
            final BlockingQueue<Long> outputQueue = new LinkedBlockingDeque<>();
            final IntCodeInterpreter interpreter = createInterpreter();
            interpreter.execute(new BlockingQueueInputSupplier(inputQueue), new BlockingQueueOutputConsumer(outputQueue));
            return outputQueue.remove();
        });
    }


}
