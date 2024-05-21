package com.backend.apptive.service;

import com.backend.apptive.dto.UserDto;
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
    public void save(UserDto request) throws RuntimeException {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("이미 사용중인 이메일 입니다.");
        }
        userRepository.save(request.toEntity());
    }

    public List<UserDto> findAll() {
        List<UserDto> users = userRepository.findAll().stream().map(UserDto::toDto).toList();
        return users;
    }

    public UserDto findByEmail(String email){
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("not found: " + email));
        return UserDto.toDto(user);
    }

    @Transactional
    public void deleteByEmail(String email){
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("not found: " + email));
        userRepository.delete(user);
    }

    @Transactional
    public UserDto update(String email, UserDto request){
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("not found: " + email));
        user.update(request.getName());

        return UserDto.toDto(user);
    }
}
