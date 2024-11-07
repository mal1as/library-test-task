package com.mal1as.librarytesttask.controller;

import com.mal1as.librarytesttask.model.dto.ErrorResponse;
import com.mal1as.librarytesttask.model.dto.LoginDTO;
import com.mal1as.librarytesttask.model.dto.RefreshTokenDTO;
import com.mal1as.librarytesttask.model.dto.SuccessResponse;
import com.mal1as.librarytesttask.model.dto.TokenDTO;
import com.mal1as.librarytesttask.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @Operation(
            summary = "Login",
            description = "Login in system and get tokens"
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
    @PostMapping("/login")
    public ResponseEntity<SuccessResponse<TokenDTO>> login(@Valid @RequestBody LoginDTO loginDTO) {
        return ResponseEntity.ok(SuccessResponse.<TokenDTO>builder()
                .content(authService.login(loginDTO))
                .build());
    }

    @Operation(
            summary = "Refresh token",
            description = "Refresh access token via access token"
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
    @PostMapping("/refreshToken")
    public ResponseEntity<SuccessResponse<TokenDTO>> refreshToken(@Valid @RequestBody RefreshTokenDTO refreshTokenDTO) {
        return ResponseEntity.ok(SuccessResponse.<TokenDTO>builder()
                .content(authService.refreshToken(refreshTokenDTO.getRefreshToken()))
                .build());
    }
}
