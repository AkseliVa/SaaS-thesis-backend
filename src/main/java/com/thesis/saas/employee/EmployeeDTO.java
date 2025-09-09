package com.thesis.saas.employee;

public record EmployeeDTO(
        String firstname,
        String lastname,
        String email,
        String phone,
        String role,
        Long companyId
) {}
