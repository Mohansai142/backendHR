package com.hrms.service;

import com.hrms.model.LeaveRequest;
import com.hrms.repository.LeaveRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaveService {

    private final LeaveRepository repo;

    public LeaveService(LeaveRepository repo) {
        this.repo = repo;
    }

    public List<LeaveRequest> getLeavesForEmployee(Long employeeId) {
        return repo.findByEmployeeId(employeeId);
    }
}
