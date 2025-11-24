package com.code.free.requests.AuthRequests;

import com.code.free.utilities.globalEnums.RoleType;

import lombok.Data;

@Data
public class UserRegisterRequestDto {
    private String username;
    private String password;
    private String email;
    private RoleType role;
}
