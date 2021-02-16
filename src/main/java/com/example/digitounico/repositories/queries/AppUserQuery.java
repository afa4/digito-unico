package com.example.digitounico.repositories.queries;

import com.example.digitounico.utils.QueryReaderUtil;
import lombok.Getter;

@Getter
public enum AppUserQuery {
    INSERT("insert.sql"),
    UPDATE("update-by-uid.sql"),
    SELECT_ALL("select-all.sql"),
    SELECT_BY_UID("select-by-uid.sql"),
    SELECT_BY_EMAIL("select-by-email.sql"),
    DELETE("delete-by-uid.sql");

    private final String query;

    AppUserQuery(String queryName) {
        this.query = QueryReaderUtil.readQuery(queryName);
    }
}
