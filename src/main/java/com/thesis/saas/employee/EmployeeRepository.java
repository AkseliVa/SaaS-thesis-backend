package com.thesis.saas.employee;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = false)
public interface EmployeeRepository extends CrudRepository<Employee, Long> {
}
