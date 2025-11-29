package com.code.free.repositories.token;

import org.springframework.data.jpa.repository.JpaRepository;

import com.code.free.entities.token.TokenEntity;

public interface TokenRepo extends JpaRepository<TokenEntity,Long> {
    
}
