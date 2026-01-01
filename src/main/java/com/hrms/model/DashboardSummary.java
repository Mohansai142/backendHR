package com.hrms.model;

import jakarta.persistence.*;

@Entity
@Table(name = "dashboard_summary")
public class DashboardSummary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "employee_id", nullable = false)
    private Long employeeId;

    @Column(name = "leave_balance")
    private int leaveBalance;

    @Column(name = "attendance_rate")
    private int attendanceRate;

    @Column(name = "hours_worked")
    private int hoursWorked;

    @Column(name = "performance_score")
    private double performanceScore;

    // ===== GETTERS =====
    public Long getId() {
        return id;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public int getLeaveBalance() {
        return leaveBalance;
    }

    public int getAttendanceRate() {
        return attendanceRate;
    }

    public int getHoursWorked() {
        return hoursWorked;
    }

    public double getPerformanceScore() {
        return performanceScore;
    }

    // ===== SETTERS =====
    public void setId(Long id) {
        this.id = id;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public void setLeaveBalance(int leaveBalance) {
        this.leaveBalance = leaveBalance;
    }

    public void setAttendanceRate(int attendanceRate) {
        this.attendanceRate = attendanceRate;
    }

    public void setHoursWorked(int hoursWorked) {
        this.hoursWorked = hoursWorked;
    }

    public void setPerformanceScore(double performanceScore) {
        this.performanceScore = performanceScore;
    }
}
