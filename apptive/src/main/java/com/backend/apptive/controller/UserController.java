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

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public User registerUser(@RequestBody UserDTO userDTO){
        return userService.registerUser(userDTO);
    }

    @GetMapping("/{email}")
    private String getUserByEmail(@PathVariable String email){
        return userService.getUserByEmail(email).getName();
    }
    @DeleteMapping("/{email}")
    public ResponseEntity<Void> deleteUserByEmail(@PathVariable String email){
        userService.deleteUserByEmail(email);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{email}")
    public ResponseEntity<UserDTO> updateUserByEmail(@PathVariable String email,@RequestBody UserDTO userDTO){
        UserDTO updatedUser = userService.updateUserByEmail(email,userDTO.getName());
        return ResponseEntity.ok(updatedUser);
    }

}
