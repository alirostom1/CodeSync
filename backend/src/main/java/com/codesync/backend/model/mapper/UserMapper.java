package com.codesync.backend.model.mapper;

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
        return dto;
    }
}
