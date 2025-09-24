package com.thesis.saas.project;

import com.thesis.saas.employee.Employee;
import com.thesis.saas.employee.EmployeesProjects;

public record ProjectsEmployeesDTO(
        long employee_id,
        String firstname,
        String lastname
) {
    public static ProjectsEmployeesDTO fromEntity(ProjectsEmployees pe) {
        Employee e = pe.getEmployee();
        return new ProjectsEmployeesDTO(
                e.getEmployee_id(),
                e.getFirstname(),
                e.getLastname()
        );
    }
}
