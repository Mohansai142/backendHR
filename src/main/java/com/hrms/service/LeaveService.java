package com.hrms.service;

import com.hrms.model.LeaveRequest;
import com.hrms.repository.LeaveRequestRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaveService {

    private final LeaveRequestRepository repo;

    public LeaveService(LeaveRequestRepository repo) {
        this.repo = repo;
    }

    public List<LeaveRequest> getLeavesForEmployee(Long employeeId) {
        return repo.findByEmployeeId(employeeId);
    }
}
