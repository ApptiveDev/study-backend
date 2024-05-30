package com.backend.apptive.controller;

import com.backend.apptive.DTO.UserDTO;
import com.backend.apptive.domain.User;
import com.backend.apptive.repository.UserRepository;
import com.backend.apptive.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/register")
    public User registerUser(@RequestBody UserDTO userDTO){
        return userService.registerUser(userDTO);
    }
    @GetMapping("")
    public ResponseEntity<List<UserDTO>> getUserList(){
        List<UserDTO> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{phonNumber}")
    private String getUserNameAndphoneNumber(@PathVariable("name") String name){
        return userService.getUserByPhoneNumber(name).getName();
    }

    @DeleteMapping("/{phoneNumber}")
    public ResponseEntity<Void> deleteUserByPhoneNumber(@PathVariable("phoneNumber") String phoneNumber){
        userService.deleteUserByPhoneNumber(phoneNumber);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{phoneNumber}")
    public ResponseEntity<UserDTO> updateUserByPhoneNumber(@PathVariable("phoneNumber") String phoneNumber,@RequestBody UserDTO userDTO){
        UserDTO updatedUser = userService.updateUserByPhoneNumber(phoneNumber,userDTO.getName());
        return ResponseEntity.ok(updatedUser);
    }
}