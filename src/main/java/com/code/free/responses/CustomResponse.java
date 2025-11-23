package com.code.free.responses;

import org.springframework.http.HttpStatus;

import com.code.free.utilities.ApiResult;


public class CustomResponse {

    public static <T> ApiResult<T> success(T data,String message,HttpStatus status){
        ApiResponse<T> response = new ApiResponse<T>(status, data, message);
        return new ApiResult<>(response);
    }

    public static <T> ApiResult<T> failure(String errorMessage,String error,HttpStatus status){
        ApiResponse<T> response = new ApiResponse<T>(status, null, errorMessage, error);
        return new ApiResult<>(response);
    }
}
