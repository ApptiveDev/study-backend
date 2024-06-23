package com.backend.apptive.service;

import com.backend.apptive.domain.User;
import com.backend.apptive.dto.UserDto;
import com.backend.apptive.exception.DuplicateEmailException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserService {
    void save(UserDto.Request request);
    List<UserDto.Response> findAll();
    public UserDto.Response findByEmail(String email);
    void deleteByEmail(String email);
    void update(String email, UserDto.Request request);
}
