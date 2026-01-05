package com.hrms.service;
import com.hrms.repository.AttendanceRepository;
import com.hrms.repository.LeaveRequestRepository;
import com.hrms.repository.PerformanceRepository;
import java.time.Duration;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

import com.hrms.model.Attendance;
import com.hrms.model.DashboardSummary;
import com.hrms.repository.DashboardRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class DashboardService {

    private final AttendanceRepository attendanceRepo;
    private final LeaveRequestRepository leaveRepo;
    private final PerformanceRepository performanceRepo;

    public DashboardService(
            AttendanceRepository attendanceRepo,
            LeaveRequestRepository leaveRepo,
            PerformanceRepository performanceRepo
    ) {
        this.attendanceRepo = attendanceRepo;
        this.leaveRepo = leaveRepo;
        this.performanceRepo = performanceRepo;
    }

    public Map<String, Object> getDashboard(Long employeeId) {

        long totalDays = attendanceRepo.countByEmployeeId(employeeId);
        long presentDays = attendanceRepo.countByEmployeeIdAndStatus(employeeId, "PRESENT");

        double attendanceRate = totalDays == 0
                ? 0
                : (presentDays * 100.0) / totalDays;

        int leaveBalance =
                20 - (int) leaveRepo.countByEmployeeIdAndStatus(employeeId, "Approved");

        double performanceScore =
                performanceRepo.findAverageRating(employeeId).orElse(0.0);

        List<Attendance> attendanceList =
                attendanceRepo.findByEmployeeId(employeeId);

        long totalMinutesWorked = attendanceList.stream()
                .filter(a -> a.getCheckIn() != null && a.getCheckOut() != null)
                .mapToLong(a ->
                        Duration.between(a.getCheckIn(), a.getCheckOut()).toMinutes()
                )
                .sum();

        double hoursWorked = totalMinutesWorked / 60.0;

        Map<String, Object> dashboard = new HashMap<>();
        dashboard.put("attendanceRate", attendanceRate);
        dashboard.put("leaveBalance", leaveBalance);
        dashboard.put("performanceScore", performanceScore);
        dashboard.put("hoursWorked", hoursWorked);
        dashboard.put("minutesWorked", totalMinutesWorked);

        return dashboard;
    }
}


