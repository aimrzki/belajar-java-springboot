package com.example.demo.service;

import com.example.demo.model.Task;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Size;
import java.util.*;

import com.example.demo.dto.LoginRequest;
import com.example.demo.util.JwtUtil;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;
    private final BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();

    public User addUser(User user){
        user.setUserId(UUID.randomUUID().toString().split("-")[0]);
        if (user.getFirstName().trim().isEmpty()) {
            throw new IllegalArgumentException("First name cannot be blank");
        }

        // Validate first name
        if (user.getFirstName().trim().isEmpty()) {
            throw new IllegalArgumentException("First name cannot be blank");
        }

        // Check if email is already registered
        if (isEmailTaken(user.getEmail())) {
            throw new IllegalArgumentException("Email is already registered");
        }

        // Check if phone number is already registered
        if (isPhoneNumberTaken(user.getPhoneNumber())) {
            throw new IllegalArgumentException("Phone number is already registered");
        }


        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return repository.save(user);
    }


    private boolean isEmailTaken(String email) {
        return !repository.findByEmail(email).isEmpty();
    }

    private boolean isPhoneNumberTaken(String phoneNumber) {
        return !repository.findByPhoneNumber(phoneNumber).isEmpty();
    }

    public List<User> getAllUsers(){
        return repository.findAll();
    }

    public List<User> getUserByUsername(String userName){
        return repository.findByUserName(userName);
    }

    public User getUserById(String userId){
        return repository.findById(userId).get();
    }

    public User editUserById(String userId, User userRequest){
        User existingUser = repository.findById(userId).get();
        if(userRequest.getFirstName()!=null){
            existingUser.setFirstName(userRequest.getFirstName());
        }
        if (userRequest.getLastName()!=null){
            existingUser.setLastName(userRequest.getLastName());
        }
        if(userRequest.getEmail()!=null){
            existingUser.setEmail(userRequest.getEmail());
        }
        if (userRequest.getPhoneNumber()!=null){
            existingUser.setPhoneNumber(userRequest.getPhoneNumber());
        }
        if (userRequest.getPassword()!=null){
            existingUser.setPassword(userRequest.getPassword());
        }
        return repository.save(existingUser);
    }

    public String deleteUserById(String userId){
        repository.deleteById(userId);
        return userId + "user delete from dashboard";
    }

    @Autowired
    private JwtUtil jwtUtil;

    public String loginUser(LoginRequest loginRequest) throws Exception {
        Optional<User> userOpt = repository.findByUserName(loginRequest.getUserName()).stream().findFirst();

        if (userOpt.isPresent()) {
            User user = userOpt.get();
            if (passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
                return jwtUtil.generateToken(user.getUserName());
            } else {
                throw new Exception("Invalid credentials");
            }
        } else {
            throw new Exception("User not found");
        }
    }
}

