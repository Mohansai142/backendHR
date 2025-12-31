package com.hrms.repository;

import com.hrms.model.DashboardSummary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DashboardRepository extends JpaRepository<DashboardSummary, Long> {
}
