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
                .phoneNumber(userDTO.getPhoneNumber())
                .build();
        return userRepository.save(user);
    }

    //GET
    @Override
    public UserDTO getUserByPhoneNumber(String phoneNumber){
        return userRepository.findByPhoneNumber(phoneNumber)
                .map(user -> UserDTO.builder()
                        .name(user.getName())
                        .phoneNumber(user.getPhoneNumber())
                        .build())
                .orElseThrow(()->new UserNotFoundException("No User with: "+phoneNumber));
    }
    @Override
    public List<UserDTO> getAllUsers(){
        return userRepository.findAll().stream()
                .map(UserDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deleteUserByPhoneNumber(String phoneNumber){
        userRepository.deleteByPhoneNumber(phoneNumber);
    }
    @Override
    @Transactional
    public  UserDTO updateUserByPhoneNumber(String phoneNumber, String newName){
        User user = userRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(()->new UserNotFoundException("no user with"+phoneNumber));
        user.setName(newName);
        User updatedUser = userRepository.save(user);
        return UserDTO.builder()
                .name(user.getName())
                .phoneNumber(user.getPhoneNumber())
                .build();
    }
}
