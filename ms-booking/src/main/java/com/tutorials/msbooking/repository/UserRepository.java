package com.tutorials.msbooking.repository;

import com.tutorials.msbooking.entity.User;
import com.tutorials.msbooking.entity.UserStatus;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmailAndStatus(String email, UserStatus status);
}
