package com.thesis.saas.customer;

import com.thesis.saas.company.Company;
import com.thesis.saas.company.CompanyRepository;
import com.thesis.saas.employee.Employee;
import com.thesis.saas.employee.EmployeeRepository;
import com.thesis.saas.project.Project;
import com.thesis.saas.project.ProjectRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {
    private final CustomerRepository customerRepository;
    private final EmployeeRepository employeeRepository;
    private final CompanyRepository companyRepository;
    private final ProjectRepository projectRepository;

    public CustomerController(CustomerRepository customerRepository, EmployeeRepository employeeRepository, CompanyRepository companyRepository, ProjectRepository projectRepository) {this.customerRepository = customerRepository; this.employeeRepository = employeeRepository;
        this.companyRepository = companyRepository;
        this.projectRepository = projectRepository;
    }

    @GetMapping("/api/customers")
    public List<CustomerDTO> getAllCustomers() {
        return customerRepository.findAll().stream()
                .map(CustomerDTO::fromEntity)
                .toList();
    }

    @GetMapping("/api/customers/{id}")
    public CustomerDTO getCustomerById(@PathVariable long id) {
        return customerRepository.findById(id)
                .map(CustomerDTO::fromEntity)
                .orElse(null);
    }

    @PostMapping("/api/customers")
    public Customer newCustomer(@RequestBody CustomerDTO dto) {
        Company company = companyRepository.findById(dto.company_id())
                .orElseThrow(() -> new RuntimeException("Company not found"));
        Employee customerManager = employeeRepository.findById(dto.customerManager().employee_id())
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        Customer customer = new Customer(
                dto.name(),
                dto.contactPerson(),
                dto.contactEmail(),
                dto.contactPhone(),
                customerManager,
                company
        );
        return customerRepository.save(customer);}

    @PutMapping("/api/customers/{id}")
    public CustomerDTO updateCustomer(@PathVariable long id, @RequestBody CustomerDTO dto) {
        return customerRepository.findById(id)
                .map(existingCustomer -> {
                    existingCustomer.setName(dto.name());
                    existingCustomer.setContactPerson(dto.contactPerson());
                    existingCustomer.setContactEmail(dto.contactEmail());
                    existingCustomer.setContactPhone(dto.contactPhone());

                    if (dto.customerManager() != null) {
                        var manager = employeeRepository.findById(dto.customerManager().employee_id())
                                .orElse(null);
                        existingCustomer.setCustomerManager(manager);
                    }

                    var saved = customerRepository.save(existingCustomer);
                    return CustomerDTO.fromEntity(saved);
                })
                .orElseThrow(() -> new RuntimeException("Customer not found"));
    }


    @DeleteMapping("/api/customers/{id}")
    public void deleteCustomer(@PathVariable long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        customerRepository.delete(customer);
    }
}
