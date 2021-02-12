package com.example.digitounico.repositories.mappers;

import com.example.digitounico.entities.AppUser;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;


@Component
public class AppUserMapper implements RowMapper<AppUser> {
    @Override
    public AppUser mapRow(ResultSet resultSet, int i) throws SQLException {
        return AppUser.builder()
                .id(resultSet.getLong("id"))
                .uid(UUID.fromString(resultSet.getString("uid")))
                .name(resultSet.getString("name"))
                .email(resultSet.getString("email"))
                .build();
    }
}
