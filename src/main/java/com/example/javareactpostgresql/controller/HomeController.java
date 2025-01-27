package com.example.javareactpostgresql.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;


@Controller
public class HomeController {

    @GetMapping("/home")
    public String home(Principal principal, Model model) {
        // Get the logged-in user details
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName(); // Get username
        model.addAttribute("username", username);

        // Add the logged-in user's name to the model using diffrent object
//        model.addAttribute("username", principal.getName());


        // Return the name of the view to display
        return "home"; // Spring will look for a "home.html" file in the "templates" folder
    }
}
