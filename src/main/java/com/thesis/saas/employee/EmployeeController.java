package com.thesis.saas.employee;

import org.springframework.web.bind.annotation.*;

@RestController
public class EmployeeController {
    private final EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping("/api/employees")
    public Iterable<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @PostMapping("/api/employees")
    public Employee newEmployee(@RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }

    @DeleteMapping("/api/employees/{id}")
    public void deleteEmployee(@PathVariable long id) {
        employeeRepository.deleteById(id);
    }

    @PutMapping("/api/employees/{id}")
    public Employee updateEmployee(@PathVariable long id, @RequestBody Employee employee) {
        return employeeRepository.findById(id)
                .map(existingEmployee -> {
                  existingEmployee.setFirstname(employee.getFirstname());
                  existingEmployee.setLastname(employee.getLastname());
                  existingEmployee.setEmail(employee.getEmail());
                  existingEmployee.setPhone(employee.getPhone());
                  existingEmployee.setRole(employee.getRole());

                  return employeeRepository.save(existingEmployee);
                })
                .orElseThrow(() -> new RuntimeException("Employee not found"));
    }
}
