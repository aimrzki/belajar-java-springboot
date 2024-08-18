package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@Document(collection = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class User {
    @Id
    private String userId;
    @NotBlank(message = "First name cannot be blank")
    @Size(min = 1,max = 100, message = "firstName must be between 1 and 100 characters")
    private String firstName;
    @Size(max = 100, message = "lastName max 100 characters")
    private String lastName;
    @Email(message = "Email should be valid")
    private String email;
    @Pattern(regexp ="^[0-9]{10,13}$", message = "Phone number must be between 10 and 13 digits")
    private String phoneNumber;
    @Size(min = 1,max = 15, message = "userName must be between 1 and 15 characters")
    private String userName;
    @Size(min = 5,max = 100, message = "password must be between 1 and 100 characters")
    private String password;
}
