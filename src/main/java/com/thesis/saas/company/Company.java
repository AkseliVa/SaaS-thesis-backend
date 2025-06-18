package com.thesis.saas.company;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.thesis.saas.employee.Employee;
import com.thesis.saas.project.Project;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="companies")
public class Company {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long company_id;

    @Column(nullable = false, unique=true)
    private String name;

    @JsonManagedReference
    @OneToMany(mappedBy = "company")
    private List<Employee> employees;

    @OneToMany(mappedBy = "company")
    private List<Project> projects;

    public Company(String name) {
        this.name = name;
    }
}
