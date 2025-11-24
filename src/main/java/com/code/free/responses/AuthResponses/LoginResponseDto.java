package com.code.free.responses.AuthResponses;

import com.code.free.utilities.globalEnums.RoleType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponseDto {
    private String jwtToken;
    private String email;
    private String username;
    private RoleType role;
}
