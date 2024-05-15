package com.backend.apptive.service;

import com.backend.apptive.DTO.UserDTO;
import com.backend.apptive.core.error.UserNotFoundException;
import com.backend.apptive.domain.User;
import com.backend.apptive.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class UserSerivce {
    private final UserRepository userRepository;

    public UserSerivce(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public UserDTO getUser(String email) {
       User user =  userRepository.findById(email).orElseThrow(() -> new UserNotFoundException("Employee not found with id: " + email));
       String e = user.getEmail();
       String name = user.getName();
       UserDTO userDTO = new UserDTO();
       userDTO.setEmail(e);
       userDTO.setName(name);
       return userDTO;
    }
    @Transactional
    public boolean registerUser(UserDTO userDTO){
        String email = userDTO.getEmail();
        String name = userDTO.getName();

        User user = new User(email,name);

        Boolean isExist = userRepository.existsById(email);

        if (isExist){
            return false;
        }

        userRepository.save(user);

        return true;
    }

    @Transactional
    public boolean deleteUserByEmail(String email) {
        if (userRepository.existsById(email)) {
            userRepository.deleteById(email);
            return true;
        } else {
            return false;
        }
    }

    @Transactional
    public UserDTO updateUserEmail(String email, String newName) {
        User existingUser = userRepository.findById(email)
            .orElseThrow(() -> new UserNotFoundException("User not found with email: "));
        User updatedUser = existingUser.withName(newName);
        userRepository.save(updatedUser);
        UserDTO userDTO = new UserDTO();
        userDTO.setName(updatedUser.getName());
        userDTO.setEmail(updatedUser.getEmail());
        return userDTO;
    }
}
