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
public class Company {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    private String name;

    @OneToMany
    private List<Employee> employees;

    @OneToMany
    private List<Project> projects;

    public Company(String name) {
        this.name = name;
    }
}
