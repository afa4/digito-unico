package com.example.digitounico.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ApplicationExceptionType {

    ENTITY_NOT_FOUND("Entidade não encontrada.", HttpStatus.NOT_FOUND),
    INVALID_RSA_PUBLIC_KEY("Chave RSA pública mal formada.", HttpStatus.BAD_REQUEST),
    INVALID_RSA_PRIVATE_KEY("Chave RSA privada mal formada.", HttpStatus.BAD_REQUEST),
    EMAIL_ALREADY_USED("Email já cadastrado.", HttpStatus.CONFLICT),
    INTERNAL_ERROR("Erro interno de servidor.", HttpStatus.INTERNAL_SERVER_ERROR);

    private final String message;
    private final HttpStatus returnStatus;
}
