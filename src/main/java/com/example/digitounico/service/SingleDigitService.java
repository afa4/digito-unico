package com.example.digitounico.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class SingleDigitService {

    private Map<String, Integer> lastTenCalculations = new HashMap<>();

    public int getSingleDigit(int number, int repeatTimes) {
        var numberAsString = Integer.toString(number).repeat(repeatTimes);
        return getSingleDigit(numberAsString);
    }

    public int getSingleDigit(String number) {
        if (number.matches("[0-9]*")) {
            while (number.length() > 1) {
                int sum = 0;
                for (char digit : number.toCharArray()) {
                    sum += Character.getNumericValue(digit);
                }
                number = Integer.toString(sum);
            }
            return Integer.parseInt(number);
        } else {
            throw new RuntimeException();
        }
    }
}
