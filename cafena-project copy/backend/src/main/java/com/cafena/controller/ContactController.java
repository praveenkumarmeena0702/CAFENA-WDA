package com.cafena.controller;

import com.cafena.model.ContactMessage;
import com.cafena.repository.ContactMessageRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contact")
@CrossOrigin(origins = "http://localhost:5173")
public class ContactController {

    private final ContactMessageRepository contactMessageRepository;

    public ContactController(ContactMessageRepository contactMessageRepository) {
        this.contactMessageRepository = contactMessageRepository;
    }

    @PostMapping
    public ResponseEntity<?> submitContactMessage(@RequestBody ContactMessage contactMessage) {
        try {
            ContactMessage savedMessage = contactMessageRepository.save(contactMessage);
            return ResponseEntity.ok(savedMessage);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to save contact message: " + e.getMessage());
        }
    }
}
