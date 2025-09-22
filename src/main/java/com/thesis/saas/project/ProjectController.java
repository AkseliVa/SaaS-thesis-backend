package com.thesis.saas.project;

import com.thesis.saas.company.Company;
import com.thesis.saas.company.CompanyRepository;
import com.thesis.saas.employee.EmployeeRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProjectController {
    private final ProjectRepository projectRepository;
    private final CompanyRepository companyRepository;
    private final EmployeeRepository employeeRepository;

    public ProjectController(ProjectRepository projectRepository, CompanyRepository companyRepository, EmployeeRepository employeeRepository) {
        this.projectRepository = projectRepository;
        this.companyRepository = companyRepository;
        this.employeeRepository = employeeRepository;
    }

    @GetMapping("/api/projects")
    public List<ProjectDTO> getAllProjects() {
        return projectRepository.findAll()
                .stream()
                .map(ProjectDTO::fromEntity)
                .toList();
    }

    @GetMapping("/api/projects/{id}")
    public ProjectDTO getProject(@PathVariable long id) {
        return projectRepository.findById(id)
                .map(ProjectDTO::fromEntity)
                .orElseThrow(() -> new RuntimeException("Project not found"));
    }

    @PostMapping("/api/projects")
    public Project newProject(@RequestBody ProjectDTO dto) {
        Company company = companyRepository.findById(dto.company_id())
                .orElseThrow(() -> new RuntimeException("Company not found"));

        Project project = new Project(
                dto.name(),
                dto.description(),
                dto.startDate(),
                dto.endDate(),
                company,
                dto.active()
        );
        return projectRepository.save(project);
    }

    @DeleteMapping("/api/projects/{id}")
    public void deleteProject(@PathVariable Long id) {
        projectRepository.deleteById(id);
    }

    @PutMapping("/api/projects/{id}")
    public Project updateProject(@PathVariable Long id, @RequestBody ProjectDTO dto) {
        return projectRepository.findById(id)
                .map(existingProject -> {
                    existingProject.setName(dto.name());
                    existingProject.setDescription(dto.description());
                    existingProject.setStartDate(dto.startDate());
                    existingProject.setEndDate(dto.endDate());
                    existingProject.setActive(dto.active());

                    if (dto.employees() != null) {
                        existingProject.getProjectsEmployees().clear();

                        List<ProjectsEmployees> newLinks = dto.employees().stream()
                                .map(pdto -> {
                                    var employee = employeeRepository.findById(pdto.pe_id())
                                            .orElseThrow(() -> new RuntimeException("Project not found: " + pdto.pe_id()));
                                    ProjectsEmployees pe = new ProjectsEmployees();
                                    pe.setProject(existingProject);
                                    pe.setEmployee(employee);
                                    return pe;
                                })
                                .toList();

                        existingProject.getProjectsEmployees().addAll(newLinks);
                    }

                    return projectRepository.save(existingProject);
                })
                .orElseThrow(() -> new RuntimeException("Project not found"));
    }
}
