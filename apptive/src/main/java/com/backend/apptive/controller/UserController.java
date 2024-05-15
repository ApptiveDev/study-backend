package com.backend.apptive.controller;

import com.backend.apptive.DTO.UserDTO;
import com.backend.apptive.core.error.UserNotFoundException;
import com.backend.apptive.service.UserSerivce;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserSerivce userSerivce;

//    @GetMapping("/getdb")
//    public ResponseEntity<?> getUser(@RequestParam("email") String email) {
//        try {
//            UserDTO dto = userSerivce.getUser(email);
//            return ResponseEntity.ok(dto);
//        } catch (IllegalArgumentException ex) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: User not found.");
//        }
//    }
    @GetMapping("/getuser")
    public ResponseEntity<?> getUserById(@RequestParam String email) {
        try {
            UserDTO userDTO = userSerivce.getUser(email);
            return ResponseEntity.ok(userDTO);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found with email: " + email);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(UserDTO userDTO){
        boolean flag = userSerivce.registerUser(userDTO);
        if (flag){
            return ResponseEntity.ok("successfully register");
        }else{
            return ResponseEntity.status(HttpStatus.CONFLICT)
                .body("Registration failed: The email is already in use.");
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteUserByEmail(@RequestParam String email) {
        boolean isDeleted = userSerivce.deleteUserByEmail(email);
        if (isDeleted) {
            return ResponseEntity.ok( "successfully deleted.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found with email: " + email);
        }
    }

    @PatchMapping("/update")
    public ResponseEntity<String> updateUser(@RequestBody UserDTO userDTO) {
        String email = userDTO.getEmail();
        String name = userDTO.getName();
        try{
            UserDTO updatedUser = userSerivce.updateUserEmail(email, name);
            return ResponseEntity.ok("update successful");
        }catch (UserNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found with email: " + email);
        }

    }

}
