package com.example.musify.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class AlbumResponseDTO {
    private String id;
    private String title;
    private String artist;
    private Integer year;
    private List<SongResponseDTO> songs;
}
