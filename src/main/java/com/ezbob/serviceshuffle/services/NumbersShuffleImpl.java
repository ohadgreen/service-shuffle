package com.ezbob.serviceshuffle.services;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class NumbersShuffleImpl implements NumbersShuffle{

    @Override
    public List<Integer> shuffleNumberArray(int arrayLength) {
        int[] myNumbersArray = IntStream.range(1, arrayLength + 1).toArray();

        for (int i = 0; i < arrayLength; i++) {
            int nextRandom = ThreadLocalRandom.current().nextInt(0, arrayLength - i);
            int temp = myNumbersArray[arrayLength - i - 1];
            myNumbersArray[arrayLength - i - 1] = myNumbersArray[nextRandom];
            myNumbersArray[nextRandom] = temp;
        }

        return Arrays.stream(myNumbersArray).boxed().collect(Collectors.toList());
    }
}
