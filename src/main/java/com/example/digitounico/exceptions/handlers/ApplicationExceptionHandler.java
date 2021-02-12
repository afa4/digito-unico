package com.example.digitounico.exceptions.handlers;

import com.example.digitounico.exceptions.ApplicationException;
import com.example.digitounico.exceptions.ApplicationExceptionMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ApplicationException.class})
    public ResponseEntity handleApplicationException(ApplicationException ex, WebRequest request) {
        var message = new ApplicationExceptionMessage(ex.getType().getMessage());
        return ResponseEntity.status(ex.getType().getReturnStatus()).body(message);
    }
}
