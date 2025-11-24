package com.code.free.configuration;

import org.apache.tomcat.util.bcel.classfile.Constant;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.code.free.utilities.Constants;
import com.google.genai.Client;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class Config {

    private final Constants constants;

    // @Bean
    // public ModelMapper modelMapper() {
    // return new ModelMapper();
    // }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public Client geminiConfig() {
        return Client.builder().apiKey(constants.getGeminiApiKey()).build();
    }

    // @Bean
    // public UserDetailsService userDetailsService() {
    // UserDetails user_1 =
    // User.withUsername("admin").password(passwordEncoder().encode("root"))
    // .roles(ADMIN.name())
    // .build();

    // UserDetails user_2 =
    // User.withUsername("user").password(passwordEncoder().encode("root"))
    // .roles(USER.name())
    // .build();

    // return new InMemoryUserDetailsManager(user_1, user_2);
    // }
}
