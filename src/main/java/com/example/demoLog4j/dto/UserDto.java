package com.example.demoLog4j.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    private Long id;
    @NotEmpty(message = "Fullname is required")
    private String fullname;
    @NotEmpty(message = "Username is required")
    private String username;
    @NotEmpty(message = "Password is required")
    @Size(min = 6, message = "Password should have at least 6 characters")
    private String password;
    @NotEmpty(message = "Phone number is required")
    @Size(min = 10, message = "Phone number should have at least 10 characters")
    private String phone;
    private String email;

}
