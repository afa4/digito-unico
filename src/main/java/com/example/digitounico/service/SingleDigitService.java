package com.example.digitounico.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class SingleDigitService {

    private Map<String, Integer> lastTenCalculations = new HashMap<>();

    public int getSingleDigit(String number, int repeatTimes) {
        if (number.matches("[0-9]*")) {
            return getSingleDigit(repeatTimes <= 0 ? number : number.repeat(repeatTimes));
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
