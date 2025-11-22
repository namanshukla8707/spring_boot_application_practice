package com.code.free.utilities.exceptions;

import io.jsonwebtoken.JwtException;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.code.free.responses.CustomResponse;
import com.code.free.utilities.ApiResult;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DuplicateEmailException.class)
    public <T> ApiResult<T> handleDuplicateEmailException(DuplicateEmailException exception){
        return CustomResponse.failure(exception.getMessage(),exception.getError(),HttpStatus.CONFLICT);
    }

    @ExceptionHandler(AuthenticationException.class)
    public <T> ApiResult<T>  handleAuthenticationException(AuthenticationException exception) {
        return CustomResponse.failure("Authentication failed: " + exception.getMessage(), "AUTHENTICATION_FAILED", HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(JwtException.class)
    public <T> ApiResult<T> handleJwtException(JwtException exception) {
        return CustomResponse.failure("Invalid JWT token: " + exception.getMessage(), "INVALID_JWT_TOKEN", HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public <T> ApiResult<T> handleAccessDeniedException(AccessDeniedException exception) {
        return CustomResponse.failure("Access denied: Insufficient permissions", "ACCESS_DENIED", HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(Exception.class)
    public <T> ApiResult<T> handleGenericException(Exception exception) {
        return CustomResponse.failure("An unexpected error occurred: " + exception.getMessage(), "INTERNAL_SERVER_ERROR", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
