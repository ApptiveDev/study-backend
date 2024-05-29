package com.backend.apptive.service;

import com.backend.apptive.DTO.UserDTO;
import com.backend.apptive.domain.User;

import java.util.List;

public interface UserService {
    User registerUser(UserDTO userDTO);
    UserDTO getUserByEmail(String email);
    void deleteUserByEmail(String email);
    UserDTO updateUserByEmail(String email, String newName);
    List<UserDTO> getAllUsers();
}
