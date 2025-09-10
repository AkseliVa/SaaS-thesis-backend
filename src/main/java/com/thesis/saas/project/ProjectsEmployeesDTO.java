package com.thesis.saas.project;

public record ProjectsEmployeesDTO(
        long pe_id,
        String firstname,
        String lastname
) {
    public static ProjectsEmployeesDTO fromEntity(ProjectsEmployees pe) {
        return new ProjectsEmployeesDTO(
                pe.getPe_id(),
                pe.getEmployee().getFirstname(),
                pe.getEmployee().getLastname()
        );
    }
}
