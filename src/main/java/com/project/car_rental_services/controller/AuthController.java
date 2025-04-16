package com.project.car_rental_services.controller;

import com.project.car_rental_services.modal.Admin;
import com.project.car_rental_services.repository.AdminRepository;
import com.project.car_rental_services.security.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthController(AdminRepository adminRepository, PasswordEncoder passwordEncoder) {
        this.adminRepository = adminRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Admin newAdmin) {
        if (adminRepository.findByUsername(newAdmin.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().body("Username already exists.");
        }

        Admin admin = new Admin();
        admin.setUsername(newAdmin.getUsername());
        admin.setPassword(passwordEncoder.encode(newAdmin.getPassword()));

        adminRepository.save(admin);

        return ResponseEntity.ok("Admin registered successfully.");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credentials) {
        String username = credentials.get("username");
        String password = credentials.get("password");

        Admin admin = adminRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Invalid username or password"));

        if (!passwordEncoder.matches(password, admin.getPassword())) {
            throw new BadCredentialsException("Invalid username or password");
        }

        // Generate JWT token or return success response
        String token = JwtUtil.generateToken(username);
        return ResponseEntity.ok(Map.of("token", token));
    }
}

