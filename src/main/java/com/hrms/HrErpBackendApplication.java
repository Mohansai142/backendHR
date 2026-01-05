package com.hrms;

import com.hrms.service.AuthService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HrErpBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(HrErpBackendApplication.class, args);
    }

    @Bean
    CommandLineRunner initAdmin(AuthService authService) {
        return args -> {
            System.out.println("🔥 Admin bootstrap running");
            authService.createInitialAdminIfNotExists();
        };
    }
}
