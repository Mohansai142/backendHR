package com.hrms.controller;

import com.hrms.model.Attendance;
import com.hrms.service.AttendanceService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@RestController
@RequestMapping("/api/attendance")
@PreAuthorize("hasRole('EMPLOYEE')")
public class AttendanceController {

    private final AttendanceService attendanceService;

    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    // View my attendance
    @GetMapping
    public List<Attendance> getMyAttendance(Authentication auth) {
        Long employeeId = (Long) auth.getPrincipal();
        return attendanceService.getAttendanceForEmployee(employeeId);
    }

    // Check-in
    @PostMapping("/check-in")
    public Attendance checkIn(Authentication auth) {
        Long employeeId = (Long) auth.getPrincipal();
        return attendanceService.checkIn(employeeId);
    }

    // Check-out
    @PostMapping("/check-out")
    public Attendance checkOut(Authentication auth) {
        Long employeeId = (Long) auth.getPrincipal();
        return attendanceService.checkOut(employeeId);
    }
}

