package com.codesync.backend.model.mapper;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.codesync.backend.model.dto.RegistrationRequestDto;
import com.codesync.backend.model.dto.UserResponseDto;
import com.codesync.backend.model.entity.User;

@Component
public class UserMapper {
    public User toEntity(RegistrationRequestDto dto){
        User user = new User();
        user.setEmail(dto.getEmail());
        user.setUsername(dto.getUsername());
        return user;
    }
    public UserResponseDto toDto(User user){
        UserResponseDto dto = new UserResponseDto();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setCreatedAt(user.getCreatedAt());
        dto.setUpdatedAt(user.getUpdatedAt());
        if(user.getRoles() != null){
            Set<String> roleNames = user.getRoles().stream()
                        .map(role -> role.getName().name())
                        .collect(Collectors.toSet());
            dto.setRoles(roleNames);
        } 
        return dto;
    }
}
