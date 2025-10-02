package com.code.free.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.code.free.requests.UserRegisterRequest;
import com.code.free.responses.UserRegisterResponse;
import com.code.free.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserRegisterResponse> registerUser(@RequestBody UserRegisterRequest request) {
        UserRegisterResponse response = userService.registerUser(request);
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }

}
