package com.example.javareactpostgresql.controller;

import com.example.javareactpostgresql.model.User;
import com.example.javareactpostgresql.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        return userService.registerUser(user);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User user) {
        return userService.loginUser(user);
    }

    @GetMapping("/dashboard")
    public ResponseEntity<?> getDashboard(Principal principal) {
        return userService.getDashboard(principal);
    }
}

