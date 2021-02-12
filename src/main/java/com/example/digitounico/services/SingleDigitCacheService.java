package com.example.digitounico.services;

import com.example.digitounico.utils.Pair;
import org.springframework.stereotype.Service;

import java.util.LinkedList;

@Service
public class SingleDigitCacheService {
    private final LinkedList<Pair<String, Integer>> lastTenCalculations = new LinkedList<>();

    public void store(String inputNumber, Integer result) {
        if (lastTenCalculations.size() == 10)
            lastTenCalculations.removeLast();

        lastTenCalculations.addFirst(new Pair<>(inputNumber, result));
    }

    public Pair<String, Integer> get(String inputNumber) {
        return lastTenCalculations.stream()
                .filter(pair -> pair.getKey().equals(inputNumber))
                .findFirst()
                .orElse(null);
    }
}
