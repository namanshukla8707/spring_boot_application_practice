package com.code.free.requests.AuthRequests;

import lombok.Data;

@Data
public class LoginRequestDto {

    private String identifier;
    private String password;

}
