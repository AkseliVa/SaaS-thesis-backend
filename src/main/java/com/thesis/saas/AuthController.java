package com.thesis.saas;

import com.thesis.saas.admin.Admin;
import com.thesis.saas.admin.AdminRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AdminRepository repo;
    private final PasswordEncoder encoder;
    private final AuthenticationManager authManager;
    private final JwtService jwtService;

    public AuthController(AdminRepository repo, PasswordEncoder encoder,
                          AuthenticationManager authManager, JwtService jwtService) {
        this.repo = repo;
        this.encoder = encoder;
        this.authManager = authManager;
        this.jwtService = jwtService;
    }

    record RegisterRequest(String username, String password, String role) {
    }

    record LoginRequest(String username, String password) {
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest req) {
        if (repo.findByUsername(req.username()).isPresent()) {
            return ResponseEntity.badRequest().body("username taken");
        }
        var user = new Admin(req.username(), encoder.encode(req.password()), req.role() == null ? "USER" : req.role());
        repo.save(user);
        return ResponseEntity.ok("ok");
    }

    record AuthResponse(String token, long adminId, long companyId) { }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest req) {
        Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.username(), req.password()));
        var user = (org.springframework.security.core.userdetails.User) auth.getPrincipal();
        Admin admin = repo.findByUsername(user.getUsername()).get(); // fetch Admin entity
        String role = user.getAuthorities().stream().findFirst().map(a -> a.getAuthority()).orElse("ROLE_USER");
        String token = jwtService.generateToken(user.getUsername(), role);
        return ResponseEntity.ok(new AuthResponse(token, admin.getAdmin_id(), admin.getCompany().getCompany_id()));
    }
}

