package com.example.demoLog4j.dto.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {
    @NotEmpty(message = "Username is required!")
    private String username;
    @NotEmpty(message = "Password is required!")
    private String password;
}
