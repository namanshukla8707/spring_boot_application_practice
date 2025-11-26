package com.code.free.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class InvalidEmailException extends RuntimeException {
    private final String error = "INVALID_EMAIL_FORMAT";

    public InvalidEmailException(String errorMsg) {
        super(errorMsg);
    }

}
