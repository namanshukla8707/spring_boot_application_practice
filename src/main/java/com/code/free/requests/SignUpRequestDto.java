package com.code.free.requests;

import com.code.free.utilities.globalEnums.RoleType;

import lombok.Data;

@Data
public class SignUpRequestDto {
    private String username;
    private String password;
    private String email;
    private RoleType role;
}
