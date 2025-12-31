package com.hrms.service;

import com.hrms.model.Payroll;
import com.hrms.repository.PayrollRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PayrollService {

    private final PayrollRepository repo;

    public PayrollService(PayrollRepository repo) {
        this.repo = repo;
    }

    public List<Payroll> getPayrollForEmployee(Long employeeId) {
        return repo.findByEmployeeId(employeeId);
    }
}
