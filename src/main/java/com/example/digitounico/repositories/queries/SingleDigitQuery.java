package com.example.digitounico.repositories.queries;

import com.example.digitounico.utils.QueryReaderUtil;
import lombok.Getter;

@Getter
public enum SingleDigitQuery {
    SELECT_SINGLE_DIGIT_BY_APP_USER_ID("select-single-digit-by-appuser-id.sql"),
    SELECT_ALL_SINGLE_DIGITS("select-all-single-digits.sql");

    private final String query;

    SingleDigitQuery(String queryName) {
        this.query = QueryReaderUtil.readQuery(queryName);
    }
}
