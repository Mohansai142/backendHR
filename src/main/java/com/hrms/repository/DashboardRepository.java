package com.hrms.repository;

import com.hrms.model.DashboardSummary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DashboardRepository extends JpaRepository<DashboardSummary, Long> {

    Optional<DashboardSummary> findByEmployeeId(Long employeeId);
}
