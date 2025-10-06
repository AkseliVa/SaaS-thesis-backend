package com.thesis.saas.project;

import com.fasterxml.jackson.annotation.*;
import com.thesis.saas.company.Company;
import com.thesis.saas.customer.Customer;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="projects")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long project_id;

    @Column(nullable = false)
    private String name;

    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private Boolean active;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name="customer_id")
    private Customer customer;

    @JsonManagedReference
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProjectsEmployees> projectsEmployees;

    public Project(String name, String description, LocalDate startDate, LocalDate endDate, Company company) {
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.company = company;
    }

    public Project(String name, String description, LocalDate startDate, LocalDate endDate, Company company, Boolean active) {
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.company = company;
        this.active = active;
    }

    public Project(String name, String description, LocalDate startDate, LocalDate endDate, Company company, Boolean active, Customer customer) {
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.company = company;
        this.active = active;
        this.customer = customer;
    }

}
