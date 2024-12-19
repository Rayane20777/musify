package com.example.musify.mapper;

import com.example.musify.dto.request.SongRequestDTO;
import com.example.musify.dto.response.SongResponseDTO;
import com.example.musify.entity.Song;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface SongMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "album", ignore = true)
    Song toEntity(SongRequestDTO songRequestDTO);

    @Mapping(source = "album.id", target = "albumId")
    SongResponseDTO toDto(Song song);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "album", ignore = true)
    void updateEntityFromDto(SongRequestDTO songRequestDTO, @MappingTarget Song song);
}
