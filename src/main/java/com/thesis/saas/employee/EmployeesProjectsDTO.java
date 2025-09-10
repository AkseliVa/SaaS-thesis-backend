package com.thesis.saas.employee;

import com.thesis.saas.project.ProjectsEmployees;
import com.thesis.saas.project.ProjectsEmployeesDTO;

public record EmployeesProjectsDTO(
        long ep_id,
        String name,
        String description
    ) {
        public static EmployeesProjectsDTO fromEntity(EmployeesProjects ep) {
            return new EmployeesProjectsDTO(
                    ep.getEp_id(),
                    ep.getProject().getName(),
                    ep.getProject().getDescription()
            );
        }
}
