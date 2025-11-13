package com.code.free.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.code.free.entities.UserEntity;
import com.code.free.repositories.UserRepo;
import com.code.free.requests.UserRegisterRequest;
import com.code.free.responses.UserRegisterResponse;
import com.code.free.security.JwtTokenProvider;

@Service
public class UserService {

    private UserRepo userRepo;

    private AuthenticationManager authenticationManager;

    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    public UserRegisterResponse registerUser(UserRegisterRequest user) {
        String name = user.getName();
        String email = user.getEmail();
        String password = user.getPassword();

        Boolean userExists = userRepo.existsByEmail(email);
        if (userExists) {

        }

        UserEntity newUser = new UserEntity();
        newUser.setName(name);
        newUser.setEmail(email);
        newUser.setPassword(passwordEncoder.encode(password));
        UserEntity newCreatedUser = userRepo.save(newUser);

        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(email, password);
        Authentication auth = authenticationManager.authenticate(authToken);
        SecurityContextHolder.getContext().setAuthentication(auth);

        String jwtToken = jwtTokenProvider.generateJWTToken(auth);

        UserRegisterResponse response = new UserRegisterResponse();
        response.setEmail(newUser.getEmail());
        response.setName(newUser.getName());
        response.setAccessToken("Bearer " + jwtToken);
        response.setId(newCreatedUser.getId());

        return response;
    }

}
