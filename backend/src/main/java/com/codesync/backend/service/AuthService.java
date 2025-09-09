package com.codesync.backend.service;


import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.codesync.backend.model.dto.LoginRequestDto;
import com.codesync.backend.model.dto.RegistrationRequestDto;
import com.codesync.backend.model.dto.UserResponseDto;
import com.codesync.backend.model.entity.User;
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
    
    @Autowired
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
