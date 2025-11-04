package org.example.laji.service;

import org.example.laji.entity.Admin;
import org.example.laji.repository.AdminRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AdminService {
    
    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;
    
    public AdminService(AdminRepository adminRepository, PasswordEncoder passwordEncoder) {
        this.adminRepository = adminRepository;
        this.passwordEncoder = passwordEncoder;
    }
    
    public Optional<Admin> findByUsername(String username) {
        return adminRepository.findByUsername(username);
    }
    
    public Admin createAdmin(Admin admin) {
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        return adminRepository.save(admin);
    }
    
    public boolean validatePassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
    
    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }
    
    public boolean existsByUsername(String username) {
        return adminRepository.existsByUsername(username);
    }
}

