package com.example.digitounico.exceptions;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ApplicationExceptionMessage {
    @JsonProperty("mensagem")
    private final String message;
}
