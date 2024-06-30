package com.example.demoLog4j.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest {
    private Long id;
    @NotEmpty(message = "fullname is required")
    private String fullname;
    @NotEmpty(message = "username is required")
    private String username;
    @JsonIgnore
    private String password;
    @NotEmpty(message = "Phone number is required")
    private String phone;
    private String email;
}
