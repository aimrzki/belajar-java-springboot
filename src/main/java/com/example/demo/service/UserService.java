package com.example.demo.service;

import com.example.demo.model.Task;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Size;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;
    private final BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();

    public User addUser(User user){
        user.setUserId(UUID.randomUUID().toString().split("-")[0]);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return repository.save(user);
    }

    public List<User> getAllUsers(){
        return repository.findAll();
    }

    public User getUserById(String userId){
        return repository.findById(userId).get();
    }
    public List<User> getUserByUsername(String userName){
        return repository.findByUserName(userName);
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
}

