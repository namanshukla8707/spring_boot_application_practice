package com.code.free.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.code.free.entities.user.UserView;
import com.code.free.services.AdminService.AdminService;
import com.code.free.utilities.ApiResult;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;

@RequestMapping("/api/v1/admin")
@RestController
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @GetMapping("/users")
    public ApiResult<List<UserView>> getUsers() {
        return adminService.getUsers();
    }

}
