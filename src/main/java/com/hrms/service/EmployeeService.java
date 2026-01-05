package com.hrms.service;

import com.hrms.model.Employee;
import com.hrms.model.Role;
import com.hrms.repository.EmployeeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder passwordEncoder;

    public EmployeeService(EmployeeRepository employeeRepository,
                           PasswordEncoder passwordEncoder) {
        this.employeeRepository = employeeRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // =========================
    // Get employee by ID
    // =========================
    public Employee getById(Long employeeId) {
        return employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Employee not found"
                ));
    }

    // =========================
    // ADMIN: Get all employees
    // =========================
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    // =========================
    // ADMIN: Create employee
    // =========================
    public Employee createEmployee(String name, String email, String password) {

        if (employeeRepository.existsByEmail(email)) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Email already exists"
            );
        }

        Employee employee = new Employee();
        employee.setName(name);
        employee.setEmail(email);

        // ✅ HASH PASSWORD BEFORE SAVING
        employee.setPassword(passwordEncoder.encode(password));

        employee.setRole(Role.EMPLOYEE);
        employee.setStatus("ACTIVE");

        return employeeRepository.save(employee);
    }
}
