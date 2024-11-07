package com.mal1as.librarytesttask.controller;

import com.mal1as.librarytesttask.model.dto.LoginDTO;
import com.mal1as.librarytesttask.model.dto.RefreshTokenDTO;
import com.mal1as.librarytesttask.model.dto.SuccessResponse;
import com.mal1as.librarytesttask.model.dto.TokenDTO;
import com.mal1as.librarytesttask.service.AuthService;
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

    @PostMapping("/login")
    public ResponseEntity<SuccessResponse<TokenDTO>> login(@Valid @RequestBody LoginDTO loginDTO) {
        return ResponseEntity.ok(SuccessResponse.<TokenDTO>builder()
                .content(authService.login(loginDTO))
                .build());
    }

    @PostMapping("/refreshToken")
    public ResponseEntity<SuccessResponse<TokenDTO>> refreshToken(@Valid @RequestBody RefreshTokenDTO refreshTokenDTO) {
        return ResponseEntity.ok(SuccessResponse.<TokenDTO>builder()
                .content(authService.refreshToken(refreshTokenDTO.getRefreshToken()))
                .build());
    }
}
