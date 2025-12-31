package com.hrms.service;

import com.hrms.model.Attendance;
import com.hrms.repository.AttendanceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttendanceService {

    private final AttendanceRepository repo;

    public AttendanceService(AttendanceRepository repo) {
        this.repo = repo;
    }

    public List<Attendance> getAttendanceForEmployee(Long employeeId) {
        return repo.findByEmployeeId(employeeId);
    }
}
