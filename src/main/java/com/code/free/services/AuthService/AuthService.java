package com.code.free.services.AuthService;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.code.free.configuration.Config;
import com.code.free.entities.user.UserEntity;
import com.code.free.repositories.user.UserRepo;
import com.code.free.requests.LoginRequestDto;
import com.code.free.responses.CustomResponse;
import com.code.free.responses.LoginResponseDto;
import com.code.free.responses.UserRegisterResponseDto;
import com.code.free.security.AuthUtil;
import com.code.free.utilities.ApiResult;
import com.code.free.utilities.exceptions.DuplicateEmailException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final AuthUtil authUtil;
    private final UserRepo userRepo;
    private final Config config;

    public ApiResult<LoginResponseDto> login(LoginRequestDto request) {
        String username = request.getUsername();
        String password = request.getPassword();
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(username, password));

        UserEntity user = (UserEntity) authentication.getPrincipal();
        String token = authUtil.generateAccessToken(user);

        return CustomResponse.success(new LoginResponseDto(token, user.getId()), "Login Successful", HttpStatus.OK);
    }

    public ApiResult<UserRegisterResponseDto> registerUser(LoginRequestDto request) {
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

        return CustomResponse.success(new UserRegisterResponseDto(user.getId(), user.getUsername()),
                "User registered successfully", HttpStatus.CREATED);
    }

}
