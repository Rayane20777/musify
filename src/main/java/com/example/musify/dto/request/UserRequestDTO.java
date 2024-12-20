package com.example.musify.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserRequestDTO {
    @NotBlank(message = "Login is required")
    private String login;

    @NotBlank(message = "Password is required")
    private String password;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;
}
