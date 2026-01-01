package com.hrms.config;

import com.hrms.model.*;
import com.hrms.repository.*;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;

@Component
public class DataLoader {

    private final EmployeeRepository employeeRepository;
    private final DashboardRepository dashboardRepository;
    private final AttendanceRepository attendanceRepository;
    private final LeaveRequestRepository leaveRequestRepository;
    private final PayrollRepository payrollRepository;
    private final PerformanceRepository performanceRepository;

    public DataLoader(
            EmployeeRepository employeeRepository,
            DashboardRepository dashboardRepository,
            AttendanceRepository attendanceRepository,
            LeaveRequestRepository leaveRequestRepository,
            PayrollRepository payrollRepository,
            PerformanceRepository performanceRepository
    ) {
        this.employeeRepository = employeeRepository;
        this.dashboardRepository = dashboardRepository;
        this.attendanceRepository = attendanceRepository;
        this.leaveRequestRepository = leaveRequestRepository;
        this.payrollRepository = payrollRepository;
        this.performanceRepository = performanceRepository;
    }

    @PostConstruct
    public void loadData() {

        // ============================
        // EMPLOYEE
        // ============================
        Employee employee;

if (employeeRepository.count() == 0) {
    employee = new Employee();
    employee.setName("mohan sai");
    employee.setEmail("mohan@gmail.com");
    employee.setPassword("1234");
    employee.setRole("EMPLOYEE");
    employee.setStatus("ACTIVE");

    employee = employeeRepository.save(employee);
    System.out.println("✅ Employee inserted");
} else {
    employee = employeeRepository.findAll().get(0);
}

Long employeeId = employee.getId();

        // ============================
        // DASHBOARD SUMMARY
        // ============================
        if (dashboardRepository.count() == 0) {

            DashboardSummary ds = new DashboardSummary();
           
            ds.setEmployeeId(employeeId);
            ds.setLeaveBalance(12);
            ds.setAttendanceRate(92);
            ds.setHoursWorked(168);
            ds.setPerformanceScore(4.5);

            dashboardRepository.save(ds);
            System.out.println("✅ Dashboard summary inserted");
        }

        // ============================
        // ATTENDANCE
        // ============================
        if (attendanceRepository.count() == 0) {

            Attendance a1 = new Attendance();
            a1.setEmployeeId(employeeId);
            a1.setDate(LocalDate.now().minusDays(1));
            a1.setCheckIn(LocalTime.of(9, 10));
            a1.setCheckOut(LocalTime.of(18, 30));
            a1.setStatus("Present");

            Attendance a2 = new Attendance();
            a2.setEmployeeId(employeeId);
            a2.setDate(LocalDate.now().minusDays(2));
            a2.setCheckIn(LocalTime.of(9, 20));
            a2.setCheckOut(LocalTime.of(18, 15));
            a2.setStatus("Present");

            attendanceRepository.save(a1);
            attendanceRepository.save(a2);

            System.out.println("✅ Attendance inserted");
        }

        // ============================
        // LEAVE REQUEST
        // ============================
        if (leaveRequestRepository.count() == 0) {

            LeaveRequest lr = new LeaveRequest();
            lr.setEmployeeId(employeeId);
            lr.setFromDate(LocalDate.now().minusDays(5));
            lr.setToDate(LocalDate.now().minusDays(3));
            lr.setReason("Medical");
            lr.setStatus("Approved");

            leaveRequestRepository.save(lr);

            System.out.println("✅ Leave request inserted");
        }

        // ============================
        // PAYROLL
        // ============================
        if (payrollRepository.count() == 0) {

            Payroll p = new Payroll();
            p.setEmployeeId(employeeId);
            p.setMonth("December 2025");
            p.setBasicSalary(30000);
            p.setDeductions(2000);
            p.setNetSalary(28000);
            p.setGeneratedDate(LocalDate.now().minusDays(10));

            payrollRepository.save(p);

            System.out.println("✅ Payroll inserted");
        }

        // ============================
        // PERFORMANCE
        // ============================
        if (performanceRepository.count() == 0) {

            Performance pr = new Performance();
            pr.setEmployeeId(employeeId);
            pr.setReviewPeriod("Q4 2025");
            pr.setRating(4);
            pr.setComments("Consistent performer with good ownership");
            pr.setReviewDate(LocalDate.now().minusDays(15));

            performanceRepository.save(pr);

            System.out.println("✅ Performance review inserted");
        }

        System.out.println("🎯 ALL DEMO DATA LOADED SUCCESSFULLY");
    }
}
