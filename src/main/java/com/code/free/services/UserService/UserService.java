package com.code.free.services.UserService;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.code.free.entities.user.UserView;
import com.code.free.repositories.user.UserRepo;
import com.code.free.repositories.user.UserViewRepo;
import com.code.free.responses.CustomResponse;
import com.code.free.responses.ValidUsernameResponseDto;
import com.code.free.utilities.ApiResult;
import com.code.free.utilities.OpenAIUtils;


import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepo userRepo;
    private final UserViewRepo userViewRepo;
    private final OpenAIUtils openAIUtils;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return userRepo.findByUsername(username).orElseThrow();
    }

    public ApiResult<ValidUsernameResponseDto> validateUsername(String username) {
        UserView user = userViewRepo.findByUsername(username).orElse(null);
        ValidUsernameResponseDto response = new ValidUsernameResponseDto();
        String message = "";
        if (user != null) {
            response.setIsExist(true);
            List<String> suggestions = List.of(username + "123", username + "_user", "the_" + username);
            response.setSuggestions(suggestions);
            openAIUtils.aiUse();
            
            message = "Username is already taken. Here are some suggestions.";
        }
        else {
            response.setIsExist(false);
            message = "Username is available.";
        }
        return CustomResponse.success(response,message,HttpStatus.OK); 
    }
}
