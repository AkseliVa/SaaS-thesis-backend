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
@Table(name="admin")
public class Admin {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long admin_id;

    @Column(nullable = false, unique=true)
    private String username;

    @Column(nullable = false)
    private String password;

    @OneToOne(orphanRemoval = true)
    private Company company;

    @Column
    private String role;

    public Admin(String username, String password, Company company, String role) {
        this.username = username;
        this.password = password;
        this.company = company;
        this.role = role;
    }

    public Admin(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }
}
