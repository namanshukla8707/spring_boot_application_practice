package com.code.free.responses;

import lombok.Data;

@Data
public class UserRegisterResponse {
    
    private Long id;
    private String name;
    private String email;
    private String accessToken;
    private String refreshToken;

}
