package com.hrms.controller;

import com.hrms.model.Payroll;
import com.hrms.service.PayrollService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/payroll")
public class PayrollController {

    private final PayrollService payrollService;

    public PayrollController(PayrollService payrollService) {
        this.payrollService = payrollService;
    }

    // =========================
    // EMPLOYEE: View own payroll
    // =========================
    @GetMapping
    public List<Payroll> getMyPayroll(Authentication authentication) {
        Long employeeId = (Long) authentication.getPrincipal();
        return payrollService.getPayrollForEmployee(employeeId);
    }
}
