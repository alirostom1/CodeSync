package com.codesync.backend.service;


import java.util.HashSet;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.codesync.backend.model.dto.LoginRequestDto;
import com.codesync.backend.model.dto.RegistrationRequestDto;
import com.codesync.backend.model.dto.UserResponseDto;
import com.codesync.backend.model.entity.Role;
import com.codesync.backend.model.entity.User;
import com.codesync.backend.model.enums.ERole;
import com.codesync.backend.model.mapper.UserMapper;
import com.codesync.backend.repository.RoleRepository;
import com.codesync.backend.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class AuthService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper usermapper;
    
    public AuthService(UserRepository userRepository,RoleRepository roleRepository,
                        PasswordEncoder passwordEncoder,
                        UserMapper userMapper){
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.usermapper = userMapper;
    }
    
    public UserResponseDto register(RegistrationRequestDto dto){
        if(userRepository.existsByEmail(dto.getEmail())){
            throw new RuntimeException("Email already registred!");
        }
        if(userRepository.existsByUsername(dto.getUsername())){
            throw new RuntimeException("Username already taken!");
        }
        User user = usermapper.toEntity(dto);
        user.setPassword(passwordEncoder.encode(dto.getPassword()));


        ERole erole = ERole.valueOf(dto.getRole());
        Role role = roleRepository.findByName(erole)
                    .orElseGet(() -> {
                        Role newRole = new Role();
                        newRole.setName(erole);
                        return roleRepository.save(newRole);
                    });
        if(user.getRoles() == null){
            user.setRoles(new HashSet<>());
        }
        user.getRoles().add(role);


        User savedUser = userRepository.save(user);
        return usermapper.toDto(savedUser);
    }
    public UserResponseDto login(LoginRequestDto dto){
        if(!userRepository.existsByEmail(dto.getEmail())){
            throw new RuntimeException("Wrong Credentials !");
        }
        User user = userRepository.findByEmail(dto.getEmail()).get();
        if(passwordEncoder.matches(dto.getPassword(),user.getPassword())){
            return usermapper.toDto(user);
        }else{
            throw new RuntimeException("Wrong Credentials");
        }
    }
}
