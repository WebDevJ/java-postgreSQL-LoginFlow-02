package com.example.javareactpostgresql.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.User;
import java.util.Collections;

import com.example.javareactpostgresql.service.CustomUserDetailsService;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;



@Configuration
public class SecurityConfig {

    private final CustomUserDetailsService userDetailsService;

    public SecurityConfig(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // BCrypt is a secure hashing algorithm
    }

    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder())
                .and()
                .build();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .requestMatchers("/register", "/register.html").permitAll() // Allow public access to registration
                .anyRequest().authenticated() // Protect all other endpoints
                .and()
                .formLogin()
                .defaultSuccessUrl("/home", true) // Redirect to /home after logi
                .and()
                .logout().logoutSuccessUrl("/login")
                .and()
                .headers(headers -> headers
                        .contentSecurityPolicy(csp -> csp
                                .policyDirectives(
                                        "connect-src 'self' http://localhost:8080/; " +
                                                "script-src 'self'; " +
                                                "script-src 'self' 'unsafe-inline';")
                        )
                );

        System.out.println("SecurityFilterChain configuration applied");

        return http.build();
    }
    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails user = User.builder()
                .username("testuser")
                .password(passwordEncoder().encode("testpassword"))
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(Collections.singleton(user));
    }

}
