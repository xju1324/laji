package org.example.laji.service;

import org.example.laji.entity.RecognitionHistory;
import org.example.laji.repository.RecognitionHistoryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RecognitionHistoryService {
    
    private final RecognitionHistoryRepository historyRepository;
    
    public RecognitionHistoryService(RecognitionHistoryRepository historyRepository) {
        this.historyRepository = historyRepository;
    }
    
    public List<RecognitionHistory> getAllHistories() {
        return historyRepository.findAll();
    }
    
    public Page<RecognitionHistory> getAllHistories(Pageable pageable) {
        return historyRepository.findAll(pageable);
    }
    
    public Page<RecognitionHistory> getHistoriesByUserId(Long userId, Pageable pageable) {
        return historyRepository.findByUserId(userId, pageable);
    }
    
    public List<RecognitionHistory> getHistoriesByUserId(Long userId) {
        return historyRepository.findByUserIdOrderByCreatedAtDesc(userId);
    }
    
    public Optional<RecognitionHistory> getHistoryById(Long id) {
        return historyRepository.findById(id);
    }
    
    public RecognitionHistory createHistory(RecognitionHistory history) {
        return historyRepository.save(history);
    }
    
    public void deleteHistory(Long id) {
        historyRepository.deleteById(id);
    }
    
    public long countByUserId(Long userId) {
        return historyRepository.countByUserId(userId);
    }
    
    public long countAll() {
        return historyRepository.count();
    }
}

