package com.codesync.backend.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor 
public class LoginRequestDto {
    @NotNull(message="Email is required!")
    private String email;
    
    @NotNull(message="Password is required!")
    private String password;
}
