package com.example.digitounico.utils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class QueryReaderUtil {
    public static String readQuery(String sqlName) {
        var inputStream = ClassLoader.getSystemResourceAsStream("db/queries/" + sqlName);

        if (inputStream != null) {
            try {
                var bytes = inputStream.readAllBytes();
                return new String(bytes, StandardCharsets.UTF_8);
            } catch (IOException ignored) {
            }
        }
        throw new RuntimeException();
    }
}
