package com.hrms.config;

import com.hrms.security.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            .cors(cors -> {})
            .csrf(csrf -> csrf.disable())
            .sessionManagement(sm ->
                sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )

            // 🔥 CRITICAL FIX (DO NOT REMOVE)
            .formLogin(form -> form.disable())
            .httpBasic(basic -> basic.disable())

            .authorizeHttpRequests(auth -> auth

                // ✅ CORS preflight
                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()

                // 🔓 AUTH
                .requestMatchers("/api/auth/**").permitAll()

                // 🔓 PROFILE (ADMIN + EMPLOYEE)
                .requestMatchers("/api/employees/me").authenticated()

                // 🔒 ADMIN ONLY
                .requestMatchers("/api/employees/**").hasRole("ADMIN")
                .requestMatchers("/api/admin/**").hasRole("ADMIN")
                .requestMatchers("/api/leaves/admin/**").hasRole("ADMIN")

                // 🔒 EMPLOYEE ONLY
                .requestMatchers("/api/dashboard/**").hasRole("EMPLOYEE")
                .requestMatchers("/api/attendance/**").hasRole("EMPLOYEE")
                .requestMatchers("/api/leaves/**").hasRole("EMPLOYEE")
                .requestMatchers("/api/payroll/**").hasRole("EMPLOYEE")
                .requestMatchers("/api/performance/**").hasRole("EMPLOYEE")

                .anyRequest().authenticated()
            )
            .addFilterBefore(
                jwtAuthenticationFilter,
                UsernamePasswordAuthenticationFilter.class
            );

        return http.build();
    }

    // ✅ REQUIRED so Spring DOES NOT create in-memory users
    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration configuration
    ) throws Exception {
        return configuration.getAuthenticationManager();
    }

    // ✅ BCrypt password hashing
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
