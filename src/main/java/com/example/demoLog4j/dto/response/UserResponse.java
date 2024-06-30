package com.example.demoLog4j.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponse {
    private Long id;
    private String fullname;
    private String username;
    @JsonIgnore
    private String password;
    private String phone;
    private String email;
}
