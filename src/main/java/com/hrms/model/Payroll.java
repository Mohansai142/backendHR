package com.hrms.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "payroll")
public class Payroll {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long employeeId;

    @Column(nullable = false)
    private String month; // e.g. DECEMBER-2025

    @Column(nullable = false)
    private Double basicSalary;

    @Column(nullable = false)
    private Double deductions;

    @Column(nullable = false)
    private Double netSalary;

    @Column(nullable = false)
    private LocalDate generatedDate;

    // Getters
    public Long getId() { return id; }
    public Long getEmployeeId() { return employeeId; }
    public String getMonth() { return month; }
    public Double getBasicSalary() { return basicSalary; }
    public Double getDeductions() { return deductions; }
    public Double getNetSalary() { return netSalary; }
    public LocalDate getGeneratedDate() { return generatedDate; }

    // Setters
    public void setId(Long id) { this.id = id; }
    public void setEmployeeId(Long employeeId) { this.employeeId = employeeId; }
    public void setMonth(String month) { this.month = month; }
    public void setBasicSalary(Double basicSalary) { this.basicSalary = basicSalary; }
    public void setDeductions(Double deductions) { this.deductions = deductions; }
    public void setNetSalary(Double netSalary) { this.netSalary = netSalary; }
    public void setGeneratedDate(LocalDate generatedDate) { this.generatedDate = generatedDate; }
}
