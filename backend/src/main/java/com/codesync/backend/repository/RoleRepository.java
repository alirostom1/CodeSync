package com.codesync.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codesync.backend.entity.ERole;
import com.codesync.backend.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
    Optional<Role> findByName(ERole name);
}
