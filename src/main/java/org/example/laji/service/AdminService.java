package org.example.laji.service;

import org.example.laji.entity.Admin;
import org.example.laji.repository.AdminRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AdminService {
    
    private final AdminRepository adminRepository;
    
    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }
    
    public Optional<Admin> findByUsername(String username) {
        return adminRepository.findByUsername(username);
    }
    
    public Admin createAdmin(Admin admin) {
        // 密码明文存储，不加密
        return adminRepository.save(admin);
    }
    
    public boolean validatePassword(String rawPassword, String storedPassword) {
        // 明文密码直接比较
        return rawPassword != null && rawPassword.equals(storedPassword);
    }
    
    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }
    
    public boolean existsByUsername(String username) {
        return adminRepository.existsByUsername(username);
    }
}

