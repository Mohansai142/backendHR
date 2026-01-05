package com.hrms.service;

import com.hrms.model.Attendance;
import com.hrms.repository.AttendanceRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class AttendanceService {

    private final AttendanceRepository repo;

    public AttendanceService(AttendanceRepository repo) {
        this.repo = repo;
    }

    // =========================
    // EMPLOYEE
    // =========================

    public List<Attendance> getAttendanceForEmployee(Long employeeId) {
        return repo.findByEmployeeId(employeeId);
    }

    public Attendance checkIn(Long employeeId) {

        LocalDate today = LocalDate.now();

        repo.findByEmployeeIdAndDate(employeeId, today)
            .ifPresent(a -> {
                throw new RuntimeException("Already checked in today");
            });

        Attendance attendance = new Attendance();
        attendance.setEmployeeId(employeeId);
        attendance.setDate(today);
        attendance.setCheckIn(LocalTime.now());
        attendance.setStatus("PRESENT");

        return repo.save(attendance);
    }

    public Attendance checkOut(Long employeeId) {

        Attendance attendance = repo.findByEmployeeIdAndDate(employeeId, LocalDate.now())
                .orElseThrow(() -> new RuntimeException("No check-in found for today"));

        if (attendance.getCheckOut() != null) {
            throw new RuntimeException("Already checked out today");
        }

        attendance.setCheckOut(LocalTime.now());
        return repo.save(attendance);
    }

    // =========================
    // ADMIN
    // =========================

    public List<Attendance> getAllAttendance() {
        return repo.findAll();
    }

    public List<Attendance> getAttendanceByEmployee(Long employeeId) {
        return repo.findByEmployeeId(employeeId);
    }
}
