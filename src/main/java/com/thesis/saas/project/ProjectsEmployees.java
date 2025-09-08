package com.thesis.saas.project;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.thesis.saas.employee.Employee;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="project_employees")
public class ProjectsEmployees {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long pe_id;

    @JsonBackReference
    @OneToMany
    private List<Employee> employees;

    @JsonBackReference
    @OneToOne
    @JoinColumn(name = "project_id")
    private Project project;
}
