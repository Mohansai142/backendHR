package com.hrms.controller;

import com.hrms.dto.LoginRequest;
import com.hrms.model.Employee;
import com.hrms.repository.EmployeeRepository;
import com.hrms.service.AuthService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;
    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthController(
            AuthService authService,
            EmployeeRepository employeeRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.authService = authService;
        this.employeeRepository = employeeRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // =========================
    // LOGIN
    // =========================
    @PostMapping("/login")
public ResponseEntity<?> login(@RequestBody LoginRequest request) {

    try {
        String token = authService.login(
                request.getEmail(),
                request.getPassword()
        );

        // 🔒 FIRST LOGIN CASE
        if (token == null) {
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body("FIRST_LOGIN_PASSWORD_RESET_REQUIRED");
        }

        return ResponseEntity.ok(Map.of("token", token));

    } catch (RuntimeException ex) {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body("Invalid email or password");
    }
}


    // =========================
    // RESET PASSWORD (FIRST LOGIN)
    // =========================
    @PostMapping("/reset-password")
    public Map<String, String> resetPassword(@RequestBody Map<String, String> request) {

        String email = request.get("email");
        String oldPassword = request.get("oldPassword");
        String newPassword = request.get("newPassword");

        Employee employee = employeeRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(oldPassword, employee.getPassword())) {
            throw new RuntimeException("Invalid old password");
        }

        employee.setPassword(passwordEncoder.encode(newPassword));
        employee.setFirstLogin(false);

        employeeRepository.save(employee);

        return Map.of("message", "Password reset successful");
    }
}
