package com.hrms.controller;

import com.hrms.service.PayrollService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/payroll")
public class AdminPayrollController {

    private final PayrollService payrollService;

    public AdminPayrollController(PayrollService payrollService) {
        this.payrollService = payrollService;
    }

    // =========================
    // ADMIN: Generate payroll
    // =========================
    @PostMapping("/generate")
    public void generatePayroll(@RequestBody GeneratePayrollRequest request) {
        payrollService.generatePayroll(
                request.employeeId,
                request.month,
                request.basicSalary,
                request.deductions
        );
    }

    // =========================
    // REQUEST BODY (INTERNAL)
    // =========================
    static class GeneratePayrollRequest {
        public Long employeeId;
        public String month;          // e.g. "2026-01"
        public double basicSalary;
        public double deductions;
    }
}
