package org.manu.controller;

import org.manu.models.User;
import org.manu.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user ) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

        return ResponseEntity.ok("User registered");

    }

    @GetMapping("/register") // Ou @PostMapping, selon votre besoin
    public String showRegistrationForm() {
        // Logique pour afficher la page d'inscription (si @Controller)
        // Ou retourner un message (si @RestController)
        return "registration page"; // ou "register" pour une vue Thymeleaf
    }

    @GetMapping("/public")
    public String publicPage() {
        return "This is a public page (accessible to everyone).";
    }

    @GetMapping("/user/dashboard")
    public String userDashboard() {
        return "Welcome to the User Dashboard! You have ROLE_USER or ROLE_ADMIN.";
    }

    @GetMapping("/admin/settings")
    public String adminSettings() {
        return "Welcome to the Admin Settings! You have ROLE_ADMIN.";
    }

    @GetMapping("/admin/users")
    public String adminUsers() {
        return "List of users (Admin only).";
    }
}
