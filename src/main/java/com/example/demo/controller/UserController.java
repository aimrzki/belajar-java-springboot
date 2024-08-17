package com.example.demo.controller;


import com.example.demo.model.Task;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User signUpUser(@Valid @RequestBody User user){
        return service.addUser(user);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<User> getAllUser(){
        return service.getAllUsers();
    }

    @GetMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public User getUserById(@PathVariable String userId){
        return service.getUserById(userId);
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

}
