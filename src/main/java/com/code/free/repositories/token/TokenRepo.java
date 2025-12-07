package com.code.free.repositories.token;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.code.free.entities.token.TokenEntity;

@Repository
public interface TokenRepo extends JpaRepository<TokenEntity,Long> {
    
    Optional<TokenEntity> findByEmailAndOtp(String email, Integer otp);

    Long deleteByEmail(String email);
}
