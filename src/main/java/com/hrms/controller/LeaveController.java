package com.hrms.controller;

import com.hrms.model.LeaveRequest;
import com.hrms.service.LeaveService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/leaves")
public class LeaveController {

    private final LeaveService leaveService;

    public LeaveController(LeaveService leaveService) {
        this.leaveService = leaveService;
    }

    // =========================
    // EMPLOYEE: View own leaves
    // =========================
    @GetMapping
    public List<LeaveRequest> getMyLeaves(Authentication authentication) {
        Long employeeId = (Long) authentication.getPrincipal();
        return leaveService.getLeavesForEmployee(employeeId);
    }

    // =========================
    // EMPLOYEE: Apply for leave
    // =========================
    @PostMapping
    public void applyLeave(
            @RequestBody ApplyLeaveRequest request,
            Authentication authentication
    ) {
        Long employeeId = (Long) authentication.getPrincipal();

        leaveService.applyLeave(
                employeeId,
                request.startDate,
                request.endDate,
                request.reason,
                request.type
        );
    }

    // =========================
    // REQUEST BODY (INTERNAL)
    // =========================
    static class ApplyLeaveRequest {
        public LocalDate startDate;
        public LocalDate endDate;
        public String reason;
        public String type;
    }
}
