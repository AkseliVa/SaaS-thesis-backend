package com.thesis.saas.company;

import com.thesis.saas.project.Project;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/api/companies/{id}")
    public Company getCompany(@PathVariable long id) {
        return companyRepository.findById(id).orElse(null);
    }

    @PostMapping("/api/companies")
    public Company newCompany(@RequestBody Company company) {
        return companyRepository.save(company);
    }

    @DeleteMapping("/api/companies/{id}")
    public void deleteCompany(@PathVariable long id) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Company not found"));

        //for (Project project : company.getProjects()) {
        //  project.getProjectEmployees().clear();
        //}

        companyRepository.deleteById(id);
    }
}
