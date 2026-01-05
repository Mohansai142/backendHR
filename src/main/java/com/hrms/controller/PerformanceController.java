package com.hrms.controller;

import com.hrms.model.Performance;
import com.hrms.service.PerformanceService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/performance")
public class PerformanceController {

    private final PerformanceService performanceService;

    public PerformanceController(PerformanceService performanceService) {
        this.performanceService = performanceService;
    }

    // =========================
    // EMPLOYEE: View own performance
    // =========================
    @GetMapping
    public List<Performance> getMyPerformance(Authentication authentication) {
        Long employeeId = (Long) authentication.getPrincipal();
        return performanceService.getPerformanceForEmployee(employeeId);
    }
}
