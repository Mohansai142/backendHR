package com.hrms.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "attendance")
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "employee_id", nullable = false)
    private Long employeeId;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "check_in")
    private LocalTime checkIn;

    @Column(name = "check_out")
    private LocalTime checkOut;

    @Column(name = "status", nullable = false)
    private String status;

    // ===== GETTERS =====
    public Long getId() { return id; }
    public Long getEmployeeId() { return employeeId; }
    public LocalDate getDate() { return date; }
    public LocalTime getCheckIn() { return checkIn; }
    public LocalTime getCheckOut() { return checkOut; }
    public String getStatus() { return status; }

    // ===== SETTERS =====
    public void setId(Long id) { this.id = id; }
    public void setEmployeeId(Long employeeId) { this.employeeId = employeeId; }
    public void setDate(LocalDate date) { this.date = date; }
    public void setCheckIn(LocalTime checkIn) { this.checkIn = checkIn; }
    public void setCheckOut(LocalTime checkOut) { this.checkOut = checkOut; }
    public void setStatus(String status) { this.status = status; }
}
