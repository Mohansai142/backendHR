package com.hrms.repository;

import com.hrms.model.LeaveRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, Long> {

    // Employee leave page
    List<LeaveRequest> findByEmployeeId(Long employeeId);

    // Admin approval page
    List<LeaveRequest> findByStatus(String status);

    // Admin dashboard
    long countByStatus(String status);

    // Employee dashboard
    long countByEmployeeIdAndStatus(Long employeeId, String status);
}
