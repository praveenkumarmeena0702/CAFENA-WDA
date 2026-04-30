package com.cafena.controller;

import com.cafena.model.LoginRequest;
import com.cafena.model.User;
import com.cafena.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    /**
     * POST /api/auth/register
     * Body: { username, email, password, phone }
     */
    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody LoginRequest req) {
        try {
            User saved = authService.register(req);
            return ResponseEntity.ok(Map.of(
                "id",       saved.getId(),
                "username", saved.getUsername(),
                "email",    saved.getEmail(),
                "phone",    saved.getPhone(),
                "address",  saved.getAddress(),
                "message",  "Registration successful"
            ));
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    /**
     * POST /api/auth/login
     * Body: { email, password }
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> body) {
        String email    = body.get("email");
        String password = body.get("password");
        try {
            User user = authService.login(email, password);
            return ResponseEntity.ok(Map.of(
                "id",       user.getId(),
                "username", user.getUsername(),
                "email",    user.getEmail(),
                "phone",    user.getPhone(),
                "address",  user.getAddress()
            ));
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(401).body(ex.getMessage());
        }
    }

    /**
     * GET /api/auth/users  – for the admin/debug table
     */
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(authService.getAllUsers());
    }
}
