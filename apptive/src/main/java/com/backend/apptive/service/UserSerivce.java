package com.backend.apptive.service;

import com.backend.apptive.DTO.UserDTO;
import com.backend.apptive.core.error.CustomException;
import com.backend.apptive.core.error.UserNotFoundException;
import com.backend.apptive.domain.User;
import com.backend.apptive.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import static com.backend.apptive.core.error.ErrorCode.ALREADY_SAVED_NAME;

@Service
@Transactional(readOnly = true)
public class UserSerivce {
    private final UserRepository userRepository;

    //build 패턴 적용하기
    public UserSerivce(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    // bulid 패턴 사용, toDto 사용
    public UserDTO getUser(String email) {
       User user =  userRepository.findById(email).orElseThrow(() -> new UserNotFoundException("Employee not found with id: " + email));
       String e = user.getEmail();
       String name = user.getName();
       UserDTO userDTO = new UserDTO();
       userDTO.setEmail(e);
       userDTO.setName(name);
       return userDTO;
    }
    //toEntity 함수 사용
    @Transactional
    public boolean registerUser(UserDTO userDTO){
        User user = userDTO.toEntity();
        user
        // 예외 던지기
        Boolean isExist = userRepository.existsById(email);
        if (isExist){
             throw new CustomException(ALREADY_SAVED_NAME);
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
        // user를 새로 만듦 ??
        User updatedUser = existingUser.withName(newName);
        userRepository.save(updatedUser);
        //setter 사용보다 빌더 패턴 사용
        UserDTO userDTO = new UserDTO();
        userDTO.setName(updatedUser.getName());
        userDTO.setEmail(updatedUser.getEmail());
        return userDTO;
    }
}
