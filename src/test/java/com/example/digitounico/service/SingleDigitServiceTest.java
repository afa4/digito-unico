package com.example.digitounico.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class SingleDigitServiceTest {

    SingleDigitService singleDigitService = new SingleDigitService();

    @Test
    public void shouldThrowException_whenStringHasANonDigitChar() {
        assertThrows(RuntimeException.class, () -> singleDigitService.getSingleDigit("sasa7s4as"));
    }

    @Test
    public void shouldReturn2_whenStringInputIs9875() {
        assertEquals(2, singleDigitService.getSingleDigit("9875"));
    }

    @Test
    public void shouldReturn8_whenInputsAre9875And4() {
        assertEquals(8, singleDigitService.getSingleDigit(9875, 4));
    }
}
