package org.example.laji.controller;

import org.example.laji.dto.ApiResponse;
import org.example.laji.entity.User;
import org.example.laji.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {
    
    private final UserService userService;
    
    public UserController(UserService userService) {
        this.userService = userService;
    }
    
    @GetMapping
    public ResponseEntity<ApiResponse<List<User>>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(ApiResponse.success(users));
    }
    
    @GetMapping("/page")
    public ResponseEntity<ApiResponse<Map<String, Object>>> getUsersPage(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy) {
        
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).descending());
        Page<User> userPage = userService.getAllUsers(pageable);
        
        Map<String, Object> response = new HashMap<>();
        response.put("users", userPage.getContent());
        response.put("currentPage", userPage.getNumber());
        response.put("totalItems", userPage.getTotalElements());
        response.put("totalPages", userPage.getTotalPages());
        
        return ResponseEntity.ok(ApiResponse.success(response));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<User>> getUserById(@PathVariable Long id) {
        return userService.getUserById(id)
                .map(user -> ResponseEntity.ok(ApiResponse.success(user)))
                .orElse(ResponseEntity.ok(ApiResponse.error("用户不存在")));
    }
    
    @PostMapping
    public ResponseEntity<ApiResponse<User>> createUser(@RequestBody User user) {
        User createdUser = userService.createUser(user);
        return ResponseEntity.ok(ApiResponse.success("创建成功", createdUser));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<User>> updateUser(@PathVariable Long id, @RequestBody User user) {
        return userService.getUserById(id)
                .map(existingUser -> {
                    user.setId(id);
                    User updatedUser = userService.updateUser(user);
                    return ResponseEntity.ok(ApiResponse.success("更新成功", updatedUser));
                })
                .orElse(ResponseEntity.ok(ApiResponse.error("用户不存在")));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteUser(@PathVariable Long id) {
        return userService.getUserById(id)
                .map(user -> {
                    userService.deleteUser(id);
                    return ResponseEntity.ok(ApiResponse.<Void>success("删除成功", null));
                })
                .orElse(ResponseEntity.ok(ApiResponse.error("用户不存在")));
    }
    
    @GetMapping("/count")
    public ResponseEntity<ApiResponse<Long>> countUsers() {
        long count = userService.countUsers();
        return ResponseEntity.ok(ApiResponse.success(count));
    }
}

