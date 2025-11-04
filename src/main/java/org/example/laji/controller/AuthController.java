package org.example.laji.controller;

import jakarta.validation.Valid;
import org.example.laji.dto.ApiResponse;
import org.example.laji.dto.LoginRequest;
import org.example.laji.dto.LoginResponse;
import org.example.laji.entity.Admin;
import org.example.laji.service.AdminService;
import org.example.laji.util.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    
    private final AdminService adminService;
    private final JwtUtil jwtUtil;
    
    public AuthController(AdminService adminService, JwtUtil jwtUtil) {
        this.adminService = adminService;
        this.jwtUtil = jwtUtil;
    }
    
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponse>> login(@Valid @RequestBody LoginRequest request) {
        // 查找管理员
        Admin admin = adminService.findByUsername(request.getUsername())
                .orElse(null);
        
        if (admin == null) {
            return ResponseEntity.ok(ApiResponse.error(401, "用户名或密码错误"));
        }
        
        // 验证密码
        if (!adminService.validatePassword(request.getPassword(), admin.getPassword())) {
            return ResponseEntity.ok(ApiResponse.error(401, "用户名或密码错误"));
        }
        
        // 生成JWT token
        String token = jwtUtil.generateToken(admin.getUsername());
        
        LoginResponse response = new LoginResponse(token, admin.getUsername(), "登录成功");
        return ResponseEntity.ok(ApiResponse.success(response));
    }
    
    @PostMapping("/register")
    public ResponseEntity<ApiResponse<Admin>> register(@RequestBody Admin admin) {
        // 检查用户名是否已存在
        if (adminService.existsByUsername(admin.getUsername())) {
            return ResponseEntity.ok(ApiResponse.error("用户名已存在"));
        }
        
        Admin createdAdmin = adminService.createAdmin(admin);
        return ResponseEntity.ok(ApiResponse.success("注册成功", createdAdmin));
    }
}

