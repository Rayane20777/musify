package com.example.musify.dto.response;


import lombok.Data;

import java.util.List;

@Data
public class UserResponseDTO {
    private String id;
    private String login;
    private String email;
    private boolean active;
    private List<String> roles;
}

