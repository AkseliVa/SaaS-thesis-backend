package com.thesis.saas.employee;

import com.thesis.saas.company.CompanyRepository;
import com.thesis.saas.project.Project;
import com.thesis.saas.project.ProjectDTO;
import com.thesis.saas.project.ProjectRepository;
import com.thesis.saas.project.ProjectsEmployees;
import org.springframework.web.bind.annotation.*;

import com.thesis.saas.company.Company;

import java.util.List;

@RestController
public class EmployeeController {
    private final EmployeeRepository employeeRepository;
    private final CompanyRepository companyRepository;
    private final ProjectRepository projectRepository;

    public EmployeeController(EmployeeRepository employeeRepository, CompanyRepository companyRepository, ProjectRepository projectRepository) {
        this.employeeRepository = employeeRepository;
        this.companyRepository = companyRepository;
        this.projectRepository = projectRepository;
    }

    @GetMapping("/api/employees")
    public List<EmployeeDTO> getAllEmployees() {
        return employeeRepository.findAll()
                .stream()
                .map(EmployeeDTO::fromEntity)
                .toList();
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

                    if (dto.projects() != null) {
                        // Clear old links
                        existingEmployee.getProjectsEmployees().clear();

                        // Rebuild links from DTO
                        List<ProjectsEmployees> newLinks = dto.projects().stream()
                                .map(pdto -> {
                                    var project = projectRepository.findById(pdto.project_id())
                                            .orElseThrow(() -> new RuntimeException("Project not found: " + pdto.project_id()));
                                    ProjectsEmployees pe = new ProjectsEmployees();
                                    pe.setEmployee(existingEmployee);
                                    pe.setProject(project);
                                    return pe;
                                })
                                .toList();

                        existingEmployee.getProjectsEmployees().addAll(newLinks);
                    }

                    return employeeRepository.save(existingEmployee);
                })
                .orElseThrow(() -> new RuntimeException("Employee not found"));
    }
}
