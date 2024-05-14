package com.backend.apptive.repository;
import com.backend.apptive.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
