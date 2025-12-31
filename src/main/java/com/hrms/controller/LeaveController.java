package com.hrms.controller;

import com.hrms.model.LeaveRequest;
import com.hrms.service.LeaveService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/leaves")
@CrossOrigin(origins = "http://localhost:3000")
public class LeaveController {

    private final LeaveService service;

    public LeaveController(LeaveService service) {
        this.service = service;
    }

    // 🔒 Only returns leaves of that employee
    @GetMapping("/{employeeId}")
    public List<LeaveRequest> getMyLeaves(@PathVariable Long employeeId) {
        return service.getLeavesForEmployee(employeeId);
    }
}
