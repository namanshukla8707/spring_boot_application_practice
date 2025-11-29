package com.code.free.services.TokenService;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.code.free.entities.token.TokenEntity;
import com.code.free.repositories.token.TokenRepo;
import com.code.free.responses.CustomResponse;
import com.code.free.utilities.ApiResult;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {

    private final TokenRepo tokenRepo;

    public ApiResult<Boolean> createToken(TokenEntity tokenEntity) {
        TokenEntity token = tokenRepo.save(tokenEntity);
        if (token == null) {
            return CustomResponse.failure("Failed Creating a Token", "TOKEN_CREATION_FAILED",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return CustomResponse.success(null, "Successfully created token", HttpStatus.CREATED);
    }
}
