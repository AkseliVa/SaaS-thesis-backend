package com.thesis.saas.project;

import com.thesis.saas.company.Company;
import com.thesis.saas.employee.Employee;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;
    private String description;
    private Date startDate;
    private Date endDate;

    @ManyToOne
    private Company company;

    @ManyToMany
    private List<Employee> workers;

    public Project(String name, String description, Date startDate, Date endDate, Company company) {
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.company = company;
    }
}
