package com.librarylease.api.controller;

import com.librarylease.api.auth.ApplicationUser;
import com.librarylease.api.auth.UserService;
import com.librarylease.api.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;


@RequestMapping("/user")
@RestController
public class UserController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public ResponseEntity registerUser(UserDto userDto) {
        if (userService.isEmailTaken(userDto.getEmail())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email is already in use");
        } else if (userService.isUserNameTaken(userDto.getUsername())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username is already in use");
        } else {
            // Add the user to the database
            ApplicationUser applicationUser = new ApplicationUser(
                    userDto.getEmail(),
                    userDto.getUsername(),
                    passwordEncoder.encode(userDto.getPassword()),
                    Collections.emptySet(),
                    true,
                    true,
                    true,
                    true);
            userService.createUser(applicationUser);
            return ResponseEntity.status(HttpStatus.CREATED).body("User Registration Successful");
        }
    }
}
