package com.code.free.services.AdminService;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.code.free.entities.user.UserView;
import com.code.free.repositories.user.UserViewRepo;
import com.code.free.responses.CustomResponse;
import com.code.free.utilities.ApiResult;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final UserViewRepo userViewRepo;

    public ApiResult<List<UserView>> getUsers() {

        List<UserView> users = userViewRepo.findAll();
        String message = users.isEmpty() ? "No users found" : "Users fetched successfully";
        return CustomResponse.success(users, message, HttpStatus.OK);
    }
}
