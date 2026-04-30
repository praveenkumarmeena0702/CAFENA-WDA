package com.cafena.service;

import com.cafena.model.LoginRequest;
import com.cafena.model.User;
import com.cafena.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Register a new user.
     * Throws IllegalArgumentException if email already exists.
     */
    public User register(LoginRequest req) {
        if (userRepository.existsByEmail(req.getEmail())) {
            throw new IllegalArgumentException("Email already registered: " + req.getEmail());
        }

        User user = User.builder()
                .username(req.getUsername())
                .email(req.getEmail())
                .password(req.getPassword())   // In production: hash with BCrypt
                .phone(req.getPhone())
                .address(req.getAddress())
                .build();

        return userRepository.save(user);
    }

    /**
     * Basic login — finds user by email and matches password.
     */
    public User login(String email, String password) {
        return userRepository.findByEmail(email)
                .filter(u -> u.getPassword().equals(password))
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
