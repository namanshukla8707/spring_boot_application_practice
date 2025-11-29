package com.code.free.requests.AuthRequests;

import lombok.Data;

@Data
public class ForgetPasswordRequestDto {

    private String email;
    private String password;
    private String confirmPassword;
    private Integer otp;
}
