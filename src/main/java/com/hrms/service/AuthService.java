package com.hrms.service;

import com.hrms.model.Employee;
import com.hrms.model.Role;
import com.hrms.repository.EmployeeRepository;
import com.hrms.security.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthService(
            EmployeeRepository employeeRepository,
            PasswordEncoder passwordEncoder,
            JwtUtil jwtUtil
    ) {
        this.employeeRepository = employeeRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    // =========================
    // LOGIN
    // =========================
    public String login(String email, String password) {

    Employee employee = employeeRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("Invalid credentials"));

    if (!passwordEncoder.matches(password, employee.getPassword())) {
        throw new RuntimeException("Invalid credentials");
    }

    // 🔒 FIRST LOGIN → DO NOT THROW EXCEPTION
    if (employee.isFirstLogin()) {
        return null;
    }

    return jwtUtil.generateToken(
            employee.getId(),
            employee.getRole().name()
    );
}


    // =========================
    // INITIAL ADMIN BOOTSTRAP
    // =========================
    public void createInitialAdminIfNotExists() {

        if (employeeRepository.findByEmail("admin@gmail.com").isPresent()) {
            return;
        }

        Employee admin = new Employee();
        admin.setName("Admin");
        admin.setEmail("admin@gmail.com");
        admin.setPassword(passwordEncoder.encode("admin123"));
        admin.setRole(Role.ADMIN);
        admin.setStatus("ACTIVE");
        admin.setFirstLogin(false); // 🔑 ADMIN SHOULD NOT RESET

        employeeRepository.save(admin);
    }
}
