package com.code.free.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.code.free.entities.UserEntity;

@Repository
public interface UserRepo extends JpaRepository<UserEntity, Long> {
    Boolean existsByEmail(String email);

}
