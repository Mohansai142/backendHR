package com.hrms.service;

import com.hrms.model.Employee;
import com.hrms.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final EmployeeRepository repo;

    public AuthService(EmployeeRepository repo) {
        this.repo = repo;
    }

    public Employee login(String email, String password) {

        // 1️⃣ Find employee by email
        Employee emp = repo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));

        // 2️⃣ Check password (PLAIN TEXT for now)
        // ⚠️ Later this will be BCrypt
        if (!emp.getPassword().equals(password)) {
            throw new RuntimeException("Invalid credentials");
        }

        // 3️⃣ Check account status
        if (!emp.getStatus().equalsIgnoreCase("Active")) {
            throw new RuntimeException("Account is disabled");
        }

        // 4️⃣ Login successful → return employee
        return emp;
    }
}
