package com.backend.apptive.repository;

import com.backend.apptive.DTO.UserDTO;
import com.backend.apptive.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByPhoneNumber(String phoneNumber);
    void deleteByPhoneNumber(String phoneNumber);
    Optional<User> findByNameAndPhoneNumber(String name, String phoneNumber);
}
