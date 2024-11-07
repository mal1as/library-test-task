package com.mal1as.librarytesttask.security;

import com.mal1as.librarytesttask.model.entity.User;
import com.mal1as.librarytesttask.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AccessTokenFilter extends OncePerRequestFilter {

    private final JwtHelper jwtHelper;
    private final UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Optional<String> accessToken = parseAccessToken(request);
        if(accessToken.isPresent() && jwtHelper.validateToken(accessToken.get())) {
            String userId = jwtHelper.getUserIdFromToken(accessToken.get());
            User user = userService.findById(Long.parseLong(userId));
            UsernamePasswordAuthenticationToken upat = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            upat.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(upat);
        }

        filterChain.doFilter(request, response);
    }

    private Optional<String> parseAccessToken(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if(StringUtils.hasText(authHeader) && authHeader.startsWith("Bearer ")) {
            return Optional.of(authHeader.replace("Bearer ", ""));
        }
        return Optional.empty();
    }
}
