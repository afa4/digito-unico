package com.example.digitounico.repositories.mappers;


import com.example.digitounico.entities.SingleDigit;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class SingleDigitMapper implements RowMapper<SingleDigit> {
    @Override
    public SingleDigit mapRow(ResultSet resultSet, int i) throws SQLException {
        return SingleDigit.builder()
                .appUserId(resultSet.getLong("app_user_id"))
                .integer(resultSet.getString("integer"))
                .repeatTimes(resultSet.getInt("repeat_times"))
                .singleDigit(resultSet.getInt("single_digit"))
                .build();
    }
}
