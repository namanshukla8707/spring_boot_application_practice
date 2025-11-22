package com.code.free.responses;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class ApiResponse<T> {
    private Boolean success;
    private HttpStatus status;
    private Integer statusCode;
    private T data;
    private String message;
    private LocalDateTime timestamp;
    private String error;

    public ApiResponse() {
        this.timestamp = LocalDateTime.now();
    }

    public ApiResponse(Boolean success, HttpStatus status, T data, String message) {
        this();
        this.success = success;
        this.status = status;
        this.statusCode = status.value();
        this.message = message;
        this.data = data;
        this.error = null;
    }

    public ApiResponse(Boolean success, HttpStatus status, T data, String message, String error) {
        this();
        this.success = success;
        this.status = status;
        this.statusCode = status.value();
        this.message = message;
        this.data = data;
        this.error = error;
    }
}