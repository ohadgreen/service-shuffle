package com.ezbob.serviceshuffle.services;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class NumbersShuffleServiceTest {

    @Test
    void shuffleTest_allNumsExist() {
        int arrayLength = 100;
        NumbersShuffle numbersShuffle = new NumbersShuffleImpl();

        List<Integer> integerList = numbersShuffle.shuffleNumberArray(arrayLength);

        assertEquals(arrayLength, integerList.size());
        Set<Integer> integerSet = new HashSet<>(integerList);

        Arrays.stream(IntStream.range(1, arrayLength + 1).toArray()).forEach(
                i -> assertTrue(integerSet.contains(i))
        );
    }
}