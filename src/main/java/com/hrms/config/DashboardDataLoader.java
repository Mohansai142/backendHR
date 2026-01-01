package com.hrms.config;

import com.hrms.model.DashboardSummary;
import com.hrms.repository.DashboardRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class DashboardDataLoader {

    private final DashboardRepository repository;

    public DashboardDataLoader(DashboardRepository repository) {
        this.repository = repository;
    }

    @PostConstruct
    public void loadDashboardData() {

        if (repository.count() == 0) {

            DashboardSummary d = new DashboardSummary();
            d.setEmployeeId(1L); // <-- VERY IMPORTANT
            d.setLeaveBalance(20);
            d.setAttendanceRate(95);
            d.setHoursWorked(168);
            d.setPerformanceScore(4.6);

            repository.save(d);

            System.out.println("✅ Dashboard summary inserted for employeeId = 1");
        }
    }
}
