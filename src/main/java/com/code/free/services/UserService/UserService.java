package com.code.free.services.UserService;

import java.io.IOException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.code.free.entities.user.UserEntity;
import com.code.free.entities.user.UserView;
import com.code.free.repositories.user.UserRepo;
import com.code.free.repositories.user.UserViewRepo;
import com.code.free.responses.CustomResponse;
import com.code.free.responses.ValidUsernameResponseDto;
import com.code.free.utilities.ApiResult;
import com.code.free.utilities.GeminiUtils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepo userRepo;
    private final UserViewRepo userViewRepo;
    private final GeminiUtils geminiUtils;

    @Override
    public UserDetails loadUserByUsername(String identifier) throws UsernameNotFoundException {
        Boolean isEmail = identifier.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
        UserEntity user = new UserEntity();
        if (isEmail) {
            user = userRepo.findByEmail(identifier).orElseThrow();
        } else {
            user = userRepo.findByUsername(identifier).orElseThrow();
        }
        return user;
    }

    public ApiResult<ValidUsernameResponseDto> validateUsername(String username) throws IOException {
        UserView user = userViewRepo.findByUsername(username).orElse(null);
        ValidUsernameResponseDto response = new ValidUsernameResponseDto();
        String message = "";
        if (user != null) {
            response.setIsExist(true);
            List<String> suggestions = geminiUtils.usernameSuggestionByGemini(username);
            response.setSuggestions(suggestions);
            message = "Username is already taken. Here are some suggestions.";
        } else {
            response.setIsExist(false);
            message = "Username is available.";
        }
        return CustomResponse.success(response, message, HttpStatus.OK);
    }
}
