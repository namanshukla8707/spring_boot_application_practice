package com.code.free.utilities;

import org.springframework.http.ResponseEntity;

import com.code.free.responses.ApiResponse;

public class ApiResult<T> extends ResponseEntity<ApiResponse<T>> {

    public ApiResult(ApiResponse<T> body) {
        super(body, body.getStatus());
    }
}
