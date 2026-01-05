package com.hrms.controller;

import com.hrms.model.LeaveRequest;
import com.hrms.service.LeaveService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/leaves")
public class AdminLeaveApprovalController {

    private final LeaveService leaveService;

    public AdminLeaveApprovalController(LeaveService leaveService) {
        this.leaveService = leaveService;
    }

    @GetMapping("/pending")
    public List<LeaveRequest> getPendingLeaves() {
        return leaveService.getPendingLeaves();
    }

    @PutMapping("/{id}/approve")
    public void approveLeave(@PathVariable Long id) {
        leaveService.approveLeave(id);
    }

    @PutMapping("/{id}/reject")
    public void rejectLeave(@PathVariable Long id) {
        leaveService.rejectLeave(id);
    }
}
