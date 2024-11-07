package com.mal1as.librarytesttask.controller;

import com.mal1as.librarytesttask.model.dto.SuccessResponse;
import com.mal1as.librarytesttask.service.OperationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/library/operation")
@RequiredArgsConstructor
public class OperationController {

    private final OperationService operationService;

    @PostMapping
    public ResponseEntity<SuccessResponse<?>> saveOperation(@RequestParam Long clientId, @RequestParam Long bookId) {
        operationService.saveOperation(clientId, bookId);
        return ResponseEntity.ok(new SuccessResponse<>());
    }
}
