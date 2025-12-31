package com.hrms.controller;

import com.hrms.model.DashboardSummary;
import com.hrms.service.DashboardService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    private final DashboardService service;

    public DashboardController(DashboardService service) {
        this.service = service;
    }

    // 🔹 TEST ENDPOINT
    @GetMapping("/test")
    public String test() {
        return "OK";
    }

    // 🔹 REAL ENDPOINT
    @GetMapping("/summary/{id}")
    public DashboardSummary getDashboard(@PathVariable Long id) {
        return service.getDashboard(id);
    }
}
