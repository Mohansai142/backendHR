package com.hrms.service;

import com.hrms.model.Payroll;
import com.hrms.repository.PayrollRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@Service
public class PayrollService {

    private final PayrollRepository repo;

    public PayrollService(PayrollRepository repo) {
        this.repo = repo;
    }

    // =========================
    // EMPLOYEE USE (READ ONLY)
    // =========================
    public List<Payroll> getPayrollForEmployee(Long employeeId) {
        return repo.findByEmployeeId(employeeId);
    }

    // =========================
    // ADMIN USE (GENERATE)
    // =========================
    public void generatePayroll(
            Long employeeId,
            String month,
            double basicSalary,
            double deductions
    ) {
        // 1️⃣ Prevent duplicate payroll
        if (repo.existsByEmployeeIdAndMonth(employeeId, month)) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Payroll already generated for this employee and month"
            );
        }

        // 2️⃣ Calculate net salary
        double netSalary = basicSalary - deductions;

        // 3️⃣ Create payroll record
        Payroll payroll = new Payroll();
        payroll.setEmployeeId(employeeId);
        payroll.setMonth(month);
        payroll.setBasicSalary(basicSalary);
        payroll.setDeductions(deductions);
        payroll.setNetSalary(netSalary);
        payroll.setGeneratedDate(LocalDate.now());

        // 4️⃣ Save to DB
        repo.save(payroll);
    }
}
