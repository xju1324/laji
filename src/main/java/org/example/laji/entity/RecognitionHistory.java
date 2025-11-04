package org.example.laji.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "recognition_history")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecognitionHistory {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "user_id", nullable = false)
    private Long userId;
    
    @Column(name = "image_url", nullable = false, length = 500)
    private String imageUrl;
    
    @Column(name = "garbage_type", length = 50)
    private String garbageType;
    
    @Column(length = 100)
    private String category;
    
    @Column(columnDefinition = "DECIMAL(5,2)")
    private Double confidence;
    
    @Column(name = "recognition_result", columnDefinition = "TEXT")
    private String recognitionResult;
    
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}

