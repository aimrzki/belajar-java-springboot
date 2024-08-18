package com.example.demo.controller;


import com.example.demo.dto.ApiResponse;
import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.UserResponse;
import com.example.demo.model.Task;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import com.example.demo.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.View;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")

public class UserController {
    @Autowired
    private UserService service;
    @Autowired
    private View error;
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<User> signUpUser(@Valid @RequestBody User user){
        User createdUser = service.addUser(user);
        return new ApiResponse<>(201, "User successfully created", createdUser);
    }
    /*
    public User signUpUser(@Valid @RequestBody User user){
        return service.addUser(user);
    }
     */

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<List<User>> getAllUser(){
        List<User> getUser = service.getAllUsers();
        return new ApiResponse<>(200,"All user succesfuly reteirved",getUser);
    }
    /*
    public List<User> getAllUser(){
        return service.getAllUsers();
    }
     */

    /*
    @GetMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public User getUserById(@PathVariable String userId){
        return service.getUserById(userId);
    }
     */

    @GetMapping("/me")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<UserResponse> getUserByToken(@RequestHeader("Authorization") String token) {
        String jwtToken = token.substring(7);
        String username = jwtUtil.extractUsername(jwtToken);
        User user = service.getUserByUsername(username).stream().findFirst().orElse(null);

        if (user != null) {
            UserResponse userResponse = new UserResponse(
                    user.getUserId(),
                    user.getFirstName(),
                    user.getLastName(),
                    user.getEmail(),
                    user.getPhoneNumber(),
                    user.getUserName()
            );
            return new ApiResponse<>(200, "User data retrieved successfully", userResponse);
        } else {
            return new ApiResponse<>(404, "User not found", null);
        }
    }

    @GetMapping("/username/{userName}")
    @ResponseStatus(HttpStatus.OK)
    public List<User> getUserByUserName(@PathVariable String userName){
        return service.getUserByUsername(userName);
    }

    @PutMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public User editUserById(@PathVariable String userId,@RequestBody User user){
        return service.editUserById(userId,user);
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteUserById(@PathVariable String userId){
        return service.deleteUserById(userId);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<String>> loginUser(@RequestBody LoginRequest loginRequest) {
        try {
            String token = service.loginUser(loginRequest);
            return new ResponseEntity<>(new ApiResponse<>(200, "Login successful", token), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse<>(401, e.getMessage(), null), HttpStatus.UNAUTHORIZED);
        }
    }

}
