package com.thesis.saas.company;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CompanyController {
    private final CompanyRepository companyRepository;

    public CompanyController(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @GetMapping("/api/companies")
    public List<CompanyDTO> getAllCompanies() {
        return companyRepository.findAll().stream()
                .map(CompanyDTO::fromEntity)
                .toList();
    }

    @GetMapping("/api/companies/{id}")
    public CompanyDTO getCompany(@PathVariable long id) {
        return companyRepository.findById(id)
                .map(CompanyDTO::fromEntity)
                .orElse(null);
    }

    @PostMapping("/api/companies")
    public Company newCompany(@RequestBody Company company) {
        return companyRepository.save(company);
    }

    @DeleteMapping("/api/companies/{id}")
    public void deleteCompany(@PathVariable long id) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Company not found"));

        companyRepository.deleteById(id);
    }
}
