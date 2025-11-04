package org.example.laji.controller;

import org.example.laji.dto.ApiResponse;
import org.example.laji.entity.RecognitionHistory;
import org.example.laji.service.RecognitionHistoryService;
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
@RequestMapping("/api/recognition-history")
public class RecognitionHistoryController {
    
    private final RecognitionHistoryService historyService;
    
    public RecognitionHistoryController(RecognitionHistoryService historyService) {
        this.historyService = historyService;
    }
    
    @GetMapping
    public ResponseEntity<ApiResponse<List<RecognitionHistory>>> getAllHistories() {
        List<RecognitionHistory> histories = historyService.getAllHistories();
        return ResponseEntity.ok(ApiResponse.success(histories));
    }
    
    @GetMapping("/page")
    public ResponseEntity<ApiResponse<Map<String, Object>>> getHistoriesPage(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) Long userId) {
        
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        Page<RecognitionHistory> historyPage;
        
        if (userId != null) {
            historyPage = historyService.getHistoriesByUserId(userId, pageable);
        } else {
            historyPage = historyService.getAllHistories(pageable);
        }
        
        Map<String, Object> response = new HashMap<>();
        response.put("histories", historyPage.getContent());
        response.put("currentPage", historyPage.getNumber());
        response.put("totalItems", historyPage.getTotalElements());
        response.put("totalPages", historyPage.getTotalPages());
        
        return ResponseEntity.ok(ApiResponse.success(response));
    }
    
    @GetMapping("/user/{userId}")
    public ResponseEntity<ApiResponse<List<RecognitionHistory>>> getHistoriesByUserId(@PathVariable Long userId) {
        List<RecognitionHistory> histories = historyService.getHistoriesByUserId(userId);
        return ResponseEntity.ok(ApiResponse.success(histories));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<RecognitionHistory>> getHistoryById(@PathVariable Long id) {
        return historyService.getHistoryById(id)
                .map(history -> ResponseEntity.ok(ApiResponse.success(history)))
                .orElse(ResponseEntity.ok(ApiResponse.error("记录不存在")));
    }
    
    @PostMapping
    public ResponseEntity<ApiResponse<RecognitionHistory>> createHistory(@RequestBody RecognitionHistory history) {
        RecognitionHistory createdHistory = historyService.createHistory(history);
        return ResponseEntity.ok(ApiResponse.success("创建成功", createdHistory));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteHistory(@PathVariable Long id) {
        return historyService.getHistoryById(id)
                .map(history -> {
                    historyService.deleteHistory(id);
                    return ResponseEntity.ok(ApiResponse.<Void>success("删除成功", null));
                })
                .orElse(ResponseEntity.ok(ApiResponse.error("记录不存在")));
    }
    
    @GetMapping("/count")
    public ResponseEntity<ApiResponse<Long>> countHistories(@RequestParam(required = false) Long userId) {
        long count = userId != null ? historyService.countByUserId(userId) : historyService.countAll();
        return ResponseEntity.ok(ApiResponse.success(count));
    }
}

