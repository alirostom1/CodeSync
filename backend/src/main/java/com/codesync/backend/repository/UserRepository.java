package com.codesync.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codesync.backend.model.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository <User, Long>{
    Optional<User> findByEmail(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}
