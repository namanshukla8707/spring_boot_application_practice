package com.code.free.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.code.free.requests.AuthRequests.LoginRequestDto;
import com.code.free.requests.AuthRequests.UserRegisterRequestDto;
import com.code.free.responses.AuthResponses.LoginResponseDto;
import com.code.free.responses.AuthResponses.UserRegisterResponseDto;
import com.code.free.services.AuthService.AuthService;
import com.code.free.utilities.ApiResult;

import lombok.RequiredArgsConstructor;

import java.io.IOException;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthService authService;

    @PostMapping("/login")
    public ApiResult<LoginResponseDto> login(@RequestBody LoginRequestDto request) {
        return authService.login(request);

    }

    @PostMapping("/signup")
    public ApiResult<UserRegisterResponseDto> registerUser(@RequestBody UserRegisterRequestDto request) {
        return authService.registerUser(request);
    }

    @GetMapping("/send-otp")
    public ApiResult<String> requestOtp(@RequestParam("email") String email) throws IOException {
        return authService.sendOtpToEmail(email);
    }
}
