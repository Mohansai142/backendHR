package com.hrms.service;

import com.hrms.model.Performance;
import com.hrms.repository.PerformanceRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PerformanceService {

    private final PerformanceRepository repo;

    public PerformanceService(PerformanceRepository repo) {
        this.repo = repo;
    }

    // =========================
    // EMPLOYEE USE (READ ONLY)
    // =========================
    public List<Performance> getPerformanceForEmployee(Long employeeId) {
        return repo.findByEmployeeId(employeeId);
    }

    // =========================
    // ADMIN USE (ADD REVIEW)
    // =========================
    public void addPerformance(
            Long employeeId,
            String reviewPeriod,
            int rating,
            String comments
    ) {
        Performance performance = new Performance();
        performance.setEmployeeId(employeeId);
        performance.setReviewPeriod(reviewPeriod);
        performance.setRating(rating);
        performance.setComments(comments);
        performance.setReviewDate(LocalDate.now());

        repo.save(performance);
    }
}
