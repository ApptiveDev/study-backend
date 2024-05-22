package com.backend.apptive.service;

import com.backend.apptive.dto.UserDto;
import com.backend.apptive.exception.DuplicateEmailException;
import com.backend.apptive.exception.ResourceNotFoundException;
import com.backend.apptive.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import com.backend.apptive.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class UserService {
    @Autowired
    private final UserRepository userRepository;

    @Transactional
    public void save(UserDto.Request request) throws RuntimeException {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateEmailException("이미 존재하는 이메일입니다: " + request.getEmail());
        }
        userRepository.save(request.toEntity());
    }

    public List<UserDto.Response> findAll() {
        return userRepository.findAll()
                .stream()
                .map(UserDto.Response::toDto)
                .toList();
    }

    public UserDto.Response findByEmail(String email){
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("유저를 찾을 수 없습니다: " + email));
        return UserDto.Response.toDto(user);
    }

    @Transactional
    public void deleteByEmail(String email){
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("유저를 찾을 수 없습니다: " + email));
        userRepository.delete(user);
    }

    @Transactional
    public void update(String email, UserDto.Request request){
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("유저를 찾을 수 없습니다: " + email));
        user.update(request.getName());
    }
}
