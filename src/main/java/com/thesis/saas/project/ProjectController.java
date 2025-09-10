package com.thesis.saas.project;

import com.thesis.saas.company.Company;
import com.thesis.saas.company.CompanyRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProjectController {
    private final ProjectRepository projectRepository;
    private final CompanyRepository companyRepository;

    public ProjectController(ProjectRepository projectRepository, CompanyRepository companyRepository) {
        this.projectRepository = projectRepository;
        this.companyRepository = companyRepository;
    }

    @GetMapping("/api/projects")
    public List<ProjectDTO> getAllProjects() {
        return projectRepository.findAll()
                .stream()
                .map(ProjectDTO::fromEntity)
                .toList();
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

                    return projectRepository.save(existingProject);
                })
                .orElseThrow(() -> new RuntimeException("Project not found"));
    }
}
