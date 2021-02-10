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
        assertThrows(RuntimeException.class, () -> singleDigitService.getSingleDigit("sasa7s4as", 1));
    }

    @Test
    public void shouldReturn2_whenInputsAreNumber9875AndRepeatTimes1() {
        assertEquals(2, singleDigitService.getSingleDigit("9875", 1));
    }

    @Test
    public void shouldReturn8_whenInputsAreNumber9875AndRepeatTimes4() {
        assertEquals(8, singleDigitService.getSingleDigit("9875", 4));
    }

    @Test
    public void shouldReturnSingleDigitWithoutRepeatingInputNumber_whenInputRepeatTimeIsZero() {
        assertEquals(2, singleDigitService.getSingleDigit("9875", 0));
    }

    @Test
    public void shouldReturnSingleDigitWithoutRepeatingInputNumber_whenInputRepeatTimeIsLessThanZero() {
        assertEquals(2, singleDigitService.getSingleDigit("9875", -1));
    }
}
