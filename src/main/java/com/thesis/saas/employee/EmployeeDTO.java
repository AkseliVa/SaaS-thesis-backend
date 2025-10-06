package com.thesis.saas.employee;

import java.util.List;

public record EmployeeDTO(
        long employee_id,
        String firstname,
        String lastname,
        String email,
        String phone,
        String role,
        List<EmployeesProjectsDTO> projects,
        long company_id
) {
    public static EmployeeDTO fromEntity(Employee employee) {
        return new EmployeeDTO(
                employee.getEmployee_id(),
                employee.getFirstname(),
                employee.getLastname(),
                employee.getEmail(),
                employee.getPhone(),
                employee.getRole(),
                employee.getProjectsEmployees().stream()
                        .map(EmployeesProjectsDTO::fromEntity)
                        .toList(),
                employee.getCompany().getCompany_id()
        );
    }
}
