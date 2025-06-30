package com.thesis.saas.admin;

import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/api/admins")
    public Admin newAdmin(@RequestBody Admin admin) {
        return adminRepository.save(admin);
    }

    @GetMapping("/api/admins/{id}")
    public Admin getAdmin(@PathVariable long id) {
        return adminRepository.findById(id).orElse(null);
    }

    @DeleteMapping("/api/admins/{id}")
    public void deleteAdmin(@PathVariable long id) {
        adminRepository.deleteById(id);
    }
    
}
