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
@Table(name="employee")
public class Employee {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private String firstname;

    @Column(nullable = false)
    private String lastname;

    private String email;
    private String phone;
    private String role;

    @ManyToMany(mappedBy = "workers")
    private List<Project> projects;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    public Employee(String firstname, String lastname, String email, String phone, String role) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.phone = phone;
        this.role = role;
    }
}
