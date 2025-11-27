package com.code.free.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class EmailNotFoundException extends RuntimeException {
    private final String error = "EMAIL_NOT_FOUND";

    public EmailNotFoundException(String errorMsg) {
        super(errorMsg);
    }
}
