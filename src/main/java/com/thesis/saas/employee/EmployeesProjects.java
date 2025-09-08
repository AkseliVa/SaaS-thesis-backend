package com.thesis.saas.employee;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Table(name="employee_projects")
public class EmployeesProjects {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ep_id;

    @JsonBackReference
    @OneToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @JsonBackReference
    @OneToMany
    private List<Project> projects;
}
