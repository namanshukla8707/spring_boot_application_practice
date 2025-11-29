package com.code.free.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor;

import static com.code.free.utilities.globalEnums.RoleType.*;

@Configuration
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final JwtAuthFilter jwtAuthFilter;
    private final FilterChainAccessDeniedExpections filterChainAccessDeniedExpections;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrfConfig -> csrfConfig.disable())
                .sessionManagement(
                        sessionConfig -> sessionConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth.requestMatchers("/api/v1/auth/**").permitAll()
                        .requestMatchers("/api/v1/admin/**").hasRole(ADMIN.name())
                        .requestMatchers("/api/v1/courses/**").hasRole(TEACHER.name())
                        .anyRequest().authenticated())
                        .exceptionHandling(expection->expection.accessDeniedHandler(filterChainAccessDeniedExpections))
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

}