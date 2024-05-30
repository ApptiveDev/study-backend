package com.backend.apptive.service;

import com.backend.apptive.DTO.UserDTO;
import com.backend.apptive.domain.User;

import java.time.LocalDate;
import java.util.List;

public interface UserService {
    User registerUser(UserDTO userDTO);

    //GET
    UserDTO getUserByPhoneNumber(String phoneNumber);
    List<UserDTO> getAllUsers();

    //DELETE
    void deleteUserByPhoneNumber(String phoneNumber);
    UserDTO updateUserByPhoneNumber(String phoneNumber, String newName);
}
