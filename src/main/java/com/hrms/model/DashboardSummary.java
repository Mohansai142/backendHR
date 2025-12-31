package com.hrms.model;

import jakarta.persistence.*;

@Entity
@Table(name = "dashboard_summary")
public class DashboardSummary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "employee_name")
    private String employeeName;

    @Column(name = "leave_balance")
    private int leaveBalance;

    @Column(name = "attendance_rate")
    private int attendanceRate;

    @Column(name = "hours_worked")
    private int hoursWorked;

    @Column(name = "performance_score")
    private double performanceScore;

    public Long getId() {
        return id;
    }

    public String getEmployeeName() {
        return employeeName;
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

    public void setId(Long id) {
        this.id = id;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
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
