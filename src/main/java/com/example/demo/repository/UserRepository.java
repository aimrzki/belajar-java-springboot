package com.example.demo.repository;

import com.example.demo.model.Task;
import com.example.demo.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends MongoRepository<User,String> {
    @Query("{userName: ?0}")
    List<User> findByUserName(String userName);
    List<User> findByEmail(String email);
    List<User> findByPhoneNumber(String phoneNumber);
}
