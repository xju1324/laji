package org.example.laji.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "wechat_openid", unique = true, length = 100)
    private String wechatOpenid;
    
    @Column(length = 100)
    private String nickname;
    
    @Column(name = "avatar_url", length = 500)
    private String avatarUrl;
    
    @Column(length = 20)
    private String phone;
    
    @Column(name = "points", columnDefinition = "INT DEFAULT 0")
    private Integer points;
    
    @Column(name = "total_recognition", columnDefinition = "INT DEFAULT 0")
    private Integer totalRecognition;
    
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
        if (points == null) points = 0;
        if (totalRecognition == null) totalRecognition = 0;
    }
    
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}

