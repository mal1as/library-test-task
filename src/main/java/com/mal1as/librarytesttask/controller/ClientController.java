package com.mal1as.librarytesttask.controller;

import com.mal1as.librarytesttask.model.dto.ClientDTO;
import com.mal1as.librarytesttask.model.dto.SuccessResponse;
import com.mal1as.librarytesttask.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/library/client")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @GetMapping("/mostReading")
    public ResponseEntity<SuccessResponse<ClientDTO>> findMostReading() {
        return ResponseEntity.ok(SuccessResponse.<ClientDTO>builder()
                .content(clientService.findMostReading())
                .build());
    }

    @GetMapping("/byNonReturnCount")
    public ResponseEntity<SuccessResponse<List<ClientDTO>>> getAllByNonReturnCount(Pageable pageable) {
        Page<ClientDTO> clientPage = clientService.getAllByNonReturnCount(pageable);
        return ResponseEntity.ok(SuccessResponse.<List<ClientDTO>>builder()
                .content(clientPage.toList())
                .totalCount(clientPage.getTotalElements())
                .totalPages(clientPage.getTotalPages())
                .build());
    }
}
