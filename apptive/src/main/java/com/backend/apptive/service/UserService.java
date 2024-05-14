package com.backend.apptive.service;

import com.backend.apptive.dto.AddUserRequest;
import com.backend.apptive.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import com.backend.apptive.domain.User;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    public User save(AddUserRequest request) {
        return userRepository.save(request.toEntity());
    }
}
