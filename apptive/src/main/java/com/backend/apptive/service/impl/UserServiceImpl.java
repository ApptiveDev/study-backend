package com.backend.apptive.service.impl;

import com.backend.apptive.DTO.UserDTO;
import com.backend.apptive.domain.User;
import com.backend.apptive.exception.UserNotFoundException;
import com.backend.apptive.repository.UserRepository;
import com.backend.apptive.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Override
    @Transactional
    public User registerUser(UserDTO userDTO) {
        User user = User.builder()
                .name(userDTO.getName())
                .email(userDTO.getEmail())
                .build();
        return userRepository.save(user);
    }
    @Override
    public UserDTO getUserByEmail(String email){
        return userRepository.findByEmail(email)
                .map(user -> UserDTO.builder()
                        .name(user.getName())
                        .email(user.getEmail())
                        .build())
                .orElseThrow(()->new UserNotFoundException("No User with: "+email));
    }
    @Override
    @Transactional
    public void deleteUserByEmail(String email){
        userRepository.deleteByEmail(email);
    }
    @Override
    @Transactional
    public  UserDTO updateUserByEmail(String email, String newName){
        User user = userRepository.findByEmail(email)
                .orElseThrow(()->new UserNotFoundException("no user with"+email));
        user.setName(newName);
        User updatedUser = userRepository.save(user);
        return UserDTO.builder()
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }
}
