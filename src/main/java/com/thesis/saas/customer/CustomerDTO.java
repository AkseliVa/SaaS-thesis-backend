package com.thesis.saas.customer;

import com.thesis.saas.employee.EmployeeDTO;
import com.thesis.saas.project.ProjectDTO;

import java.util.List;

public record CustomerDTO(
        long customer_id,
        String name,
        String contactPerson,
        String contactEmail,
        String contactPhone,
        List<ProjectDTO> projects,
        EmployeeDTO customerManager,
        long company_id
) {
    public static CustomerDTO fromEntity(Customer customer) {
        return new CustomerDTO(
                customer.getCustomer_id(),
                customer.getName(),
                customer.getContactPerson(),
                customer.getContactEmail(),
                customer.getContactPhone(),
                customer.getProjects() != null
                        ? customer.getProjects().stream()
                            .map(ProjectDTO::fromEntity)
                            .toList()
                        : List.of(),
                customer.getCustomerManager() != null
                        ? EmployeeDTO.fromEntity(customer.getCustomerManager())
                        : null,
                customer.getCompany().getCompany_id()
        );
    }
}
