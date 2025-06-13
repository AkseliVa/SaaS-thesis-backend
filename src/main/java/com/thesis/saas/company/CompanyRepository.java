package com.thesis.saas.company;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = false)
public interface CompanyRepository extends CrudRepository<Company, Long> {
}
