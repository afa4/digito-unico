package com.example.digitounico.service;

import com.example.digitounico.util.Pair;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.LinkedList;

@Getter
@Service
public class SingleDigitService {

    private final LinkedList<Pair<String, Integer>> lastTenCalculations = new LinkedList<>();

    public int getSingleDigit(String number, int repeatTimes) {
        if (number.matches("[0-9]*")) {
            var inputNumber = repeatTimes <= 0 ? number : number.repeat(repeatTimes);

            var result = getSingleDigit(inputNumber);

            storeResultInMemory(inputNumber, result);

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

    private void storeResultInMemory(String inputNumber, Integer result) {
        if (lastTenCalculations.size() == 10)
            lastTenCalculations.removeLast();

        lastTenCalculations.addFirst(new Pair<>(inputNumber, result));
    }

    private Pair getResultFromMemory(String inputNumber) {
        return lastTenCalculations.stream()
                .filter(pair -> pair.getKey().equals(inputNumber))
                .findFirst().orElse(null);
    }
}
