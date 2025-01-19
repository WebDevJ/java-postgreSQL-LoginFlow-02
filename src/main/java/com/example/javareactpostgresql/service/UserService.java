package com.example.javareactpostgresql.service;

import com.example.javareactpostgresql.model.User;
import com.example.javareactpostgresql.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public ResponseEntity<?> registerUser(User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("Email already in use");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return ResponseEntity.ok("User registered successfully");
    }

    public ResponseEntity<?> loginUser(User user) {
        var existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser.isPresent() && passwordEncoder.matches(user.getPassword(), existingUser.get().getPassword())) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Login successful");
            response.put("role", existingUser.get().getRole());
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.badRequest().body("Invalid credentials");
    }

    public ResponseEntity<?> getDashboard(Principal principal) {
        var user = userRepository.findByEmail(principal.getName());
        if (user.isPresent()) {
            Map<String, Object> dashboardData = new HashMap<>();
            if ("recruiter".equals(user.get().getRole())) {
                dashboardData.put("pages", "Blog, Portfolio, Resume");
            } else {
                dashboardData.put("pages", "Portfolio, Blog");
            }
            return ResponseEntity.ok(dashboardData);
        }
        return ResponseEntity.badRequest().body("User not found");
    }
}