package com.hrms.controller;

import com.hrms.model.Payroll;
import com.hrms.service.PayrollService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payroll")
@CrossOrigin(origins = "http://localhost:3000")
public class PayrollController {

    private final PayrollService service;

    public PayrollController(PayrollService service) {
        this.service = service;
    }

    // 🔒 Only logged-in employee payroll
    @GetMapping("/{employeeId}")
    public List<Payroll> getMyPayroll(@PathVariable Long employeeId) {
        return service.getPayrollForEmployee(employeeId);
    }
}
