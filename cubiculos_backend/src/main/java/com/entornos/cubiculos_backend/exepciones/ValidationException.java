package com.entornos.cubiculos_backend.exepciones;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ValidationException extends RuntimeException {
    private final String validationError;

    public ValidationException(String validationError) {
        super(validationError);
        this.validationError = validationError;
    }

    public String getValidationError() {
        return validationError;
    }
}
