package com.mal1as.librarytesttask.controller;

import com.mal1as.librarytesttask.model.dto.AuthorDTO;
import com.mal1as.librarytesttask.model.dto.ErrorResponse;
import com.mal1as.librarytesttask.model.dto.SuccessResponse;
import com.mal1as.librarytesttask.service.AuthorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/library/author")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @Operation(
            summary = "Get most popular author",
            description = "Get most popular author in library between two dates"
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
    @GetMapping("/mostPopular")
    public ResponseEntity<SuccessResponse<AuthorDTO>> getMostPopularAuthorBetween(@RequestParam LocalDate from,
                                                                                  @RequestParam LocalDate to) {
        return ResponseEntity.ok(SuccessResponse.<AuthorDTO>builder()
                .content(authorService.getMostPopularBetween(from, to))
                .build());
    }
}
