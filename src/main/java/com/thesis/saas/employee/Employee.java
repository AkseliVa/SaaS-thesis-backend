package com.thesis.saas.employee;

import com.thesis.saas.company.Company;
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
public class Employee {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    private String firstname;
    private String lastname;
    private String email;
    private String phone;
    private String role;

    @ManyToMany
    private List<Project> projects;

    @ManyToOne
    private Company company;

    public Employee(String firstname, String lastname, String email, String phone, String role) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.phone = phone;
        this.role = role;
    }
}
