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

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    //POST
    @Override
    @Transactional
    public User registerUser(UserDTO userDTO) {
        User user = User.builder()
                .name(userDTO.getName())
                .email(userDTO.getEmail())
                .build();
        return userRepository.save(user);
    }
    //GET
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
    public List<UserDTO> getAllUsers(){
        return userRepository.findAll().stream()
                .map(UserDTO::fromEntity)
                .collect(Collectors.toList());
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
