package com.example.musify.dto.response;

import lombok.Data;

@Data
public class SongResponseDTO {
    private String id;
    private String title;
    private Integer duration;
    private Integer trackNumber;
    private String albumId;
}

