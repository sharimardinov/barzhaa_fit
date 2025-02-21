package com.barzhaa.auth.repository;

import com.barzhaa.auth.model.User;
import com.barzhaa.auth.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByEmail(String email);
    Optional<User> findByPhone(String phone);
    List<User> findByStatus(Status status);
}
