package com.code.free.services.TokenService;

import java.time.LocalDateTime;

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

    public ApiResult<String> getTokenByEmailAndOtp(String email, Integer otp) {
        TokenEntity tokenEntity = tokenRepo.findByEmailAndOtp(email, otp).orElse(null);
        if (tokenEntity == null || tokenEntity.getExpiryTime().isBefore(java.time.LocalDateTime.now())) {
            return CustomResponse.failure("Invalid OTP", "OTP verification failed", HttpStatus.BAD_REQUEST);
        }
        tokenEntity.setOtp(null);
        tokenEntity.setExpiryTime(LocalDateTime.now().plusMinutes(5));
        ApiResult<Boolean> updateResponse = saveUpdatedToken(tokenEntity);
        if (updateResponse.getBody().getSuccess() == false) {
            return CustomResponse.failure("Something went wrong", "Internal Server Error",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return CustomResponse.success(email, "Otp Verification successful", HttpStatus.OK);
    }

    public ApiResult<Boolean> saveUpdatedToken(TokenEntity tokenEntity) {
        TokenEntity updatedToken = tokenRepo.save(tokenEntity);
        if (updatedToken == null) {
            return CustomResponse.failure("Failed to update token", "TOKEN_UPDATE_FAILED",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return CustomResponse.success(true, "Token updated successfully", HttpStatus.OK);
    }
}
