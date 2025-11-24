package com.code.free.services.AdminService;

import java.util.List;

import com.code.free.entities.user.UserView;
import com.code.free.utilities.ApiResult;

public interface AdminService {

    ApiResult<List<UserView>> getUsers();
    
}
