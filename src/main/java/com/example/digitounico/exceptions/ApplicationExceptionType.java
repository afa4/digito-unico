package com.example.digitounico.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ApplicationExceptionType {

    ENTITY_NOT_FOUND("Entidade não encontrada.", HttpStatus.NOT_FOUND),
    EMAIL_ALREADY_USED("Este email já está sendo utilizado.", HttpStatus.BAD_REQUEST);

    private final String message;
    private final HttpStatus returnStatus;
}
