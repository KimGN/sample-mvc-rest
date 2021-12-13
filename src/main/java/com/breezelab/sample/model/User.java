package com.breezelab.sample.model;

import lombok.Data;

@Data
public class User {
    private int idx;
    private String account;
    private String password;
    private String email;
    private String role;
}
