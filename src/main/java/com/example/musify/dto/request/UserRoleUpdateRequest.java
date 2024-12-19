package com.example.musify.dto.request;

import com.example.musify.entity.enums.Role;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;

@Data
public class UserRoleUpdateRequest {
    @NotEmpty(message = "Roles list cannot be empty")
    private List<Role> roles;
}

