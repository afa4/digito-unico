package com.example.digitounico.repositories;

import com.example.digitounico.repositories.mappers.AppUserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class SingleDigitRepository {

    private final AppUserMapper appUserMapper;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;


}
