package com.thesis.saas.admin;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = false)
public interface AdminRepository extends CrudRepository<Admin, Long> {
}
