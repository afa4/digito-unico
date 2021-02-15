package com.example.digitounico.services;

import com.example.digitounico.utils.Pair;
import org.springframework.stereotype.Service;

import java.util.LinkedList;

@Service
public class CacheService {
    private final LinkedList<Pair<String, Integer>> lastTenSingleDigits = new LinkedList<>();

    public void store(String inputNumber, Integer result) {
        if (lastTenSingleDigits.size() == 10)
            lastTenSingleDigits.removeLast();

        lastTenSingleDigits.addFirst(new Pair<>(inputNumber, result));
    }

    public Pair<String, Integer> get(String inputNumber) {
        return lastTenSingleDigits.stream()
                .filter(pair -> pair.getKey().equals(inputNumber))
                .findFirst()
                .orElse(null);
    }
}
