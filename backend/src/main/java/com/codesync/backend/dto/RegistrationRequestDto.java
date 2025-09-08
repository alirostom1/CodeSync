package com.codesync.backend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor 
public class RegistrationRequestDto {
    
    @NotNull(message = "Username required!")
    private String username;

    @NotNull(message = "Email is required!")
    @Email(message = "Email should be valid!")
    private String email;

    @NotNull(message = "Password is required!")
    @Size(min = 8, message = "Password must be greater than 8 characters!")
    private String password;

}
