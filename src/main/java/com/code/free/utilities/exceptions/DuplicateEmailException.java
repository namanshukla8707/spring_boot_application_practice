package com.code.free.utilities.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class DuplicateEmailException extends RuntimeException {

    private final String error="EMAIL_ALREADY_EXISTS";

    public DuplicateEmailException(String errorMsg) {
        super(errorMsg);
    }

}
