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

    // Serve the registration page
//    @GetMapping("/register")
//    public RedirectView showRegistrationForm() {
//        return new RedirectView("/register.html"); // This will serve static/register.html
//    }

    @GetMapping("/register")
    public String showRegistrationForm() {
        return "register";
    }

    // Handle form submission
    @PostMapping("/register")
    public String registerUser(
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            Model model
    ) {
        if (userRepository.findByEmail(email).isPresent()) {
            model.addAttribute("error", "Email is already in use");
            return "register";
        }

        User user = new User();
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);

        model.addAttribute("success", "User registered successfully!");
        return "register";
    }
}
