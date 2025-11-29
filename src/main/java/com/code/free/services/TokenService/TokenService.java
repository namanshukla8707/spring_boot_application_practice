package com.code.free.services.TokenService;

import com.code.free.entities.token.TokenEntity;
import com.code.free.utilities.ApiResult;

public interface TokenService {

    ApiResult<Boolean> createToken(TokenEntity tokenEntity);

    ApiResult<String> getTokenByEmailAndOtp(String email, Integer otp);

    ApiResult<Boolean> saveUpdatedToken(TokenEntity tokenEntity);

    ApiResult<Void> deleteAllTokenByEmail(String email);
}
