package com.example.digitounico.repositories;

import com.example.digitounico.entities.AppUser;
import com.example.digitounico.entities.SingleDigit;
import com.example.digitounico.exceptions.ApplicationException;
import com.example.digitounico.exceptions.ApplicationExceptionType;
import com.example.digitounico.repositories.mappers.AppUserMapper;
import com.example.digitounico.repositories.queries.AppUserQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static java.util.Objects.nonNull;

@Repository
@RequiredArgsConstructor
public class AppUserRepository {

    private final AppUserMapper appUserMapper;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final SingleDigitRepository singleDigitRepository;

    public void create(AppUser appUser) {
        var params = Map.of(
                "uid", nonNull(appUser.getUid()) ? appUser.getUid() : UUID.randomUUID(),
                "name", appUser.getName(),
                "email", appUser.getEmail()
        );
        namedParameterJdbcTemplate.update(AppUserQuery.INSERT.getQuery(), params);
    }

    public List<AppUser> findAll() {
        var users = namedParameterJdbcTemplate.query(
                AppUserQuery.SELECT_ALL.getQuery(), Map.of(), appUserMapper);

        var singleDigitsMappedByUserId = singleDigitRepository.findAllMappedByAppUserId();

        users.forEach(user -> {
            var singleDigits = singleDigitsMappedByUserId.get(user.getId());
            user.setSingleDigits(nonNull(singleDigits) ? singleDigits : Collections.emptyList());
        });

        return users;
    }

    public AppUser findByUid(UUID uid) {
        var params = Map.of(
                "uid", uid
        );

        try {
            var user = namedParameterJdbcTemplate.queryForObject(
                    AppUserQuery.SELECT_BY_UID.getQuery(), params, appUserMapper);

            return includeSingleDigits(user);
        } catch (EmptyResultDataAccessException ignored) {
        }

        return null;
    }

    public AppUser findByEmail(String email) {
        var params = Map.of(
                "email", email
        );
        try {
            var user = namedParameterJdbcTemplate.queryForObject(
                    AppUserQuery.SELECT_BY_EMAIL.getQuery(), params, appUserMapper);
            return includeSingleDigits(user);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public void update(AppUser appUser) {
        var params = Map.of(
                "uid", appUser.getUid(),
                "name", appUser.getName(),
                "email", appUser.getEmail()
        );
        namedParameterJdbcTemplate.update(AppUserQuery.UPDATE.getQuery(), params);
    }

    public void delete(UUID uid) {
        var params = Map.of(
                "uid", uid
        );
        namedParameterJdbcTemplate.update(AppUserQuery.DELETE.getQuery(), params);
    }

    public void insertSingleDigit(SingleDigit singleDigit) {
        var params = Map.of(
                "appUserId", singleDigit.getAppUserId(),
                "integer", singleDigit.getInteger(),
                "repeatTimes", singleDigit.getRepeatTimes(),
                "singleDigit", singleDigit.getSingleDigit()
        );
        namedParameterJdbcTemplate.update(AppUserQuery.INSERT_SINGLE_DIGIT.getQuery(), params);
    }

    private AppUser includeSingleDigits(AppUser user) {
        if (nonNull(user)) {
            var singleDigits = singleDigitRepository.findByAppUserId(user.getId());
            user.setSingleDigits(nonNull(singleDigits) ? singleDigits : Collections.emptyList());
            return user;
        }
        throw new ApplicationException(ApplicationExceptionType.INTERNAL_ERROR);
    }
}
