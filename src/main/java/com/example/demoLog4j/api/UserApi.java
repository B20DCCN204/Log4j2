package com.example.demoLog4j.api;

import com.example.demoLog4j.dto.UserDto;
import com.example.demoLog4j.dto.request.LoginRequest;
import com.example.demoLog4j.dto.response.UserResponse;
import com.example.demoLog4j.mapper.UserMapper;
import com.example.demoLog4j.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserApi {

    private Logger logger = LogManager.getLogger(UserApi.class);

    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserDto userDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            List<String> errors = new ArrayList<>();
            for(FieldError error : bindingResult.getFieldErrors()){
                errors.add(error.getField() + ":" + error.getDefaultMessage());
                logger.error("Bad request: " + error.getDefaultMessage());
            }
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
        UserResponse user = userService.saveUser(userDto);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest, HttpSession session, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            List<String> errors = new ArrayList<>();
            for(FieldError error : bindingResult.getFieldErrors()){
                errors.add(error.getField() + ":" + error.getDefaultMessage());
                logger.error("Bad request: " + error.getDefaultMessage());
            }
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
        UserDto existingUser = userService.findByUsername(loginRequest.getUsername());

        if (existingUser == null || !userService.checkPassword(existingUser, loginRequest.getPassword())) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Invalid email or password");
            logger.warn("Invalid email or password");
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
        session.setAttribute("user", existingUser);
        return new ResponseEntity<>("Login successfully!!", HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id){
        UserResponse user = userService.getUserById(id);
        if(user == null){
            Map<String, String> response = new HashMap<>();
            response.put("message", "User doesn't exist");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUser(HttpSession session) {
        UserDto user = (UserDto) session.getAttribute("user");
        if (user == null) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "No user logged in");
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
        UserResponse userResponse = userMapper.toUserReponse(user);
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Delete user successfully!");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
