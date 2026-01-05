package com.hrms.controller;

import com.hrms.model.Attendance;
import com.hrms.service.AttendanceService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/attendance")
@PreAuthorize("hasRole('ADMIN')")
public class AdminAttendanceController {

    private final AttendanceService attendanceService;

    public AdminAttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    // ==================================
    // VIEW ALL EMPLOYEES ATTENDANCE
    // ==================================
    @GetMapping
    public List<Attendance> getAllAttendance() {
        return attendanceService.getAllAttendance();
    }

    // ==================================
    // VIEW ATTENDANCE BY EMPLOYEE ID
    // ==================================
    @GetMapping("/employee/{employeeId}")
    public List<Attendance> getAttendanceByEmployee(
            @PathVariable Long employeeId
    ) {
        return attendanceService.getAttendanceByEmployee(employeeId);
    }
}
