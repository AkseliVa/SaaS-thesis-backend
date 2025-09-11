package com.thesis.saas.project;

import java.time.LocalDate;

public record EmployeesProjectsDTO(
        long project_id,
        String name,
        String description,
        LocalDate startDate,
        LocalDate endDate,
        Boolean active
) {
    public static EmployeesProjectsDTO fromEntity(ProjectsEmployees pe) {
        Project p = pe.getProject();
        return new EmployeesProjectsDTO(
                p.getProject_id(),
                p.getName(),
                p.getDescription(),
                p.getStartDate(),
                p.getEndDate(),
                p.getActive()
        );
    }
}
