package com.example.digitounico.repositories;

import com.example.digitounico.entities.SingleDigit;
import com.example.digitounico.repositories.mappers.SingleDigitMapper;
import com.example.digitounico.repositories.queries.SingleDigitQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class SingleDigitRepository {

    private final SingleDigitMapper singleDigitMapper;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public Map<Long, List<SingleDigit>> findAllMappedByAppUserId() {
        var singleDigitList = namedParameterJdbcTemplate.query(
                SingleDigitQuery.SELECT_ALL_SINGLE_DIGITS.getQuery(), Map.of(), singleDigitMapper);

        Map<Long, List<SingleDigit>> map = new HashMap<>();

        singleDigitList.forEach(singleDigit -> {
            var appUserId = singleDigit.getAppUserId();

            List<SingleDigit> singleDigitsOfUser;

            if (map.get(appUserId) != null)
                singleDigitsOfUser = map.get(appUserId);
            else
                singleDigitsOfUser = new ArrayList<>();

            singleDigitsOfUser.add(singleDigit);
            map.put(appUserId, singleDigitsOfUser);
        });

        return map;
    }

    public List<SingleDigit> findByAppUserId(Long appUserId) {
        return namedParameterJdbcTemplate.query(
                SingleDigitQuery.SELECT_SINGLE_DIGIT_BY_APP_USER_ID.getQuery(),
                Map.of("appUserId", appUserId),
                singleDigitMapper
        );
    }
}
