package com.example.digitounico.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ApplicationExceptionType {

    ENTITY_NOT_FOUND("Entidade não encontrada.", HttpStatus.NOT_FOUND),
    EMAIL_ALREADY_USED("Email já cadastrado.", HttpStatus.CONFLICT),
    INVALID_RSA_KEY("Chave RSA mal formada, verifique se esse dado está codificado em base 64.", HttpStatus.BAD_REQUEST),
    TOO_LONG_DATA_TO_BE_ENCRYPTED("Informação muito longa para ser criptografada pela chave.", HttpStatus.BAD_REQUEST),
    TOO_LONG_DATA_TO_BE_DECRYPTED("Informação muito longa para ser descriptografada pela chave.", HttpStatus.BAD_REQUEST),
    USER_DATA_ALREADY_ENCRYPTED("Os dados do usuário já foram criptografados.", HttpStatus.FORBIDDEN),
    INTERNAL_ERROR("Erro interno da aplicação.", HttpStatus.INTERNAL_SERVER_ERROR);

    private final String message;
    private final HttpStatus returnStatus;
}
