package com.thesis.saas.company;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CompanyController {
    private final CompanyRepository companyRepository;

    public CompanyController(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @GetMapping("/api/companies")
    public Iterable<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @PostMapping("/api/companies")
    public Company newCompany(@RequestBody Company company) {
        return companyRepository.save(company);
    }
}
