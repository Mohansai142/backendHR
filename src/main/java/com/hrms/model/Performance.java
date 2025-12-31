package com.hrms.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "performance")
public class Performance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long employeeId;

    @Column(nullable = false)
    private String reviewPeriod; // e.g. Q4-2025

    @Column(nullable = false)
    private Double rating; // 1.0 – 5.0

    @Column(length = 500)
    private String comments;

    @Column(nullable = false)
    private LocalDate reviewDate;

    // Getters
    public Long getId() { return id; }
    public Long getEmployeeId() { return employeeId; }
    public String getReviewPeriod() { return reviewPeriod; }
    public Double getRating() { return rating; }
    public String getComments() { return comments; }
    public LocalDate getReviewDate() { return reviewDate; }

    // Setters
    public void setId(Long id) { this.id = id; }
    public void setEmployeeId(Long employeeId) { this.employeeId = employeeId; }
    public void setReviewPeriod(String reviewPeriod) { this.reviewPeriod = reviewPeriod; }
    public void setRating(Double rating) { this.rating = rating; }
    public void setComments(String comments) { this.comments = comments; }
    public void setReviewDate(LocalDate reviewDate) { this.reviewDate = reviewDate; }
}
