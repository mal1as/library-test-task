package com.mal1as.librarytesttask.controller;

import com.mal1as.librarytesttask.model.dto.ErrorResponse;
import com.mal1as.librarytesttask.model.dto.SuccessResponse;
import com.mal1as.librarytesttask.service.OperationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/library/operation")
@RequiredArgsConstructor
public class OperationController {

    private final OperationService operationService;

    @Operation(
            summary = "Create new operation",
            description = "Create new operation with book and client (TAKE or RETURN)"
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
    @PostMapping
    public ResponseEntity<SuccessResponse<?>> saveOperation(@RequestParam Long clientId, @RequestParam Long bookId) {
        operationService.saveOperation(clientId, bookId);
        return ResponseEntity.ok(new SuccessResponse<>());
    }
}
