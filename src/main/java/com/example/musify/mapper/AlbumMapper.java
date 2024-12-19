package com.example.musify.mapper;

import com.example.musify.dto.request.AlbumRequestDTO;
import com.example.musify.dto.response.AlbumResponseDTO;
import com.example.musify.entity.Album;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {SongMapper.class})
public interface AlbumMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "songs", ignore = true)
    Album toEntity(AlbumRequestDTO albumRequestDTO);

    AlbumResponseDTO toDto(Album album);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "songs", ignore = true)
    void updateEntityFromDto(AlbumRequestDTO albumRequestDTO, @MappingTarget Album album);
}