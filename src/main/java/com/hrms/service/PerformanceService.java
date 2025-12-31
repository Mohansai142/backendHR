package com.hrms.service;

import com.hrms.model.Performance;
import com.hrms.repository.PerformanceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PerformanceService {

    private final PerformanceRepository repo;

    public PerformanceService(PerformanceRepository repo) {
        this.repo = repo;
    }

    public List<Performance> getPerformanceForEmployee(Long employeeId) {
        return repo.findByEmployeeId(employeeId);
    }
}
