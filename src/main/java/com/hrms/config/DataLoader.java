package com.hrms.config;

import com.hrms.model.Employee;
import com.hrms.repository.EmployeeRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class DataLoader {

    private final EmployeeRepository employeeRepository;

    public DataLoader(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @PostConstruct
    public void loadData() {

        if (employeeRepository.count() == 0) {

            Employee emp = new Employee();
            emp.setName("mohan sai");
            emp.setEmail("mohan@gmail.com");
            emp.setPassword("1234"); // plain for now
            emp.setRole("EMPLOYEE");
            emp.setStatus("ACTIVE");

            employeeRepository.save(emp);

            System.out.println("✅ Demo employee inserted");
        }
    }
}
