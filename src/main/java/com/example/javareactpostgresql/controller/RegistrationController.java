package com.example.javareactpostgresql.controller;

import com.example.javareactpostgresql.model.User;
import com.example.javareactpostgresql.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RegistrationController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/register")
    public String showRegistrationForm() {
        return "register"; // Corresponds to the register.html template
    }

    @PostMapping("/register")
    public String registerUser(
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            @RequestParam("accountType") String accountType,
            @RequestParam("linkedin") String linkedin,
            Model model
    ) {
        if (userRepository.findByEmail(email).isPresent()) {
            model.addAttribute("error", "Email is already in use");
            return "register";
        }

        User user = new User();
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password)); // Hash the password
        user.setAccountType(accountType);
        user.setLinkedin(linkedin);
        userRepository.save(user);

        model.addAttribute("success", "User registered successfully!");
        return "register";
    }
}
