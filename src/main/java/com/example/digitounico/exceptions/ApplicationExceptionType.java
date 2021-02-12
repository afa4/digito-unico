package com.example.digitounico.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ApplicationExceptionType {

    ENTITY_NOT_FOUND("Entidade não encontrada.", HttpStatus.NOT_FOUND),
    INVALID_RSA_PUBLIC_KEY("Chave pública RSA mal formada.", HttpStatus.BAD_REQUEST),
    EMAIL_ALREADY_USED("Email já cadastrado.", HttpStatus.CONFLICT);

    private final String message;
    private final HttpStatus returnStatus;
}
