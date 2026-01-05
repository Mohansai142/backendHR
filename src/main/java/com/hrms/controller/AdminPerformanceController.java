package com.hrms.controller;

import com.hrms.service.PerformanceService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/performance")
public class AdminPerformanceController {

    private final PerformanceService performanceService;

    public AdminPerformanceController(PerformanceService performanceService) {
        this.performanceService = performanceService;
    }

    // =========================
    // ADMIN: Add performance review
    // =========================
    @PostMapping
    public void addPerformance(@RequestBody AddPerformanceRequest request) {
        performanceService.addPerformance(
                request.employeeId,
                request.reviewPeriod,
                request.rating,
                request.comments
        );
    }

    // =========================
    // REQUEST BODY (INTERNAL)
    // =========================
    static class AddPerformanceRequest {
        public Long employeeId;
        public String reviewPeriod; // e.g. "Q1-2026"
        public int rating;          // 1–5
        public String comments;
    }
}
