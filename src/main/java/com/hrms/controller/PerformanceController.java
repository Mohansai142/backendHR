package com.hrms.controller;

import com.hrms.model.Performance;
import com.hrms.service.PerformanceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/performance")
@CrossOrigin(origins = "http://localhost:3000")
public class PerformanceController {

    private final PerformanceService service;

    public PerformanceController(PerformanceService service) {
        this.service = service;
    }

    // 🔒 Only logged-in employee
    @GetMapping("/{employeeId}")
    public List<Performance> getMyPerformance(@PathVariable Long employeeId) {
        return service.getPerformanceForEmployee(employeeId);
    }
}
