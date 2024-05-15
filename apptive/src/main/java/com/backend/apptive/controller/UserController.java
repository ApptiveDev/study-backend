package com.backend.apptive.controller;

import com.backend.apptive.DTO.UserDTO;
import com.backend.apptive.core.error.UserNotFoundException;
import com.backend.apptive.domain.User;
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

    // 계층적으로 설계
    // @GetMapping("/{email}")
    // @PathVariable String email
    //URL Restful 하게 설정하기
    @GetMapping("/user/")
    public ResponseEntity<?> getUserById(@RequestParam String email) {
        try {
            UserDTO userDTO = userSerivce.getUser(email);
            return ResponseEntity.ok(userDTO);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found with email: " + email);
        }
    }

    @PostMapping("/register")
    // flag 사용보다 예외 처리 사용하기 registerUser 함수 속 확인
    public ResponseEntity<String> registerUser(UserDTO userDTO){
        boolean flag = userSerivce.registerUser(userDTO);
        if (flag){
            return ResponseEntity.ok("successfully register");
        }else{
            return ResponseEntity.status(HttpStatus.CONFLICT)
                .body("Registration failed: The email is already in use.");
        }
    }

    //위와 같이 예처리 url 변경
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
        // 빌더 패턴 사용
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
