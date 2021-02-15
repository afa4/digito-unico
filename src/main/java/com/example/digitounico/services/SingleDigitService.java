package com.example.digitounico.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class SingleDigitService {

    private final CacheService cache;

    public int getSingleDigit(String number, int repeatTimes) {
        if (number.matches("[0-9]*")) {
            var inputNumber = repeatTimes <= 0 ? number : number.repeat(repeatTimes);

            var resultFromMemory = cache.get(inputNumber);
            if (Objects.nonNull(resultFromMemory))
                return resultFromMemory.getValue();

            var result = getSingleDigit(inputNumber);
            cache.store(inputNumber, result);

            return result;
        }
        throw new RuntimeException();
    }

    private int getSingleDigit(String number) {
        while (number.length() > 1) {
            int sum = 0;
            for (char digit : number.toCharArray()) {
                sum += Character.getNumericValue(digit);
            }
            number = Integer.toString(sum);
        }
        return Integer.parseInt(number);
    }
}
