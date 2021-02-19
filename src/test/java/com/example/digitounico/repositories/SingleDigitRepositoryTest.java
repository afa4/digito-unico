package com.example.digitounico.repositories;

import com.example.digitounico.repositories.mappers.SingleDigitMapper;
import com.example.digitounico.utils.QueryReaderUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.List;
import java.util.Map;

import static com.example.digitounico.utils.DigitoUnicoApplicationUtil.mockSingleDigit;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SingleDigitRepositoryTest {

    @Mock
    private SingleDigitMapper singleDigitMapper;

    @Mock
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @InjectMocks
    private SingleDigitRepository singleDigitRepository;

    @Test
    public void shouldBuildAMapOfUserIdToSingleDigitList_whenFindingAllSingleDigits() {
        var query = QueryReaderUtil.readQuery("select-all-single-digits.sql");

        var allSingleDigits = List.of(
                mockSingleDigit(1L),
                mockSingleDigit(1L),
                mockSingleDigit(2L),
                mockSingleDigit(3L),
                mockSingleDigit(3L),
                mockSingleDigit(3L)
        );

        when(namedParameterJdbcTemplate.query(query, Map.of(), singleDigitMapper))
                .thenReturn(allSingleDigits);

        var result = singleDigitRepository.findAllMappedByAppUserId();

        assertEquals(3, result.size());

        assertEquals(2, result.get(1L).size());
        assertEquals(1, result.get(2L).size());
        assertEquals(3, result.get(3L).size());
    }


}
