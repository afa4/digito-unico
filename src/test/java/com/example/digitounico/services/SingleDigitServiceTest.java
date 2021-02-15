package com.example.digitounico.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SingleDigitServiceTest {

    @Mock
    private CacheService cache;

    @InjectMocks
    private SingleDigitService singleDigitService;

    @Test
    public void shouldThrowException_whenStringHasANonDigitChar() {
        assertThrows(RuntimeException.class, () -> singleDigitService.getSingleDigit("sasa7s4as", 1));
    }

    @Test
    public void shouldReturn2_whenInputsAreNumber9875AndRepeatTimes1() {
        setupCacheToReturnNull();
        assertEquals(2, singleDigitService.getSingleDigit("9875", 1));
    }

    @Test
    public void shouldReturn8_whenInputsAreNumber9875AndRepeatTimes4() {
        setupCacheToReturnNull();
        assertEquals(8, singleDigitService.getSingleDigit("9875", 4));
    }

    @Test
    public void shouldReturnSingleDigitWithoutRepeatingInputNumber_whenInputRepeatTimeIsZero() {
        setupCacheToReturnNull();
        assertEquals(2, singleDigitService.getSingleDigit("9875", 0));
    }

    @Test
    public void shouldReturnSingleDigitWithoutRepeatingInputNumber_whenInputRepeatTimeIsLessThanZero() {
        setupCacheToReturnNull();
        assertEquals(2, singleDigitService.getSingleDigit("9875", -1));
    }

    private void setupCacheToReturnNull() {
        when(cache.get(any())).thenReturn(null);
        doNothing().when(cache).store(anyString(), anyInt());
    }
}
