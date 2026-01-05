package com.hrms.controller;

import com.hrms.repository.EmployeeRepository;
import com.hrms.repository.LeaveRequestRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/admin/dashboard")
@PreAuthorize("hasRole('ADMIN')")
public class AdminDashboardController {

    private final EmployeeRepository employeeRepository;
    private final LeaveRequestRepository leaveRepository;

    public AdminDashboardController(
            EmployeeRepository employeeRepository,
            LeaveRequestRepository leaveRepository
    ) {
        this.employeeRepository = employeeRepository;
        this.leaveRepository = leaveRepository;
    }

    @GetMapping
    public Map<String, Object> getAdminDashboard() {

        Map<String, Object> data = new HashMap<>();
        data.put("totalEmployees", employeeRepository.count());
        data.put("pendingLeaves", leaveRepository.countByStatus("Pending"));
        data.put("approvedLeaves", leaveRepository.countByStatus("Approved"));

        return data;
    }
}
