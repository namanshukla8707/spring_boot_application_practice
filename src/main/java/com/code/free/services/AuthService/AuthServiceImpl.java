package com.code.free.services.AuthService;

import java.io.IOException;

import org.springframework.http.HttpStatus;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.code.free.configuration.Config;
import com.code.free.entities.token.TokenEntity;
import com.code.free.entities.user.UserEntity;
import com.code.free.exceptions.DuplicateEmailException;
import com.code.free.exceptions.EmailNotFoundException;
import com.code.free.exceptions.InvalidEmailException;
import com.code.free.repositories.user.UserRepo;
import com.code.free.requests.AuthRequests.LoginRequestDto;
import com.code.free.requests.AuthRequests.UserRegisterRequestDto;
import com.code.free.responses.CustomResponse;
import com.code.free.responses.AuthResponses.LoginResponseDto;
import com.code.free.responses.AuthResponses.UserRegisterResponseDto;
import com.code.free.security.AuthUtil;
import com.code.free.services.TokenService.TokenService;
import com.code.free.utilities.ApiResult;
import com.code.free.utilities.Constants;
import com.code.free.utilities.ConstantsReaderWrapper;
import com.code.free.utilities.Utils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final AuthUtil authUtil;
    private final UserRepo userRepo;
    private final Config config;
    private final ConstantsReaderWrapper constantsReaderWrapper;
    private final Constants constants;
    private final Utils utils;
    private final TokenService tokenService;

    public ApiResult<LoginResponseDto> login(LoginRequestDto request) {
        String identifier = request.getIdentifier();
        String password = request.getPassword();
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(identifier, password));

        UserEntity user = (UserEntity) authentication.getPrincipal();
        String token = authUtil.generateAccessToken(user);

        return CustomResponse.success(new LoginResponseDto(token, user.getEmail(), user.getUsername(), user.getRole()),
                "Login Successful", HttpStatus.OK);
    }

    public ApiResult<UserRegisterResponseDto> registerUser(UserRegisterRequestDto request) {
        UserEntity user = userRepo.findByEmail(request.getEmail()).orElse(null);
        if (user != null) {
            throw new DuplicateEmailException("Email is already in use");
        }

        UserEntity newUser = UserEntity.builder().username(request.getUsername())
                .password(config.passwordEncoder().encode(request.getPassword())).email(request.getEmail())
                .build();

        if (request.getRole() != null) {
            newUser.setRole(request.getRole());
        }

        user = userRepo.save(newUser);
        String token = authUtil.generateAccessToken(user);

        return CustomResponse.success(
                new UserRegisterResponseDto(user.getUsername(), user.getEmail(), user.getRole(), token),
                "User registered successfully", HttpStatus.CREATED);
    }

    public ApiResult<String> sendOtpToEmail(String email) throws IOException {
        String body = constantsReaderWrapper.getValueByKey(constants.getOtpEmailBodyKey());
        String subject = constantsReaderWrapper.getValueByKey(constants.getOtpEmailSubjectKey());
        String EMAIL_REGEX = constantsReaderWrapper.getValueByKey(constants.getEmailRegexKey());

        if (!email.matches(EMAIL_REGEX)) {
            throw new InvalidEmailException("Invalid email format");
        }

        UserEntity user = userRepo.findByEmail(email).orElse(null);
        if (user == null) {
            throw new EmailNotFoundException("Email not found");
        }

        Integer otp = utils.generateOtp();
        body = body.replace("{OTP}", String.valueOf(otp));
        ApiResult<Boolean> tokenCreationResponse = tokenService
                .createToken(new TokenEntity(null, email, otp, null, utils.getExpiryTime()));
        if (tokenCreationResponse.getBody().getSuccess() == false) {
            return CustomResponse.failure(email, "OTP cannot be sent due to service error",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
        utils.sendEmail(email, body, subject);
        return CustomResponse.success(email, "OTP sent successfully", HttpStatus.OK);
    }

    public ApiResult<String> verifyOtp(String email, Integer otp) throws IOException {
        if (email == null || otp == null) {
            return CustomResponse.failure("Email and OTP must be provided", "OTP verification failed",
                    HttpStatus.BAD_REQUEST);
        }
        String EMAIL_REGEX = constantsReaderWrapper.getValueByKey(constants.getEmailRegexKey());

        if (!email.matches(EMAIL_REGEX)) {
            throw new InvalidEmailException("Invalid email format");
        }
        ApiResult<String> result = tokenService.getTokenByEmailAndOtp(email, otp);
        return result;
    }

    public ApiResult<String> resetPassword(String email, String password, String confirmPassword) {
        if (!password.equals(confirmPassword)) {
            return CustomResponse.failure("Passwords do not match", "Password reset failed", HttpStatus.BAD_REQUEST);
        }

        UserEntity user = userRepo.findByEmail(email).orElse(null);
        if (user == null) {
            throw new EmailNotFoundException("Email not found");
        }

        user.setPassword(config.passwordEncoder().encode(password));
        userRepo.save(user);
        return CustomResponse.success(email, "Password reset successful", HttpStatus.OK);
    }

}
