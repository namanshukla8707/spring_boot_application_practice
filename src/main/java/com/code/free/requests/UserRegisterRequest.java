package com.code.free.requests;
import jakarta.persistence.Column;
import lombok.Data;

@Data
public class UserRegisterRequest {
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;
}
