package com.thesis.saas.company;

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
    private long id;

    @Column(nullable = false, unique=true)
    private String name;

    @OneToMany(mappedBy = "company")
    private List<Employee> employees;

    @OneToMany(mappedBy = "company")
    private List<Project> projects;

    public Company(String name) {
        this.name = name;
    }
}
