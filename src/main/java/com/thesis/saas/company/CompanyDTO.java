package com.thesis.saas.company;

import com.thesis.saas.customer.CustomerDTO;
import com.thesis.saas.employee.EmployeeDTO;
import com.thesis.saas.project.ProjectDTO;

import java.util.List;

public record CompanyDTO(
        long company_id,
        String name,
        List<EmployeeDTO> employees,
        List<ProjectDTO> projects,
        List<CustomerDTO> customers
) {
    public static CompanyDTO fromEntity(Company company) {
        return new CompanyDTO(
                company.getCompany_id(),
                company.getName(),
                company.getEmployees().stream()
                        .map(EmployeeDTO::fromEntity)
                        .toList(),
                company.getProjects().stream()
                        .map(ProjectDTO::fromEntity)
                        .toList(),
                company.getCustomers().stream()
                        .map(CustomerDTO::fromEntity)
                        .toList()
        );
    }
}
