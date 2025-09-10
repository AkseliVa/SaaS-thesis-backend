package com.thesis.saas.employee;

import com.thesis.saas.company.CompanyRepository;
import org.springframework.web.bind.annotation.*;

import com.thesis.saas.company.Company;

@RestController
public class EmployeeController {
    private final EmployeeRepository employeeRepository;
    private final CompanyRepository companyRepository;

    public EmployeeController(EmployeeRepository employeeRepository, CompanyRepository companyRepository) {
        this.employeeRepository = employeeRepository;
        this.companyRepository = companyRepository;
    }

    @GetMapping("/api/employees")
    public Iterable<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @PostMapping("/api/employees")
    public Employee newEmployee(@RequestBody EmployeeDTO dto) {
        Company company = companyRepository.findById(dto.companyId())
                .orElseThrow(() -> new RuntimeException("Company not found"));

        Employee employee = new Employee(
                dto.firstname(),
                dto.lastname(),
                dto.email(),
                dto.phone(),
                dto.role(),
                company
        );

        return employeeRepository.save(employee);
    }

    @DeleteMapping("/api/employees/{id}")
    public void deleteEmployee(@PathVariable long id) {
        employeeRepository.deleteById(id);
    }

    @PutMapping("/api/employees/{id}")
    public Employee updateEmployee(@PathVariable long id, @RequestBody EmployeeDTO dto) {
        return employeeRepository.findById(id)
                .map(existingEmployee -> {
                  existingEmployee.setFirstname(dto.firstname());
                  existingEmployee.setLastname(dto.lastname());
                  existingEmployee.setEmail(dto.email());
                  existingEmployee.setPhone(dto.phone());
                  existingEmployee.setRole(dto.role());

                  return employeeRepository.save(existingEmployee);
                })
                .orElseThrow(() -> new RuntimeException("Employee not found"));
    }
}
