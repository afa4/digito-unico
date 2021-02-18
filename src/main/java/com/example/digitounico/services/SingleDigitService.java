package com.example.digitounico.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class SingleDigitService {

    private final CacheService cache;

    private final String DIGITS = "[0-9]*";

    public int getSingleDigit(String number, int repeatTimes) {
        if (number.matches(DIGITS)) {
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
            number = Integer.toString(
                    number.chars().map(Character::getNumericValue).sum()
            );
        }
        return Integer.parseInt(number);
    }
}
