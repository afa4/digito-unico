package com.example.digitounico.repositories;

import com.example.digitounico.entities.AppUser;
import com.example.digitounico.repositories.mappers.AppUserMapper;
import com.example.digitounico.repositories.queries.AppUserQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import static java.util.Objects.nonNull;

@Repository
@RequiredArgsConstructor
public class AppUserRepository {
    private final AppUserMapper appUserMapper;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public void create(AppUser appUser) {
        var params = Map.of(
                "uid", nonNull(appUser.getUid()) ? appUser.getUid() : UUID.randomUUID(),
                "name", appUser.getName(),
                "email", appUser.getEmail()
        );
        namedParameterJdbcTemplate.update(AppUserQuery.INSERT.getQuery(), params);
    }

    public List<AppUser> findAll() {
        return namedParameterJdbcTemplate.query(
                AppUserQuery.SELECT_ALL.getQuery(), Map.of(), appUserMapper);
    }

    public AppUser findByUid(UUID uid) {
        var params = Map.of(
                "uid", uid
        );
        return namedParameterJdbcTemplate.queryForObject(
                AppUserQuery.SELECT_BY_UID.getQuery(), params, appUserMapper);
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
}
