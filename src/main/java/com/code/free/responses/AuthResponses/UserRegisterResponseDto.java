package com.code.free.responses.AuthResponses;

import com.code.free.utilities.globalEnums.RoleType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterResponseDto {
    
    private String username;
    private String email;
    private RoleType role;
    private String token;

}
