package com.thesis.saas.admin;

import com.thesis.saas.company.Company;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Admin {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    private String username;
    private String password;
    private String firstname;
    private String lastname;

    @OneToOne
    private Company company;

    public Admin(String username, String password, String firstname, String lastname, Company company) {
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.company = company;
    }
}
