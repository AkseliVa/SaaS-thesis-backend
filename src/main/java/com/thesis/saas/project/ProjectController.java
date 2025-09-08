package com.thesis.saas.project;

import org.springframework.web.bind.annotation.*;

@RestController
public class ProjectController {
    private final ProjectRepository projectRepository;

    public ProjectController(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @GetMapping("/api/projects")
    public Iterable<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    @PostMapping("/api/projects")
    public Project newProject(@RequestBody Project project) {
        return projectRepository.save(project);
    }

    @DeleteMapping("/api/projects/{id}")
    public void deleteProject(@PathVariable Long id) {
        projectRepository.deleteById(id);
    }

    @PutMapping("/api/projects/{id}")
    public Project updateProject(@PathVariable Long id, @RequestBody Project project) {
        return projectRepository.findById(id)
                .map(existingProject -> {
                    existingProject.setName(project.getName());
                    existingProject.setDescription(project.getDescription());
                    existingProject.setStartDate(project.getStartDate());
                    existingProject.setEndDate(project.getEndDate());
                    existingProject.setActive(project.getActive());

                    return projectRepository.save(existingProject);
                })
                .orElseThrow(() -> new RuntimeException("Project not found"));
    }
}
