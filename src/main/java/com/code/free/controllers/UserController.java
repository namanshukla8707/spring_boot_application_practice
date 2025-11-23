package com.code.free.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.code.free.responses.ValidUsernameResponseDto;
import com.code.free.services.UserService.UserService;
import com.code.free.utilities.ApiResult;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {
    
    private final UserService userService;

    @GetMapping("/validate-name")
    public ApiResult<ValidUsernameResponseDto> validateUsername(@RequestParam("username") String username){

       return userService.validateUsername(username);
    }
   


}
