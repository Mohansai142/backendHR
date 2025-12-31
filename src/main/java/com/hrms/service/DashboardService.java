package com.hrms.service;

import com.hrms.model.DashboardSummary;
import com.hrms.repository.DashboardRepository;
import org.springframework.stereotype.Service;

@Service
public class DashboardService {

    private final DashboardRepository repository;

    public DashboardService(DashboardRepository repository) {
        this.repository = repository;
    }

    public DashboardSummary getDashboard(Long id) {
        return repository.findById(id).orElse(null);
    }
}
