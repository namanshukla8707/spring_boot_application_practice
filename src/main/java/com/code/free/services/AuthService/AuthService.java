package com.code.free.services.AuthService;

import java.io.IOException;

import com.code.free.requests.AuthRequests.LoginRequestDto;
import com.code.free.requests.AuthRequests.UserRegisterRequestDto;
import com.code.free.responses.AuthResponses.LoginResponseDto;
import com.code.free.responses.AuthResponses.UserRegisterResponseDto;
import com.code.free.utilities.ApiResult;

public interface AuthService {

    ApiResult<LoginResponseDto> login(LoginRequestDto request);

    ApiResult<UserRegisterResponseDto> registerUser(UserRegisterRequestDto request);
    
    ApiResult<String> sendOtpToEmail(String email) throws IOException;
}
