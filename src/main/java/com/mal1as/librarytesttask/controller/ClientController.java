package com.mal1as.librarytesttask.controller;

import com.mal1as.librarytesttask.model.dto.ClientDTO;
import com.mal1as.librarytesttask.model.dto.ErrorResponse;
import com.mal1as.librarytesttask.model.dto.SuccessResponse;
import com.mal1as.librarytesttask.service.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(
            summary = "Get most reading client",
            description = "Get client that read most books"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Ok", useReturnTypeSchema = true),
                    @ApiResponse(responseCode = "400", description = "Bad request",
                            content = {@Content(schema = @Schema(implementation = ErrorResponse.class),
                                    mediaType = "application/json")}),
                    @ApiResponse(responseCode = "500", description = "Internal server error",
                            content = {@Content(schema = @Schema(implementation = ErrorResponse.class),
                                    mediaType = "application/json")})
            }
    )
    @GetMapping("/mostReading")
    public ResponseEntity<SuccessResponse<ClientDTO>> findMostReading() {
        return ResponseEntity.ok(SuccessResponse.<ClientDTO>builder()
                .content(clientService.findMostReading())
                .build());
    }

    @Operation(
            summary = "Get all clients",
            description = "Get all clients with sorting by non-return books count"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Ok", useReturnTypeSchema = true),
                    @ApiResponse(responseCode = "400", description = "Bad request",
                            content = {@Content(schema = @Schema(implementation = ErrorResponse.class),
                                    mediaType = "application/json")}),
                    @ApiResponse(responseCode = "500", description = "Internal server error",
                            content = {@Content(schema = @Schema(implementation = ErrorResponse.class),
                                    mediaType = "application/json")})
            }
    )
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
