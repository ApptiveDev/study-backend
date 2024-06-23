package com.backend.apptive.repository;
import com.backend.apptive.domain.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);

//
//    @Query(
//            "select u from User u" +
//                    " join fetch u.posts" +
//                    " where u.id = :id"
//    )
//    Optional<User> findByAllRelation(@Param("id") Long userId);
}
