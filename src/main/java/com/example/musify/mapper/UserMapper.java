package com.example.musify.mapper;

import com.example.musify.dto.request.UserRequestDTO;
import com.example.musify.dto.response.UserResponseDTO;
import com.example.musify.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "active", constant = "true")
    @Mapping(target = "roles", ignore = true)
    User toEntity(UserRequestDTO userRequestDTO);

    UserResponseDTO toDto(User user);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "active", ignore = true)
    @Mapping(target = "roles", ignore = true)
    void updateEntityFromDto(UserRequestDTO userRequestDTO, @MappingTarget User user);
}

