package com.code.free.services.AuthService;

import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.code.free.configuration.Config;
import com.code.free.entities.UserEntity;
import com.code.free.repositories.UserRepo;
import com.code.free.requests.LoginRequestDto;
import com.code.free.responses.LoginResponseDto;
import com.code.free.responses.UserRegisterResponseDto;
import com.code.free.security.AuthUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final AuthUtil authUtil;
    private final UserRepo userRepo;
    private final Config config;

    public LoginResponseDto login(LoginRequestDto request) {
        String username = request.getUsername();
        String password = request.getPassword();
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(username, password));

        UserEntity user = (UserEntity) authentication.getPrincipal();
        String token = authUtil.generateAccessToken(user);

        return new LoginResponseDto(token, user.getId());
    }

    public UserRegisterResponseDto registerUser(LoginRequestDto request) {
        UserEntity user = userRepo.findByUsername(request.getUsername()).orElse(null);
        if (user != null) {
            throw new IllegalArgumentException("Username already exists");
        }

        UserEntity newUser = UserEntity.builder().username(request.getUsername())
                .password(config.passwordEncoder().encode(request.getPassword())).email(request.getEmail())
                .build();

        if (request.getRole() != null) {
            newUser.setRole(request.getRole());
        }

        user = userRepo.save(newUser);

        return new UserRegisterResponseDto(user.getId(), user.getUsername());
    }

}
