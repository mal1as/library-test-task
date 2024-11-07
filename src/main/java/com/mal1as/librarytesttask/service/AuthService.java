package com.mal1as.librarytesttask.service;

import com.mal1as.librarytesttask.model.dto.LoginDTO;
import com.mal1as.librarytesttask.model.dto.TokenDTO;
import com.mal1as.librarytesttask.model.entity.User;
import com.mal1as.librarytesttask.repository.UserRepository;
import com.mal1as.librarytesttask.security.JwtHelper;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import static com.mal1as.librarytesttask.utils.ErrorMessages.INVALID_TOKEN_MESSAGE;
import static com.mal1as.librarytesttask.utils.ErrorMessages.USER_NOT_FOUND_BY_ID_MESSAGE;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtHelper jwtHelper;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;

    public TokenDTO login(LoginDTO loginDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        User user = (User) authentication.getPrincipal();

        return TokenDTO.builder()
                .accessToken(jwtHelper.generateAccessToken(user))
                .refreshToken(jwtHelper.generateRefreshToken(user))
                .build();
    }

    public TokenDTO refreshToken(String refreshToken) {
        if (jwtHelper.validateToken(refreshToken)) {
            Long userId = Long.parseLong(jwtHelper.getUserIdFromToken(refreshToken));
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new ValidationException(String.format(USER_NOT_FOUND_BY_ID_MESSAGE, userId)));
            return TokenDTO.builder()
                    .accessToken(jwtHelper.generateAccessToken(user))
                    .refreshToken(refreshToken)
                    .build();
        }

        throw new ValidationException(String.format(INVALID_TOKEN_MESSAGE, refreshToken));
    }
}
