package com.hrms.service;

import com.hrms.model.LeaveRequest;
import com.hrms.repository.LeaveRequestRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@Service
public class LeaveService {

    private final LeaveRequestRepository repo;

    public LeaveService(LeaveRequestRepository repo) {
        this.repo = repo;
    }

    // =========================
    // EMPLOYEE USE
    // =========================
    public List<LeaveRequest> getLeavesForEmployee(Long employeeId) {
        return repo.findByEmployeeId(employeeId);
    }

    public void applyLeave(
            Long employeeId,
            LocalDate startDate,
            LocalDate endDate,
            String reason,
            String type
    ) {
        LeaveRequest leave = new LeaveRequest();

        leave.setEmployeeId(employeeId);
        leave.setStartDate(startDate);
        leave.setEndDate(endDate);
        leave.setReason(reason);
        leave.setType(type);
        leave.setStatus("PENDING");

        repo.save(leave);
    }

    // =========================
    // ADMIN USE
    // =========================
    public List<LeaveRequest> getPendingLeaves() {
        return repo.findByStatus("PENDING");
    }

    public void approveLeave(Long id) {
        LeaveRequest leave = repo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Leave request not found"
                ));

        leave.setStatus("APPROVED");
        repo.save(leave);
    }

    public void rejectLeave(Long id) {
        LeaveRequest leave = repo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Leave request not found"
                ));

        leave.setStatus("REJECTED");
        repo.save(leave);
    }
}
