package com.thesis.saas.project;

import com.thesis.saas.customer.CustomerDTO;
import com.thesis.saas.customer.CustomerSimpleDTO;

import java.time.LocalDate;
import java.util.List;

public record ProjectDTO(
        long project_id,
        String name,
        String description,
        LocalDate startDate,
        LocalDate endDate,
        Boolean active,
        List<ProjectsEmployeesDTO> employees,
        long company_id,
        CustomerSimpleDTO customer
) {
    public static ProjectDTO fromEntity(Project project) {
        return new ProjectDTO(
                project.getProject_id(),
                project.getName(),
                project.getDescription(),
                project.getStartDate(),
                project.getEndDate(),
                project.getActive(),
                project.getProjectsEmployees().stream()
                        .map(ProjectsEmployeesDTO::fromEntity)
                        .toList(),
                project.getCompany().getCompany_id(),
                project.getCustomer() != null
                        ? CustomerSimpleDTO.fromEntity(project.getCustomer())
                        : null
        );
    }
}
