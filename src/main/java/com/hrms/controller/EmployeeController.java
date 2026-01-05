package com.hrms.controller;

import com.hrms.model.Employee;
import com.hrms.service.EmployeeService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // =========================
    // EMPLOYEE + ADMIN
    // Get logged-in user profile
    // =========================
    @GetMapping("/me")
    public Employee getMyProfile(Authentication authentication) {
        Long employeeId = (Long) authentication.getPrincipal();
        return employeeService.getById(employeeId);
    }

    // =========================
    // ADMIN: Get all employees
    // =========================
    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    // =========================
    // ADMIN: Add new employee
    // =========================
    @PostMapping
    public Employee addEmployee(@RequestBody CreateEmployeeRequest request) {
        return employeeService.createEmployee(
                request.name,
                request.email,
                request.password
        );
    }

    // =========================
    // REQUEST BODY (INTERNAL)
    // =========================
    static class CreateEmployeeRequest {
        public String name;
        public String email;
        public String password;
    }
}
