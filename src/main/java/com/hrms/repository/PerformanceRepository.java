package com.hrms.repository;

import com.hrms.model.Performance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PerformanceRepository extends JpaRepository<Performance, Long> {

    // Employee performance page
    List<Performance> findByEmployeeId(Long employeeId);

    // Dashboard average score
    @Query("""
        SELECT AVG(p.rating)
        FROM Performance p
        WHERE p.employeeId = :employeeId
    """)
    Optional<Double> findAverageRating(@Param("employeeId") Long employeeId);
}
