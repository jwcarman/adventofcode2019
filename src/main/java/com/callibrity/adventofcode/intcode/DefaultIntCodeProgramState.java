package com.callibrity.adventofcode.intcode;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
public class DefaultIntCodeProgramState implements IntCodeProgramState {

    private final Map<Long, Long> memoryMap = new HashMap<>();
    private long instructionPointer = 0L;
    private long relativeBase = 0L;

    public DefaultIntCodeProgramState(int... values) {
        this(IntStream.of(values).mapToLong(i -> i).boxed().collect(Collectors.toList()));
    }

    public DefaultIntCodeProgramState(final List<Long> memory) {
        IntStream.range(0, memory.size()).forEach(i -> memoryMap.put((long) i, memory.get(i)));
    }

    @Override
    public long readRelativeValue(long position) {
        return readValue(relativeBase + position);
    }

    public long readNextValue() {
        return readValue(instructionPointer++);
    }

    @Override
    public void adjustRelativeBase(long offset) {
        relativeBase += offset;
        log.debug("Adjusted relative base to {}.", relativeBase);
    }

    @Override
    public long readValue(long position) {
        return memoryMap.getOrDefault(position, 0L);
    }

    @Override
    public void writeRelativeValue(long position, long value) {
        memoryMap.put(position + relativeBase, value);
        log.debug("Wrote value {} to relative position {} ({}).", value, position, position + relativeBase);
    }

    @Override
    public void writeValue(long position, long value) {
        memoryMap.put(position, value);
        log.debug("Wrote value {} to position {}.", value, position);
    }

    @Override
    public void jump(long position) {
        log.debug("Jumping to position {}.", position);
        instructionPointer = position;
    }
}
