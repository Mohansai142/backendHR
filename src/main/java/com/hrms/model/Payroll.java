package com.hrms.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "payroll")
public class Payroll {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "employee_id", nullable = false)
    private Long employeeId;

    @Column(name = "month", nullable = false)
    private String month;

    @Column(name = "basic_salary", nullable = false)
    private double basicSalary;

    @Column(name = "deductions", nullable = false)
    private double deductions;

    @Column(name = "net_salary", nullable = false)
    private double netSalary;

    @Column(name = "generated_date", nullable = false)
    private LocalDate generatedDate;

    // ===== GETTERS =====
    public Long getId() { return id; }
    public Long getEmployeeId() { return employeeId; }
    public String getMonth() { return month; }
    public double getBasicSalary() { return basicSalary; }
    public double getDeductions() { return deductions; }
    public double getNetSalary() { return netSalary; }
    public LocalDate getGeneratedDate() { return generatedDate; }

    // ===== SETTERS =====
    public void setId(Long id) { this.id = id; }
    public void setEmployeeId(Long employeeId) { this.employeeId = employeeId; }
    public void setMonth(String month) { this.month = month; }
    public void setBasicSalary(double basicSalary) { this.basicSalary = basicSalary; }
    public void setDeductions(double deductions) { this.deductions = deductions; }
    public void setNetSalary(double netSalary) { this.netSalary = netSalary; }
    public void setGeneratedDate(LocalDate generatedDate) { this.generatedDate = generatedDate; }
}
