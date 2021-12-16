package com.breezelab.sample.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class User {
    private int idx;
    private String username;
    private String password;
    private String email;
    private String role;
    private LocalDateTime createdAt;

    private String provider;
    private String providerId;
}
