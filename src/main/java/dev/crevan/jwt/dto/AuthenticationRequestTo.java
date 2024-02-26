package dev.crevan.jwt.dto;

import lombok.Data;

@Data
public class AuthenticationRequestTo {

    private String username;
    private String password;
}
