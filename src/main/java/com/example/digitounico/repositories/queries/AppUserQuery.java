package com.example.digitounico.repositories.queries;

import com.example.digitounico.utils.QueryReaderUtil;
import lombok.Getter;

@Getter
public enum AppUserQuery {
    INSERT("appuser/insert.sql"),
    UPDATE("appuser/update-by-uid.sql"),
    SELECT_ALL("appuser/select-all.sql"),
    SELECT_BY_UID("appuser/select-by-uid.sql"),
    SELECT_BY_EMAIL("appuser/select-by-email.sql"),
    DELETE("appuser/delete-by-uid.sql");

    private final String query;

    AppUserQuery(String queryName) {
        this.query = QueryReaderUtil.readQuery(queryName);
    }
}
