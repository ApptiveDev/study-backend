package com.backend.apptive.service;

import com.backend.apptive.dto.AddUserRequest;
import com.backend.apptive.dto.UpdateUserRequest;
import com.backend.apptive.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import com.backend.apptive.domain.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public User save(AddUserRequest request) {
        return userRepository.save(request.toEntity());
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findByEmail(String email){
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("not found: " + email));
    }

    @Transactional
    public void deleteByEmail(String email){
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("not found: " + email));
        userRepository.delete(user);
    }

    @Transactional
    public User update(String email, UpdateUserRequest request){
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("not found: " + email));
        user.update(request.getName());
        return user;
    }
}
