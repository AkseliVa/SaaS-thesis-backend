package com.thesis.saas.project;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = false)
public interface ProjectsEmployeesRepository extends CrudRepository<ProjectsEmployees, Long> {
}
