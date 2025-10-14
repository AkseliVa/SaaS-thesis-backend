package com.thesis.saas.customer;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.thesis.saas.company.Company;
import com.thesis.saas.employee.Employee;
import com.thesis.saas.employee.EmployeeDTO;
import com.thesis.saas.project.Project;
import com.thesis.saas.project.ProjectDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="customers")
public class Customer {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long customer_id;

    @Column(nullable = false, unique=true)
    private String name;

    @JsonManagedReference
    @OneToMany(mappedBy = "customer", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Project> projects;

    private String contactPerson;

    private String contactEmail;

    private String contactPhone;

    @ManyToOne
    private Employee customerManager;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "company_id")
    private Company company;

    public Customer(String name) {this.name = name;}

    public Customer(String name, String contactPerson, String contactEmail, String contactPhone, Employee customerManager, Company company) {
        this.name = name;
        this.contactPerson = contactPerson;
        this.contactEmail = contactEmail;
        this.contactPhone = contactPhone;
        this.customerManager = customerManager;
        this.company = company;
    }

    public Customer(String name, String contactPerson, String contactEmail, String contactPhone, Employee customerManager, Company company, List<Project> projects) {
        this.name = name;
        this.contactPerson = contactPerson;
        this.contactEmail = contactEmail;
        this.contactPhone = contactPhone;
        this.customerManager = customerManager;
        this.projects = projects;
        this.company = company;
    }
}
