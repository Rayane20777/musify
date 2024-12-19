package com.example.musify.service;

import com.example.musify.dto.request.SignupRequest;
import com.example.musify.dto.response.UserResponseDTO;
import com.example.musify.entity.enums.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    void registerUser(SignupRequest signUpRequest);
    UserResponseDTO getUserByUsername(String username);
    UserResponseDTO getUserById(String id);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    Page<UserResponseDTO> getAllUsers(Pageable pageable);
    UserResponseDTO updateUserRoles(String userId, List<Role> roles);
}
