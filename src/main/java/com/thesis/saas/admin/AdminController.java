package com.thesis.saas.admin;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {
    private final AdminRepository adminRepository;

    public AdminController(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @GetMapping("/api/admins")
    public Iterable<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }
}
