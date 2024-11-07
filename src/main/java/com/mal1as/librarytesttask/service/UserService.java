package com.mal1as.librarytesttask.service;

import com.mal1as.librarytesttask.model.entity.User;
import com.mal1as.librarytesttask.repository.UserRepository;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static com.mal1as.librarytesttask.utils.ErrorMessages.USER_NOT_FOUND_BY_ID_MESSAGE;
import static com.mal1as.librarytesttask.utils.ErrorMessages.USERNAME_NOT_FOUND_MESSAGE;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format(USERNAME_NOT_FOUND_MESSAGE, username)));
    }

    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ValidationException(String.format(USER_NOT_FOUND_BY_ID_MESSAGE, id)));
    }
}
