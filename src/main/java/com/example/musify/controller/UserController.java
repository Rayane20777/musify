package com.example.musify.controller;

import com.example.musify.dto.request.UserRoleUpdateRequest;
import com.example.musify.dto.response.UserResponseDTO;
import com.example.musify.service.impl.UserServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/users")
@PreAuthorize("hasRole('ADMIN')")
public class UserController {

    private final UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<Page<UserResponseDTO>> getAllUsers(Pageable pageable) {
        Page<UserResponseDTO> users = userService.getAllUsers(pageable);
        return ResponseEntity.ok(users);
    }

    @PutMapping("/{id}/roles")
    public ResponseEntity<UserResponseDTO> updateUserRoles(@PathVariable String id, @RequestBody UserRoleUpdateRequest request) {
        UserResponseDTO updatedUser = userService.updateUserRoles(id, request.getRoles());
        return ResponseEntity.ok(updatedUser);
    }
}

