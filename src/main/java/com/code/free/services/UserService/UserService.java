package com.code.free.services.UserService;

import java.io.IOException;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.code.free.responses.ValidUsernameResponseDto;
import com.code.free.utilities.ApiResult;

public interface UserService extends UserDetailsService {

    ApiResult<ValidUsernameResponseDto> validateUsername(String username) throws IOException;

}
