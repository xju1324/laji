package org.example.laji.repository;

import org.example.laji.entity.RecognitionHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecognitionHistoryRepository extends JpaRepository<RecognitionHistory, Long> {
    Page<RecognitionHistory> findByUserId(Long userId, Pageable pageable);
    List<RecognitionHistory> findByUserIdOrderByCreatedAtDesc(Long userId);
    long countByUserId(Long userId);
}

