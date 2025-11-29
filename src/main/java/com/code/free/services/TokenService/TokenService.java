package com.code.free.services.TokenService;

import com.code.free.entities.token.TokenEntity;
import com.code.free.utilities.ApiResult;

public interface TokenService {

    ApiResult<Boolean> createToken(TokenEntity tokenEntity);
}
